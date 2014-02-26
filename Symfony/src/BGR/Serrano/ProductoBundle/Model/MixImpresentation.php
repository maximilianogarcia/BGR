<?php
namespace BGR\Serrano\ProductoBundle\Model;

use JMS\Serializer\Annotation\Type;
class MixImpresentation
{
	/**
	 * @Type("string")
	 * @var unknown
	 */
    private $nombre_mix;
    /**
     * @Type("string")
     * @var unknown
     */
    private $nombre_presentacion;
    /**
     * @Type("integer")
     * @var unknown
     */
    private $cantidad_de_paquetes;
    /**
     * @Type("ArrayCollection<BGR\Serrano\ProductoBundle\Model\MixData>")
     * @var unknown
     */
    private $mix_data_list;
	/**
	 * @Type("integer") 
	 * @var unknown
	 */
    private $unidad_de_medida_id;
    
	public function getNombreMix() {
		return $this->nombre_mix;
	}
	public function setNombreMix($nombre_mix) {
		$this->nombre_mix = $nombre_mix;
		return $this;
	}
	public function getNombrePresentacion() {
		return $this->nombre_presentacion;
	}
	public function setNombrePresentacion($nombre_presentacion) {
		$this->nombre_presentacion = $nombre_presentacion;
		return $this;
	}
	public function getCantidadDePaquetes() {
		return $this->cantidad_de_paquetes;
	}
	public function setCantidadDePaquetes($cantidad_de_paquetes) {
		$this->cantidad_de_paquetes = $cantidad_de_paquetes;
		return $this;
	}
	public function getMixDataList() {
		return $this->mix_data_list;
	}
	public function setMixDataList($mix_data_list) {
		$this->mix_data_list = $mix_data_list;
		return $this;
	}
	public function getUnidadDeMedidaId() {
		return $this->unidad_de_medida_id;
	}
	public function setUnidadDeMedidaId($unidad_de_medida_id) {
		$this->unidad_de_medida_id = $unidad_de_medida_id;
		return $this;
	}
	

	
	
    
	
  
	/*
	 * {
  "nombreMix":"mix1",
  "nombrePrensentacion":"presentacion de mix1",
  "cantidad_de_paquetes":"4",
  "mixDataList":[private productoid;
    {
      "producto_id":"1".
      "paquete_id":"142",
      "cantidad_original":"500",
      "cantidad_nueva":"100"
    },
    {
      "producto_id":"5".
      "paquete_id":"145",
      "cantidad_original":"400",
      "cantidad_nueva":"80"
    }
  ]
}
	 * */

}