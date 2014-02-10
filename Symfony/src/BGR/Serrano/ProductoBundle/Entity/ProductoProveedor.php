<?php

namespace BGR\Serrano\ProductoBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use JMS\Serializer\Annotation\Type;

/**
 * ProductoProveedor
 *
 * @ORM\Table(name="producto_proveedor")
 * @ORM\Entity(repositoryClass="BGR\Serrano\ProductoBundle\Entity\ProductoProveedorRepository")
 */
class ProductoProveedor
{
 
	/**
	 * @var integer
	 * @ORM\Column(name="producto_id", type="integer")
	 * @ORM\Id
	 * @Type("integer")
	 */
	protected $producto_id;
	
	/**
	 * @var integer
	 * @ORM\Column(name="proveedor_id", type="integer")
	 * @ORM\Id
	 * @Type("integer")
	 */
	protected $proveedor_id;
    /**
     * @var float
     *
     * @ORM\Column(name="precio_reposicion", type="decimal",options={"default" = 0})
	 * @Type("double")
     */
    private $precio_reposicion;
	public function getProductoId() {
		return $this->producto_id;
	}
	public function setProductoId($producto_id) {
		$this->producto_id = $producto_id;
		return $this;
	}
	public function getProveedorId() {
		return $this->proveedor_id;
	}
	public function setProveedorId($proveedor_id) {
		$this->proveedor_id = $proveedor_id;
		return $this;
	}
	public function getPrecioReposicion() {
		return $this->precio_reposicion;
	}
	public function setPrecioReposicion($precioReposicion) {
		$this->precioReposicion = $precioReposicion;
		return $this;
	}
	
    
    


    
}
