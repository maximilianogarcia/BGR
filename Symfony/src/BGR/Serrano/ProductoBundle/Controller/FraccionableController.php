<?php

namespace BGR\Serrano\ProductoBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;

class FraccionableController extends Controller
{
    /**
     * @Route("/getFraccionableById")
     */
    public function getFraccionableByIdAction()
    {
    	$presentacionId = $this->get('request')->request->get('data');
    	$serializer =  SerializerBuilder::create()->build();
    	$em = $this->getDoctrine()->getManager();
    	$presentacion = $em->getRepository('BGRSerranoProductoBundle:Presentacion')->find($presentacionId);
    	
    	
    }

}
