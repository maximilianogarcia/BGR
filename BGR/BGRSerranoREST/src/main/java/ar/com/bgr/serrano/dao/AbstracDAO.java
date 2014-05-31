package ar.com.bgr.serrano.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public abstract class AbstracDAO<T> {


	private Class<?> clasz;

	
	public AbstracDAO(Class<?> clasz) {
		this.clasz = clasz;
	}
	public AbstracDAO() {
	}
	
	public List<T> list(){
		Criteria criteria =  getSessionFactory().getCurrentSession().createCriteria(getClasz());
		return  criteria.list();
	}

	public T getById(int id){
		return  (T) getSessionFactory().getCurrentSession().get(getClasz(), id);
	}
	
	public T getByName(String name){
		Criteria criteria =  getSessionFactory().getCurrentSession().createCriteria(getClasz());
		return (T) criteria.add(Restrictions.eq("name", name));
	}

	public T saveOrUpdate(T entity){
		getSessionFactory().getCurrentSession().saveOrUpdate(entity);
		return entity;
	}
	
	public void remove(int id){
		T entity=   (T) getSessionFactory().getCurrentSession().get(getClasz(), id);
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public Class<?> getClasz() {
		return clasz;
	}

	public void setClasz(Class<?> clasz) {
		this.clasz = clasz;
	}

	public abstract SessionFactory getSessionFactory();

	public abstract void setSessionFactory(SessionFactory sessionFactory) ;
}
