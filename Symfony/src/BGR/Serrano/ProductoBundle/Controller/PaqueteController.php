<?php

namespace BGR\Serrano\ProductoBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;
use Symfony\Component\HttpFoundation\Response;
use JMS\Serializer\SerializerBuilder;
use Doctrine\Common\Collections\ArrayCollection;

class PaqueteController extends Controller
{
    /**
     * @Route("/paquete/getCantidadDisponibleByPresentacionId")
     * @Template()
     */
    public function getCantidadDisponibleByPresentacionIdAction()
    {
    	$presentacionId = $this->get('request')->request->get('data');
    	$em = $this->getDoctrine()->getManager();
    	$presentacion = $em->getRepository('BGRSerranoProductoBundle:Presentacion')->find($presentacionId);
    	 
    	$cantidad = $em->getRepository('BGRSerranoProductoBundle:Paquete')->findDisponiblesBy($presentacion);
    	
    	return new Response($cantidad);
    }

    
    /**
     * @Route("/paquete/getPaquetesDisponiblesPorProducto")
     * @Template()
     */
    public function getPaquetesDisponiblesPorProductoAction()
    {
    	$productoId = $this->get('request')->query->get('productoId');
    	
    	$em = $this->getDoctrine()->getManager();
    	$data = $em->getRepository('BGRSerranoProductoBundle:Paquete')->findPaquetesByProducto($productoId, "DISPONIBLE");

    	$serializer =  SerializerBuilder::create()->build();

    	$responseData = $serializer->serialize(Array(  'producto_id' => $productoId,
    					'data' => $data), 'json'); 
     	     	
     	$response = new Response($responseData);
     	
    	$response->headers->set('Content-Type', 'application/json');
    	return $response;
    }

    
    
}
