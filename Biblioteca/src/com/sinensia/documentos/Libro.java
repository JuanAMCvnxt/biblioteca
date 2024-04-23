package com.sinensia.documentos;

public class Libro extends Documento{
	
	private int anhoPublicacion;

	public Libro(String codigoDoc, String titulo, int anhoPublicacion, boolean disponible) {
		super(codigoDoc, titulo, disponible);
		this.anhoPublicacion = anhoPublicacion;
	}

	public int getAnhoPublicacion() {
		return anhoPublicacion;
	}

	@Override
	public String toString() {
		return "Libro [codigoDoc=" + codigoDoc + ", titulo=" + titulo + ", disponible=" + disponible + ", anhoPublicacion=" + anhoPublicacion + "]";
	}
	
	
}
