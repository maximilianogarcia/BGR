<?php
namespace BGR\Serrano\ProductoBundle\Model;

use JMS\Serializer\Annotation\Type;
class MixImpresentation
{
	/**
	 * @Type("string")
	 * @var unknown
	 */
    private $nombre;
    /**
     * @Type("DateTime<'Y-m-d'>")
     * @var unknown
     */
    private $vencimiento;
    /**
     * @Type("integer")
     * @var unknown
     */
    private $unidad_de_medida;
    /**
	 * @Type("integer")
     * @var unknown
     */
    private $categoria;
    
	/**
	 * @Type("ArrayCollection<BGR\Serrano\ProductoBundle\Model\PaqueteMix>")
	 * @var unknown
	 */
    private $paquetes_mix;
	
	/**
	 *
	 * @return the unknown
	 */
	public function getNombre() {
		return $this->nombre;
	}
	
	/**
	 *
	 * @param
	 *        	$nombre
	 */
	public function setNombre($nombre) {
		$this->nombre = $nombre;
		return $this;
	}
	
	/**
	 *
	 * @return the unknown
	 */
	public function getVencimiento() {
		return $this->vencimiento;
	}
	
	/**
	 *
	 * @param
	 *        	$vencimiento
	 */
	public function setVencimiento($vencimiento) {
		$this->vencimiento = $vencimiento;
		return $this;
	}
	
	/**
	 *
	 * @return the unknown
	 */
	public function getUnidadDeMedida() {
		return $this->unidad_de_medida;
	}
	
	/**
	 *
	 * @param
	 *        	$unidad_de_medida
	 */
	public function setUnidadDeMedida($unidad_de_medida) {
		$this->unidad_de_medida = $unidad_de_medida;
		return $this;
	}
	
	/**
	 *
	 * @return the unknown
	 */
	public function getCategoria() {
		return $this->categoria;
	}
	
	/**
	 *
	 * @param
	 *        	$categoria
	 */
	public function setCategoria($categoria) {
		$this->categoria = $categoria;
		return $this;
	}
	
	/**
	 *
	 * @return the unknown
	 */
	public function getPaquetesMix() {
		return $this->paquetes_mix;
	}
	
	/**
	 *
	 * @param
	 *        	$paquetesMix
	 */
	public function setPaquetesMix($paquetes_mix) {
		$this->paquetes_mix = $paquetes_mix;
		return $this;
	}
	
    

	
	


}