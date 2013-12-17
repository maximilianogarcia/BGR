<?php

namespace BGR\Serrano\ProductoBundle\Entity;

use Doctrine\ORM\EntityRepository;
use Doctrine\Common\Collections\ArrayCollection;

/**
 * ProductoRepository
 *
 * This class was generated by the Doctrine ORM. Add your own custom
 * repository methods below.
 */
class ProductoRepository extends EntityRepository
{

	public function save($producto)
    {   
        $em = $this->getEntityManager();
	    $producto->setCategoria($em->merge($producto->getCategoria()));
	    
	    $temporales = new ArrayCollection();
	      
	    foreach($producto->getUnidadDeMedidas() as $unidadMedida){
				$temporales->add($em->merge($unidadMedida));
        }
        
        $producto->setUnidadDeMedidas($temporales);
        
	     
        $em->persist($producto);
        $em->flush();
    }

	public function update($producto)
    {   
        $em = $this->getEntityManager();
        
  /*    $producto->setCategoria($em->merge($producto->getCategoria()));
 		  $temporales = new ArrayCollection();
	      
	     foreach($producto->getUnidadDeMedidas() as $unidadMedida){
				$temporales->add($em->merge($unidadMedida));
        }
        
         
        	$logger = this->get('logger');
			$logge->info();             
        
        */
        
	     $temporales = new ArrayCollection();
	      
	     foreach($producto->getUnidadDeMedidas() as $unidadMedida){
	
  			   $temporales->add($em->merge($unidadMedida));

				$unidadMedida->addProducto($producto);
				
				$em->persist($em->merge($unidadMedida));
        }        
        
 		  $producto->setUnidadDeMedidas($temporales);
 		   
        $em->persist($em->merge($producto));

        $em->flush();
    }

    public function delete($producto)
    {   
        $em = $this->getEntityManager();
        $em->remove($em->merge($producto));
        $em->flush();
    }


}
