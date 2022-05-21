package com.ibm.academia.ruleta.models.entities;

import java.io.Serializable;
import java.util.Date;

import javax.management.loading.PrivateClassLoader;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.academia.ruleta.enums.ColoresPosibles;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="apuestas")
public class Apuesta implements Serializable
{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	
	@Min(value = 0, message = "Valores de la apuesta deben ser mayores a 0")
    @Max(value = 36, message = "Valores de la apuesta deben ser menores a 36")
	@Column(name="numero_apostado")
	private Integer numeroApostado;
	
	@Column(name="color_apostado")
	@Enumerated(EnumType.STRING)
	private ColoresPosibles colorApostado;
	
	
	@Positive(message = "Debe ser mayor a 0")
	@Max(value = 10000, message = "No se puede apostar una cantidad mayor a 10,000 dólares")
	@Column(name="cantidad_apostada")
	private Double dineroApostado;
	
	
	@Column(name="fecha_alta")
    private Date fechaAlta;

    @Column(name="fecha_modificacion")
    private Date fechaModificacion;
    
    
    @ManyToOne(optional = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name="ruleta_id")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "apuestas"})
    private Ruleta ruleta;
	
	

	public Apuesta(
			@Min(value = 0, message = "Valores de la apuesta deben ser mayores a 0") @Max(value = 36, message = "Valores de la apuesta deben ser menores a 36") Integer numeroApostado,
			ColoresPosibles colorApostado,
			@Positive(message = "Debe ser mayor a 0") @Max(value = 10000, message = "No se puede apostar una cantidad mayor a 10,000 dólares") Double dineroApostado,
			Ruleta ruleta) {
		super();
		this.numeroApostado = numeroApostado;
		this.colorApostado = colorApostado;
		this.dineroApostado = dineroApostado;
		this.ruleta = ruleta;
	}
	
	public Apuesta() 
	{
		super();
	}
	
	
	public Boolean ganar()
	{
		return ruleta.getNumeroGanador() == numeroApostado ;
	}


	private static final long serialVersionUID = 8606478432350480600L;
	
	//Métodos
    @PrePersist
    private void antesPersistir(){
    	
        this.fechaAlta = new Date();      
    }
    

}
