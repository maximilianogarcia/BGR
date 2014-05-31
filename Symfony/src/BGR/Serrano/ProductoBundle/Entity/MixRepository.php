<?php

namespace BGR\Serrano\ProductoBundle\Entity;

use Doctrine\ORM\EntityRepository;

/**
 * MixRepository
 *
 * This class was generated by the Doctrine ORM. Add your own custom
 * repository methods below.
 */
class MixRepository extends EntityRepository
{
	
	public function save($mix)
	{
		$em = $this->getEntityManager();
		$em->persist($mix);
		$em->flush();
		return $mix;
	}
	

	public function desactivar($mixId,$message)
	{
	
		$em = $this->getEntityManager();
		$p = $em->getRepository('BGRSerranoProductoBundle:Mix')->find($mixId);
		$p->setActive(false);
		$p->setMessage($message);
		$em->persist($p);
		 
		$conection = $em->getConnection();
		 
		$count = $conection->executeUpdate('UPDATE Paquete SET estado = ? WHERE mix_id = ? AND estado = ?',
				array('DESACTIVADO',$mixId,'DISPONIBLE'));
		 
		$em->flush();
		return $p;
	}
}
