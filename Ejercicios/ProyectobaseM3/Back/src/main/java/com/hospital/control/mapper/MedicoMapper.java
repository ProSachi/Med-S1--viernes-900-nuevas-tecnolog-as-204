package com.hospital.control.mapper;

import com.hospital.control.dto.MedicoRequestDTO;
import com.hospital.control.dto.MedicoResponseDTO;
import com.hospital.control.model.Medico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper para convertir entre la entidad Medico y sus DTOs.
 * Usa PacienteMapper para mapear las listas de pacientes.
 */
@Mapper(componentModel = "spring", uses = PacienteMapper.class)
public interface MedicoMapper {
  /**
   * Convierte una entidad Medico a un DTO de respuesta, incluyendo sus pacientes.
   * @param medico La entidad a convertir.
   * @return El DTO de respuesta.
   */
  @Mapping(source = "pacientes", target = "pacientes")
  MedicoResponseDTO toMedicoResponseDTO(Medico medico);

  /**
   * Convierte un DTO de solicitud a una entidad Medico.
   * @param medicoRequestDTO El DTO de solicitud.
   * @return La entidad Medico.
   */
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "pacientes", ignore = true)
  Medico toMedico(MedicoRequestDTO medicoRequestDTO);

  /**
   * Actualiza una entidad Medico a partir de un DTO de solicitud.
   * @param dto El DTO con los datos para actualizar.
   * @param entidad La entidad a actualizar.
   */
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "pacientes", ignore = true)
  void actualizarEntidadDesdeDto(
    MedicoRequestDTO dto,
    @MappingTarget Medico entidad
  );
}