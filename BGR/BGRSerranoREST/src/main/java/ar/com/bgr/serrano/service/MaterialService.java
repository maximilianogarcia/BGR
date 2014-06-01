/**
 * 
 */
package ar.com.bgr.serrano.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.dao.MaterialDao;
import ar.com.bgr.serrano.model.Material;

/**
 * 
 * Descripcion:
 * 
 * {}
 *
 * @author matias
 * 
 * @since 01/06/2014
 */
@Component
@Transactional
public class MaterialService {
	
	@Autowired
	MaterialDao dao;
	
	public Material save(Material material) {
		return dao.saveOrUpdate(material);
	}

	public List<Material> list() {
		return dao.list();
	}

	public void remove(int id) {
		dao.remove(id);		
	}
	
	public Material getById(int id) {
		return dao.getById(id);
	}
}
