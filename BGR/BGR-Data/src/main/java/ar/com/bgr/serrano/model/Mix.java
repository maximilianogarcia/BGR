package ar.com.bgr.serrano.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="Mix")
@Table(name="Mix")
public class Mix {
	
	@Id
    @GeneratedValue
    private Integer id;

    @Column(name="name")  
    private String name;

    @Column(name="descripcion")  
    private String descripcion;

    @Column(name="vencimiento")  
    private Date vencimiento;
        
    @ManyToOne
    @JoinColumn(name="unidad_de_medida_id")
    private UnidadDeMedida unidad_de_medida;

	@ManyToOne(fetch = FetchType.EAGER)	
	@JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

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
	 * @return the vencimiento
	 */
	public Date getVencimiento() {
		return vencimiento;
	}

	/**
	 * @param vencimiento the vencimiento to set
	 */
	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
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
