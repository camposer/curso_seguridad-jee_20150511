package service;

import java.util.List;

import javax.ejb.Local;

import model.Articulo;

@Local
public interface ArticuloServiceLocal {
	public List<Articulo> obtenerArticulos();
}
