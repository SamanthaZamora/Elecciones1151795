package com.elecciones.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.elecciones.model.TipoDocumento;
import com.elecciones.util.Conexion;
import com.elecciones.util.ConexionFactory;

public class TipoDocumentoDao implements GenericDao<TipoDocumento>{

	private Conexion conexion;

	private static final String INSERT_TIPO_SQL = "INSERT INTO tipodocumento (descripcion) VALUES (?);";
	private static final String DELETE_TIPO_SQL = "DELETE FROM tipodocumento WHERE id = ?;";
	private static final String UPDATE_TIPO_SQL = "UPDATE tipodocumento SET descripcion = ? WHERE id = ?;";
	private static final String SELECT_TIPO_BY_ID = "SELECT * FROM tipodocumento WHERE id =?;";
	private static final String SELECT_ALL_TIPO = "SELECT * FROM tipodocumento;";
	
	
	public TipoDocumentoDao() {
		this.conexion = ConexionFactory.getConexion();
	}

	//INSERTAR
	public void insert(TipoDocumento tipo) throws SQLException {
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(INSERT_TIPO_SQL);
			preparedStatement.setString(1, tipo.getDescripcion());
			
			
			System.out.println(preparedStatement);
			conexion.execute();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	// ELIMINAR  POR EL ID
	public void delete(int id) throws SQLException {
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(DELETE_TIPO_SQL);
			preparedStatement.setInt(1, id);

			conexion.execute();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
			
	// ACTUALIZAR 
	public void update(TipoDocumento tipo) throws SQLException {
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(UPDATE_TIPO_SQL);
			preparedStatement.setString(1, tipo.getDescripcion());
			preparedStatement.setInt(2, tipo.getId());

			conexion.execute();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
				
	// SELECCIONAR POR ID
	public TipoDocumento select(int id) {

		TipoDocumento tipo = null;

		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_TIPO_BY_ID);
			preparedStatement.setInt(1, id);

			System.out.println(preparedStatement);

			ResultSet rs = conexion.query();

			while (rs.next()) {
				String descripcion = rs.getString("descripcion");
				
				tipo = new TipoDocumento(id, descripcion);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return tipo;
	}

	// SELECCIONAR TODOS 
	public List<TipoDocumento> selectAll() {
		List<TipoDocumento> votante = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement( SELECT_ALL_TIPO);
			System.out.println(preparedStatement);

			ResultSet rs = conexion.query();

			while (rs.next()) {
				int id = rs.getInt("id");
				String descripcion = rs.getString("descripcion");
				
				votante.add(new TipoDocumento(id, descripcion));
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
