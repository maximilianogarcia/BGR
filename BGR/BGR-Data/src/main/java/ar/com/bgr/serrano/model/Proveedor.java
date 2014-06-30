/**
 * 
 */
package ar.com.bgr.serrano.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

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
@Entity(name="Proveedor")
@Table(name="Proveedor")
public class Proveedor {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="name")
	private String name;		
		
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "productoProveedoresId.proveedor", cascade=CascadeType.ALL)
	private Set<ProductoProveedor> productoProveedoresId;
	
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
	 * @return the productoProveedoresId
	 */
	@JsonIgnore
	public Set<ProductoProveedor> getProductoProveedoresId() {
		return productoProveedoresId;
	}

	/**
	 * @param productoProveedoresId the productoProveedoresId to set
	 */
	@JsonIgnore
	public void setProductoProveedoresId(
			Set<ProductoProveedor> productoProveedoresId) {
		this.productoProveedoresId = productoProveedoresId;
	}
	
}
