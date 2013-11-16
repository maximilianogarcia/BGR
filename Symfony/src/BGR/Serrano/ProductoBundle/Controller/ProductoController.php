<?php

namespace BGR\Serrano\ProductoBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;
use JMS\Serializer\SerializerBuilder;
use BGR\Serrano\ProductoBundle\Entity\Producto as Producto;
use Symfony\Component\HttpFoundation\Response as Response;
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
     * @Route("/getById")
     */
    public function getByIdAction()
    {
    }
}
