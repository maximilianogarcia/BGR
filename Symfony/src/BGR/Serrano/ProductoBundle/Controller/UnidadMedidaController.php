<?php

namespace BGR\Serrano\ProductoBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;

use JMS\Serializer\SerializerBuilder;
use Symfony\Component\HttpFoundation\Response as Response;
use Doctrine\Common\Collections\ArrayCollection;
use BGR\Serrano\ProductoBundle\Model\ProductoPaqueteQuery;
use BGR\Serrano\ProductoBundle\Model\PaqueteQuery;

class UnidadMedidaController extends Controller
{
    /**
     * @Route("/unidadMedida/getAll")
     * @Template()
     */
    public function getAllAction()
    {
        $em = $this->getDoctrine()->getManager();
        $data = $em->getRepository('BGRSerranoProductoBundle:UnidadDeMedida')->findAll();

        $serializer =  SerializerBuilder::create()->build();
        $jsonContent = $serializer->serialize($data, 'json');
        $response = new Response($jsonContent);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }
    /**
     * @Route("/unidadMedida/getNoDivisibles")
     * @Template()
     */
    public function getNoDivisiblesAction()
    {
        $em = $this->getDoctrine()->getManager();
        $data = $em->getRepository('BGRSerranoProductoBundle:UnidadDeMedida')->findNotDivisibles();

        $serializer =  SerializerBuilder::create()->build();
        $jsonContent = $serializer->serialize($data, 'json');
        $response = new Response($jsonContent);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }


    /**
     * @Route("/unidadMedida/save")
     * @Template()
     */
    public function saveAction()
    {
        $jsonData = $this->get('request')->request->get('data');

        $serializer =  SerializerBuilder::create()->build();

        $object = $serializer->deserialize($jsonData, 'BGR\Serrano\ProductoBundle\Entity\UnidadDeMedida', 'json');

        $em = $this->getDoctrine()->getManager();
        $em->getRepository('BGRSerranoProductoBundle:UnidadDeMedida')->save($object);

        $response = new Response($serializer->serialize($object,'json'));
        
        $response->headers->set('Content-Type', 'application/json');

        return $response;
    }

    /**
     * @Route("/unidadMedida/delete")
     * @Template()
     */
    public function deleteAction()
    {
        $jsonData = $this->get('request')->request->get('data');
        
        $serializer =  SerializerBuilder::create()->build();

        $object = $serializer->deserialize($jsonData, 'BGR\Serrano\ProductoBundle\Entity\UnidadDeMedida', 'json');

        $em = $this->getDoctrine()->getManager();
        $em->getRepository('BGRSerranoProductoBundle:UnidadDeMedida')->delete($object);

        return new Response($jsonData);
    }

    /**
     * @Route("/unidadMedida/update")
     * @Template()
     */
    public function updateAction()
    {

        $jsonData = $this->get('request')->request->get('data');

        $serializer =  SerializerBuilder::create()->build();

        $object = $serializer->deserialize($jsonData, 'BGR\Serrano\ProductoBundle\Entity\UnidadDeMedida', 'json');

        $em = $this->getDoctrine()->getManager();
        
        $em->getRepository('BGRSerranoProductoBundle:UnidadDeMedida')->update($object);

        $response = new Response($serializer->serialize($object,'json'));
        
        $response->headers->set('Content-Type', 'application/json');

        return $response;
    }
    
//     /**
//      * @Route("/unidadMedida/getTotalByPaquetes")
//      * @Template()
//      */
//     public function getTotalByPaquetesAction()
//     {
//     	$em = $this->getDoctrine()->getManager();
    	
//     	$unidadId = $this->get('request')->query->get('unidad_id');
//     	$cantidad = $this->get('request')->query->get('cantidad');
    	
    	
//     	$unidad = $em->getRepository('BGRSerranoProductoBundle:UnidadDeMedida')->find($unidadId);
//     	$valor =(float) $cantidad;
//     	$unidadReal = (int) $unidadId;
//     	$unidad_nombre = $unidad->getName();
//     	if ($unidad->getDivisible()) {
//     		$valor *=  $unidad->getEquivalencia();
//     		$unidadReal = $unidad->getDerivaDe()->getId();
//     		$unidad_nombre = $unidad->getDerivaDe()->getName();
//     	}
    	
//     	$respuesta = Array(
//     		"cantidad" => $valor,
//     		"unidad_id" =>	$unidadReal,
//     		"nombre" => $unidad_nombre
//     	);
    	
//     	$serializer =  SerializerBuilder::create()->build();
//     	$jsonContent = $serializer->serialize($respuesta, 'json');
//     	$response = new Response($jsonContent);
//     	$response->headers->set('Content-Type', 'application/json');
//     	return $response;
//     }
    
    
    /**
     * @Route("/unidadMedida/getTotalByPaquetes")
     * @Template()
     */
    public function getTotalByPaquetesAction()
    {
    	$em = $this->getDoctrine()->getManager();    	
    	$jsonData = $this->get('request')->request->get('data');
    	
    	$serializer =  SerializerBuilder::create()->build();
        $object = $serializer->deserialize($jsonData, 'BGR\Serrano\ProductoBundle\Model\ProductoPaqueteQuery', 'json');

        $totales = new ArrayCollection();
		$paquetes = $object->getPaqueteQuery();
		$respuesta = Array("producto_id"=>$object->getProductoId());
		
        foreach ( $paquetes as $query){

	     	$unidad = $em->getRepository('BGRSerranoProductoBundle:UnidadDeMedida')->find($query->getUnidad());
	     	
	     	$valor =(float) $query->getCantidad();
	     	
	     	$unidadReal = (int) $query->getUnidad();
	    	$unidad_nombre = $unidad->getName();
	    	
	    	if ($unidad->getDivisible()) {
	    		$valor *=  $unidad->getEquivalencia();
	    		$unidadReal = $unidad->getDerivaDe()->getId();
	    		$unidad_nombre = $unidad->getDerivaDe()->getName();
	    	}
 
	    	$totales->add(Array(
		    			"cantidad" => $valor,
		    			"unidad_id" =>	$unidadReal,
		    			"nombre" => $unidad_nombre,
		    			"paquete_id" => $query->getPaqueteId()
	    	));
        }
        $respuesta["totales"]=$totales;
    	$jsonContent = $serializer->serialize($respuesta, 'json');
    	
    	$response = new Response($jsonContent );
    	   
//    	$response = new Response($jsonContent = $serializer->serialize($object, 'json'));
    	$response->headers->set('Content-Type', 'application/json');
    	return $response;
    }

}
