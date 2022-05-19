package com.ibm.academia.ruleta.services;

import java.util.List;

import com.ibm.academia.ruleta.models.entities.Apuesta;
import com.ibm.academia.ruleta.models.entities.Ruleta;

public interface ApuestaDAO extends GenericoDAO<Apuesta>
{

	 public Apuesta asociarApuestaConRuleta(Apuesta apuesta, Ruleta ruleta);
	 public List<Apuesta> historialRuleta(Long id);
	 
}
