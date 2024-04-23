package com.sinensia.documentos;

public abstract class Documento{
	
	protected String codigoDoc;
	protected String titulo;
	protected boolean disponible;
	
	public Documento(String codigoDoc,String titulo, boolean disponible) {
		this.codigoDoc = codigoDoc;
		this.titulo = titulo;
		this.disponible = disponible;
	}
	
	public String getCodigoDoc() {
		return codigoDoc;
	}

	public String getTitulo() {
		return titulo;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
}
