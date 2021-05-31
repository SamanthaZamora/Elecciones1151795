package com.elecciones.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoDocumento implements Serializable {

	private int id;
	private String descripcion;
	
	public TipoDocumento(String descripcion) {
		super();
		this.descripcion = descripcion;
	}
}
