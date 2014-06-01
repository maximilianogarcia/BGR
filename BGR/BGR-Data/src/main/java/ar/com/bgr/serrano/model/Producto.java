/**
 * 
 */
package ar.com.bgr.serrano.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Entity(name="producto")
@Table(name="producto")
public class Producto {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="name")
	private String name;	
	
	@Column(name="actualizador_precio")
	private Double actualizador_precio;

	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name = "categoria_id", nullable = false)
	private Categoria categoria;

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
	 * @return the actualizador_precio
	 */
	public Double getActualizador_precio() {
		return actualizador_precio;
	}

	/**
	 * @param actualizador_precio the actualizador_precio to set
	 */
	public void setActualizador_precio(Double actualizador_precio) {
		this.actualizador_precio = actualizador_precio;
	}

	/**
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
