<?php

namespace BGR\Serrano\ProductoBundle\Model;

use JMS\Serializer\Annotation\Type;
class MixData
{
	/**
	 * @Type("string")
	 * @var unknown
	 */
	private $producto_id;
	/**
	 * @Type("integer")
	 * @var unknown
	 */
	private $paquete_id;
	/**
	 * @Type("integer")
	 * @var unknown
	 */
	private $cantidad_original;
	/**
	 * @Type("integer")
	 * @var unknown
	 */
	private $cantidad_nueva;	
	
	public function getProductoId() {
		return $this->producto_id;
	}
	public function setProductoId($producto_id) {
		$this->producto_id = $producto_id;
		return $this;
	}
	public function getPaqueteId() {
		return $this->paquete_id;
	}
	public function setPaqueteId($paquete_id) {
		$this->paquete_id = $paquete_id;
		return $this;
	}
	public function getCantidadOriginal() {
		return $this->cantidad_original;
	}
	public function setCantidadOriginal($cantidad_original) {
		$this->cantidad_original = $cantidad_original;
		return $this;
	}
	public function getCantidadNueva() {
		return $this->cantidad_nueva;
	}
	public function setCantidadNueva($cantidad_nueva) {
		$this->cantidad_nueva = $cantidad_nueva;
		return $this;
	}
	
	
	
	
	
}