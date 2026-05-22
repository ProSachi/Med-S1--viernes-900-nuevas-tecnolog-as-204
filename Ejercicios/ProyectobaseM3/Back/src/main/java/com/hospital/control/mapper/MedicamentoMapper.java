package com.hospital.control.mapper;

import com.hospital.control.dto.MedicamentoRequestDTO;
import com.hospital.control.dto.MedicamentoResponseDTO;
import com.hospital.control.model.Medicamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper para convertir entre la entidad Medicamento y sus DTOs.
 */
@Mapper(componentModel = "spring")
public interface MedicamentoMapper {
  /**
   * Convierte una entidad Medicamento a un DTO de respuesta.
   * @param medicamento La entidad a convertir.
   * @return El DTO de respuesta.
   */
  MedicamentoResponseDTO toMedicamentoResponseDTO(Medicamento medicamento);

  /**
   * Convierte un DTO de solicitud a una entidad Medicamento.
   * @param medicamentoRequestDTO El DTO de solicitud.
   * @return La entidad Medicamento.
   */
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "prescripciones", ignore = true)
  Medicamento toMedicamento(MedicamentoRequestDTO medicamentoRequestDTO);

  /**
   * Actualiza una entidad Medicamento a partir de un DTO de solicitud.
   * @param dto El DTO con los datos para actualizar.
   * @param entidad La entidad a actualizar.
   */
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "prescripciones", ignore = true)
  void actualizarEntidadDesdeDto(
    MedicamentoRequestDTO dto,
    @MappingTarget Medicamento entidad
  );
}