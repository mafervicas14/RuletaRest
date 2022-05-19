package com.ibm.academia.ruleta.models.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="ruletas")
public class Ruleta implements Serializable
{
	

	
	public Ruleta(@Size(min = 3, max = 40) String estado) {
		super();
		this.estado = estado;
	}
	
	
	
	public Ruleta() {
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
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
	
	@OneToMany(mappedBy = "ruleta", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"apuesta"})
    private Set<Apuesta> apuestas;
	
	private static final long serialVersionUID = 3715474539401173545L;
	
	


	//Métodos
    @PrePersist
    private void antesPersistir(){
    	
        this.fechaAlta = new Date();      
    }
    
    
    
    @PreUpdate
    private void antesActualizar(){
        this.fechaModificacion = new Date();
    }

    @PostPersist
    private void despuesPersistir(){
    	//Para número Ganador
    	Random rand = new Random();
    	
    	//Obtener Color Ganador
    	Random r=new Random();     
    	String[] arr= {"negro","rojo"};
      	int randomNumber=r.nextInt(arr.length);
      	
      	this.colorGanador= (arr[randomNumber]).toString();
        this.numeroGanador = rand.nextInt((36 - 0) + 1) + 0;
    }

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ruleta other = (Ruleta) obj;
		return Objects.equals(id, other.id);
	}
    
    
    

}
