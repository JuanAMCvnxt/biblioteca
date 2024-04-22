package com.sinensia.conexion;

import java.sql.*;

public class Conexion {

	private String nombreBd = "biblioteca";
	private String usuario = "root";
	private String password = "Abc_123.";
	private String url = "jdbc:mysql://localhost:3306/" + nombreBd + "?useUnicode=true&use"
			+ "JDBCCompliantTimezoneShift = true&useLegacyDatetimeCode=false&" + "serverTimezone=UTC";

	Connection conn = null;

	public Conexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, usuario, password);

			if (conn != null) {
				System.out.println("Conexión realizada correctamente.");
				System.out.println("Conexion a la base de datos: " + nombreBd + "." + "\nUsuario: " + usuario + ".");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Error, clase no encontrada: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error sql: " + e.getMessage());
		}

	}
	
	public Connection getConnection() {
		return conn;
	}
	
}
