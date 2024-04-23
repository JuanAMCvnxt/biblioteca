package com.sinensia.conexion;

import java.sql.*;

public class Conexion {

	private static String nombreBd = "biblioteca";
	private static String usuario = "root";
	private static String password = "Abc_123.";
	private static String url = "jdbc:mysql://localhost:3306/" + nombreBd + "?useUnicode=true&use"
			+ "JDBCCompliantTimezoneShift = true&useLegacyDatetimeCode=false&" + "serverTimezone=UTC";

    private static Connection conex;

    public static Connection crearConexion() throws ClassNotFoundException {
        // Comprueba si la conexión existe, y si no crea la conexión
        if (conex == null) {
            try {
                // Se carga el driver de Mysql
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Se establece la conexión utilizando la URL de la base datos, el usuario y
                // contraseña
                conex = DriverManager.getConnection(url, usuario, password);
                // Imprime que la conexión a la base de datos Mysql fue creada.
                System.out.println("Conexion a la base de datos creada.");

            } catch (SQLException e) {
                // Captura la posible excepción si se produjera un error al conectar con la base
                // de datos
                System.out.println("Error al conectar con la base de datos. \n" + e.getMessage().toString());
            }
        }
        // Devuelve la conexión
        return conex;
    }

    // Método que cierra la conexión a la base de datos Mysql
    public static void cerrarConexion(Connection conex) {
        try {
            // Compruibea si la conexion no es nula
            if (conex != null) {
                // Cierra la conexión
                conex.close();
                // imprime que la base de datos ha sido cerrada.
                System.out.println("Conexion a la base de datos cerrada.");
            } else {
                // Si la conexión en nula imprime que la conexión ya estaba cerrada
                System.out.println("La conexión ya estaba cerrada.");
            }
        } catch (Exception e) {
            // Captura la excepción en caso de que hubiera un error al cerrar la conexión
            System.out.println("Error al cerrar la base de datos: \n" + e.getMessage().toString());
        }
    }
}
