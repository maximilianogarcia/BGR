package ar.com.bgr.serrano.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Contacto")
@Table(name = "Contacto")
public class Contacto {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "name", nullable=false)
	private String name;

	@Column(name = "webSite")
	private String webSite;

	@Column(name = "blog")
	private String blog;

	@Column(name = "telefonoMovil", nullable=false)
	private String telefonoMovil;

	@Column(name = "telefonoFijo", nullable=false)
	private String telefonoFijo;
	
	@Column(name = "radio")
	private String radio;

	@Column(name = "fax")
	private String fax;

	@Column(name = "diaHoraContacto", nullable=false)
	private String diaHoraContacto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUCURSAL_ID", nullable = false)
	private Sucursal sucursal;
	
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

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public String getTelefonoMovil() {
		return telefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getDiaHoraContacto() {
		return diaHoraContacto;
	}

	public void setDiaHoraContacto(String diaHoraContacto) {
		this.diaHoraContacto = diaHoraContacto;
	}

	public String getTelefonoFijo() {
		return telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

}
