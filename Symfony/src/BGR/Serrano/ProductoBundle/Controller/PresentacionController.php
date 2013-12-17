<?php

namespace BGR\Serrano\ProductoBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;
use JMS\Serializer\SerializerBuilder;

use Symfony\Component\HttpFoundation\Response as Response;
use Doctrine\Common\Collections\ArrayCollection;
use BGR\Serrano\ProductoBundle\Entity\Presentacion as Presentacion;

class PresentacionController extends Controller
{
    /**
     * @Route("/presentacion/getAll")
     * @Template()
     */
    public function getAllAction()
    {

        
        $em = $this->getDoctrine()->getManager();
        $data = $em->getRepository('BGRSerranoProductoBundle:Presentacion')->findAll();
       
      
        $serializer =  SerializerBuilder::create()->build();
        $jsonContent = $serializer->serialize($data, 'json');
        $response = new Response($jsonContent);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

    /**
     * @Route("/presentacion/save")
     * @Template()
     */
    public function saveAction()
    {
    }

    /**
     * @Route("/presentacion/update")
     * @Template()
     */
    public function updateAction()
    {
    }

    /**
     * @Route("/presentacion/delete")
     * @Template()
     */
    public function deleteAction()
    {
    }

}
