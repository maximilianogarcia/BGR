<?php

namespace BGR\Serrano\ProductoBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;
use JMS\Serializer\Annotation\Type;
use JMS\Serializer\Annotation\Exclude;
/**
 * Categoria
 *
 * @ORM\Table(uniqueConstraints={@ORM\UniqueConstraint(name="search_idx", columns={"name"})})
 * @ORM\Entity(repositoryClass="BGR\Serrano\ProductoBundle\Entity\CategoriaRepository")
 */
class Categoria
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     * @Type("integer")
     */
    protected $id;

    /**
     * @var string
     *
     * @ORM\Column(name="name", type="string", length=255)
     * @Type("string")
     */
    protected $name;

    /**
     * @var string
     *
     * @ORM\Column(name="descripcion", type="string", length=255)
     * @Type("string")
     */
    protected $descripcion;

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
     * @return Categoria
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
     * Get name
     *
     * @return string 
     */
    public function getDescripcion()
    {
        return $this->descripcion;
    }


}
