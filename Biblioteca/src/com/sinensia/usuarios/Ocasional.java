package com.sinensia.usuarios;

public class Ocasional extends Usuario {

	private static final int NUM_MAX_DOCS = 2;
	
	public Ocasional(String nombre, String apellido, String dni) {
		super(nombre, apellido, dni);
	}

	public static int getNumMaxDocs() {
		return NUM_MAX_DOCS;
	}

}
