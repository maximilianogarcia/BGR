<?php

namespace BGR\Serrano\ProductoBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;
use Symfony\Component\HttpFoundation\Response;
use JMS\Serializer\SerializerBuilder;
use Doctrine\ORM\Query\ResultSetMapping;

class ProveedorController extends Controller
{
    /**
     * @Route("/proveedor/getAll")
     * @Template()
     */
    public function getAllAction()
    {
    	$em = $this->getDoctrine()->getManager();
    	$data = $em->getRepository('BGRSerranoProductoBundle:Proveedor')->findAll();
    	
    	
    
    	
    	$serializer =  SerializerBuilder::create()->build();
    	$jsonContent = $serializer->serialize($data, 'json');
    	$response = new Response($jsonContent);
    	$response->headers->set('Content-Type', 'application/json');
    	return $response;
    }

    /**
     * @Route("/proveedor/saveRelation")
     * @Template()
     */
    public function saveRelationAction()
    {
        $jsonData = $this->get('request')->request->get('data');

        $producto_id = $this->get('request')->request->get('data');
        
        $logger = $this->get('logger');
        
        $logger->info("aspodpoaisdipojdas ".$jsonData);
        
        
        $serializer =  SerializerBuilder::create()->build();

        $object = $serializer->deserialize($jsonData, 'BGR\Serrano\ProductoBundle\Entity\ProductoProveedor', 'json');

        $em = $this->getDoctrine()->getManager();
        $em->getRepository('BGRSerranoProductoBundle:ProductoProveedor')->update($object);


        $response = new Response($serializer->serialize($object,'json'));
        
        $response->headers->set('Content-Type', 'application/json');

        return $response;
    }    
    
    /**
     * @Route("/proveedor/getAllProductoRelations")
     * @Template()
     */
    public function getAllProductoRelationsAction()
    {
    	
    	$producto_id = $this->get('request')->request->get('data');
    	 
    	$logger = $this->get('logger');

    	$logger->info("producto id: ");
    	 
    	$logger->info($producto_id);
    	
    	$em = $this->getDoctrine()->getManager();
    	$data = $em->getRepository('BGRSerranoProductoBundle:ProductoProveedor')->findByProductoId($producto_id);
       	
    	$serializer =  SerializerBuilder::create()->build();
    	$jsonContent = $serializer->serialize($data, 'json');
    	$response = new Response($jsonContent);
    	$response->headers->set('Content-Type', 'application/json');
    	return $response;
    }
    /**
     * @Route("/proveedor/getProveedoresByProductoId")
     * @Template()
     */
    public function getProveedoresByProductoIdAction()
    {
    	$producto_id = $this->get('request')->request->get('data');
    	$em = $this->getDoctrine()->getManager();
    	$data = $em->getRepository('BGRSerranoProductoBundle:Proveedor')->findByProductoId($producto_id);
    	
    	$serializer =  SerializerBuilder::create()->build();
    	$jsonContent = $serializer->serialize($data, 'json');
    	
    	$response = new Response($jsonContent);
    	$response->headers->set('Content-Type', 'application/json');
    	return $response;

    }   
}
