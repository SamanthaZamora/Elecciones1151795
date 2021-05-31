package com.elecciones.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidato implements Serializable {

	private int id;
	private String documento;
	private String nombre;
	private String apellido;
	private int eleccion;
	private int numero;
	
	
	public Candidato(String documento, String nombre, String apellido, int eleccion, int numero) {
		super();
		this.documento = documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.eleccion = eleccion;
		this.numero = numero;
	}
	
	
}
