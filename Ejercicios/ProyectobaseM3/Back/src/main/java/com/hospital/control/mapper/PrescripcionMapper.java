package com.hospital.control.mapper;

import com.hospital.control.dto.PrescripcionResponseDTO;
import com.hospital.control.model.Prescripcion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper para convertir entre la entidad Prescripcion y sus DTOs.
 */
@Mapper(componentModel = "spring")
public interface PrescripcionMapper {
    
    /**
     * Convierte una entidad Prescripcion a un DTO de respuesta.
     * @param prescripcion La entidad a convertir.
     * @return El DTO de respuesta.
     */
    @Mapping(source = "medicamento.nombreMedicamento", target = "nombreMedicamento")
    @Mapping(source = "paciente.nombre", target = "nombrePaciente")
    @Mapping(source = "paciente.apellido", target = "apellidoPaciente")
    @Mapping(source = "medico.nombre", target = "nombreMedico")
    @Mapping(source = "medico.apellido", target = "apellidoMedico")
    PrescripcionResponseDTO toPrescripcionResponseDTO(Prescripcion prescripcion);
}
