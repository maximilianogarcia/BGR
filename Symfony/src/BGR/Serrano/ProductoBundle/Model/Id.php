<?php
namespace BGR\Serrano\ProductoBundle\Model;

use JMS\Serializer\Annotation\Type;
class Fraccionable
{
	/**
	 * 
	 * @var integer
	 * 
	 * @Type("integer")
	 */
	private $id;
	
	public function getId() {
		return $this->id;
	}
	public function setId($id) {
		$this->id = $id;
		return $this;
	}
	

}