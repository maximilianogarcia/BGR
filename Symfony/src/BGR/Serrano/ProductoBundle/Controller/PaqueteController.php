<?php

namespace BGR\Serrano\ProductoBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;
use Symfony\Component\HttpFoundation\Response;

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

}
