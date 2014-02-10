<?php

namespace BGR\Serrano\ProductoBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use JMS\Serializer\Annotation\Type;
/**
 * Remanente
 *
 * @ORM\Table()
 * @ORM\Entity(repositoryClass="BGR\Serrano\ProductoBundle\Entity\RemanenteRepository")
 */
class Remanente
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @ORM\ManyToOne(targetEntity="Producto") 
     * @ORM\JoinColumn(name="producto_id", referencedColumnName="id")
     * @Type("BGR\Serrano\ProductoBundle\Entity\Producto")
     *
     */
    protected $producto;

    /**
     * @var float
     *
     * @ORM\Column(name="cantidad", type="decimal")
 	  * @Type("double")
     */
    private $cantidad;


    /**
     * Get id
     *
     * @return integer 
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set producto
     *
     * @param Producto $producto
     * @return Remanente
     */
    public function setProducto($producto)
    {
        $this->producto = $producto;
    
        return $this;
    }

    /**
     * Get producto
     *
     * @return Producto 
     */
    public function getProducto()
    {
        return $this->producto;
    }

    /**
     * Set cantidad
     *
     * @param float $cantidad
     * @return Remanente
     */
    public function setCantidad($cantidad)
    {
        $this->cantidad = $cantidad;
    
        return $this;
    }

    /**
     * Get cantidad
     *
     * @return float 
     */
    public function getCantidad()
    {
        return $this->cantidad;
    }
}
