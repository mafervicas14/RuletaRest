package com.ibm.academia.ruleta.services;

import com.ibm.academia.ruleta.models.entities.Apuesta;
import com.ibm.academia.ruleta.models.entities.Ruleta;

public interface ApuestaDAO extends GenericoDAO<Apuesta>
{

	 public Apuesta asociarApuestaConRuleta(Apuesta apuesta, Ruleta ruleta);
	 public Iterable<Apuesta> historialRuleta(Long id);
	 
}
