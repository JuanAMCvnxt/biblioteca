package com.sinensia.usuarios;

public class Usuario {

	private int idUsuario;
	private String dni;
	private String nombre;
	private String apellido;
	private String direccion;
	private int telefono;
	private String email;
	private boolean socio;

	public Usuario(int idUsuario, String dni, String nombre, String apellido, String direccion, int telefono,
			String email, boolean socio) {
		this.idUsuario = idUsuario;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.socio = socio;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public int getTelefono() {
		return telefono;
	}

	public String getEmail() {
		return email;
	}

	public boolean isSocio() {
		return socio;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", direccion=" + direccion + ", telefono=" + telefono + ", email=" + email + ", socio=" + socio + "]";
	}

}
