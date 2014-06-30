/**
 * 
 */
package ar.com.bgr.serrano.model;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.IndexColumn;

import ar.com.bgr.serrano.utils.ProductoJsonDeserializer;
import ar.com.bgr.serrano.utils.ProductoJsonSerializer;


/**
 * 
 * Descripcion:
 * 
 * {mapped entity class}
 *
 * @author matias
 * 
 * @since 01/06/2014
 */
@Entity(name="Producto")
@Table(name="Producto")
@JsonDeserialize(using= ProductoJsonDeserializer.class)
@JsonSerialize(using = ProductoJsonSerializer.class)
public class Producto {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="name")
	private String name;	
	
	@Column(name="actualizador_precio")
	private Double actualizador_precio;

	@ManyToOne(fetch = FetchType.EAGER)	
	@JoinColumn(name = "categoria_id", nullable = false)
	private Categoria categoria;
	
	@JsonBackReference
	@IndexColumn(name = "id")
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "productoProveedoresId.producto", cascade=CascadeType.ALL)
	private Set<ProductoProveedor> productoProveedoresId;
	
	@IndexColumn(name = "id")
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name = "producto_unidad_de_medida", joinColumns = { 
			@JoinColumn(name = "producto_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "unidaddemedida_id", 
					nullable = false, updatable = false) })
	private Set<UnidadDeMedida> unidadesDeMedida;
	
	/**
	 * @return the unidadesDeMedida
	 */
	public Set<UnidadDeMedida> getUnidadesDeMedida() {
		return unidadesDeMedida;
	}

	/**
	 * @param unidadesDeMedida the unidadesDeMedida to set
	 */
	public void setUnidadesDeMedida(Set<UnidadDeMedida> unidadesDeMedida) {
		this.unidadesDeMedida = unidadesDeMedida;
	}

	/*
	 * Default constructor
	 */
	public Producto(){
		this.productoProveedoresId = new TreeSet<ProductoProveedor>();
	}
	
	/**
	 * @return the id
	 */
	public Integer getId() {
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

	/**
	 * @return the productoProveedor
	 */
	public Set<ProductoProveedor> getProductoProveedor() {
		return productoProveedoresId;
	}

	/**
	 * @param productoProveedor the productoProveedor to set
	 */	
	public void setProductoProveedor(Set<ProductoProveedor> productoProveedor) {
		this.productoProveedoresId = productoProveedor;
	}

	@JsonProperty("proveedores")
	public void setProveedores(List<Proveedor> list){

		for(Proveedor p:list){			
			getProductoProveedor().add(new ProductoProveedor(this,p));
		}
	}
}
