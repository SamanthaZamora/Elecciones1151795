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
	private int eleccion;
	private String descripcion;
	
	
	public Estamento( int eleccion, String descripcion) {
		super();
		this.eleccion = eleccion;
		this.descripcion = descripcion;
		
	}
	

}
