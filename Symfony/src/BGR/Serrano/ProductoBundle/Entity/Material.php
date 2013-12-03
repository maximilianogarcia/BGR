<?php

namespace BGR\Serrano\ProductoBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use JMS\Serializer\Annotation\Type;
use JMS\Serializer\Annotation\Exclude;
/**
 * Material
 *
 * @ORM\Table()
 * @ORM\Entity(repositoryClass="BGR\Serrano\ProductoBundle\Entity\MaterialRepository")
 */
class Material
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="name", type="string", length=255)
     */
    private $name;

    /**
     * @var string
     *
     * @ORM\Column(name="descripcion", type="string", length=255)
     */
    private $descripcion;

    /**
     * @var float
     *
     * @ORM\Column(name="peso", type="decimal")
     */
    private $peso;

    /**
     * @var float
     *
     * @ORM\Column(name="alto", type="decimal")
     */
    private $alto;

    /**
     * @var float
     *
     * @ORM\Column(name="ancho", type="decimal")
     */
    private $ancho;

    /**
     * @var float
     *
     * @ORM\Column(name="largo", type="decimal")
     */
    private $largo;


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
     * @return Material
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

    /**
     * Set descripcion
     *
     * @param string $descripcion
     * @return Material
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
     * Set peso
     *
     * @param float $peso
     * @return Material
     */
    public function setPeso($peso)
    {
        $this->peso = $peso;
    
        return $this;
    }

    /**
     * Get peso
     *
     * @return float 
     */
    public function getPeso()
    {
        return $this->peso;
    }

    /**
     * Set alto
     *
     * @param float $alto
     * @return Material
     */
    public function setAlto($alto)
    {
        $this->alto = $alto;
    
        return $this;
    }

    /**
     * Get alto
     *
     * @return float 
     */
    public function getAlto()
    {
        return $this->alto;
    }

    /**
     * Set ancho
     *
     * @param float $ancho
     * @return Material
     */
    public function setAncho($ancho)
    {
        $this->ancho = $ancho;
    
        return $this;
    }

    /**
     * Get ancho
     *
     * @return float 
     */
    public function getAncho()
    {
        return $this->ancho;
    }

    /**
     * Set largo
     *
     * @param float $largo
     * @return Material
     */
    public function setLargo($largo)
    {
        $this->largo = $largo;
    
        return $this;
    }

    /**
     * Get largo
     *
     * @return float 
     */
    public function getLargo()
    {
        return $this->largo;
    }
}
