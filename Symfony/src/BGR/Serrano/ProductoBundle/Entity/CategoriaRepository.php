<?php

namespace BGR\Serrano\ProductoBundle\Entity;

use Doctrine\ORM\EntityRepository;

/**
 * CategoriaRepository
 *
 * This class was generated by the Doctrine ORM. Add your own custom
 * repository methods below.
 */
class CategoriaRepository extends EntityRepository
{
	public function save($categoria)
    {   
        $em = $this->getEntityManager();
        $em->persist($categoria);
        $em->flush();
    }
}
