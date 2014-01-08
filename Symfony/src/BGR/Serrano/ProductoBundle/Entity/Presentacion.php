<?php

namespace BGR\Serrano\ProductoBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use JMS\Serializer\Annotation\Type;
use JMS\Serializer\Annotation\Exclude;

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
     * @ORM\Column(name="descripcion", type="string", length=255)
     * @Type("string")
     */
    private $descripcion;

    /**
     * @var boolean
     *
     * @ORM\Column(name="active", type="boolean")
     * @Type("boolean")
     */
    private $active;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_create", type="datetime")
     * @Type("DateTime<'Y-m-d'>")
     */
    private $date_create;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_update", type="datetime")
     * @Type("DateTime<'Y-m-d'>")
     */
    private $date_update;

    /**
     * @var boolean
     *
     * @ORM\Column(name="fraccionable", type="boolean")
     * @Type("boolean")
     */
    private $fraccionable;

    /**
     * @var float
     *
     * @ORM\Column(name="peso_neto", type="decimal", length=255)
     * @Type("double")
     */
    private $peso_neto;

    /**
     * @var float
     *
     * @ORM\Column(name="peso_escurrido", type="decimal", length=255)
     * @Type("double")
     */
    private $peso_escurrido;

    /**
     * @var integer
     *
     * @Type("integer")
     */
    protected $cantidad_paquetes;
    
    /**
     * @var integer
     * @ORM\Column(name="cantidad", type="decimal", length=255)
     * @Type("integer")
     */
    protected $cantidad;

    /**
     * Get id
     *
     * @return string 
     */
    public function getId()
    {
        return $this->id;
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
     * Set date_create
     *
     * @param \DateTime $date_create
     * @return Presentacion
     */
    public function setDate_create($date_create)
    {
        $this->date_create = $date_create;
    
        return $this;
    }

    /**
     * Get date_create
     *
     * @return \DateTime 
     */
    public function getPeso_neto()
    {
        return $this->peso_neto;
    }

     /**
     * Set peso_neto
     *
     * @param \float $peso_neto
     * @return Presentacion
     */
    public function setPeso_neto($peso_neto)
    {
        $this->peso_neto = $peso_neto;
    
        return $this;
    }


    /**
     * Get peso_escurrido
     *
     * @return \DateTime 
     */
    public function getPeso_escurrido()
    {
        return $this->peso_escurrido;
    }
     /**
     * Set peso_escurrido
     *
     * @param \float $peso_escurrido
     * @return Presentacion
     */
    public function setPeso_escurrido($peso_escurrido)
    {
        $this->peso_escurrido = $peso_escurrido;
    
        return $this;
    }
    /**
     * Get date_create
     *
     * @return \DateTime 
     */
    public function getDate_create()
    {
        return $this->date_create;
    }

    /**
     * Set dateUpdate
     *
     * @param \DateTime $dateUpdate
     * @return Presentacion
     */
    public function setDate_update($date_update)
    {
        $this->date_update = $date_update;
    
        return $this;
    }

    /**
     * Get date_update
     *
     * @return \DateTime 
     */
    public function getDate_update()
    {
        return $this->date_update;
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

    public function setMaterial(Material $material)
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
    protected $unidad_de_medida;

    public function setUnidad_de_medida(UnidadDeMedida $unidad_de_medida)
    {
        $this->unidad_de_medida = $unidad_de_medida;
    }

    public function getUnidad_de_medida()
    {
        return $this->unidad_de_medida;
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
	public function getCantidadPaquetes() {
		return $this->cantidad_paquetes;
	}
	public function setCantidadPaquetes($cantidad_paquetes) {
		$this->cantidad_paquetes = $cantidad_paquetes;
		return $this;
	}
	
	public function getCantidad() {
		return $this->cantidad;
	}
	public function setCantidad($cantidad) {
		$this->cantidad = $cantidad;
		return $this;
	}
	

}
