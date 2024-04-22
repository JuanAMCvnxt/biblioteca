package com.sinensia.usuarios;

public class Usuario {
	
	protected String nombre;
	protected String apellido;
	protected String dni;
	
	public Usuario(String nombre, String apellido, String dni) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getDni() {
		return dni;
	}

	@Override
	public String toString() {
		return "Usuarios" + "\nNombre: " + nombre + "\nApellido: " + apellido + "Dni: " + dni + ".";
	}
	
}
