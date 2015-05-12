package servlet.articulo;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Articulo;
import service.ArticuloServiceLocal;

@WebServlet("/articulo/listar")
public class ListarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private ArticuloServiceLocal articuloService; 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Articulo> articulos = articuloService.obtenerArticulos();
		request.setAttribute("articulos", articulos);
		
		getServletContext()
			.getRequestDispatcher("/WEB-INF/jsp/articulo/index.jsp")
			.forward(request, response);
	}

}
