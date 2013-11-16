<?php

namespace BGR\Serrano\ProductoBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

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
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="name", type="string", length=50)
     */
    private $name;

    /**
     * @var float
     *
     * @ORM\Column(name="precioVenta", type="decimal")
     */
    private $precioVenta;

    /**
     * @var float
     *
     * @ORM\Column(name="precionCompra", type="decimal")
     */
    private $precionCompra;


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
     * Set precionCompra
     *
     * @param float $precionCompra
     * @return Producto
     */
    public function setPrecionCompra($precionCompra)
    {
        $this->precionCompra = $precionCompra;
    
        return $this;
    }

    /**
     * Get precionCompra
     *
     * @return float 
     */
    public function getPrecionCompra()
    {
        return $this->precionCompra;
    }

    /**
     * @ORM\ManyToOne(targetEntity="Categoria", inversedBy="productps")
     * @ORM\JoinColumn(name="categoria_id", referencedColumnName="id")
     */
    private $categoria;

    public function setCategoria(Categoria $categoria)
    {
        $this->categoria = $categoria;
    }

    public function getCategoria()
    {
        return $this->categoria;
    }
    
}
