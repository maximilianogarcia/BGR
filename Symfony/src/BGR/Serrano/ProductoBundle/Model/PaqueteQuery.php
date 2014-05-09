<?php

namespace BGR\Serrano\ProductoBundle\Model;

use JMS\Serializer\Annotation\Type;

class PaqueteQuery
{
	/**
	 * @Type("integer")
	 * @var unknown
	 */
	private $paquete_id;
	/**
	 * @Type("float")
	 * @var unknown
	 */
	private $cantidad;
	/**
	 * @Type("integer")
	 * @var unknown
	 */
	private $unidad;
	
	public function getPaqueteId() {
		return $this->paquete_id;
	}
	public function setPaqueteId($paquete_id) {
		$this->paquete_id = $paquete_id;
		return $this;
	}
	public function getCantidad() {
		return $this->cantidad;
	}
	public function setCantidad($cantidad) {
		$this->cantidad = $cantidad;
		return $this;
	}
	public function getUnidad() {
		return $this->unidad;
	}
	public function setUnidad($unidad) {
		$this->unidad = $unidad;
		return $this;
	}
	
	
	
	
	
}