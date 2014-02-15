<?php

namespace BGR\Serrano\ProductoBundle\Controller;


use Symfony\Component\HttpKernel\Exception\NotFoundHttpException;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;
use JMS\Serializer\SerializerBuilder;
use BGR\Serrano\ProductoBundle\Entity\Producto as Producto;
use BGR\Serrano\ProductoBundle\Entity\ProductoUnidadDeMedida as ProductoUnidadDeMedida;
use Symfony\Component\HttpFoundation\Response as Response;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\ORM\Mapping\MappingException;
use BGR\Serrano\ProductoBundle\Entity\ProductoProveedor;

class ProductoController extends Controller
{
    /**
     * @Route("/producto/getAll")
     */
    public function getAllAction()
    {
        $em = $this->getDoctrine()->getManager();
        $data = $em->getRepository('BGRSerranoProductoBundle:Producto')->findAll();

        $serializer =  SerializerBuilder::create()->build();
        $jsonContent = $serializer->serialize($data, 'json');
        
        $response = new Response($jsonContent);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }


    /**
     * @Route("/producto/save")
     */
    public function saveAction()
    {

        $jsonData = $this->get('request')->request->get('data');

        $serializer =  SerializerBuilder::create()->build();

        $object = $serializer->deserialize($jsonData, 'BGR\Serrano\ProductoBundle\Entity\Producto', 'json');

        $em = $this->getDoctrine()->getManager();
        $em->getRepository('BGRSerranoProductoBundle:Producto')->save($object);


        $response = new Response($serializer->serialize($object,'json'));
        
        $response->headers->set('Content-Type', 'application/json');

        return $response;
    }

   /**
     * @Route("/producto/delete")
     * @Template()
     */
    public function deleteAction()
    {
        $jsonData = $this->get('request')->request->get('data');
        
        $serializer =  SerializerBuilder::create()->build();

        $object = $serializer->deserialize($jsonData, 'BGR\Serrano\ProductoBundle\Entity\Producto', 'json');

        $em = $this->getDoctrine()->getManager();
        try{
        		$em->getRepository('BGRSerranoProductoBundle:Producto')->delete($object);
        }catch(\Doctrine\DBAL\DBALException $e){
     			$response = new Response();
  				$response->setContent('No se puede eliminar un producto existente en una presentacion');
  				$response->setStatusCode(500);
  				$response->headers->set('Content-Type', 'text/html');	
			  	return $response;		
        }

        return new Response($jsonData);
    }



   /**
     * @Route("/producto/update")
     * @Template()
     */
    public function updateAction()
    {
    
        $jsonData = $this->get('request')->request->get('data');

        $serializer =  SerializerBuilder::create()->build();

        $object = $serializer->deserialize($jsonData, 'BGR\Serrano\ProductoBundle\Entity\Producto', 'json');
	
        $em = $this->getDoctrine()->getManager();
        
        $em->getRepository('BGRSerranoProductoBundle:Producto')->update($object);

        /* manyToMany behavior -> refactor This -Begin */
        $ObjectOnDB = $em->getRepository('BGRSerranoProductoBundle:Producto')->find($object->getId());
         			
		  $unidadesOnDB =  $ObjectOnDB->getUnidadDeMedidas();

		  foreach($object->getUnidadDeMedidas() as $unidadToPersist){
		  		$notExist = true;
		  		foreach($unidadesOnDB as $unidadOnDB){
		  			if($unidadToPersist->getId() == $unidadOnDB->getId())
					{
						$notExist = false;
		  			}
		  		}
		  		if($notExist){

		  			 $manualManyToMany = new ProductoUnidadDeMedida();
		  	       $manualManyToMany->setProductoId($object->getId());
					 $manualManyToMany->setUnidaddemedidaId($unidadToPersist->getId());
		  			 $em->getRepository('BGRSerranoProductoBundle:ProductoUnidadDeMedida')->save($manualManyToMany);

		  		}
		  }
 
		  foreach($unidadesOnDB as $unidadOnDB){
		  		$notExist = true;
		  		foreach($object->getUnidadDeMedidas() as $unidadToPersist){
		  			if($unidadToPersist->getId() == $unidadOnDB->getId())
					{
						$notExist = false;
		  			}
		  		}
		  		if($notExist){
		  			 $manualManyToMany = new ProductoUnidadDeMedida();
		  	       $manualManyToMany->setProductoId($object->getId());
		    	    $manualManyToMany->setUnidaddemedidaId($unidadOnDB->getId());
					 //existe en presentacion		    	    
					 
		    	    if ($this->existOnPresentacion($manualManyToMany)){
	  						$response = new Response();
			  				$response->setContent('No puede existir un producto sin unidades de medida relacionadas');
			  				$response->setStatusCode(500);
			  				$response->headers->set('Content-Type', 'text/html');	
			  				return $response;			    	   		    	    	
		  			 }
		  			 $em->getRepository('BGRSerranoProductoBundle:ProductoUnidadDeMedida')->delete($manualManyToMany);
		  		}
		  }   

		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  $proveedoresOnDB =  $ObjectOnDB->getProveedores();
		  
		  foreach($object->getProveedores() as $proveedorToPersist){
		  	$notExist = true;
		  	foreach($proveedoresOnDB as $proveedorOnDB){
		  		if($proveedorToPersist->getId() == $proveedorOnDB->getId())
		  		{
		  			$notExist = false;
		  		}
		  	}
		  	if($notExist){
		  
		  		$manualManyToManyProveedor = new ProductoProveedor();
		  		$manualManyToManyProveedor->setProductoId($object->getId());
		  		$manualManyToManyProveedor->setProveedorId($proveedorToPersist->getId());
		  		$manualManyToManyProveedor->setPrecioReposicion(0);
		  		$em->getRepository('BGRSerranoProductoBundle:ProductoProveedor')->save($manualManyToManyProveedor);
		  
		  	}
		  }
		  
		  foreach($proveedoresOnDB as $proveedorOnDB){
		  	$notExist = true;
		  	foreach($object->getProveedores() as $proveedorToPersist){
		  		if($proveedorToPersist->getId() == $proveedorOnDB->getId())
		  		{
		  			$notExist = false;
		  		}
		  	}
		  	if($notExist){
		  		$manualManyToManyProveedor = new ProductoProveedor();
		  		$manualManyToManyProveedor->setProductoId($object->getId());
		  		$manualManyToManyProveedor->setProveedorId($proveedorOnDB->getId());
		  		//existe en presentacion
		  
		  		if ($this->existOnPresentacionProveedor($manualManyToManyProveedor)){
		  			throw MappingException::illegalInverseIdentifierAssocation('ProductoUnidadDeMedida JOIN Presentacion','producto_id, unidadDeMedida_id');
		  		}
		  		$em->getRepository('BGRSerranoProductoBundle:ProductoProveedor')->delete($manualManyToManyProveedor);
		  	}
		  }
		  
		  
        /* manyToMany behavior -> refactor This -End */        
        
        $response = new Response($serializer->serialize($object,'json'));
        
        $response->headers->set('Content-Type', 'application/json');

        return $response;
    }

