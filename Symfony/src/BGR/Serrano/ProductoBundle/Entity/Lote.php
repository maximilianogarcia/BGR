<?php

namespace BGR\Serrano\ProductoBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use JMS\Serializer\Annotation\Type;
/**
 * Lote
 *
 * @ORM\Table()
 * @ORM\Entity(repositoryClass="BGR\Serrano\ProductoBundle\Entity\LoteRepository")
 */
class Lote
{
    /**
     * @var integer
     *
     * @Type("integer")
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     *
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    protected $id;

    /**
     * @var string
     *
     *
     * @Type("string")
     *
     * @ORM\Column(name="descripcion", type="string", length=255)
     */
    protected $descripcion;

    /**
     * @var \DateTime
     *
     * @Type("DateTime<'Y-m-d'>")
     *
     * @ORM\Column(name="fechaDeElaboracion", type="date")
     */
    protected $fecha_de_elaboracion;

    /**
     * @var \DateTime
     *
     * @Type("DateTime<'Y-m-d'>")
     *
     * @ORM\Column(name="fechaDeVencimiento", type="date")
     */
    protected $fecha_de_vencimiento;

    /**
     * @var string
     *
     * @Type("string")
     *
     * @ORM\Column(name="codigo", type="string", length=255)
     */
    protected $codigo;


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
     * Set descripsion
     *
     * @param string $descripsion
     * @return Lote
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
     * Set fechaDeElaboracion
     *
     * @param \DateTime $fechaDeElaboracion
     * @return Lote
     */
    public function setFechaDeElaboracion($fecha_de_elaboracion)
    {
        $this->fecha_de_elaboracion = $fecha_de_elaboracion;
    
        return $this;
    }

    /**
     * Get fechaDeElaboracion
     *
     * @return \DateTime 
     */
    public function getFechaDeElaboracion()
    {
        return $this->fecha_de_elaboracion;
    }

    /**
     * Set fechaDeVencimiento
     *
     * @param \DateTime $fechaDeVencimiento
     * @return Lote
     */
    public function setFechaDeVencimiento($fecha_de_vencimiento)
    {
        $this->$fecha_de_vencimiento = $fecha_de_vencimiento;
    
        return $this;
    }

    /**
     * Get fechaDeVencimiento
     *
     * @return \DateTime 
     */
    public function getFechaDeVencimiento()
    {
        return $this->$fecha_de_vencimiento;
    }

    /**
     * Set codigo
     *
     * @param string $codigo
     * @return Lote
     */
    public function setCodigo($codigo)
    {
        $this->codigo = $codigo;
    
        return $this;
    }

    /**
     * Get codigo
     *
     * @return string 
     */
    public function getCodigo()
    {
        return $this->codigo;
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
