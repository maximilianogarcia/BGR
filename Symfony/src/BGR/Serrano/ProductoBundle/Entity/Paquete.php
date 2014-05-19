<?php

namespace BGR\Serrano\ProductoBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use JMS\Serializer\Annotation\Type;
use JMS\Serializer\Annotation\Exclude;
/**
 * Paquete
 *
 * @ORM\Table()
 * @ORM\Entity(repositoryClass="BGR\Serrano\ProductoBundle\Entity\PaqueteRepository")
 */
class Paquete
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
     * @ORM\Column(name="codigo", type="string", length=255)
     * @Type("string")
     */
    private $codigo;

    /**
     * @var string
     *
     * @ORM\Column(name="estado", type="string", length=255)
     * @Type("string")
     */
    private $estado;


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
     * Set codigo
     *
     * @param string $codigo
     * @return Paquete
     */
    public function setCodigo($codigo)
    {
        $this->codigo = $codigo;
    
        return $this;
    }

    /**
     * Get codigo
     *
     * @return string 
     */
    public function getCodigo()
    {
        return $this->codigo;
    }

    /**
     * Set estado
     *
     * @param string $estado
     * @return Paquete
     */
    public function setEstado($estado)
    {
        $this->estado = $estado;
    
        return $this;
    }

    /**
     * Get estado
     *
     * @return string 
     */
    public function getEstado()
    {
        return $this->estado;
    }




   /**
     * @ORM\ManyToOne(targetEntity="Presentacion") 
     * @ORM\JoinColumn(name="presentacion_id", referencedColumnName="id")
     * @Type("BGR\Serrano\ProductoBundle\Entity\Presentacion")
     */
    protected $presentacion;

   /**
     * @ORM\ManyToOne(targetEntity="Mix") 
     * @ORM\JoinColumn(name="mix_id", referencedColumnName="id")
     * @Type("BGR\Serrano\ProductoBundle\Entity\Mix")
     */
    protected $mix;

    public function setPresentacion(Presentacion $presentacion)
    {
        $this->presentacion = $presentacion;
    }

    public function getPresentacion()
    {
        return $this->presentacion;
    }
	public function getMix() {
		return $this->mix;
	}
	public function setMix($mix) {
		$this->mix = $mix;
		return $this;
	}
	

}