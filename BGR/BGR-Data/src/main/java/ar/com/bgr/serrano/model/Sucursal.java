package ar.com.bgr.serrano.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity(name = "Sucursal")
@Table(name = "Sucursal")
@JsonIgnoreProperties(value={"contactos"})
public class Sucursal {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "name", nullable=false)
	private String name;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "observacion")
	private String observacion;
	
	@Column(name = "calle", nullable=false)
	private String calle;

	@Column(name = "numero", nullable=false)
	private String numero;
	
	@Column(name = "piso")
	private String piso;

	@Column(name = "pais",nullable=false)
	private String pais;

	@Column(name = "provincia", nullable=false)
	private String provincia;

	@Column(name = "localidad", nullable=false)
	private String localidad;

	@Column(name = "zona", nullable=false)
	private String zona;

	@Column(name = "codigoPostal", nullable=false)
	private String codigoPostal;

	@Column(name = "observacionDireccion", nullable=false)
	private String observacionDireccion;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "eoi_id", nullable = false)
	private Eoi eoi;
	
	//@OneToMany(fetch = FetchType.EAGER, mappedBy = "sucursal")
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "contacto_sucursal",  joinColumns = { 
			@JoinColumn(name = "CONTACTO_ID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "SUCURSAL_ID", 
					nullable = false, updatable = false) })
	private List<Contacto> contactos;
	
	public List<Contacto> getContactos() {
		return this.contactos;
	}
 
	public void setContactos(List<Contacto> contactos) {
		 this.contactos = contactos;
	}
	
	
	
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getObservacionDireccion() {
		return observacionDireccion;
	}

	public void setObservacionDireccion(String observacionDireccion) {
		this.observacionDireccion = observacionDireccion;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public Eoi getEoi() {
		return eoi;
	}

	public void setEoi(Eoi eoi) {
		this.eoi = eoi;
	}


}
