<?php

namespace BGR\Serrano\ProductoBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use JMS\Serializer\Annotation\Type;
use JMS\Serializer\Annotation\Exclude;

/**
 * Proveedor
 *
 * @ORM\Table()
 * @ORM\Entity(repositoryClass="BGR\Serrano\ProductoBundle\Entity\ProveedorRepository")
 */
class Proveedor
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     * @Type("integer")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="name", type="string", length=255)
     * @Type("string")
     */
    private $name;

    /**
     * @ORM\ManyToMany(targetEntity="Producto", mappedBy="unidad_de_medidas")
     * @Type("ArrayCollection<BGR\Serrano\ProductoBundle\Entity\Producto>")
     * @Exclude
     */
    private $productos;
    
    /**
     * Get id
     *
     * @return integer 
     */
    public function getId()
    {
        return $this->id;
        
    }

    /**
     * Set name
     *
     * @param string $name
     * @return Proveedor
     */
    public function setName($name)
    {
        $this->name = $name;
    
        return $this;
    }

    /**
     * Get name
     *
     * @return string 
     */
    public function getName()
    {
        return $this->name;
    }
	public function getProductos() {
		return $this->productos;
	}
	public function setProductos($productos) {
		$this->productos = $productos;
		return $this;
	}
	
}
