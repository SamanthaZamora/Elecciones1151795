package com.elecciones.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estamento implements Serializable {

	private int id;
	private String descripcion;
	private int eleccion;
	
	
	public Estamento(String descripcion, int eleccion) {
		super();
		this.descripcion = descripcion;
		this.eleccion = eleccion;
	}
	

}
