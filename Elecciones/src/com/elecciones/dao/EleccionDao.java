package com.elecciones.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.elecciones.model.Eleccion;
import com.elecciones.util.Conexion;
import com.elecciones.util.ConexionFactory;

public class EleccionDao implements GenericDao<Eleccion> {

	private Conexion conexion;

	private static final String INSERT_ELECCION_SQL = "INSERT INTO eleccion (nombre, fechainicio, fechainicio, cargo) VALUES (?,?,?, ?);";
	private static final String DELETE_ELECCION_SQL = "DELETE FROM eleccion WHERE id = ?;";
	private static final String UPDATE_ELECCION_SQL = "UPDATE eleccion SET nombre = ?, fechainicio=?, fechainicio=?, cargo=? WHERE id = ?;";
	private static final String SELECT_ELECCION_BY_ID = "SELECT * FROM eleccion WHERE id =?;";
	private static final String SELECT_ALL_ELECCION = "SELECT * FROM eleccion;";
	
	
	public EleccionDao() {
		this.conexion = ConexionFactory.getConexion();
	}

	//INSERTAR
	public void insert(Eleccion eleccion) throws SQLException {
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(INSERT_ELECCION_SQL);
			preparedStatement.setString(1, eleccion.getNombre());
			preparedStatement.setDate(2,   (Date) eleccion.getFechainicio());
			preparedStatement.setDate(3,  (Date)  eleccion.getFechafin());
			preparedStatement.setString(4, eleccion.getCargo());
					

			System.out.println(preparedStatement);
			conexion.execute();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	// ELIMINAR  POR EL ID
	public void delete(int id) throws SQLException {
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(DELETE_ELECCION_SQL);
			preparedStatement.setInt(1, id);

			conexion.execute();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
			
	// ACTUALIZAR 
	public void update(Eleccion eleccion) throws SQLException {
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(UPDATE_ELECCION_SQL);
			preparedStatement.setString(1, eleccion.getNombre());
			preparedStatement.setDate(2,   (Date) eleccion.getFechainicio());
			preparedStatement.setDate(3,  (Date)  eleccion.getFechafin());
			preparedStatement.setString(4, eleccion.getCargo());
			preparedStatement.setInt(5, eleccion.getId());

			conexion.execute();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
				
	// SELECCIONAR POR ID
	public Eleccion select(int id) {

		Eleccion eleccion = null;

		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_ELECCION_BY_ID);
			preparedStatement.setInt(1, id);

			System.out.println(preparedStatement);

			ResultSet rs = conexion.query();

			while (rs.next()) {
				String nombre = rs.getString("nombre");
				Date fechainicio = rs.getDate("fechainicio");
				Date fechafin = rs.getDate("fechafin");
				String cargo = rs.getString("cargo");
				
				eleccion = new Eleccion(id, nombre, fechainicio, fechafin, cargo);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return eleccion;
	}

	// SELECCIONAR TODOS 
	public List<Eleccion> selectAll() {
		List<Eleccion> ELECCION = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement( SELECT_ALL_ELECCION);
			System.out.println(preparedStatement);

			ResultSet rs = conexion.query();

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				Date fechainicio = rs.getDate("fechainicio");
				Date fechafin = rs.getDate("fechafin");
				String cargo = rs.getString("cargo");
				
				ELECCION.add(new Eleccion(id,  nombre, fechainicio, fechafin, cargo));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return ELECCION;
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
