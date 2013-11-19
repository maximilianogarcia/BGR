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
     * @ORM\Column(name="descripsion", type="string", length=255)
     */
    protected $descripsion;

    /**
     * @var \DateTime
     *
     * @Type("DateTime")
     *
     * @ORM\Column(name="fechaDeElaboracion", type="date")
     */
    protected $fechaDeElaboracion;

    /**
     * @var \DateTime
     *
     * @Type("DateTime")
     *
     * @ORM\Column(name="fechaDeVencimiento", type="date")
     */
    protected $fechaDeVencimiento;

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
    public function setDescripsion($descripsion)
    {
        $this->descripsion = $descripsion;
    
        return $this;
    }

    /**
     * Get descripsion
     *
     * @return string 
     */
    public function getDescripsion()
    {
        return $this->descripsion;
    }

    /**
     * Set fechaDeElaboracion
     *
     * @param \DateTime $fechaDeElaboracion
     * @return Lote
     */
    public function setFechaDeElaboracion($fechaDeElaboracion)
    {
        $this->fechaDeElaboracion = $fechaDeElaboracion;
    
        return $this;
    }

    /**
     * Get fechaDeElaboracion
     *
     * @return \DateTime 
     */
    public function getFechaDeElaboracion()
    {
        return $this->fechaDeElaboracion;
    }

    /**
     * Set fechaDeVencimiento
     *
     * @param \DateTime $fechaDeVencimiento
     * @return Lote
     */
    public function setFechaDeVencimiento($fechaDeVencimiento)
    {
        $this->fechaDeVencimiento = $fechaDeVencimiento;
    
        return $this;
    }

    /**
     * Get fechaDeVencimiento
     *
     * @return \DateTime 
     */
    public function getFechaDeVencimiento()
    {
        return $this->fechaDeVencimiento;
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
     */
    protected $producto;

    public function setProducto(Categoria $producto)
    {
        $this->producto = $producto;
    }

    public function getProducto()
    {
        return $this->producto;
    }



}
