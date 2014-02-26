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

class MixController extends Controller
{
	
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
        
        
        //crea el producto mix. con nombre $mixImpresentation->getNombreMix();     
        $newProducto = new Producto();
        $categoria= $em->getRepository('BGRSerranoProductoBundle:Categoria')->find(1);
        $newProducto->setCategoria($categoria);
		$newProducto->setName($mixImpresentation->getNombreMix());
		
		$proveedor= $em->getRepository('BGRSerranoProductoBundle:Proveedor')->find(1);
		

		

        
   /*     foreach( $mixData as $mixImpresentation->getMixDataList()){        
        	//marco como mix	
        	$em->getRepository('BGRSerranoProductoBundle:Paquete')->markMixed($mixData->getPaqueteId()); 
			//calculo el remanente
        	$cant_ =  $mixData->getCantidadOriginal() % $mixData->getCantidadNueva();
        	
        	//get producto
        	$producto = $em->getRepository('BGRSerranoProductoBundle:Producto')->find($mixData->getProductoId());
  
        	$remanente = $em->getRepository('BGRSerranoProductoBundle:Remanente')
        	->findOneBy(array('producto'=>$producto));
        	
        	if($remanente == null){
        		$remanente = new Remanente();
        		$remanente->setProducto($object->getProducto());
        		$remanente->setCantidad(0);
        	}
        	
        	$remanente->setUnidadDeMedida($object->getUnidad_de_medida());
        	$remanente->setCantidad($remanente->getCantidad()+$cant_);
        	$em->getRepository('BGRSerranoProductoBundle:Remanente')->save($remanente);
        	
        }
        
        //crearPresentacion con nombre 
        $mixImpresentation->getNombrePresentacion();
        
        //creo los paquetes nuevos.
        $cantidad_de_paquetes = $mixImpresentation->getCantidadDePaquetes();*/
        
        
        $response = new Response($serializer->serialize($newProducto,'json'));
        return $response;
	}
	
	
}
