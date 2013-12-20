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
        $jsonData = $this->get('request')->request->get('data');

        $serializer =  SerializerBuilder::create()->build();

        $object = $serializer->deserialize($jsonData, 'BGR\Serrano\ProductoBundle\Entity\Presentacion', 'json');

        $em = $this->getDoctrine()->getManager();
        $object->getLote()->setProducto($object->getProducto());
        $em->getRepository('BGRSerranoProductoBundle:Presentacion')->save($object);

        $response = new Response($serializer->serialize($object,'json'));
        
        $response->headers->set('Content-Type', 'application/json');

        return $response;
    }

    /**
     * @Route("/presentacion/update")
     * @Template()
     */
    public function updateAction()
    {

        $jsonData = $this->get('request')->request->get('data');
        $serializer =  SerializerBuilder::create()->build();
        $object = $serializer->deserialize($jsonData, 'BGR\Serrano\ProductoBundle\Entity\Presentacion', 'json');
        $em = $this->getDoctrine()->getManager();
        $object->getLote()->setProducto($object->getProducto());
        $em->getRepository('BGRSerranoProductoBundle:Presentacion')->update($object);
        $response = new Response($serializer->serialize($object,'json'));
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

    /**
     * @Route("/presentacion/delete")
     * @Template()
     */
    public function deleteAction()
    {
    }

}