<?php

namespace BGR\Serrano\ProductoBundle\Service;

use Symfony\Component\DependencyInjection\Container;
use Doctrine\ORM\EntityManager;
use BGR\Serrano\ProductoBundle\Entity\Presentacion;
use BGR\Serrano\ProductoBundle\Entity\Paquete;

class ProductoService{
	
	
	public function crear_paquetes(EntityManager $em,  $productos){
		for ($i =0; $i< $presentacion->getCantidadPaquetes(); $i++){
			$paquete = new Paquete();
			$paquete->setPresentacion($presentacion);
			$paquete->setCodigo("un codigo");
			$paquete->setEstado("DISPONIBLE");
			$em->persist($paquete);
		}
		$em->flush();
	}
	public function stockDisponible(EntityManager $em,Presentacion $presentacion){
		
	}
}
