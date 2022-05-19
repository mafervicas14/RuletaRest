package com.ibm.academia.ruleta.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.ruleta.models.entities.Apuesta;

@Repository
public interface ApuestaRepository extends CrudRepository<Apuesta, Long> 
{
	@Query("select a from Apuesta a join fetch a.ruleta r where r.id = ?1")
    public List<Apuesta> historialRuleta(Long id);

}
