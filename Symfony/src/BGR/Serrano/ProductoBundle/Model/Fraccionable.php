<?php

namespace BGR\Serrano\ProductoBundle\Model;

use JMS\Serializer\Annotation\Type;
class Fraccionable
{
    /**
     * @var integer
     * @Type("integer")
     */
	protected $stock;
    /**
     * @var integer
     * @Type("BGR\Serrano\ProductoBundle\Entity\Presentacion")
     */
	protected $presentacion;
	public function getStock() {
		return $this->stock;
	}
	public function setStock($stock) {
		$this->stock = $stock;
		return $this;
	}
	public function getPresentacion() {
		return $this->presentacion;
	}
	public function setPresentacion($presentacion) {
		$this->presentacion = $presentacion;
		return $this;
	}
	
	
}