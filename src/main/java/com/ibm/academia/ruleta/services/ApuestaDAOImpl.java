package com.ibm.academia.ruleta.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ibm.academia.ruleta.models.entities.Apuesta;
import com.ibm.academia.ruleta.models.entities.Ruleta;
import com.ibm.academia.ruleta.repositories.ApuestaRepository;


@Service
public class ApuestaDAOImpl extends GenericoDAOImpl<Apuesta, ApuestaRepository> implements ApuestaDAO 
{

	public ApuestaDAOImpl(ApuestaRepository repository) {
		super(repository);
		
	}

	@Override
	@Transactional
	public Apuesta asociarApuestaConRuleta(Apuesta apuesta, Ruleta ruleta) {
		((Apuesta)apuesta).setRuleta(ruleta);
		return repository.save(apuesta);
	}

	@Override
	public List<Apuesta> historialRuleta(Long id) {
		return (List<Apuesta>) repository.historialRuleta(id);
	}

}
