package com.hospital.control.mapper;

import com.hospital.control.dto.PacienteRequestDTO;
import com.hospital.control.dto.PacienteResponseDTO;
import com.hospital.control.model.Medico;
import com.hospital.control.model.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MappingTarget;

/**
 * Mapper para convertir entre la entidad Paciente y sus DTOs.
 */
@Mapper(componentModel = "spring")
public interface PacienteMapper {
  /**
   * Convierte una entidad Paciente a un DTO de respuesta.
   * @param paciente La entidad a convertir.
   * @return El DTO de respuesta.
   */
  PacienteResponseDTO toPacienteResponseDTO(Paciente paciente);

  /**
   * Convierte un DTO de solicitud a una entidad Paciente.
   * @param pacienteRequestDTO El DTO de solicitud.
   * @param medico El médico asociado al paciente.
   * @return La entidad Paciente.
   */
  @Mappings(
    {
      @Mapping(source = "pacienteRequestDTO.nombre", target = "nombre"),
      @Mapping(source = "pacienteRequestDTO.apellido", target = "apellido"),
      @Mapping(source = "medico.id", target = "medico.id"),
      @Mapping(target = "id", ignore = true),
      @Mapping(target = "prescripciones", ignore = true),
    }
  )
  Paciente toPaciente(
    PacienteRequestDTO pacienteRequestDTO,
    Medico medico
  );

  /**
   * Actualiza una entidad Paciente a partir de un DTO de solicitud.
   * @param dto El DTO con los datos para actualizar.
   * @param entidad La entidad a actualizar.
   */
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "medico", ignore = true)
  @Mapping(target = "prescripciones", ignore = true)
  void actualizarEntidadDesdeDto(
    PacienteRequestDTO dto,
    @MappingTarget Paciente entidad
  );
}