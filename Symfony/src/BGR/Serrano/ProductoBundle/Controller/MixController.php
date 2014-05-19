<?php

namespace BGR\Serrano\ProductoBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;
use BGR\Serrano\ProductoBundle\Model\MixData;
use BGR\Serrano\ProductoBundle\Entity\Remanente as Remanente;
use BGR\Serrano\ProductoBundle\Model\MixImpresentation;
use Symfony\Component\HttpFoundation\Response;
use JMS\Serializer\SerializerBuilder;
use BGR\Serrano\ProductoBundle\Entity\Producto;
use BGR\Serrano\ProductoBundle\Entity\Mix;
use BGR\Serrano\ProductoBundle\Entity\Categoria;
use BGR\Serrano\ProductoBundle\Entity\UnidadDeMedida;
use Doctrine\Common\Collections\ArrayCollection;
use Symfony\Component\Form\Exception\InvalidArgumentException;
use BGR\Serrano\ProductoBundle\Entity\Paquete;

class MixController extends Controller
{
	/**
	 * @Route("/mix/getAll")
	 */
	public function getAllAction()
	{
		$em = $this->getDoctrine()->getManager();
		$data = $em->getRepository('BGRSerranoProductoBundle:Mix')->findAll();
	
		$serializer =  SerializerBuilder::create()->build();
		$jsonContent = $serializer->serialize($data, 'json');
	
		$response = new Response($jsonContent);
		$response->headers->set('Content-Type', 'application/json');
		return $response;
	}
	
	
	/**
	 * @Route("/mix/test")
	 */
	public function testAction()
	{
 			$a  = new MixImpresentation();
 			$a->setCantidadDePaquetes(1);
 			$a->setNombreMix("Hola Mundo");
 			$a->setNombrePresentacion("sarasa sa sasa");
 			$serializer =  SerializerBuilder::create()->build();
 			
 			$response = new Response($serializer->serialize($a,'json'));
 			return $response;
	}
	
	
	/**
	 * @Route("/mix/saveMix")
	 */
	public function saveMixAction()
	{
		$em = $this->getDoctrine()->getManager();
		
		$serializer =  SerializerBuilder::create()->build();
		
        $jsonData = $this->get('request')->request->get('data');
        
        $mixImpresentation = $serializer->deserialize($jsonData, 'BGR\Serrano\ProductoBundle\Model\MixImpresentation', 'json');

        $newMix = new Mix();
        $categoria = $em->getRepository('BGRSerranoProductoBundle:Categoria')->find($mixImpresentation->getCategoria());
        $unidad_de_medida = $em->getRepository('BGRSerranoProductoBundle:UnidadDeMedida')->find($mixImpresentation->getUnidadDeMedida());

        $newMix->setCategoria($categoria);
        $newMix->setUnidadDeMedida($unidad_de_medida);
		$newMix->setNombre($mixImpresentation->getNombre());
		$newMix->setVencimiento($mixImpresentation->getVencimiento());

		$remanentes = [];

		$ok = true;
		$index = 0;
				
		$paqueteMixInicial = $mixImpresentation->getPaquetesMix()[$index];
	
	
		
		$cantidad_original = floor( $paqueteMixInicial->getCantidad() / $paqueteMixInicial->getCantElegida() );
		$index++;
		while ( $ok && sizeof($mixImpresentation->getPaquetesMix()) > $index){

			$paqueteMix = $mixImpresentation->getPaquetesMix()[$index];
			$cantidad = floor( $paqueteMix->getCantidad() / $paqueteMix->getCantElegida() );
			$ok = $cantidad_original == $cantidad;
			$index++;

		}
        
        if(!$ok){
       
        	$response_error = new Response();
        	$response_error->setContent("Las cantidades elegidas son incompatibles");
        	$response_error->setStatusCode(408);
        	$response_error->headers->set('Content-Type', 'text/html');
        	return $response_error;
      	
        }
        
        foreach( $mixImpresentation->getPaquetesMix() as  $paqueteMix){  
        	//calculo el remanente
        	$cant_ =  $paqueteMix->getCantidad() % $paqueteMix->getCantElegida();

        	//get producto
        	$producto = $em->getRepository('BGRSerranoProductoBundle:Producto')->find($paqueteMix->getId());

        	$remanente = $em->getRepository('BGRSerranoProductoBundle:Remanente')
        	->findOneBy(array('producto'=>$producto));

        	if($remanente == null){
        		$remanente = new Remanente();
        		$remanente->setProducto($producto);
        		$remanente->setCantidad(0);
        	}

        	$remanente->setUnidadDeMedida($newMix->getUnidadDeMedida());
        	$remanente->setCantidad($remanente->getCantidad()+$cant_);

        	foreach($paqueteMix->getPaquetes() as $paquete ){
				$em->getRepository('BGRSerranoProductoBundle:Paquete')->markMixed($paquete->getId());
			}

        }

        
        /*
         * 
         * GUARDAR LOS PAQUETES NUEVOS
         * 
         */

        //primero salvar el mix
        $newMix = $em->getRepository('BGRSerranoProductoBundle:Mix')->save($newMix);
        
        //creo los paquetes nuevos.
         for ($i = 1; $i <= $cantidad_original; $i++) {
         	$paq = new Paquete();
         	$paq->setEstado("DISPONIBLE");
         	$paq->setMix($newMix);
         	$paq->setCodigo("MIXED");
         	$em->getRepository('BGRSerranoProductoBundle:Paquete')->save($paq);
         }

        $response = new Response($serializer->serialize($newMix,'json'));
        return $response;
	}
	
	

	
}
