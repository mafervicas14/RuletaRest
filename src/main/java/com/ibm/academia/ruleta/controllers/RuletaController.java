package com.ibm.academia.ruleta.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.ruleta.exceptions.BadRequestException;
import com.ibm.academia.ruleta.exceptions.NotFoundException;
import com.ibm.academia.ruleta.models.entities.Ruleta;
import com.ibm.academia.ruleta.services.RuletaDAO;

@RestController
@RequestMapping("/ruleta")
public class RuletaController 
{

	@Autowired
	private RuletaDAO ruletaDAO;
	
	
	/**
	 * EndPoint para mostrar todos los Objetos tipo Ruleta
	 * @return Retorna la lista de Ruletas
	 * @BadRequestException En caso de que no exista ningun objeto de tipo Ruleta
	 * @author mafervicas 05/19/2022
	 */
	@GetMapping("/listaRuletas")
	public List<Ruleta> listarTodasRuletas()
	{
		List<Ruleta> ruletas = (List<Ruleta>) ruletaDAO.ListarTodos();
        if(ruletas.isEmpty())
            throw  new BadRequestException("No existen ruletas");
        return ruletas;
	}
	
	
	
	/**
	 * EndPoint para guardar/agregar un nuevo objeto de tipo Ruleta
	 * @param ruleta Objeto con la información a crear
	 * @param result Muestra si encontró un error y de ser así anexa el mensaje previamente especificado
	 * @return Retorna si hubo un error al agregar el objeto o si se pudo crear correctamente, la información
	 * @author Mafervicas 05/19/2022
	 */
	@PostMapping
    public ResponseEntity<?> guardarCarrera(@Valid @RequestBody Ruleta ruleta, BindingResult result)
    {
        Map<String, Object> validaciones = new HashMap<String, Object>();
        if(result.hasErrors())
        {
            List<String> listaErrores = result.getFieldErrors()
                    .stream()
                    .map(errores -> "Campo: '" + errores.getField() + "'" + errores.getDefaultMessage())
                    .collect(Collectors.toList());
            validaciones.put("Lista Errores", listaErrores);
            return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
        }

        Ruleta ruletaGuardada = ruletaDAO.guardar(ruleta);
        return new ResponseEntity<Ruleta>(ruletaGuardada, HttpStatus.CREATED);
    }
	
	
	/**
	 * EndPoint para mostrar si existe o no una ruleta
	 * @param ruletaId
	 * @return
	 */
	@GetMapping("/id/{ruletaId}")
	public ResponseEntity<?> encontrarPorId(@PathVariable Long ruletaId)
	{
		Optional<Ruleta> oRuleta = ruletaDAO.buscarPorId(ruletaId);
        if(!oRuleta.isPresent())
            throw new BadRequestException(String.format("La ruleta con ID: %d no existe", ruletaId));
        
        return new ResponseEntity<List<Ruleta>>((List<Ruleta>)oRuleta.get(), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/upd/ruletaId/{ruletaId}")
	public ResponseEntity<?> aperturaRuletaConfirmacion(@PathVariable Long ruletaId, @RequestBody Ruleta ruleta)
	{
		 Optional<Ruleta> oRuleta = ruletaDAO.buscarPorId(ruletaId);

	        if(!oRuleta.isPresent())
	            throw new NotFoundException(String.format("OPERACIÓN DENEGADA, la ruleta con id %d no existe", ruletaId));

	        Ruleta ruletaUpdate = ruletaDAO.aperturaRuleta(oRuleta.get(), ruleta);
	        return new ResponseEntity<Ruleta>(ruletaUpdate, HttpStatus.OK);
	}
}
