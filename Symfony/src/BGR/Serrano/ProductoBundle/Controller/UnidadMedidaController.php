<?php

namespace BGR\Serrano\ProductoBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;

use JMS\Serializer\SerializerBuilder;
use Symfony\Component\HttpFoundation\Response as Response;

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

}
