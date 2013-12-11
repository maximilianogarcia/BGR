<?php

namespace BGR\Serrano\ProductoBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use JMS\Serializer\Annotation\Type;
use JMS\Serializer\Annotation\Exclude;
use JMS\Serializer\Annotation\Type;

/**
 * Presentacion
 *
 * @ORM\Table()
 * @ORM\Entity(repositoryClass="BGR\Serrano\ProductoBundle\Entity\PresentacionRepository")
 */
class Presentacion
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     * @Type("integer")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="SKU", type="string", length=255)
     * @Type("string")
     */
    private $sKU;

    /**
     * @var boolean
     *
     * @ORM\Column(name="active", type="boolean")
     * @Type("string")
     */
    private $active;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateCreate", type="datetime")
     */
    private $dateCreate;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateUpdate", type="datetime")
     */
    private $dateUpdate;

    /**
     * @var string
     *
     * @ORM\Column(name="fraccionable", type="string", length=255)
     * @Type("string")
     */
    private $fraccionable;


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
     * Set sKU
     *
     * @param string $sKU
     * @return Presentacion
     */
    public function setSKU($sKU)
    {
        $this->sKU = $sKU;
    
        return $this;
    }

    /**
     * Get sKU
     *
     * @return string 
     */
    public function getSKU()
    {
        return $this->sKU;
    }

    /**
     * Set active
     *
     * @param boolean $active
     * @return Presentacion
     */
    public function setActive($active)
    {
        $this->active = $active;
    
        return $this;
    }

    /**
     * Get active
     *
     * @return boolean 
     */
    public function getActive()
    {
        return $this->active;
    }

    /**
     * Set dateCreate
     *
     * @param \DateTime $dateCreate
     * @return Presentacion
     */
    public function setDateCreate($dateCreate)
    {
        $this->dateCreate = $dateCreate;
    
        return $this;
    }

    /**
     * Get dateCreate
     *
     * @return \DateTime 
     */
    public function getDateCreate()
    {
        return $this->dateCreate;
    }

    /**
     * Set dateUpdate
     *
     * @param \DateTime $dateUpdate
     * @return Presentacion
     */
    public function setDateUpdate($dateUpdate)
    {
        $this->dateUpdate = $dateUpdate;
    
        return $this;
    }

    /**
     * Get dateUpdate
     *
     * @return \DateTime 
     */
    public function getDateUpdate()
    {
        return $this->dateUpdate;
    }

    /**
     * Set fraccionable
     *
     * @param string $fraccionable
     * @return Presentacion
     */
    public function setFraccionable($fraccionable)
    {
        $this->fraccionable = $fraccionable;
    
        return $this;
    }

    /**
     * Get fraccionable
     *
     * @return string 
     */
    public function getFraccionable()
    {
        return $this->fraccionable;
    }




    /**
     * @ORM\ManyToOne(targetEntity="Material") 
     * @ORM\JoinColumn(name="material_id", referencedColumnName="id")
     * @Type("BGR\Serrano\ProductoBundle\Entity\Material")
     */
    protected $material;

    public function setMaterial(Categoria $material)
    {
        $this->material = $material;
    }

    public function getMaterial()
    {
        return $this->material;
    }


    /**
     * @ORM\ManyToOne(targetEntity="Lote") 
     * @ORM\JoinColumn(name="lote_id", referencedColumnName="id")
     * @Type("BGR\Serrano\ProductoBundle\Entity\Lote")
     */
    protected $lote;

    public function setLote(Lote $lote)
    {
        $this->lote = $lote;
    }

    public function getLote()
    {
        return $this->lote;
    }   
   /**
     * @ORM\ManyToOne(targetEntity="UnidadDeMedida") 
     * @ORM\JoinColumn(name="unidadDeMedida_id", referencedColumnName="id")
     * @Type("BGR\Serrano\ProductoBundle\Entity\UnidadDeMedida")
     */
    protected $unidadDeMedida;

    public function setUnidadDeMedida(UnidadDeMedida $unidadDeMedida)
    {
        $this->unidadDeMedida = $unidadDeMedida;
    }

    public function getUnidadDeMedida()
    {
        return $this->unidadDeMedida;
    }
    /**
     * @ORM\ManyToOne(targetEntity="Producto") 
     * @ORM\JoinColumn(name="producto_id", referencedColumnName="id")
     * @Type("BGR\Serrano\ProductoBundle\Entity\Producto")
     *
     */
    protected $producto;

    public function setProducto(Producto $producto)
    {
        $this->producto = $producto;
    }

    public function getProducto()
    {
        return $this->producto;
    }

}
