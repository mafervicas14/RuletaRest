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
import com.ibm.academia.ruleta.models.entities.Apuesta;
import com.ibm.academia.ruleta.models.entities.Ruleta;
import com.ibm.academia.ruleta.services.ApuestaDAO;
import com.ibm.academia.ruleta.services.RuletaDAO;

@RestController
@RequestMapping("/apuesta")
public class ApuestaController 
{
	
	@Autowired
	private ApuestaDAO apuestaDAO;
	
	@Autowired
	private RuletaDAO ruletaDAO;
	
	
	/**
	 * EndPoint para mostrar todos los Objetos tipo Apuestas
	 * @return Retorna la lista de Apuestas
	 * @BadRequestException En caso de que no exista ningun objeto de tipo Apuesta
	 * @author mafervicas 05/19/2022
	 */
	@GetMapping("/listaApuestas")
	public List<Apuesta> listarTodasLasApuestas()
	{
		List<Apuesta> apuestas = (List<Apuesta>) apuestaDAO.ListarTodos();
        if(apuestas.isEmpty())
            throw  new BadRequestException("No existen Apuestas");
        return apuestas;
	}
	
	
	/**
	 * EndPoint para guardar/agregar un nuevo objeto de tipo Apuesta
	 * @param apuesta Objeto con la información a crear
	 * @param result Muestra si encontró un error y de ser así anexa el mensaje previamente especificado
	 * @return Retorna si hubo un error al agregar el objeto o si se pudo crear correctamente, la información
	 * @author Mafervicas 05/19/2022
	 */
	@PostMapping
    public ResponseEntity<?> guardarApuesta(@Valid @RequestBody Apuesta apuesta, BindingResult result)
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

        Apuesta apuestaGuardada = apuestaDAO.guardar(apuesta);
        return new ResponseEntity<Apuesta>(apuestaGuardada, HttpStatus.CREATED);
    }
	
	
	
	/**
	 * EndPoint para asignar Apuestas a un objeto tipo Ruleta
	 * @param apuestaId recibe el Id de la apuesta
	 * @param ruletaId recibe el Id de la ruleta
	 * @return La Apuesta asociada a la ruleta
	 * @NotFoundException en caso de que no encuentre ninguno de los dos opbjetos
	 * @author Mafervicas 05/19/2022
	 */
	
	@PutMapping("/apuestaId/{apuestaId}/ruletaId/{ruletaId}")
    public ResponseEntity<?> asignarApuestaRuleta(@PathVariable Long apuestaId, @PathVariable Long ruletaId)
    {
        Optional<Apuesta> oApuesta = apuestaDAO.buscarPorId(apuestaId);
        if(!oApuesta.isPresent())
            throw new NotFoundException(String.format("Apuesta con ID: %d no existe", apuestaId));
        Optional<Ruleta> oRuleta = ruletaDAO.buscarPorId(ruletaId);
        if(!oRuleta.isPresent())
            throw new NotFoundException(String.format("Ruleta con ID: %d no existe", ruletaId));
        Apuesta apuesta = ((ApuestaDAO)apuestaDAO).asociarApuestaConRuleta(oApuesta.get(),oRuleta.get());
        
        
        return new ResponseEntity<Apuesta>(apuesta, HttpStatus.OK);
    }
	
	@GetMapping("/historialFinal/ruletaId/{ruletaId}")
	public Optional<Ruleta> historialFinal(@PathVariable Long ruletaId)
	{
		 Optional<Ruleta> oRuleta = ruletaDAO.buscarPorId(ruletaId);
		 if(oRuleta.isEmpty())
	             throw  new BadRequestException("No existen ruletas con ese Id");
	      
	       return oRuleta;
	    		 
	}

}
