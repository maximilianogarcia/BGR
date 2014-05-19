<?php

namespace BGR\Serrano\ProductoBundle\Model;

use JMS\Serializer\Annotation\Type;

class PaqueteMix
{
	/**
	 * @Type("string")
	 * @var unknown
	 * 
	 * producto_id
	 */
	private $id;
	/**
	 * @Type("integer")
	 * @var unknown
	 */
	private $cantidad;
	
	/**
	 * @Type("ArrayCollection<BGR\Serrano\ProductoBundle\Entity\Paquete>")
	 * @var unknown
	 */
	private $paquetes;
	
	/**
	 * @Type("integer")
	 * @var unknown
	 */
	private $cant_elegida;
	
	
	public function getId() {
		return $this->id;
	}
	public function setId($id) {
		$this->id = $id;
		return $this;
	}
	public function getCantidad() {
		return $this->cantidad;
	}
	public function setCantidad($cantidad) {
		$this->cantidad = $cantidad;
		return $this;
	}
	public function getPaquetes() {
		return $this->paquetes;
	}
	public function setPaquetes($paquetes) {
		$this->paquetes = $paquetes;
		return $this;
	}
	
	/**
	 *
	 * @return the unknown
	 */
	public function getCantElegida() {
		return $this->cant_elegida;
	}
	
	/**
	 *
	 * @param
	 *        	$cant_elegida
	 */
	public function setCantElegida($cant_elegida) {
		$this->cant_elegida = $cant_elegida;
		return $this;
	}
	
	

	/*
	 * {"id":1,
	 * "paquetes":[{"id":"145",
	 * "presentacion_id":"9",
	 * "codigo":"un codigo",
	 * "estado":"DISPONIBLE",
	 * "cantidad":"2",
	 * "unidad":"4"}],
	 * "cantidad":2}
	 * 
	 * 
	 * */
	
	
	
}