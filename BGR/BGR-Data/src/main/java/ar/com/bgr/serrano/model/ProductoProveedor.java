package ar.com.bgr.serrano.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@Entity(name = "Producto_proveedor")
@Table(name = "Producto_proveedor")
@AssociationOverrides({
@AssociationOverride(name = "productoProveedoresId.proveedor",
joinColumns = @JoinColumn(name = "proveedor_id")),
@AssociationOverride(name = "productoProveedoresId.producto",
joinColumns = @JoinColumn(name = "producto_id")) })
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductoProveedor implements Comparable<ProductoProveedor>{
	 	 	 	
	@EmbeddedId
	@JsonIgnore
	private ProductoProveedoresId productoProveedoresId;
	
	@Column(name="cantidad")
	private Double cantidad;
	
	@Column(name="precio_reposicion")
	private Double precio_reposicion;
	
	public ProductoProveedor(){
		this.productoProveedoresId =new ProductoProveedoresId();
	}
	
	public ProductoProveedor(Producto producto, Proveedor proveedor) {
		super();
		productoProveedoresId = new ProductoProveedoresId(producto,proveedor);		
	}

	/**
	 * @return the productoProveedoresId
	 */
	@JsonIgnore
	public ProductoProveedoresId getProductoProveedoresId() {
		return productoProveedoresId;
	}

	/**
	 * @param productoProveedoresId the productoProveedoresId to set
	 */
	@JsonIgnore
	public void setProductoProveedoresId(ProductoProveedoresId productoProveedoresId) {
		this.productoProveedoresId = productoProveedoresId;
	}

	/**
	 * @return the cantidad
	 */
	public Double getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the precio_reposicion
	 */
	public Double getPrecio_reposicion() {
		return precio_reposicion;
	}

	/**
	 * @param precio_reposicion the precio_reposicion to set
	 */
	public void setPrecio_reposicion(Double precio_reposicion) {
		this.precio_reposicion = precio_reposicion;
	}
	
	/**
	 * @return
	 */
	public Proveedor getProveedor(){
		return this.productoProveedoresId.getProveedor();
	}
	@JsonProperty("proveedor")
	public void setProveedor(Proveedor proveedor){
		this.productoProveedoresId.setProveedor(proveedor);
	}
	
	/**
	 * @return
	 */
	public Producto getProducto(){
		return this.productoProveedoresId.getProducto();
	}

	@JsonProperty("producto")
	public void setProducto(Producto producto){
		this.productoProveedoresId.setProducto(producto);
	}

	@Override
	public int compareTo(ProductoProveedor p) {
		try {
			if(this.getProducto() == null && p.getProducto() == null ){
				if(this.getProveedor() == null && p.getProveedor() == null ){
					return 0;
				}
			}
			
			if ( this.getProducto().getId() == p.getProducto().getId() ){
				if (  this.getProveedor().getId() == p.getProveedor().getId() ){
					return 0;
				}
			}
			
		} catch (Exception e) {
			return -1;
		}
		return -1;
	}

}
