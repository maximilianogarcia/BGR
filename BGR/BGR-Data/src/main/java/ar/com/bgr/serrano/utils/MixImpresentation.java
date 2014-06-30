package ar.com.bgr.serrano.utils;

import java.util.Date;
import java.util.List;

public class MixImpresentation {
	/**
	 * @Type("string")
	 * @var unknown
	 */
    private String nombre;
    /**
     * @Type("DateTime<'Y-m-d'>")
     * @var unknown
     */
    private Date vencimiento;
    /**
     * @Type("integer")
     * @var unknown
     */
    private Integer unidad_de_medida;
    /**
	 * @Type("integer")
     * @var unknown
     */
    private Integer categoria;
    
	/**
	 * @Type("ArrayCollection<BGR\Serrano\ProductoBundle\Model\PaqueteMix>")
	 * @var unknown
	 */
    private List<PaqueteMix> paquetes_mix;

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the vencimiento
	 */
	public Date getVencimiento() {
		return vencimiento;
	}

	/**
	 * @param vencimiento the vencimiento to set
	 */
	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}

	/**
	 * @return the unidad_de_medida
	 */
	public Integer getUnidad_de_medida() {
		return unidad_de_medida;
	}

	/**
	 * @param unidad_de_medida the unidad_de_medida to set
	 */
	public void setUnidad_de_medida(Integer unidad_de_medida) {
		this.unidad_de_medida = unidad_de_medida;
	}

	/**
	 * @return the categoria
	 */
	public Integer getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the paquetes_mix
	 */
	public List<PaqueteMix> getPaquetes_mix() {
		return paquetes_mix;
	}

	/**
	 * @param paquetes_mix the paquetes_mix to set
	 */
	public void setPaquetes_mix(List<PaqueteMix> paquetes_mix) {
		this.paquetes_mix = paquetes_mix;
	}
    
    
}
