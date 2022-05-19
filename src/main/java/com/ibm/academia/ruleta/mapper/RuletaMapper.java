package com.ibm.academia.ruleta.mapper;

import com.ibm.academia.ruleta.models.dto.RuletaDTO;
import com.ibm.academia.ruleta.models.entities.Ruleta;

public class RuletaMapper 
{
	public static RuletaDTO mapRuleta (Ruleta ruleta)
	{
		RuletaDTO ruletaDTO = new RuletaDTO();
		ruletaDTO.setId(ruleta.getId());
		ruletaDTO.setEstado(ruleta.getEstado());
		ruletaDTO.setNumeroGanador(ruleta.getNumeroGanador());
		ruletaDTO.setColorGanador(ruleta.getColorGanador());
		ruletaDTO.setFechaAlta(ruleta.getFechaAlta());
		ruletaDTO.setFechaModificacion(ruleta.getFechaModificacion());
		return ruletaDTO;
	}

}
