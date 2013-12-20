<?php

namespace BGR\Serrano\ProductoBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use JMS\Serializer\Annotation\Type;
use Doctrine\Common\Collections\ArrayCollection;
use JMS\Serializer\Annotation\Exclude;

/**
 * UnidadDeMedida
 *
 * @ORM\Table(uniqueConstraints={@ORM\UniqueConstraint(name="search_idx", columns={"name"})})
 * @ORM\Entity(repositoryClass="BGR\Serrano\ProductoBundle\Entity\UnidadDeMedidaRepository")
 * @ORM\HasLifecycleCallbacks()
 */
class UnidadDeMedida
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     *
     * @Type("integer")
     *
     */
    protected $id;

    /**
     * @var string
     *
     * @ORM\Column(name="name", type="string", length=50)
     *
     * @Type("string")
     *
     */
    protected $name;

    /**
     * @var string
     *
     * @ORM\Column(name="descripcion", type="string", length=50)
     *
     * @Type("string")
     *
     */
    protected $descripcion;

    /**
     * @var float
     *
     * @ORM\Column(name="equivalencia", type="float")
     *
     * @Type("float")
     *
     */
    protected $equivalencia;
  
    /**
     * @ORM\ManyToMany(targetEntity="Producto", mappedBy="unidad_de_medidas")
     * @Type("ArrayCollection<BGR\Serrano\ProductoBundle\Entity\Producto>")
     * @Exclude
     */
    private $productos;

    public function setProductos(\BGR\Serrano\ProductoBundle\Entity\Producto $productos)
    {
        $this->productos = $productos;
    }

    public function getProductos()
    {
        return $this->productos;
    }

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
     * @return UnidadDeMedida
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
     * Set descripcion
     *
     * @param string $descripcion
     * @return UnidadDeMedida
     */
    public function setDescripcion($descripcion)
    {
        $this->descripcion = $descripcion;
    
        return $this;
    }

    /**
     * Get descripcion
     *
     * @return string 
     */
    public function getDescripcion()
    {
        return $this->descripcion;
    }

    /**
     * Set equivalencia
     *
     * @param float $equivalencia
     * @return UnidadDeMedida
     */
    public function setEquivalencia($equivalencia)
    {
        $this->equivalencia = $equivalencia;
    
        return $this;
    }

    /**
     * Get equivalencia
     *
     * @return float 
     */
    public function getEquivalencia()
    {
        return $this->equivalencia;
    }
    
    public function __construct()
    {
        $this->productos = new ArrayCollection();

    }
    	

    /**
     * Add productos
     *
     * @param \BGR\Serrano\ProductoBundle\Entity\Producto $productos
     * @return UnidadDeMedida
     */
    public function addProducto(\BGR\Serrano\ProductoBundle\Entity\Producto $producto)
    {
        $this->productos->add($producto);
        return $this;
    }

    /**
     * Remove productos
     *
     * @param \BGR\Serrano\ProductoBundle\Entity\Producto $productos
     */
    public function removeProducto(\BGR\Serrano\ProductoBundle\Entity\Producto $productos)
    {
        $this->productos->removeElement($productos);
    }
}