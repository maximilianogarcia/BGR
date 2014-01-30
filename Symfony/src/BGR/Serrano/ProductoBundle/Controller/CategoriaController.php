<?php

namespace BGR\Serrano\ProductoBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;

use JMS\Serializer\SerializerBuilder;
use BGR\Serrano\ProductoBundle\Entity\Producto as Producto;
use Symfony\Component\HttpFoundation\Response as Response;
use Doctrine\Common\Collections\ArrayCollection;
use BGR\Serrano\ProductoBundle\Entity\Categoria as Categoria;



class CategoriaController extends Controller
{
    /**
     * @Route("/categoria/getAll")
     * @Template()
     */
    public function getAllAction()
    {
        $em = $this->getDoctrine()->getManager();
        $data = $em->getRepository('BGRSerranoProductoBundle:Categoria')->findAll();

        $serializer =  SerializerBuilder::create()->build();
        $jsonContent = $serializer->serialize($data, 'json');
        $response = new Response($jsonContent);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

    /**
     * @Route("/categoria/save")
     * @Template()
     */
    public function saveAction()
    {

        $jsonData = $this->get('request')->request->get('data');

        $serializer =  SerializerBuilder::create()->build();

        $object = $serializer->deserialize($jsonData, 'BGR\Serrano\ProductoBundle\Entity\Categoria', 'json');

        $em = $this->getDoctrine()->getManager();
        $em->getRepository('BGRSerranoProductoBundle:Categoria')->save($object);

        $response = new Response($serializer->serialize($object,'json'));
        
        $response->headers->set('Content-Type', 'application/json');

        return $response;
    }


    /**
     * @Route("/categoria/update")
     * @Template()
     */
    public function updateAction()
    {

        $jsonData = $this->get('request')->request->get('data');

        $serializer =  SerializerBuilder::create()->build();

        $object = $serializer->deserialize($jsonData, 'BGR\Serrano\ProductoBundle\Entity\Categoria', 'json');

        $em = $this->getDoctrine()->getManager();
        
        $em->getRepository('BGRSerranoProductoBundle:Categoria')->update($object);

        $response = new Response($serializer->serialize($object,'json'));
        
        $response->headers->set('Content-Type', 'application/json');

        return $response;
    }

    /**
     * @Route("/categoria/delete")
     * @Template()
     */
    public function deleteAction()
    {

        $jsonData = $this->get('request')->request->get('data');
        
        $serializer =  SerializerBuilder::create()->build();

        $object = $serializer->deserialize($jsonData, 'BGR\Serrano\ProductoBundle\Entity\Categoria', 'json');

        $em = $this->getDoctrine()->getManager();
		  try{	
	        $em->getRepository('BGRSerranoProductoBundle:Categoria')->delete($object);
        }catch(\Doctrine\DBAL\DBALException $e){
     			$response = new Response();
  				$response->setContent('No se puede eliminar una categoria ya relacionada con un producto');
  				$response->setStatusCode(500);
  				$response->headers->set('Content-Type', 'text/html');	
			  	return $response;		
        }


        return new Response($jsonData);
    }






}
