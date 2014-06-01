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
@Entity(name="unidadDeMedida")
@Table(name="unidadDeMedida")
public class UnidadDeMedida {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "descripcion")
	private String descripcion;	

	@Column(name = "deriva_de")
	private Integer deriva_de;

	@Column(name = "divisible")
	private Boolean divisible;
	
	@Column(name = "equivalencia")
	private Double equivalencia;

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
	 * @return the derivaDe
	 */
	public Integer getDerivaDe() {
		return deriva_de;
	}

	/**
	 * @param derivaDe the derivaDe to set
	 */
	public void setDerivaDe(Integer derivaDe) {
		this.deriva_de = derivaDe;
	}

	/**
	 * @return the divisible
	 */
	public Boolean getDivisible() {
		return divisible;
	}

	/**
	 * @param divisible the divisible to set
	 */
	public void setDivisible(Boolean divisible) {
		this.divisible = divisible;
	}

	/**
	 * @return the equivalencia
	 */
	public Double getEquivalencia() {
		return equivalencia;
	}

	/**
	 * @param equivalencia the equivalencia to set
	 */
	public void setEquivalencia(Double equivalencia) {
		this.equivalencia = equivalencia;
	}	
	
	

}
