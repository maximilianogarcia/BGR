/**
 * 
 */
package ar.com.bgr.serrano.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Descripcion:
 * 
 * {}
 *
 * @author matias
 * 
 * @since 01/06/2014
 */
@Entity(name="Material")
@Table(name="Material")
public class Material {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "peso")
	private Double peso;
	
	@Column(name = "alto")
	private Double alto;
	
	@Column(name = "ancho")
	private Double ancho;
	
	@Column(name = "largo")
	private Double largo;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the peso
	 */
	public Double getPeso() {
		return peso;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(Double peso) {
		this.peso = peso;
	}

	/**
	 * @return the alto
	 */
	public Double getAlto() {
		return alto;
	}

	/**
	 * @param alto the alto to set
	 */
	public void setAlto(Double alto) {
		this.alto = alto;
	}

	/**
	 * @return the ancho
	 */
	public Double getAncho() {
		return ancho;
	}

	/**
	 * @param ancho the ancho to set
	 */
	public void setAncho(Double ancho) {
		this.ancho = ancho;
	}

	/**
	 * @return the largo
	 */
	public Double getLargo() {
		return largo;
	}

	/**
	 * @param largo the largo to set
	 */
	public void setLargo(Double largo) {
		this.largo = largo;
	}


}
