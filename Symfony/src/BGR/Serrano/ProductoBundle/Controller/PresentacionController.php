<?php

namespace BGR\Serrano\ProductoBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;
use JMS\Serializer\SerializerBuilder;

use Symfony\Component\HttpFoundation\Response as Response;
use Doctrine\Common\Collections\ArrayCollection;
use BGR\Serrano\ProductoBundle\Entity\Presentacion as Presentacion;
use BGR\Serrano\ProductoBundle\Entity\Remanente as Remanente;
use BGR\Serrano\ProductoBundle\Service\PresentacionService;



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
        if($object->getLote() != null){
        	$object->getLote()->setProducto($object->getProducto());
        }
        	
        $em->getRepository('BGRSerranoProductoBundle:Presentacion')->save($object);

        $servicio = $this->get('presentacion_service');
        $servicio->crear_paquetes($em,$object);

        $response = new Response($serializer->serialize($object,'json'));
        
        $response->headers->set('Content-Type', 'application/json');

        return $response;
    }

   /**
    * @Route("/presentacion/saveFraccion")
    * @Template()
    */
    public function saveFraccionAction()
    {
        $jsonData = $this->get('request')->request->get('data');
        $cantidad_original = $this->get('request')->request->get('cantidad_original');
        $paquete_id = $this->get('request')->request->get('paquete');

        $serializer =  SerializerBuilder::create()->build();

        $object = $serializer->deserialize($jsonData, 'BGR\Serrano\ProductoBundle\Entity\Presentacion', 'json');

		  //calculo la cantidad de paquetes a crear
		  $cant_ =  floor($cantidad_original / $object->getCantidad());
		  $object->setCantidadPaquetes($cant_);

        $em = $this->getDoctrine()->getManager();
        $object->getLote()->setProducto($object->getProducto());
        $em->getRepository('BGRSerranoProductoBundle:Presentacion')->save($object);

        $servicio = $this->get('presentacion_service');
        $servicio->crear_paquetes($em,$object);

        //cambiar estado del paquete
        $em->getRepository('BGRSerranoProductoBundle:Paquete')->desactivar($paquete_id);        
         
        //guardar remanente
		  $cant_ =  $cantidad_original % $object->getCantidad();
		  
		  $remanente = $em->getRepository('BGRSerranoProductoBundle:Remanente')
		  ->findOneBy(array('producto'=>$object->getProducto()));
		  
		  if($remanente == null){
		    $remanente = new Remanente();
		    $remanente->setProducto($object->getProducto());
		    $remanente->setCantidad(0);
		  }
		  
		  $remanente->setUnidadDeMedida($object->getUnidad_de_medida());
		  $remanente->setCantidad($remanente->getCantidad()+$cant_);		  
		  		  
		  $em->getRepository('BGRSerranoProductoBundle:Remanente')->save($remanente);

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
    /**
     * @Route("/presentacion/getDisponibleStock")
     * @Template()
     */
    public function getDisponibleStockAction()
    {
    	
    	$jsonData = $this->get('request')->request->get('data');
    	$serializer =  SerializerBuilder::create()->build();
    	$object = $serializer->deserialize($jsonData, 'BGR\Serrano\ProductoBundle\Entity\Presentacion', 'json');
    	$em = $this->getDoctrine()->getManager();
    	$result = $em->getRepository('BGRSerranoProductoBundle:Paquete')->findDisponiblesBy($object);
    	
    	$response = new Response($result);

    	$response->headers->set('Content-Type', 'application/json');
    	return $response;
    }
    /**
     * @Route("/presentacion/getStocksByProducto")
     * @Template()
     */
    public function getStocksByProductoAction()
    {
    	 
    	$productoId = $this->get('request')->request->get('data');
    	$serializer =  SerializerBuilder::create()->build();
    	$em = $this->getDoctrine()->getManager();
    	$result = $em->getRepository('BGRSerranoProductoBundle:Presentacion')->findStocksByProducto($productoId);
    	 
    	
    	$response = new Response($serializer->serialize($result,'json'));
    
    	$response->headers->set('Content-Type', 'application/json');
    	return $response;
    }
    
    /**
     * @Route("/presentacion/desactivar")
     * @Template()
     */
    public function desactivarAction()
    {
    
    	$presentacionId = $this->get('request')->request->get('data');
    	$serializer =  SerializerBuilder::create()->build();
    	$em = $this->getDoctrine()->getManager();
    	$result =$em->getRepository('BGRSerranoProductoBundle:Presentacion')->desactivar($presentacionId);
    	$response = new Response($serializer->serialize($result,'json'));
    	$response->headers->set('Content-Type', 'application/json');
    	return $response;
    }   
    /**
     * @Route("/presentacion/activar")
     * @Template()
     */
    public function activarAction()
    {
    
    	$presentacionId = $this->get('request')->request->get('data');
    	$serializer =  SerializerBuilder::create()->build();
    	$em = $this->getDoctrine()->getManager();
    	$result =$em->getRepository('BGRSerranoProductoBundle:Presentacion')->activar($presentacionId);
    	$response = new Response($serializer->serialize($result,'json'));
    	$response->headers->set('Content-Type', 'application/json');
    	return $response;
    }   
   /**
     * @Route("/presentacion/getStocks")
     * @Template()
     */
    public function getStocksAction()
    {

    	$serializer =  SerializerBuilder::create()->build();
    	$em = $this->getDoctrine()->getManager();
    	$result = $em->getRepository('BGRSerranoProductoBundle:Presentacion')->findStocks();
   	
    	$response = new Response($serializer->serialize($result,'json'));
    
    	$response->headers->set('Content-Type', 'application/json');
    	return $response;
    }
    
    /**
     * @Route("/presentacion/getStocksByCategoria")
     * @Template()
     */
    public function getStocksByCategoriaAction()
    {
    	 
    	$jsonData = $this->get('request')->request->get('data');
    	$serializer =  SerializerBuilder::create()->build();
    	$object = $serializer->deserialize($jsonData, 'BGR\Serrano\ProductoBundle\Entity\Categoria', 'json');
    	$em = $this->getDoctrine()->getManager();
    	$result = $em->getRepository('BGRSerranoProductoBundle:Presentacion')->findStocksByCategoria($object);
    	 
    	
    	$response = new Response($serializer->serialize($result,'json'));
    
    	$response->headers->set('Content-Type', 'application/json');
    	return $response;
    }
    
    /**
     * @Route("/presentacion/getActives")
     * @Template()
     */
    public function getActivesAction()
    {
    	$em = $this->getDoctrine()->getManager();
    	$data = $em->getRepository('BGRSerranoProductoBundle:Presentacion')->findActives(true);      
    	$serializer =  SerializerBuilder::create()->build();
    	$jsonContent = $serializer->serialize($data, 'json');
    	$response = new Response($jsonContent);
    	$response->headers->set('Content-Type', 'application/json');
    	return $response;
    }
    /**
     * @Route("/presentacion/getInactives")
     * @Template()
     */
    public function getInactivesAction()
    {
    	$em = $this->getDoctrine()->getManager();
    	$data = $em->getRepository('BGRSerranoProductoBundle:Presentacion')->findActives(false);
    
    	$serializer =  SerializerBuilder::create()->build();
    	$jsonContent = $serializer->serialize($data, 'json');
    	$response = new Response($jsonContent);
    	$response->headers->set('Content-Type', 'application/json');
    	return $response;
    }
    
    /**
     * @Route("/presentacion/getPaqueteById")
     * @Template()
     */
    public function getPaqueteByIdAction()
    {
    	$presentacion_id = $this->get('request')->request->get('presentacion');
    	$paquete_id = $this->get('request')->request->get('paquete');
    	
    	$em = $this->getDoctrine()->getManager();
    	$data = $em->getRepository('BGRSerranoProductoBundle:Presentacion')->findPaquete($presentacion_id,$paquete_id);

	   if(count($data) == 0 ) {
  			$response = new Response();
			$response->setContent("El paquete no pertenece a la presentacion");
			$response->setStatusCode(500);
			$response->headers->set('Content-Type', 'text/html');	
			return $response;	
      }

    	$serializer =  SerializerBuilder::create()->build();
    	$jsonContent = $serializer->serialize($data, 'json');
    	$response = new Response($jsonContent);
    	$response->headers->set('Content-Type', 'application/json');
    	return $response;
    }
    
    
}
