<?php

namespace BGR\Serrano\ProductoBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * UnidadDeMedida
 *
 * @ORM\Table()
 * @ORM\Entity(repositoryClass="BGR\Serrano\ProductoBundle\Entity\UnidadDeMedidaRepository")
 */
class UnidadDeMedida
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    protected $id;

    /**
     * @var string
     *
     * @ORM\Column(name="descripcion", type="string", length=50)
     */
    protected $descripcion;

    /**
     * @var float
     *
     * @ORM\Column(name="equivalencia", type="float")
     */
    protected $equivalencia;


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
     * Set descripcion
     *
     * @param string $descripcion
     * @return UnidadDeMedida
     */
    public function setDescripcion($descripcion)
    {
        $this->descripcion = $descripcion;
    
        return $this;
    }

    /**
     * Get descripcion
     *
     * @return string 
     */
    public function getDescripcion()
    {
        return $this->descripcion;
    }

    /**
     * Set equivalencia
     *
     * @param float $equivalencia
     * @return UnidadDeMedida
     */
    public function setEquivalencia($equivalencia)
    {
        $this->equivalencia = $equivalencia;
    
        return $this;
    }

    /**
     * Get equivalencia
     *
     * @return float 
     */
    public function getEquivalencia()
    {
        return $this->equivalencia;
    }
}
