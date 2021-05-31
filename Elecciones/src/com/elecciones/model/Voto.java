package com.elecciones.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voto implements Serializable {

	private int id;
	private Date fechacreacion;
	private Date fechavoto;
	private String uuid;
	private String enlace;
	private int estamento;
	private int candidato;
	private int votante;
	
	public Voto(Date fechacreacion, Date fechavoto, String uuid, String enlace, int estamento, int candidato, int votante) {
		super();
		this.fechacreacion = fechacreacion;
		this.fechavoto = fechavoto;
		this.uuid = uuid;
		this.enlace = enlace;
		this.estamento = estamento;
		this.candidato = candidato;
		this.votante = votante;
	}

}
