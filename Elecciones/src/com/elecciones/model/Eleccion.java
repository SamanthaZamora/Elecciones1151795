package com.elecciones.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Eleccion implements Serializable {
	
	private int id;
	private String nombre;
	private Date fechainicio;
	private Date fechafin;
	private String cargo;
	
	
	public Eleccion(String nombre, Date fechainicio, Date fechafin, String cargo) {
		super();
		this.nombre = nombre;
		this.fechainicio = fechainicio;
		this.fechafin = fechafin;
		this.cargo = cargo;
	}

}
