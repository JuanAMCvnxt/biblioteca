package com.sinensia.documentos;

public abstract class Documentos {
	
	protected String codigoDoc;
	protected String titulo;
	protected boolean disponinble;
	
	public Documentos(String codigoDoc,String titulo) {
		this.codigoDoc = codigoDoc;
		this.titulo = titulo;
		this.disponinble = true;
	}
	
	public String getCodigoDoc() {
		return codigoDoc;
	}

	public String getTitulo() {
		return titulo;
	}

	public boolean isDisponinble() {
		return disponinble;
	}	
	
}
