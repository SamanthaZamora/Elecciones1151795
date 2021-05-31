package com.elecciones.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.elecciones.model.Estamento;
import com.elecciones.util.Conexion;
import com.elecciones.util.ConexionFactory;

public class EstamentoDao implements GenericDao<Estamento>{

	private Conexion conexion;

	private static final String INSERT_ESTAMENTO_SQL = "INSERT INTO estamento ( eleccion, descripcion) VALUES (?,?);";
	private static final String DELETE_ESTAMENTO_SQL = "DELETE FROM estamento WHERE id = ?;";
	private static final String UPDATE_ESTAMENTO_SQL = "UPDATE estamento SET eleccion=?, descripcion=?  WHERE id = ?;";
	private static final String SELECT_ESTAMENTO_BY_ID = "SELECT * FROM estamento WHERE id =?;";
	private static final String SELECT_ALL_ESTAMENTO = "SELECT * FROM estamento;";
	
	
	public EstamentoDao() {
		this.conexion = ConexionFactory.getConexion();
	}

	//INSERTAR
	public void insert(Estamento estamento) throws SQLException {
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(INSERT_ESTAMENTO_SQL);
			
			preparedStatement.setInt(1, estamento.getEleccion());
			preparedStatement.setString(2, estamento.getDescripcion());
			

			System.out.println(preparedStatement);
			conexion.execute();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	// ELIMINAR  POR EL ID
	public void delete(int id) throws SQLException {
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(DELETE_ESTAMENTO_SQL);
			preparedStatement.setInt(1, id);

			conexion.execute();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
			
	// ACTUALIZAR 
	public void update(Estamento estamento) throws SQLException {
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(UPDATE_ESTAMENTO_SQL);
	
			preparedStatement.setInt(1, estamento.getEleccion());
			preparedStatement.setString(2, estamento.getDescripcion());
			preparedStatement.setInt(3, estamento.getId());

			conexion.execute();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
				
	// SELECCIONAR POR ID
	public Estamento select(int id) {

		Estamento estamento = null;

		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_ESTAMENTO_BY_ID);
			preparedStatement.setInt(1, id);

			System.out.println(preparedStatement);

			ResultSet rs = conexion.query();

			while (rs.next()) {
				
				int eleccion = rs.getInt("eleccion");
				String descripcion = rs.getString("descripcion");
				
				estamento = new Estamento(id, eleccion, descripcion);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return estamento;
	}

	// SELECCIONAR TODOS 
	public List<Estamento> selectAll() {
		List<Estamento> estamento = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement( SELECT_ALL_ESTAMENTO);
			System.out.println(preparedStatement);

			ResultSet rs = conexion.query();

			while (rs.next()) {
				int id = rs.getInt("id");
				int eleccion = rs.getInt("eleccion");
				String descripcion = rs.getString("descripcion");
				
				estamento.add(new Estamento(id,  eleccion, descripcion));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return estamento;
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
