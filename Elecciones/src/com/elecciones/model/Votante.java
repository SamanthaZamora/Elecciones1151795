package com.elecciones.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Votante implements Serializable {
	
	private int id;
	private String nombre;
	private String email;
	private String documento;
	private int tipodocumento;
	private int eleccion;
	
	public Votante(String nombre, String email, String documento, int tipodocumento, int eleccion) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.documento = documento;
		this.tipodocumento = tipodocumento;
		this.eleccion = eleccion;
	}

}
