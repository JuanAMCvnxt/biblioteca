package com.sinensia.gestion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.sinensia.documentos.Documento;
import com.sinensia.documentos.Libro;
import com.sinensia.documentos.Revista;
import com.sinensia.usuarios.Usuario;

public class Gestion {

	public static Connection con;

	public static void prestarDocumento(Connection con, String dni, String codigoDoc) {
		Usuario usuario = usuarioPorDNI(con, dni);
		Documento doc;

		if (codigoDoc.startsWith("L")) {
			doc = libroPorCodigo(con, codigoDoc);
		} else {
			doc = revistaPorCodigo(con, codigoDoc);
		}

		if (usuario == null || doc == null) {
			System.out.println("No se puede realizar la reserva.");
		} else if (!doc.isDisponible()) {
			System.out.println("El documento ya se encuentra en otra reserva");
		} else {
			insertarPrestamo(con, usuario, doc);
			updateDisponibilidadDocumento(con, doc);
			System.out.println("jeje God");
		}
	}

	public static void devolverDoocuemnto(Connection con, String codigoDoc) {
		Documento doc;

		if (codigoDoc.startsWith("L")) {
			doc = libroPorCodigo(con, codigoDoc);
		} else {
			doc = revistaPorCodigo(con, codigoDoc);
		}

		if (doc == null) {
			System.out.println("No se puede realizar la reserva.");
		} else if (doc.isDisponible()) {
			System.out.println("El documento ya se encuentra en la biblioteca");
		} else {
			updateEstadoPrestamo(con, doc);
			updateDisponibilidadDocumento(con, doc);
			System.out.println("devolvido");
		}
	}

	public static Usuario usuarioPorDNI(Connection con, String dni) {
		Usuario ret = null;
		try (PreparedStatement selectStmt = con
				.prepareStatement("SELECT * FROM biblioteca.usuarios WHERE dni = '" + dni + "'")) {
			// Ejecuta la consulta y se obtienen un conjunto de resultados
			ResultSet resultSet = selectStmt.executeQuery();

			// Se obtienen los valores de las columnas para cada fila correspondiente
			while (resultSet.next()) {
				int idUsuario = resultSet.getInt("id_usuario");
				String dniUser = resultSet.getString("dni");
				String nombre = resultSet.getString("nombre");
				String apellido = resultSet.getString("apellido");
				String direccion = resultSet.getString("direccion");
				int telefono = resultSet.getInt("telefono");
				String email = resultSet.getString("email");
				boolean esSocio = false;
				if (resultSet.getString("tipo_usuario").equals("socio")) {
					esSocio = true;
				}
				ret = new Usuario(idUsuario, dniUser, nombre, apellido, direccion, telefono, email, esSocio);
			}
			System.out.println(ret);
		} catch (SQLException e) {
			// Captura de excepción que pudiera producirse durante la consulta de mostrar
			// los datos
			System.out.println("Error al buscar usuario: \n" + e.getMessage().toString());
		}

		return ret;
	}

	public static Libro libroPorCodigo(Connection con, String codigoDocumento) {
		Documento ret = null;
		try (PreparedStatement selectStmt = con.prepareStatement(
				"SELECT * FROM biblioteca.documentos WHERE codigo_documento = '" + codigoDocumento + "'")) {
			// Ejecuta la consulta y se obtienen un conjunto de resultados
			ResultSet resultSet = selectStmt.executeQuery();

			// Se obtienen los valores de las columnas para cada fila correspondiente
			while (resultSet.next()) {
				String codigo_documento = resultSet.getString("codigo_documento");
				String titulo = resultSet.getString("titulo");
				int año_publicacion = resultSet.getInt("año_publicacion");
				boolean disponible = resultSet.getBoolean("disponible");

				ret = new Libro(codigo_documento, titulo, año_publicacion, disponible);
			}
			System.out.println(ret);
		} catch (SQLException e) {
			// Captura de excepción que pudiera producirse durante la consulta de mostrar
			// los datos
			System.out.println("Error al buscar libro: \n" + e.getMessage().toString());
		}
		return (Libro) ret;
	}

