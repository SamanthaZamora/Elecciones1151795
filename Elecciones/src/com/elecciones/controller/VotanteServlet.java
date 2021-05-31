package com.elecciones.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elecciones.dao.EleccionDao;
import com.elecciones.dao.EstamentoDao;
import com.elecciones.dao.GenericDao;
import com.elecciones.dao.TipoDocumentoDao;
import com.elecciones.dao.VotanteDao;
import com.elecciones.model.Eleccion;
import com.elecciones.model.Estamento;
import com.elecciones.model.TipoDocumento;
import com.elecciones.model.Votante;

@WebServlet("/")
public class VotanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private GenericDao newvotante;
	private GenericDao newEstamento;
	private GenericDao newTipo;
	private GenericDao newEleccion;
  
    public VotanteServlet() {
        super();      
    }

    public void init(ServletConfig config) throws ServletException {
		this.newvotante = new VotanteDao();
		this.newEstamento = new EstamentoDao();
		this.newTipo = new TipoDocumentoDao();
		this.newEleccion = new EleccionDao();
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

		System.out.println(action);

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insert(request, response);
				break;
			case "/delete":
				delete(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				update(request, response);
				break;
			default:
				list(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("votante.jsp");

		List<Estamento> e = newEstamento.selectAll();
		request.setAttribute("e", e);
		
		List<TipoDocumento> tipo = newTipo.selectAll();
		request.setAttribute("tipo", tipo);
		
		List<Eleccion> elec = newEleccion.selectAll();
		request.setAttribute("elec", elec);
		
		dispatcher.forward(request, response);
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {

		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String documento = request.getParameter("documento");
		int tipodocumento = Integer.parseInt(request.getParameter("tipodocumento"));
		int eleccion = Integer.parseInt(request.getParameter("eleccion"));

		Votante votante = new Votante(nombre, email, documento, tipodocumento, eleccion);

		newvotante.insert(votante);
		response.sendRedirect("list");
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)	throws SQLException, ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		Votante votante =  (Votante) newvotante.select(id);
		
		//*****
		List<Estamento> e = newEstamento.selectAll();
		request.setAttribute("estamento", e);
		
		List<TipoDocumento> tipo = newTipo.selectAll();
		request.setAttribute("tipoDocumento", tipo);
		
		List<Eleccion> elec = newEleccion.selectAll();
		request.setAttribute("eleccion", elec);
		
		request.setAttribute("Votante", votante);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("votante.jsp");		
		dispatcher.forward(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String documento = request.getParameter("documento");
		int tipodocumento = Integer.parseInt(request.getParameter("tipodocumento"));
		int eleccion = Integer.parseInt(request.getParameter("eleccion"));

		Votante votante = new Votante(nombre, email, documento, tipodocumento, eleccion);

		newvotante.update(votante);
		response.sendRedirect("list");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		newvotante.delete(id);
		response.sendRedirect("list");
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
		
		List<Votante> listV = newvotante.selectAll();

		request.setAttribute("listV", listV);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("listVotante.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
