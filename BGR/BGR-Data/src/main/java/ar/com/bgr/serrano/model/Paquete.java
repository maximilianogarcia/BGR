package ar.com.bgr.serrano.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity(name="Paquete")
@Table(name="Paquete")
public class Paquete {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="codigo")
	private String codigo;
	
	@Column(name="estado")
	private String estado;
	
    @Column(name="presentacion_id")
    private Integer presentacion_id;    
	
    @Column(name="mix_id")
    private Integer mix_id; 
    
	public Paquete(){}
	
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
	 * @return the mix_id
	 */
	public Integer getMix_id() {
		return mix_id;
	}

	/**
	 * @param mix_id the mix_id to set
	 */
	public void setMix_id(Integer mix_id) {
		this.mix_id = mix_id;
	}
    
	
}
