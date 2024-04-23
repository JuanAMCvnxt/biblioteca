package com.sinensia.documentos;

public class Revista extends Documento {

	public Revista(String codigoDoc, String titulo, boolean disponible) {
		super(codigoDoc, titulo, disponible);
	}

	@Override
	public String toString() {
		return "Revista [codigoDoc=" + codigoDoc + ", titulo=" + titulo + ", disponible=" + disponible + "]";
	}

}
