package ar.com.bgr.serrano.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;

/**
 * 
 * @author matias
 *
 */
@Entity(name="Presentacion")
@Table(name="Presentacion")
public class Presentacion {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="descripcion")
	private String descripcion;	
	
	@Column(name="active")
	private Boolean active;

	@Column(name="date_create")
	private Date date_create;

	@Column(name="date_update")
	private Date date_update;

	@Column(name="fraccionable")
	private Boolean fraccionable;

	@Column(name="peso_neto")
    private Double peso_neto;
	
	@Column(name="peso_escurrido")
    private Double peso_escurrido;

	@Column(name="cantidad_paquetes")
    private Integer cantidad_paquetes;
    
	@Column(name="cantidad")
    private Double cantidad; 
    
    @Column(name="message")
    private String message;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="material_id")
    private Material material;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="proveedor_id")
    private Proveedor proveedor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="lote_id")
    private Lote lote;

    @ManyToOne
    @JoinColumn(name="unidadDeMedida_id")
    private UnidadDeMedida unidad_de_medida;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="producto_id")
    private Producto producto;
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
    @JoinColumn(name="presentacion_id")
    @IndexColumn(name="id")
    private Set<Paquete> paquetes;

    public Presentacion(){}
    
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
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}


	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}


	/**
	 * @return the date_create
	 */
	public Date getDate_create() {
		return date_create;
	}


	/**
	 * @param date_create the date_create to set
	 */
	public void setDate_create(Date date_create) {
		this.date_create = date_create;
	}


	/**
	 * @return the date_update
	 */
	public Date getDate_update() {
		return date_update;
	}


	/**
	 * @param date_update the date_update to set
	 */
	public void setDate_update(Date date_update) {
		this.date_update = date_update;
	}


	/**
	 * @return the fraccionable
	 */
	public Boolean getFraccionable() {
		return fraccionable;
	}


	/**
	 * @param fraccionable the fraccionable to set
	 */
	public void setFraccionable(Boolean fraccionable) {
		this.fraccionable = fraccionable;
	}


	/**
	 * @return the peso_neto
	 */
	public Double getPeso_neto() {
		return peso_neto;
	}


	/**
	 * @param peso_neto the peso_neto to set
	 */
	public void setPeso_neto(Double peso_neto) {
		this.peso_neto = peso_neto;
	}


	/**
	 * @return the peso_escurrido
	 */
	public Double getPeso_escurrido() {
		return peso_escurrido;
	}


	/**
	 * @param peso_escurrido the peso_escurrido to set
	 */
	public void setPeso_escurrido(Double peso_escurrido) {
		this.peso_escurrido = peso_escurrido;
	}


	/**
	 * @return the cantidad_paquetes
	 */
	public Integer getCantidad_paquetes() {
		return cantidad_paquetes;
	}


	/**
	 * @param cantidad_paquetes the cantidad_paquetes to set
	 */
	public void setCantidad_paquetes(Integer cantidad_paquetes) {
		this.cantidad_paquetes = cantidad_paquetes;
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
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}


	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}


	/**
	 * @param material the material to set
	 */
	public void setMaterial(Material material) {
		this.material = material;
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


	/**
	 * @return the lote
	 */
	public Lote getLote() {
		return lote;
	}


	/**
	 * @param lote the lote to set
	 */
	public void setLote(Lote lote) {
		this.lote = lote;
	}


	/**
	 * @return the unidad_de_medida
	 */
	public UnidadDeMedida getUnidad_de_medida() {
		return unidad_de_medida;
	}


	/**
	 * @param unidad_de_medida the unidad_de_medida to set
	 */
	public void setUnidad_de_medida(UnidadDeMedida unidad_de_medida) {
		this.unidad_de_medida = unidad_de_medida;
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
	 * @return the paquete
	 */
	public Set<Paquete> getPaquetes() {
		return paquetes;
	}

	/**
	 * @param paquete the paquete to set
	 */
	public void setPaquetes(Set<Paquete> paquetes) {
		this.paquetes = paquetes;
	}
    
    
    
}
