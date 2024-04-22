package com.sinensia.usuarios;

public class Socios extends Usuario {
	
	private static final int NUM_MAX_DOCS = 20;

	public Socios(String nombre, String apellido, String dni) {
		super(nombre, apellido, dni);
	}

	public static int getNumMaxDocs() {
		return NUM_MAX_DOCS;
	}

}
