package ar.com.bgr.serrano.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity(name = "EOI")
@Table(name = "EOI")
@JsonIgnoreProperties({"contactos"})
public class Eoi {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "name", nullable=false)
	private String name;
	
	//tipo (cliente/proveedor)
	@Column(name = "type", nullable=false)
	private String type;

	@Column(name = "razonSocial")
	private String razonSocial;

	@Column(name = "cuit")
	private String cuit;
	

	@Column(name = "condicionImpositiva", nullable=false)
	private String condicionImpositiva;

	@Column(name = "ingresosBrutos", nullable=false)
	private String ingresosBrutos;
	
	@Column(name = "observaciones")
	private String observaciones;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getCondicionImpositiva() {
		return condicionImpositiva;
	}

	public void setCondicionImpositiva(String condicionImpositiva) {
		this.condicionImpositiva = condicionImpositiva;
	}

	public String getIngresosBrutos() {
		return ingresosBrutos;
	}

	public void setIngresosBrutos(String ingresosBrutos) {
		this.ingresosBrutos = ingresosBrutos;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
 

	public String getCuit() {
		return cuit;
	}
	
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
}
