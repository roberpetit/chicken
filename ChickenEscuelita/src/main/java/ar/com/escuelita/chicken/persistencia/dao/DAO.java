package ar.com.escuelita.chicken.persistencia.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.escuelita.chicken.persistencia.dao.util.QueryParametrosUtil;

public class DAO {

	@Autowired
	protected SessionFactory sessionFactory;

	public List buscarUsandoQueryConParametros(QueryParametrosUtil qp) {

		Session session = sessionFactory.openSession();
		List list = session.createQuery(qp.formatHQLtoJPQL()).list();
		return list;
	}
	
	protected String obtenerOperadorBusqueda(String query) {
		String operador = " where ";
		
		if (!query.isEmpty()) {
			operador = " and ";  
		}
		return operador;
	}
}
