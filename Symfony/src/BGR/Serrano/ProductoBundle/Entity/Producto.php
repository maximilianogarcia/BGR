<?php

namespace BGR\Serrano\ProductoBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use JMS\Serializer\Annotation\Type;


/**
 * Producto
 *
 * @ORM\Table()
 * @ORM\Entity(repositoryClass="BGR\Serrano\ProductoBundle\Entity\ProductoRepository")
 */
class Producto
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     * @Type("integer")
     */
    protected $id;

    /**
     * @var string
     *
     * @ORM\Column(name="name", type="string", length=50)
     * @Type("string")
     */
    protected $name;

    /**
     * @var float
     *
     * @ORM\Column(name="precioVenta", type="decimal")
     * @Type("float")
     */
    protected $precioVenta;

    /**
     * @var float
     *
     * @ORM\Column(name="precioCompra", type="decimal")
     * @Type("float")
     */
    protected $precioCompra;


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
     * Set name
     *
     * @param string $name
     * @return Producto
     */
    public function setName($name)
    {
        $this->name = $name;
    
        return $this;
    }

    /**
     * Get name
     *
     * @return string 
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * Set precioVenta
     *
     * @param float $precioVenta
     * @return Producto
     */
    public function setPrecioVenta($precioVenta)
    {
        $this->precioVenta = $precioVenta;
    
        return $this;
    }

    /**
     * Get precioVenta
     *
     * @return float 
     */
    public function getPrecioVenta()
    {
        return $this->precioVenta;
    }

    /**
     * Set precioCompra
     *
     * @param float $precioCompra
     * @return Producto
     */
    public function setPrecioCompra($precioCompra)
    {
        $this->precioCompra = $precioCompra;
    
        return $this;
    }

    /**
     * Get precioCompra
     *
     * @return float 
     */
    public function getPrecioCompra()
    {
        return $this->precioCompra;
    }

    /**
     * @ORM\ManyToOne(targetEntity="Categoria") 
     * @ORM\JoinColumn(name="categoria_id", referencedColumnName="id")
     * @Type("BGR\Serrano\ProductoBundle\Entity\Categoria")
     */
    protected $categoria;

    public function setCategoria(Categoria $categoria)
    {
        $this->categoria = $categoria;
    }

    public function getCategoria()
    {
        return $this->categoria;
    }
    
}
