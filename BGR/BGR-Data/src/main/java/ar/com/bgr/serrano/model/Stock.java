package ar.com.bgr.serrano.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

@Entity
@Subselect("SELECT COUNT( * ) as stock,  p.id as id, cat.id as categoria, pr.id as productoId, "
		+ "u.name as medida, p.descripcion as presentacion, "
		+ "pa.estado as estado, "
		+ "(p.peso_neto * COUNT( * ) )/ 1000 as kgTotal, "
		+ "pr.name as producto, lote.descripcion as lote, "
		+ "lote.fechaDeElaboracion as elaboracion, "
		+ "lote.fechaDeVencimiento as vencimiento " 
		+ "FROM Presentacion p "
		+ "JOIN Paquete pa ON ( pa.presentacion_id = p.id )      "
		+ "JOIN Producto pr on (p.producto_id = pr.id)           "
    	+ "JOIN Categoria cat on ( pr.categoria_id = cat.id)     "
		+ "JOIN Lote lote on (p.lote_id = lote.id)               "
		+ "JOIN UnidadDeMedida u on (p.unidadDeMedida_id = u.id) "
		+ "GROUP BY p.id "
		)
@Synchronize({ "Presentacion", "Paquete", "Producto", "Lote", "UnidadDeMedida", "Categoria" })
public class Stock {

	@Id
	private long id;
	private String stock;
	private String medida;
	private String presentacion;
	private long kgTotal;
	private String producto;
	private Integer productoId;
	private String lote;
	private String estado;
	private Integer categoria;
	private Date elaboracion;
	private Date vencimiento;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public long getKgTotal() {
		return kgTotal;
	}

	public void setKgTotal(long kgTotal) {
		this.kgTotal = kgTotal;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getElaboracion() {
		return elaboracion;
	}

	public void setElaboracion(Date elaboracion) {
		this.elaboracion = elaboracion;
	}

	public Date getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public Integer getProductoId() {
		return productoId;
	}

	public void setProductoId(Integer productoId) {
		this.productoId = productoId;
	}



}
