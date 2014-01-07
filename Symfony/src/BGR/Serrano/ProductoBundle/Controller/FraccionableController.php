<?php

namespace BGR\Serrano\ProductoBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;
use BGR\Serrano\ProductoBundle\Model\Fraccionable;
use Symfony\Component\HttpFoundation\Response;
use JMS\Serializer\SerializerBuilder;

class FraccionableController extends Controller
{
    /**
     * @Route("/fraccion/getFraccionableByPresentacionId")
     */
    public function getFraccionableByPresentacionIdAction()
    {
    	$presentacionId = $this->get('request')->request->get('data');
    	$serializer =  SerializerBuilder::create()->build();
    	$em = $this->getDoctrine()->getManager();
    	$presentacion = $em->getRepository('BGRSerranoProductoBundle:Presentacion')->find($presentacionId);
    	$cantidad = $em->getRepository('BGRSerranoProductoBundle:Paquete')->findDisponiblesBy($presentacion);
    	$fraccionable = new Fraccionable();
        $fraccionable->setStock($cantidad);
        $fraccionable->setPresentacion($presentacion);
        
        $jsonContent = $serializer->serialize($fraccionable, 'json');
        
        $response = new Response($jsonContent);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
        
    	
    }

}
