package com.ibm.academia.ruleta.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.ruleta.models.entities.Ruleta;

@Repository
public interface RuletaRepository extends CrudRepository<Ruleta, Long>
{
	

}
