<?php

namespace BGR\Serrano\ProductoBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;
use JMS\Serializer\SerializerBuilder;
use BGR\Serrano\ProductoBundle\Entity\Producto as Producto;
use Symfony\Component\HttpFoundation\Response as Response;
use Doctrine\Common\Collections\ArrayCollection;

class ProductoController extends Controller
{
    /**
     * @Route("/getAll")
     */
    public function getAllAction()
    {
        $em = $this->getDoctrine()->getManager();
        $data = $em->getRepository('BGRSerranoProductoBundle:Producto')->findAll();

        $serializer =  SerializerBuilder::create()->build();
        $jsonContent = $serializer->serialize($data, 'json');

        $response = new Response($jsonContent);
      //  $response->headers->set('Content-Type', 'application/json');
        return $response;
    }


    /**
     * @Route("/save")
     */
    public function saveAction()
    {
        $jsonData = $this->get('request')->request->get('data');
        $serializer =  SerializerBuilder::create()->build();
        $object = $serializer->deserialize($jsonData, 'BGR\Serrano\ProductoBundle\Entity\Producto', 'json');

        $em = $this->getDoctrine()->getManager();
        $em->getRepository('BGRSerranoProductoBundle:Producto')->save($object);
 
        return new Response($jsonData);
    }

    /**
     * @Route("/getById")
     */
    public function getByIdAction()
    {
    }
}
