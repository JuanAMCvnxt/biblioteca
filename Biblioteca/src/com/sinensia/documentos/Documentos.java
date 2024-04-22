package com.sinensia.documentos;

public abstract class Documentos {
	
	protected String titulo;
	protected boolean disponinble;
	
	public Documentos(String titulo) {
		this.titulo = titulo;
		this.disponinble = true;
	}

	public String getTitulo() {
		return titulo;
	}

	public boolean isDisponinble() {
		return disponinble;
	}
	
	
	
}
