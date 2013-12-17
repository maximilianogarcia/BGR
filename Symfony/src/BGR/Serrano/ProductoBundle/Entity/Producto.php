<?php

namespace BGR\Serrano\ProductoBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use JMS\Serializer\Annotation\Type;
use JMS\Serializer\Annotation\Exclude;
use Doctrine\Common\Collections\ArrayCollection;

/**
 * Producto
 *
 * @ORM\Table()
 * @ORM\Entity(repositoryClass="BGR\Serrano\ProductoBundle\Entity\ProductoRepository")
 * @ORM\HasLifecycleCallbacks()
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
     * @Type("double")
     */
    protected $precioVenta;

    /**
     * @var float
     *
     * @ORM\Column(name="precioCompra", type="decimal")
     * @Type("double")
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


    public function getCategoria()
    {
        return $this->categoria;
    }
    
    public function setCategoria(Categoria $categoria)
    {
        $this->categoria = $categoria;
    }
 
    /**
     * @ORM\ManyToMany(targetEntity="UnidadDeMedida", inversedBy="productos")
	  * @ORM\JoinTable(name="producto_unidad_de_medida")
	  *
     * @Type("ArrayCollection<BGR\Serrano\ProductoBundle\Entity\UnidadDeMedida>")
     *
     */
    protected $unidad_de_medidas;


    public function getUnidadDeMedidas()
    {
        return $this->unidad_de_medidas;
    }

    public function setUnidadDeMedidas($unidadDeMedidas)
    {
        $this->unidad_de_medidas = $unidadDeMedidas;
    }

     /**
     * @ORM\OneToMany(targetEntity="Lote", mappedBy="producto")
     * @Type("ArrayCollection<BGR\Serrano\ProductoBundle\Entity\Lote>")
     * @Exclude
     */
    protected $lotes;

    public function __construct()
    {
        $this->lotes = new ArrayCollection();
        $this->unidad_de_medidas = new ArrayCollection();
    }
    

    /**
     * Add unidad_de_medidas
     *
     * @param \BGR\Serrano\ProductoBundle\Entity\UnidadDeMedida $unidadDeMedidas
     * @return Producto
     */
    public function addUnidadDeMedida(\BGR\Serrano\ProductoBundle\Entity\UnidadDeMedida $unidadDeMedidas)
    {
        $this->unidad_de_medidas[] = $unidadDeMedidas;
    
        return $this;
    }

    /**
     * Remove unidad_de_medidas
     *
     * @param \BGR\Serrano\ProductoBundle\Entity\UnidadDeMedida $unidadDeMedidas
     */
    public function removeUnidadDeMedida(\BGR\Serrano\ProductoBundle\Entity\UnidadDeMedida $unidadDeMedidas)
    {
        $this->unidad_de_medidas->removeElement($unidadDeMedidas);
    }

    /**
     * Add lotes
     *
     * @param \BGR\Serrano\ProductoBundle\Entity\Lote $lotes
     * @return Producto
     */
    public function addLote(\BGR\Serrano\ProductoBundle\Entity\Lote $lotes)
    {
        $this->lotes[] = $lotes;
    
        return $this;
    }

    /**
     * Remove lotes
     *
     * @param \BGR\Serrano\ProductoBundle\Entity\Lote $lotes
     */
    public function removeLote(\BGR\Serrano\ProductoBundle\Entity\Lote $lotes)
    {
        $this->lotes->removeElement($lotes);
    }

    /**
     * Get lotes
     *
     * @return \Doctrine\Common\Collections\Collection 
     */
    public function getLotes()
    {
        return $this->lotes;
    }
}