package com.elecciones.util;

public class ConexionFactory {

	static String tipo = "mysql";

	public static Conexion getConexion() {
		if (tipo.contentEquals("postgresql")) {
			return  ConexionPostgreSQL.getConexion();
		} else if (tipo.contentEquals("mysql")) {
			return  ConexionMysql.getConexion();
		}

		return null;
	}
}

