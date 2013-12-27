<?php

namespace BGR\Serrano\ProductoBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use JMS\Serializer\Annotation\Type;


class ProductoUnidadDeMedida
{

    /**
     * @var integer
     * @ORM\Column(name="producto_id", type="integer")
     * @ORM\Id
     * @Type("integer")
     */
    protected $producto_id;
    
    /**
     * @var integer
     * @ORM\Column(name="unidaddemedida_id", type="integer")
     * @ORM\Id
     * @Type("integer")
     */
    protected $unidaddemedida_id;            
        
    public function getProductoId()
    {
        return $this->producto_id;
    }
    
    public function setProductoId($unId)
    {
        $this->producto_id =$unId;
    }
    
    public function getUnidaddemedidaId()
    {
        return $this->unidaddemedida_id;
    }
    
    public function setUnidaddemedidaId($unId)
    {
        $this->unidaddemedida_id =$unId;
    }

}