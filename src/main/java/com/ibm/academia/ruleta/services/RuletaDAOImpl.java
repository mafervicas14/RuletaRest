package com.ibm.academia.ruleta.services;


import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ibm.academia.ruleta.models.entities.Ruleta;
import com.ibm.academia.ruleta.repositories.RuletaRepository;

@Service
public class RuletaDAOImpl extends GenericoDAOImpl<Ruleta, RuletaRepository> implements RuletaDAO 
{

	public RuletaDAOImpl(RuletaRepository repository) {
		super(repository);
		
	}

	@Override
	@Transactional
	public Ruleta aperturaRuleta(Ruleta ruletaEncontrada, Ruleta ruleta) {
		Ruleta ruletaActualizada = null;
		ruletaEncontrada.setEstado(ruleta.getEstado());
		ruletaActualizada = repository.save(ruletaEncontrada);
		return ruletaActualizada;
	}
	


}
