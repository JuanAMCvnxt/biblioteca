package com.sinensia.prestamos;

import java.sql.Date;

public class Prestamo {

	private static final int NUM_MAX_DOCS_SOCIOS = 20;
	private static final int NUM_MAX_DOCS_OCASIONAL = 2;

	private int id_prestamo;
	private int id_usuario;
	private int id_documento;
	private Date fecha_prestamo;
	private Date fecha_devolucion;
	private boolean devuelto;

	public Prestamo(int id_prestamo, int id_usuario, int id_documento, Date fecha_prestamo, Date fecha_devolucion,
			boolean devuelto) {
		super();
		this.id_prestamo = id_prestamo;
		this.id_usuario = id_usuario;
		this.id_documento = id_documento;
		this.fecha_prestamo = fecha_prestamo;
		this.fecha_devolucion = fecha_devolucion;
		this.devuelto = false;
	}

	public int getId_prestamo() {
		return id_prestamo;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public int getId_documento() {
		return id_documento;
	}

	public Date getFecha_prestamo() {
		return fecha_prestamo;
	}

	public Date getFecha_devolucion() {
		return fecha_devolucion;
	}

	public boolean isDevuelto() {
		return devuelto;
	}

	public static int getNumMaxDocsSocios() {
		return NUM_MAX_DOCS_SOCIOS;
	}

	public static int getNumMaxDocsOcasional() {
		return NUM_MAX_DOCS_OCASIONAL;
	}

	
	
}
