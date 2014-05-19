<?php

namespace BGR\Serrano\ProductoBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use JMS\Serializer\Annotation\Type;

/**
 * Mix
 *
 * @ORM\Table()
 * @ORM\Entity(repositoryClass="BGR\Serrano\ProductoBundle\Entity\MixRepository")
 */
class Mix
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
     * @ORM\Column(name="nombre", type="string", length=50)
     */
    private $nombre;

    /**
     * @var string
     *
     * @ORM\Column(name="descripcion", type="string", length=200)
     */
    private $descripcion;

    /**
     * @var \DateTime
     * @Type("DateTime<'Y-m-d'>")
     * @ORM\Column(name="vencimiento", type="date")
     */
    private $vencimiento;
        
    /**
     * @ORM\ManyToOne(targetEntity="UnidadDeMedida")
     * @ORM\JoinColumn(name="unidad_de_medida_id", referencedColumnName="id")
     * @Type("BGR\Serrano\ProductoBundle\Entity\UnidadDeMedida")
     */
    protected $unidad_de_medida;
    
    /**
     * @ORM\ManyToOne(targetEntity="Categoria") 
     * @ORM\JoinColumn(name="categoria_id", referencedColumnName="id")
     * @Type("BGR\Serrano\ProductoBundle\Entity\Categoria")
     */
    protected $categoria;
	
	/**
	 *
	 * @return the integer
	 */
	public function getId() {
		return $this->id;
	}
	
	/**
	 *
	 * @param
	 *        	$id
	 */
	public function setId($id) {
		$this->id = $id;
		return $this;
	}
	
	/**
	 *
	 * @return the string
	 */
	public function getNombre() {
		return $this->nombre;
	}
	
	/**
	 *
	 * @param string $nombre        	
	 */
	public function setNombre($nombre) {
		$this->nombre = $nombre;
		return $this;
	}
	
	/**
	 *
	 * @return the string
	 */
	public function getDescripcion() {
		return $this->descripcion;
	}
	
	/**
	 *
	 * @param string $descripcion        	
	 */
	public function setDescripcion($descripcion) {
		$this->descripcion = $descripcion;
		return $this;
	}
	
	/**
	 *
	 * @return the DateTime
	 */
	public function getVencimiento() {
		return $this->vencimiento;
	}
	
	/**
	 *
	 * @param  $vencimiento        	
	 */
	public function setVencimiento($vencimiento) {
		$this->vencimiento = $vencimiento;
		return $this;
	}
	
	/**
	 *
	 * @return the unknown_type
	 */
	public function getUnidadDeMedida() {
		return $this->unidad_de_medida;
	}
	
	/**
	 *
	 * @param unknown_type $unidad_de_medida        	
	 */
	public function setUnidadDeMedida($unidad_de_medida) {
		$this->unidad_de_medida = $unidad_de_medida;
		return $this;
	}
	
	/**
	 *
	 * @return the unknown_type
	 */
	public function getCategoria() {
		return $this->categoria;
	}
	
	/**
	 *
	 * @param unknown_type $categoria        	
	 */
	public function setCategoria($categoria) {
		$this->categoria = $categoria;
		return $this;
	}
	
    
}
