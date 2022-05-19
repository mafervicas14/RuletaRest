package com.ibm.academia.ruleta.services;

import java.util.Optional;

public interface GenericoDAO <E> 
{
	public Optional<E> buscarPorId(Long id);
	public E guardar (E entidad);
	public Iterable<E> ListarTodos();
	public void eliminarPorId(Long id);
}
