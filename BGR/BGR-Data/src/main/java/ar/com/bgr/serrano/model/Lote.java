/**
 * 
 */
package ar.com.bgr.serrano.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import ar.com.bgr.serrano.utils.DateJsonSerializer;

/**
 * 
 * Descripcion:
 * 
 * {}
 *
 * @author matias
 * 
 * @since 31/05/2014
 */
@Entity(name="Lote")
@Table(name="Lote")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lote {

	@GeneratedValue
	@Id
	private Integer id;
	
	@Column( name = "producto_id")
	private int productoId;
	
	@Column( name = "descripcion")
	private String descripcion;
	
	@Column( name = "fechaDeElaboracion")
	private Date fechaDeElaboracion;

	@Column( name = "fechaDeVencimiento")
	private Date fechaDeVencimiento;

	@Column( name = "precio_compra")
	private double precio_compra;
	 
	@Column( name = "proveedor_id")
	private int proveedor_id;
	
	
	public Lote(){}

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
	 * @return the productoId
	 */
	public int getProductoId() {
		return productoId;
	}

	/**
	 * @param productoId the productoId to set
	 */
	public void setProductoId(int productoId) {
		this.productoId = productoId;
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
	 * @return the fechaDeElaboracion
	 */
	@JsonSerialize(using = DateJsonSerializer.class)
	public Date getFechaDeElaboracion() {
		return fechaDeElaboracion;
	}

	/**
	 * @param fechaDeElaboracion the fechaDeElaboracion to set
	 */
	public void setFechaDeElaboracion(Date fechaDeElaboracion) {
		this.fechaDeElaboracion = fechaDeElaboracion;
	}

	/**
	 * @return the fechaDeVencimiento
	 */
	@JsonSerialize(using = DateJsonSerializer.class)
	public Date getFechaDeVencimiento() {
		return fechaDeVencimiento;
	}

	/**
	 * @param fechaDeVencimiento the fechaDeVencimiento to set
	 */
	public void setFechaDeVencimiento(Date fechaDeVencimiento) {
		this.fechaDeVencimiento = fechaDeVencimiento;
	}

	/**
	 * @return the precio_compra
	 */
	public double getPrecio_compra() {
		return precio_compra;
	}

	/**
	 * @param precio_compra the precio_compra to set
	 */
	public void setPrecio_compra(double precio_compra) {
		this.precio_compra = precio_compra;
	}

	/**
	 * @return the proveedor_id
	 */
	public int getProveedor_id() {
		return proveedor_id;
	}

	/**
	 * @param proveedor_id the proveedor_id to set
	 */
	public void setProveedor_id(int proveedor_id) {
		this.proveedor_id = proveedor_id;
	}
	
	
	
}
