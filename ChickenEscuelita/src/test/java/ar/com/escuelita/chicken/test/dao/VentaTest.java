package ar.com.escuelita.chicken.test.dao;

import java.sql.Date;

import org.junit.runner.RunWith;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.escuelita.chicken.base.enumerador.EnumModulo;
import ar.com.escuelita.chicken.persistencia.dao.IProveedorDAO;
import ar.com.escuelita.chicken.persistencia.dao.IUsuarioDAO;
import ar.com.escuelita.chicken.persistencia.dao.IVentaDAO;
import ar.com.escuelita.chicken.persistencia.modelo.ProveedorModel;
import ar.com.escuelita.chicken.persistencia.modelo.UsuarioModel;
import ar.com.escuelita.chicken.persistencia.modelo.VentaModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-context.xml"})
public class VentaTest {
	
	@Autowired
	IVentaDAO ventaDAO;
	
	@Autowired 
	IUsuarioDAO usuarioDAO;
	
	@Autowired
	IProveedorDAO proveedorDAO;
	
	private UsuarioModel crearUsuario() {
		UsuarioModel u = new UsuarioModel();
		u.setApellido("dd");
		u.setContrasenia("cc");
		u.setNombre("NN");
		u.setNombreUsuario("NU");
		return u;
	}
	
	private ProveedorModel crearProveedor() {
		ProveedorModel p = new ProveedorModel();
		p.setDireccion("CalleFalsa123");
		p.setMail("pepe@gmail.com");
		p.setNombre("Pepe");
		p.setTelefono("342423");
		return p;
	}
	
	private VentaModel crearVenta() throws Exception  {
		ProveedorModel p = crearProveedor();
		proveedorDAO.guardar(p);
		UsuarioModel u = crearUsuario();
		usuarioDAO.guardar(u);
		
		VentaModel v = new VentaModel();
		v.setCantidad(33);
		v.setFecha(new Date(1000));
		v.setPrecio(100);
		v.setProveedor(proveedorDAO.get(p.getId()));
		v.setUsuario(usuarioDAO.get(u.getId()));
		return v;
	}
	
	@Test
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED)
	public void test_InsertarYBuscarVenta() throws Exception  {
		VentaModel v = crearVenta();
		ventaDAO.guardar(v);
		
		VentaModel v2 = ventaDAO.get(v.getId());
		
		Assert.assertTrue(v2.getCantidad() == v.getCantidad());
		Assert.assertTrue(v2.getPrecio() == v.getPrecio());
		Assert.assertTrue(v2.getProveedor().getId() == v.getProveedor().getId());
		Assert.assertTrue(v2.getUsuario().getId() == v.getUsuario().getId());
		Assert.assertTrue(v2.getId() == v.getId());
	}
	
	@Test
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED)
	public void test_BorrarVenta() throws Exception {
		VentaModel v = crearVenta();
		ventaDAO.guardar(v);
		
		ventaDAO.borrar(v.getId());
		
		VentaModel p2 = ventaDAO.get(v.getId());
		
		Assert.assertTrue(p2.isBorrado());
	}
	
	@Test
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED)
	public void test_ModificarVenta() throws Exception {
		VentaModel v = crearVenta();
		
		ventaDAO.guardar(v);
		
		VentaModel v2 = ventaDAO.get(v.getId());
		
		Assert.assertTrue(v2.getPrecio() == v.getPrecio());
		
		v.setPrecio(3242342);
		
		ventaDAO.modificar(v);
		
		VentaModel v3 = ventaDAO.get(v.getId());
		
		Assert.assertTrue(v2.getPrecio() != v3.getPrecio());
	}
}