	public static Revista revistaPorCodigo(Connection con, String codigoDocumento) {
		Documento ret = null;
		try (PreparedStatement selectStmt = con.prepareStatement(
				"SELECT * FROM biblioteca.documentos WHERE codigo_documento = '" + codigoDocumento + "'")) {
			// Ejecuta la consulta y se obtienen un conjunto de resultados
			ResultSet resultSet = selectStmt.executeQuery();

			// Se obtienen los valores de las columnas para cada fila correspondiente
			while (resultSet.next()) {
				String codigo_documento = resultSet.getString("codigo_documento");
				String titulo = resultSet.getString("titulo");
				boolean disponible = resultSet.getBoolean("disponible");

				ret = new Revista(codigo_documento, titulo, disponible);
			}
			System.out.println(ret);
		} catch (SQLException e) {
			// Captura de excepción que pudiera producirse durante la consulta de mostrar
			// los datos
			System.out.println("Error al buscar revista: \n" + e.getMessage().toString());
		}
		return (Revista) ret;
	}

	public static void insertarPrestamo(Connection con, Usuario user, Documento doc) {
		try (PreparedStatement insertStmt = con.prepareStatement(
				"INSERT INTO biblioteca.prestamos(id_usuario, codigo_documento, fecha_prestamo, fecha_devolucion, devuelto) VALUES (?,?,?,?,?)")) {

			insertStmt.setInt(1, user.getIdUsuario());
			insertStmt.setString(2, doc.getCodigoDoc());
			insertStmt.setDate(3, Date.valueOf(LocalDate.now()));
			if (doc.getCodigoDoc().startsWith("L")) {
				if (user.isSocio()) {
					insertStmt.setDate(4, Date.valueOf(LocalDate.now().plusDays(30)));
				} else {
					insertStmt.setDate(4, Date.valueOf(LocalDate.now().plusDays(30 / 3)));
				}
			} else {
				if (user.isSocio()) {
					insertStmt.setDate(4, Date.valueOf(LocalDate.now().plusDays(15)));
				} else {
					insertStmt.setDate(4, Date.valueOf(LocalDate.now().plusDays(15 / 3)));
				}
			}
			insertStmt.setBoolean(5, false);
			insertStmt.execute();

			System.out.println("Datos introducidos correctamente");

		} catch (SQLException e) {
			System.out.println("Error al introducir los datos en la tabla: \n" + e.getMessage().toString());
		}
	}

	public static void updateDisponibilidadDocumento(Connection con, Documento doc) {
		try (PreparedStatement updateStmt = con
				.prepareStatement("UPDATE biblioteca.documentos SET disponible=? WHERE codigo_documento=?")) {

			updateStmt.setBoolean(1, !doc.isDisponible());
			updateStmt.setString(2, doc.getCodigoDoc());
			updateStmt.executeUpdate();

			System.out.println("Datos actualizados correctamente");

		} catch (SQLException e) {
			System.out.println("Error al actualizar los datos en la tabla: \n" + e.getMessage().toString());
		}
	}

	public static void updateEstadoPrestamo(Connection con, Documento doc) {
		try (PreparedStatement updateStmt = con.prepareStatement(
				"UPDATE biblioteca.prestamos SET devuelto=? WHERE codigo_documento=? AND devuelto=?")) {

			updateStmt.setBoolean(1, true);
			updateStmt.setString(2, doc.getCodigoDoc());
			updateStmt.setBoolean(3, false);
			updateStmt.executeUpdate();

			System.out.println("Datos actualizados correctamente");

		} catch (SQLException e) {
			System.out.println("Error al actualizar los datos en la tabla: \n" + e.getMessage().toString());
		}
	}

//	public static void mostrarInformePrestamos(Connection con) {
//		try (PreparedStatement selectStmt = con.prepareStatement("SELECT * FROM biblioteca.prestamos")) {
//			// Ejecuta la consulta y se obtienen un conjunto de resultados
//			ResultSet resultSet = selectStmt.executeQuery();
//
//			// Se obtienen los valores de las columnas para cada fila correspondiente
//			while (resultSet.next()) {
//				int idUsuario = resultSet.getInt("id_usuario");
//				String dniUser = resultSet.getString("dni");
//				String nombre = resultSet.getString("nombre");
//				String apellido = resultSet.getString("apellido");
//				String direccion = resultSet.getString("direccion");
//				int telefono = resultSet.getInt("telefono");
//				String email = resultSet.getString("email");
//				boolean esSocio = false;
//				if (resultSet.getString("tipo_usuario").equals("socio")) {
//					esSocio = true;
//				}
//			}
//		} catch (SQLException e) {
//			// Captura de excepción que pudiera producirse durante la consulta de mostrar
//			// los datos
//			System.out.println("Error al buscar usuario: \n" + e.getMessage().toString());
//		}
//	}
}
