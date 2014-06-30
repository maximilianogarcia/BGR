package ar.com.bgr.serrano.utils;

import java.util.List;

public class PaqueteMix {

	/**
	 * @Type("string")
	 * @var unknown
	 * 
	 * producto_id
	 */
	private Integer id;
	/**
	 * @Type("integer")
	 * @var unknown
	 */
	private Integer cantidad;
	
	/**
	 * @Type("ArrayCollection<BGR\Serrano\ProductoBundle\Entity\Paquete>")
	 * @var unknown
	 */
	private List<PaqueteFromQuery> paquetes;
	
	/**
	 * @Type("integer")
	 * @var unknown
	 */
	private Integer cant_elegida;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the paquetes
	 */
	public List<PaqueteFromQuery> getPaquetes() {
		return paquetes;
	}

	/**
	 * @param paquetes the paquetes to set
	 */
	public void setPaquetes(List<PaqueteFromQuery> paquetes) {
		this.paquetes = paquetes;
	}

	/**
	 * @return the cant_elegida
	 */
	public Integer getCant_elegida() {
		return cant_elegida;
	}

	/**
	 * @param cant_elegida the cant_elegida to set
	 */
	public void setCant_elegida(Integer cant_elegida) {
		this.cant_elegida = cant_elegida;
	}
	
	
}
