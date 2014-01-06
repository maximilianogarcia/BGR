<?php

namespace BGR\Serrano\ProductoBundle\Entity;

use Doctrine\ORM\EntityRepository;
use Doctrine\ORM\Query\ResultSetMapping;
/**
 * PresentacionRepository
 *
 * This class was generated by the Doctrine ORM. Add your own custom
 * repository methods below.
 */
class PresentacionRepository extends EntityRepository
{
    public function save($presentacion)
    {   
        $em = $this->getEntityManager();
	    $presentacion->setProducto($em->merge($presentacion->getProducto()));
	    $presentacion->setLote($em->merge($presentacion->getLote()));
	    $presentacion->setMaterial($em->merge($presentacion->getMaterial()));
	    $presentacion->setUnidad_de_medida($em->merge($presentacion->getUnidad_de_medida()));
        $em->persist($presentacion);
        $em->flush();
    }

    public function update($presentacion)
    {   
        $em = $this->getEntityManager();
        $presentacion->setProducto($em->merge($presentacion->getProducto()));
        $presentacion->setLote($em->merge($presentacion->getLote()));
        $presentacion->setMaterial($em->merge($presentacion->getMaterial()));
        $presentacion->setUnidad_de_medida($em->merge($presentacion->getUnidad_de_medida()));
        $em->persist($em->merge($presentacion));
        $em->flush();
    }
    public function findStocksByProducto(Producto $producto)
    {
    	$em = $this->getEntityManager();
    	 
    	$rsm = new ResultSetMapping();
    	

    	$rsm->addEntityResult('BGR\Serrano\ProductoBundle\Entity\Presentacion', 's');
    	$rsm->addScalarResult('id', 'id');
    	$rsm->addScalarResult('presentacion', 'presentacion');
    	$rsm->addScalarResult('stock', 'stock');
    	$rsm->addScalarResult('producto', 'producto');
    	$rsm->addScalarResult('lote', 'lote');
    	$rsm->addScalarResult('vencimiento', 'vencimiento');
    	$rsm->addScalarResult('elaboracion', 'elaboracion');
    	$rsm->addScalarResult('medida', 'medida');
    	$rsm->addScalarResult('kgTotal', 'kgTotal');
    	 
    
    	$query = $em->createNativeQuery('
		  SELECT  
    			COUNT( * ) as stock, 
    			p.id as id,
     			u.name as medida,
     			p.descripcion as presentacion,
    			(p.peso_neto * COUNT( * ) )/ 1000 as kgTotal,
    			pr.name as producto,
    			lote.descripcion as lote,
    			lote.fechaDeElaboracion as elaboracion,
    			lote.fechaDeVencimiento as vencimiento
    			
          FROM Presentacion p
          JOIN Paquete pa ON ( pa.presentacion_id = p.id )
    	  JOIN Producto pr on (p.producto_id = pr.id)
       	  JOIN Lote lote on (p.lote_id = lote.id) 
       	  JOIN UnidadDeMedida u on (p.unidadDeMedida_id = u.id) 			
		  WHERE  p.producto_id = ? AND pa.estado= ?
          GROUP BY pa.presentacion_id',$rsm);
    	$query->setParameter(1,$producto->getId());
    
    	$result = $query->getResult(); 
    	return $result;
    }
    
    public function desactivar($presentacionId)
    {
    	 
    	$em = $this->getEntityManager();
        $p = $em->getRepository('BGRSerranoProductoBundle:Presentacion')->find($presentacionId);
    	$p->setActive(false);
    	$em->persist($p);
    	
    	$conection = $em->getConnection();
    	
	    
    	$count = $conection->executeUpdate('UPDATE Paquete SET estado = ? WHERE presentacion_id = ? AND estado = ?', 
    			array('DESACTIVADO',$presentacionId,'DISPONIBLE'));
    	
      	$em->flush();
      	return $p;
    }
    public function activar($presentacionId)
    {
    	 
    	$em = $this->getEntityManager();
        $p = $em->getRepository('BGRSerranoProductoBundle:Presentacion')->find($presentacionId);
    	$p->setActive(true);
    	$em->persist($p);
    	
    	$conection = $em->getConnection();
    	
	    
    	$count = $conection->executeUpdate('UPDATE Paquete SET estado = ? WHERE presentacion_id = ? AND estado = ?', 
    			array('DISPONIBLE',$presentacionId,'DESACTIVADO'));
    	
      	$em->flush();
      	return $p;
    }
    
    public function findActives($data)
    {
    	$em = $this->getEntityManager();
    	return $em->getRepository('BGRSerranoProductoBundle:Presentacion')->findBy(
    			array(
    					'active' => $data
    			)
    	);
    }
      
    public function findStocks()
    {
    	$em = $this->getEntityManager();
    	 
    	$rsm = new ResultSetMapping();    	

    	$rsm->addEntityResult('BGR\Serrano\ProductoBundle\Entity\Presentacion', 's');
    	$rsm->addScalarResult('id', 'id');
    	$rsm->addScalarResult('presentacion', 'presentacion');
    	$rsm->addScalarResult('stock', 'stock');
    	$rsm->addScalarResult('producto', 'producto');
    	$rsm->addScalarResult('lote', 'lote');
    	$rsm->addScalarResult('vencimiento', 'vencimiento');
    	$rsm->addScalarResult('elaboracion', 'elaboracion');
    	$rsm->addScalarResult('medida', 'medida');
    	$rsm->addScalarResult('kgTotal', 'kgTotal');
    	 
    
    	$query = $em->createNativeQuery('
		  SELECT  
    			COUNT( * ) as stock, 
    			p.id as id,
     			u.name as medida,
     			p.descripcion as presentacion,
    			(p.peso_neto * COUNT( * ) )/ 1000 as kgTotal,
    			pr.name as producto,
    			lote.descripcion as lote,
    			lote.fechaDeElaboracion as elaboracion,
    			lote.fechaDeVencimiento as vencimiento
    			
          FROM Presentacion p
          JOIN Paquete pa ON ( pa.presentacion_id = p.id )
    	  JOIN Producto pr on (p.producto_id = pr.id)
       	  JOIN Lote lote on (p.lote_id = lote.id) 
       	  JOIN UnidadDeMedida u on (p.unidadDeMedida_id = u.id) 			
          GROUP BY pa.presentacion_id',$rsm);
    
    	$result = $query->getResult(); 
    	return $result;
    }
    
    public function findStocksByCategoria(Categoria $categoria)
    {
    	$em = $this->getEntityManager();
    	 
    	$rsm = new ResultSetMapping();
    	

    	$rsm->addEntityResult('BGR\Serrano\ProductoBundle\Entity\Presentacion', 's');
    	$rsm->addScalarResult('id', 'id');
    	$rsm->addScalarResult('presentacion', 'presentacion');
    	$rsm->addScalarResult('stock', 'stock');
    	$rsm->addScalarResult('producto', 'producto');
    	$rsm->addScalarResult('lote', 'lote');
    	$rsm->addScalarResult('vencimiento', 'vencimiento');
    	$rsm->addScalarResult('elaboracion', 'elaboracion');
    	$rsm->addScalarResult('medida', 'medida');
    	$rsm->addScalarResult('kgTotal', 'kgTotal');
    	 
    
    	$query = $em->createNativeQuery('
		  SELECT  
    			COUNT( * ) as stock, 
    			p.id as id,
     			u.name as medida,
     			p.descripcion as presentacion,
    			(p.peso_neto * COUNT( * ) )/ 1000 as kgTotal,
    			pr.name as producto,
    			lote.descripcion as lote,
    			lote.fechaDeElaboracion as elaboracion,
    			lote.fechaDeVencimiento as vencimiento
    			
          FROM Presentacion p
          JOIN Paquete pa ON ( pa.presentacion_id = p.id )
    	    JOIN Producto pr on (p.producto_id = pr.id)
    	    JOIN Categoria cat on ( pr.categoria_id = cat.id)
       	 JOIN Lote lote on (p.lote_id = lote.id) 
       	 JOIN UnidadDeMedida u on (p.unidadDeMedida_id = u.id) 			
		   WHERE cat.id = ?
      GROUP BY pa.presentacion_id',$rsm);
    	$query->setParameter(1,$categoria->getId());
    
    	$result = $query->getResult(); 
    	return $result;
    }
}
