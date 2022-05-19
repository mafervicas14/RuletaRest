package com.ibm.academia.ruleta.models.dto;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.academia.ruleta.models.entities.Apuesta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RuletaDTO 
{
	
	private Long id;
	
	@Column(name="estado")
	private String estado;
	
	@Column(name="numero_ganador")
    private Integer numeroGanador;
	
	@Column(name="color_ganador")
	private String colorGanador;
	
	@Column(name="fecha_alta")
    private Date fechaAlta;
	
	@Column(name="fecha_modificacion")
	private Date fechaModificacion;
	
	
	

}
