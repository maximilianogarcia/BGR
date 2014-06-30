package ar.com.bgr.serrano.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="Remanente")
@Table(name="Remanente")
public class Remanente {

    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
	@Id
	@GeneratedValue
    private Integer id;

    /**
     * @ORM\ManyToOne(targetEntity="Producto") 
     * @ORM\JoinColumn(name="producto_id", referencedColumnName="id")
     * @Type("BGR\Serrano\ProductoBundle\Entity\Producto")
     *
     */
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="producto_id")
    private Producto producto;

    /**
     * @var float
     *
     * @ORM\Column(name="cantidad", type="decimal")
 	  * @Type("double")
     */
	@Column(name="cantidad")
    private Double cantidad;
    
   /**
     * @ORM\ManyToOne(targetEntity="UnidadDeMedida") 
     * @ORM\JoinColumn(name="unidadDeMedida_id", referencedColumnName="id")
     * @Type("BGR\Serrano\ProductoBundle\Entity\UnidadDeMedida")
     */
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="unidadDeMedida_id")
    protected UnidadDeMedida unidad_de_medida;

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
	
	
}
