package com.elecciones.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.elecciones.model.Votante;
import com.elecciones.util.Conexion;
import com.elecciones.util.ConexionFactory;


public class VotanteDao implements GenericDao<Votante>{

	private Conexion conexion;

	private static final String INSERT_VOTANTE_SQL = "INSERT INTO votante (nombre, email, documento, tipodocumento, eleccion) VALUES (?,?,?,?, ?);";
	private static final String DELETE_VOTANTE_SQL = "DELETE FROM votante WHERE id = ?;";
	private static final String UPDATE_VOTANTE_SQL = "UPDATE votante SET nombre = ?, email=?, documento=?, tipodocumento=?, eleccion=? WHERE id = ?;";
	private static final String SELECT_VOTANTE_BY_ID = "SELECT * FROM votante WHERE id =?;";
	private static final String SELECT_ALL_VOTANTE = "SELECT * FROM votante;";
	
	
	public VotanteDao() {
		this.conexion = ConexionFactory.getConexion();
	}

	//INSERTAR
	public void insert(Votante votante) throws SQLException {
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(INSERT_VOTANTE_SQL);
			preparedStatement.setString(1, votante.getNombre());
			preparedStatement.setString(2, votante.getEmail());
			preparedStatement.setString(3, votante.getDocumento());
			preparedStatement.setInt(4, votante.getTipodocumento());
			preparedStatement.setInt(5, votante.getEleccion());
			

			System.out.println(preparedStatement);
			conexion.execute();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	// ELIMINAR  POR EL ID
	public void delete(int id) throws SQLException {
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(DELETE_VOTANTE_SQL);
			preparedStatement.setInt(1, id);

			conexion.execute();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
			
	// ACTUALIZAR 
	public void update(Votante votante) throws SQLException {
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(UPDATE_VOTANTE_SQL);
			preparedStatement.setString(1, votante.getNombre());
			preparedStatement.setString(2, votante.getEmail());
			preparedStatement.setString(3, votante.getDocumento());
			preparedStatement.setInt(4, votante.getTipodocumento());
			preparedStatement.setInt(5, votante.getEleccion());
			preparedStatement.setInt(6, votante.getId());

			conexion.execute();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
				
	// SELECCIONAR POR ID
	public Votante select(int id) {

		Votante votante = null;

		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_VOTANTE_BY_ID);
			preparedStatement.setInt(1, id);

			System.out.println(preparedStatement);

			ResultSet rs = conexion.query();

			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String email = rs.getString("email");
				String documento = rs.getString("documento");
				int tipodocumento = rs.getInt("tipodocumento");
				int eleccion = rs.getInt("eleccion");
				
				votante = new Votante(id, nombre, email, documento, tipodocumento, eleccion);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return votante;
	}

	// SELECCIONAR TODOS 
	public List<Votante> selectAll() {
		List<Votante> votante = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement( SELECT_ALL_VOTANTE);
			System.out.println(preparedStatement);

			ResultSet rs = conexion.query();

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String email = rs.getString("email");
				String documento = rs.getString("documento");
				int tipodocumento = rs.getInt("tipodocumento");
				int eleccion = rs.getInt("eleccion");
				
				votante.add(new Votante(id, nombre, email, documento, tipodocumento, eleccion));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return votante;
	}

	// METODO INFORME DE LAS EXCEPCIONES SQL
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
