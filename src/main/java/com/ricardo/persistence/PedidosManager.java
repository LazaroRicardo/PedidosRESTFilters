package com.ricardo.persistence;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ricardo.models.Pedido;

public class PedidosManager {

	private static PedidosManager instance = null;

	private static SessionFactory sfactory;

	public static PedidosManager getInstance() {
		if (instance == null) instance = new PedidosManager();
		return instance;
	}

	private PedidosManager() {
		sfactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}

	public List<Pedido> getPedidos() {
		Session sess = sfactory.openSession();

		List<Pedido> listaPedidos = sess.createQuery("from Pedido").list();

		sess.close();
		return listaPedidos;
	}

	public boolean borrarPedido(int pid){
		boolean todoOk = false;
		
		Session sess = sfactory.openSession();
		Transaction t = sess.beginTransaction();
		Pedido paqueton= sess.get(Pedido.class, pid);
		
		sess.delete(paqueton);
		
		t.commit();
		todoOk = true;
		
		sess.close();
		return todoOk;
	
	}
}
