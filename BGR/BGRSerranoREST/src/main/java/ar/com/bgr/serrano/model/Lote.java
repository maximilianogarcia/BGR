/**
 * 
 */
package ar.com.bgr.serrano.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 
 * Descripcion:
 * 
 * {}
 *
 * @author matias
 * 
 * @since 31/05/2014
 */
//@Entity(name="lote")
//@Table(name="lote")
public class Lote {

//	@GeneratedValue
	@Id
	private int id;
	
	@Column( name = "producto_id")
	private int productoId;
	
	@Column( name = "descripcion")
	private String descripcion;
	
	@Column( name = "fechaDeElaboracion")
	private Date fechaDeElaboracion;
	
	@Column( name = "fechaDeVencimiento")
	private Date fechaDeVencimiento;

	@Column( name = "precio_compra")
	private double precio_compra;
	 
	@Column( name = "proveedor_id")
	private int proveedor_id;
	
	
	
}
