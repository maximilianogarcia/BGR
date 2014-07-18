/**
 * 
 */
package ar.com.bgr.serrano.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

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
@Entity(name="UnidadDeMedida")
@Table(name="UnidadDeMedida", uniqueConstraints = @UniqueConstraint(columnNames={"name"}))
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnidadDeMedida {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "descripcion")
	private String descripcion;	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "deriva_de_id")
	private UnidadDeMedida deriva_de;

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
	public UnidadDeMedida getDerivaDe() {
		return deriva_de;
	}

	/**
	 * @param derivaDe the derivaDe to set
	 */
	public void setDerivaDe(UnidadDeMedida derivaDe) {
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
