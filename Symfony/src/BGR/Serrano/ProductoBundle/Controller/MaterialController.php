<?php

namespace BGR\Serrano\ProductoBundle\Controller;

use JMS\Serializer\SerializerBuilder;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;
use Symfony\Component\HttpFoundation\Response as Response;
use Doctrine\Common\Collections\ArrayCollection;
use BGR\Serrano\ProductoBundle\Entity\Material as Material;

class MaterialController extends Controller
{
    /**
     * @Route("/material/getAll")
     */
    public function getAllAction()
    {
        $em = $this->getDoctrine()->getManager();
        $data = $em->getRepository('BGRSerranoProductoBundle:Material')->findAll();

        $serializer =  SerializerBuilder::create()->build();
        $jsonContent = $serializer->serialize($data, 'json');
        $response = new Response($jsonContent);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

    /**
     * @Route("/material/delete")
     */
    public function deleteAction()
    {
        $jsonData = $this->get('request')->request->get('data');
        
        $serializer =  SerializerBuilder::create()->build();

        $object = $serializer->deserialize($jsonData, 'BGR\Serrano\ProductoBundle\Entity\Material', 'json');

        $em = $this->getDoctrine()->getManager();
        
		  try{
		  	  $em->getRepository('BGRSerranoProductoBundle:Material')->delete($object);
			} catch (\Doctrine\DBAL\DBALException $e) {
  			  $response = new Response();
			  $response->setContent('No se puede eliminar un material con productos relacionados');
			  $response->setStatusCode(500);
			  $response->headers->set('Content-Type', 'text/html');	
			  return $response;	
			}

        return new Response($jsonData);
    }

    /**
     * @Route("/material/update")
     */
    public function updateAction()
    {
       $jsonData = $this->get('request')->request->get('data');

        $serializer =  SerializerBuilder::create()->build();

        $object = $serializer->deserialize($jsonData, 'BGR\Serrano\ProductoBundle\Entity\Material', 'json');

        $em = $this->getDoctrine()->getManager();
        
        $em->getRepository('BGRSerranoProductoBundle:Material')->update($object);

        $response = new Response($serializer->serialize($object,'json'));
        
        $response->headers->set('Content-Type', 'application/json');

        return $response;
    }

    /**
     * @Route("/material/save")
     */
    public function saveAction()
    {

        $jsonData = $this->get('request')->request->get('data');

        $serializer =  SerializerBuilder::create()->build();

        $object = $serializer->deserialize($jsonData, 'BGR\Serrano\ProductoBundle\Entity\Material', 'json');

        $em = $this->getDoctrine()->getManager();
        $em->getRepository('BGRSerranoProductoBundle:Material')->save($object);

        $response = new Response($serializer->serialize($object,'json'));
        
        $response->headers->set('Content-Type', 'application/json');

        return $response;

    }
    /**
     * @Route("/material/getMaterialesByProducto")
     */
    public function getMaterialesByProducto()
    {
        $em = $this->getDoctrine()->getManager();
        $logger = $this->get('logger');

        $jsonData = $this->get('request')->request->get('data');

        $serializer =  SerializerBuilder::create()->build();
       
        /*        
        $object = $serializer->deserialize($jsonData, 'BGR\Serrano\ProductoBundle\Entity\Producto', 'json');

        $data = $em->getRepository('BGRSerranoProductoBundle:Material')->findByProducto($object);
         */
        $data = $em->getRepository('BGRSerranoProductoBundle:Material')->findAll();
        
        $response = new Response($serializer->serialize($data,'json'));
       
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

}