    /**
     * @Route("/producto/getByCategoria")
     */
    public function getByCategoriaAction()
    {
    	$em = $this->getDoctrine()->getManager();
    	 
    	$categoriaId = $this->get('request')->request->get('data');
    	$serializer =  SerializerBuilder::create()->build();
    	
    	$result = $em->getRepository('BGRSerranoProductoBundle:Producto')->findByCategoria($categoriaId);
    	
    	$response = new Response($serializer->serialize($result,'json'));
    	$response->headers->set('Content-Type', 'application/json');
    	
    	return $response;
    	
    	
    }
    /**
     * @Route("/producto/getById")
     */
    public function getByIdAction()
    {
    }
    
    private function existOnPresentacion($pu){
   	$em = $this->getDoctrine()->getManager();
    	$presentaciones = $em->getRepository('BGRSerranoProductoBundle:Presentacion')->findAll();
    	foreach($presentaciones as $presentacion){
    		if( $pu->getProductoId() == $presentacion->getProducto()->getId() &&
    		    $pu->getUnidaddemedidaId() == $presentacion->getUnidad_de_medida()->getId()){
    		   return true;
    		}
    	}
    	return false;
    	
    }
    
    
    private function existOnPresentacionProveedor($pu){
   /* 	$em = $this->getDoctrine()->getManager();
    	$presentaciones = $em->getRepository('BGRSerranoProductoBundle:Presentacion')->findAll();
    	foreach($presentaciones as $presentacion){
    		if( $pu->getProductoId() == $presentacion->getProducto()->getId() &&
    		$pu->getUnidaddemedidaId() == $presentacion->getUnidad_de_medida()->getId()){
    			return true;
    		}
    	}*/
    	return false;
    	 
    }
    
    
    
    /**
     * @Route("/producto/getRemanenteByProducto")
     */
    public function getRemanenteAction()
    {
    	$em = $this->getDoctrine()->getManager();
    
    	$productoId = $this->get('request')->request->get('data');
    	$serializer =  SerializerBuilder::create()->build();
    	 
    	$result = $em->getRepository('BGRSerranoProductoBundle:Producto')->findRemanente($productoId);
    	 
    	$response = new Response($serializer->serialize($result,'json'));
    	$response->headers->set('Content-Type', 'application/json');
    	 
    	return $response;
    	 
    	 
    }
    /**
     * @Route("/producto/getAllRemanentes")
     */
    public function getAllRemanentesAction()
    {
    	$em = $this->getDoctrine()->getManager();
    
    	$serializer =  SerializerBuilder::create()->build();
    
    	$result = $em->getRepository('BGRSerranoProductoBundle:Producto')->getAllRemanentes();
    
    	$response = new Response($serializer->serialize($result,'json'));
    	$response->headers->set('Content-Type', 'application/json');
    
    	return $response;
    
    
    }
    
}
