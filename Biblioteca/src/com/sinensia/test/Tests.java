package com.sinensia.test;

import java.sql.Connection;

import com.sinensia.conexion.Conexion;
import com.sinensia.gestion.Gestion;

public class Tests {

	public static void main(String[] args) throws ClassNotFoundException {

		Connection con = Conexion.crearConexion();
	
		//Gestion.prestarDocumento(con, "45144709X", "L12345");
		Gestion.devolverDoocuemnto(con, "R54321");
	}

}
