package ar.com.bgr.serrano.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.dao.EOIDAO;
import ar.com.bgr.serrano.model.Eoi;

@Component
@Transactional
public class ClienteService {

	@Autowired
	EOIDAO dao;

	public Eoi save(Eoi cliente) {
		return dao.saveOrUpdateCliente(cliente);
	}

	public List<Eoi> list() {
		return dao.listClientes();
	}

	public void remove(int id) {
		dao.removeCliente(id);		
	}

	public Eoi getById(int id) {
		return dao.getClienteById(id);
	}

}
