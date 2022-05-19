package com.ibm.academia.ruleta.services;

import org.springframework.stereotype.Repository;

import com.ibm.academia.ruleta.models.entities.Ruleta;

@Repository
public interface RuletaDAO extends GenericoDAO<Ruleta>
{

	public Ruleta aperturaRuleta (Ruleta ruletaEncontrada, Ruleta ruleta);
	
}
