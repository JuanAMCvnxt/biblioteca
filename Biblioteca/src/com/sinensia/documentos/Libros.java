package com.sinensia.documentos;

import java.sql.Date;

public class Libros extends Documentos {
	
	private Date anhoPublicacion;

	public Libros(String codigoDoc, String titulo) {
		super(codigoDoc, titulo);
	}

	public Libros(String codigoDoc, String titulo, Date anhoPublicacion) {
		super(codigoDoc, titulo);
		this.anhoPublicacion = anhoPublicacion;
	}

	public Date getAnhoPublicacion() {
		return anhoPublicacion;
	}

}
