package com.elecciones.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionPostgreSQL implements Conexion {

	public Connection con;

	private static ConexionPostgreSQL db;

	private PreparedStatement preparedStatement;
	
	public Statement st = null;

	private ConexionPostgreSQL() {
		
		String host = "queenie.db.elephantsql.com";
		String db = "mnjgxshj";

		String url = "jdbc:postgresql://" + host + ":5432/" + db;
		String user = "mnjgxshj";
		String password = "Uzjqo00sxV0W9OzPEB1q3wpoVvGMbbUV";
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		try {
			con = DriverManager.getConnection(url, user, password);

			boolean valid = con.isValid(50000);
			System.out.print(valid ? "TEST OK" : "TEST FAIL");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// PATRON SINGLETON (UNA UNICA CONEXION ABIERTA)
	public static ConexionPostgreSQL getConexion() {

		if (db == null) {
			db = new ConexionPostgreSQL();
		}
		return db;
	}

	// METODO DE CONSULTAS A LA BD
	public ResultSet query() throws SQLException {
		ResultSet res = preparedStatement.executeQuery();
		return res;
	}

	// METODO DE ACTUALIZACION A LA BD
	public int execute() throws SQLException {
		int result = preparedStatement.executeUpdate();
		return result;
	}

	// INICIALIZAR EL OBJETO PREPAREDSTATEMENT
	public PreparedStatement setPreparedStatement(String sql) throws SQLException {
		this.preparedStatement = con.prepareStatement(sql);
		return this.preparedStatement;
	}

	//CERRAR CONEXION
	public void cerrarConexion() {
		try {
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*public ResultSet consultar(String sql) {

		try {
			Statement st = this.con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			return rs;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// COMPROBAR
	public static void main(String[] args) {
		ConexionPostgreSQL cp = new ConexionPostgreSQL();

		ResultSet r = cp.consultar("Select * from candidato");
		try {
			while (r.next()) {
				int id = r.getInt(1);
				String documento = r.getString("documento");
				String nombre = r.getString("nombre");
				String apellido = r.getString("apellido");
				System.out.print("\n" + id + "documento" +documento+ " Nombre:" + nombre + "apellido"+ apellido);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		cp.cerrarConexion();
	}*/
}
