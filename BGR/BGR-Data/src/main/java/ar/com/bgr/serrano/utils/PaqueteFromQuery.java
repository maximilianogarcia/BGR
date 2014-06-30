package ar.com.bgr.serrano.utils;

public class PaqueteFromQuery {

	private Integer id;
	private Integer presentacion_id;
	private String codigo;
	private String estado;
	private Double cantidad;
	private Integer unidad;
	private String nombre;
	
	public PaqueteFromQuery(){}

	/**
	 * 
	 * @param id
	 * @param presentacion_id
	 * @param codigo
	 * @param estado
	 * @param cantidad
	 * @param unidad
	 */
	public PaqueteFromQuery(Integer id, Integer presentacion_id,
			String codigo, String estado, Double cantidad, Integer unidad) {
		super();
		this.id = id;
		this.presentacion_id = presentacion_id;
		this.codigo = codigo;
		this.estado = estado;
		this.cantidad = cantidad;
		this.unidad = unidad;
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
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * @return the presentacion_id
	 */
	public Integer getPresentacion_id() {
		return presentacion_id;
	}


	/**
	 * @param presentacion_id the presentacion_id to set
	 */
	public void setPresentacion_id(Integer presentacion_id) {
		this.presentacion_id = presentacion_id;
	}


	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}


	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}


	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
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
	 * @return the unidad
	 */
	public Integer getUnidad() {
		return unidad;
	}


	/**
	 * @param unidad the unidad to set
	 */
	public void setUnidad(Integer unidad) {
		this.unidad = unidad;
	}

	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}
