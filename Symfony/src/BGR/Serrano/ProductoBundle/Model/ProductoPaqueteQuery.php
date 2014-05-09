<?php

namespace BGR\Serrano\ProductoBundle\Model;

use JMS\Serializer\Annotation\Type;
use Doctrine\Common\Collections\ArrayCollection;

class ProductoPaqueteQuery
{
	/**
	 * @Type("integer")
	 * @var unknown
	 */
	
	private $producto_id;
	/**
     * @Type("ArrayCollection<BGR\Serrano\ProductoBundle\Model\PaqueteQuery>")
	 * @var unknown
	 */
	private $paquete_query;
	
	public function getProductoId() {
		return $this->producto_id;
	}
	public function setProductoId($producto_id) {
		$this->producto_id = $producto_id;
		return $this;
	}
	public function getPaqueteQuery() {
		return $this->paquete_query;
	}
	public function setPaqueteQuery($paquete_query) {
		$this->paquete_query = $paquete_query;
		return $this;
	}
}