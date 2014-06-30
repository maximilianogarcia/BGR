package ar.com.bgr.serrano.integration.test;

import java.util.TreeSet;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.model.Lote;
import ar.com.bgr.serrano.model.Producto;
import ar.com.bgr.serrano.model.ProductoProveedor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@Transactional
public class ProductoProveedorTest {

	@Autowired
    private SessionFactory sessionFactory;
	
	@Test
	@Rollback(false)
	public void testRemove(){
		Producto p = (Producto) getCurrentSession().get(Producto.class, 3);
		
		Query query = getCurrentSession().createQuery("delete producto_proveedor where proveedor_id = :proveedor_id and producto_id = :producto_id");
		query.setParameter("proveedor_id", 2);
		query.setParameter("producto_id", 3);
		query.executeUpdate();
		
		System.out.println(p.getName());
	}
	
	@Test
	public void testLalala(){
		Criteria criteria =  getCurrentSession().createCriteria(Producto.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.eq("categoria.id", 1));
		criteria.list();
	}
	
	@Test
	//@Rollback(false)
	public void testRemoveProduct(){
		Producto persisted =   (Producto) getCurrentSession().get(Producto.class, 5);
		Producto producto = new Producto();
		producto.setProductoProveedor(new TreeSet<ProductoProveedor>());
		removeRelation(producto, persisted);

		getCurrentSession().merge(persisted);
		getCurrentSession().delete(persisted);
	}
	
	@Test
	public void testLote(){
		Criteria criteria =  getCurrentSession().createCriteria(Lote.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.eq("productoId", 4));
		criteria.list();
	}
	
	
	private void removeRelation(Producto producto, Producto persisted) {
		for(ProductoProveedor pp : persisted.getProductoProveedor()){
			
			Boolean found = Boolean.FALSE;
			for(ProductoProveedor ppnp : producto.getProductoProveedor()){
				if(ppnp.getProveedor().getId() == pp.getProveedor().getId()){
					found = true;
				}
			}
			if(!found){				
				Query query = getCurrentSession().createQuery("delete producto_proveedor where proveedor_id = :proveedor_id and producto_id = :producto_id");
				query.setParameter("proveedor_id", pp.getProveedor().getId());
				query.setParameter("producto_id", pp.getProducto().getId());
				query.executeUpdate();
			}
		}
	}
	
	protected final  Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
}
