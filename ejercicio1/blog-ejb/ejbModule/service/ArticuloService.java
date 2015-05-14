package service;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Articulo;

/**
 * Session Bean implementation class ArticuloService
 */
@Stateless
@LocalBean
@DeclareRoles({ "ADMIN", "USER" })
public class ArticuloService implements ArticuloServiceLocal {
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	@RolesAllowed("USER")
	public List<Articulo> obtenerArticulos() {
		return entityManager
				.createNamedQuery("Articulo.findAll")
				.getResultList();
	}

	
}
