package ar.com.bgr.serrano.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class ProductoProveedoresId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Producto producto;
	@ManyToOne	
	private Proveedor proveedor;
	
	public ProductoProveedoresId(Producto producto, Proveedor proveedor) {
		this.producto = producto;
		this.proveedor = proveedor;
	}
	public ProductoProveedoresId() {
		super();
	}
	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}
	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	/**
	 * @return the proveedor
	 */
	public Proveedor getProveedor() {
		return proveedor;
	}
	/**
	 * @param proveedor the proveedor to set
	 */
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
		
		
}