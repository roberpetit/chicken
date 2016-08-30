package ar.com.escuelita.chicken.persistencia.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import ar.com.escuelita.chicken.persistencia.dao.DAO;
import ar.com.escuelita.chicken.persistencia.dao.IMovimientoDAO;
import ar.com.escuelita.chicken.persistencia.dao.util.QueryParametrosUtil;
import ar.com.escuelita.chicken.persistencia.modelo.MovimientoModel;
import ar.com.escuelita.chicken.presentacion.filtro.MovimientoFiltro;

public class MovimientoDAOImpl extends DAO implements IMovimientoDAO {
	
	@Transactional
	public MovimientoModel get(long id) {
		Session s = sessionFactory.openSession();
		MovimientoModel e = s.get(MovimientoModel.class, id);
		s.close();
		return e;
	}

	public List<MovimientoModel> listar() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<MovimientoModel> lista = session.createQuery("from MovimientoModel").list();
		session.close();
		return lista;
	}

	@Transactional
	public void guardar(MovimientoModel movimientoModel) {
		Session s = sessionFactory.openSession();
		Transaction tx = s.beginTransaction();
		s.save(movimientoModel);
		tx.commit();
		s.close();
		
	}

	@Transactional
	public void modificar(MovimientoModel movimientoModel) {
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		s.update(movimientoModel);
		s.getTransaction().commit();
		s.close();
	}

	@Transactional
	public void borrar(long id) {
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		s.delete(s.get(MovimientoModel.class,id));
		s.getTransaction().commit();
		s.close();
	}
	
	@Override
	public List<MovimientoModel> listar(MovimientoFiltro movimientoFiltro) {
		String query = "select mov from MovimientoModel as mov" 
				+ " join mov.gallinero as g"
				+ " join g.usuario as u";
		
		QueryParametrosUtil qp = generarConsulta(query, movimientoFiltro);
		List<MovimientoModel> list = (List<MovimientoModel>) buscarUsandoQueryConParametros(qp);
		return list;
	}
	
	private QueryParametrosUtil generarConsulta(String query, MovimientoFiltro filtro){
		QueryParametrosUtil qp = new QueryParametrosUtil();
		
		String str = "";
		
		/*   ID PRODUCTOR   */
		if (filtro.getProductorId() != -1) {
//			str += " where u.id=?";
//			qp.addParametro(new Long(filtro.getProductorId()));
			str += " where u.id=" + filtro.getProductorId();
		}
		
		/*   CANTIDAD   */
		if (filtro.getCantidad() != -1) {
//			str += obtenerOperadorBusqueda(str) + " mov.cantidad=?";
//			qp.addParametro(filtro.getCantidad());
			str += obtenerOperadorBusqueda(str) + " mov.cantidad=" + filtro.getCantidad();
		}
		
		/*   FECHA   */
		if (filtro.getFecha() != null) {
//			str += obtenerOperadorBusqueda(str) + " mov.fecha=?";
//			qp.addParametro(filtro.getFecha());
			str += obtenerOperadorBusqueda(str) + " mov.fecha=" + filtro.getFecha();
		}
		
		qp.setSql(query + str);
		return qp;
	}
}
