package com.hospital.control.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.hospital.control.dto.EnfermerosRequestDTO;
import com.hospital.control.dto.EnfermerosResponseDTO;
import com.hospital.control.model.Enfemeros;

/**
 * Mapper para convertir entre la entidad Enfemeros y sus DTOs.
 * Utiliza MapStruct para generar las implementaciones automáticamente.
 * 
 * componentModel = "spring" indica a MapStruct que genere un bean de Spring
 * que puede ser inyectado automáticamente en los servicios.
 */
@Mapper(componentModel = "spring")
public interface EnfermerosMapper {

    /**
     * Convierte una entidad Enfemeros a un DTO de respuesta.
     * 
     * @param enfermeros La entidad a convertir.
     * @return El DTO de respuesta con los datos del enfermero.
     */
    EnfermerosResponseDTO toResponseDTO(Enfemeros enfermeros);

    /**
     * Convierte un DTO de solicitud a una entidad Enfemeros.
     * El ID es autogenerado por la base de datos, no lo mapeamos.
     * 
     * @param enfermerosRequestDTO El DTO con los datos para crear el enfermero.
     * @return La entidad Enfemeros lista para ser guardada.
     */
    @Mapping(target = "id", ignore = true)
    Enfemeros toEnfermeros(EnfermerosRequestDTO enfermerosRequestDTO);

    /**
     * Actualiza una entidad Enfemeros existente a partir de un DTO.
     * El ID no puede ser actualizado.
     * 
     * @param dto     El DTO con los datos a actualizar.
     * @param entidad La entidad a actualizar (anotada con @MappingTarget).
     */
    @Mapping(target = "id", ignore = true)
    void actualizarEntidadDesdeDto(EnfermerosRequestDTO dto, @MappingTarget Enfemeros entidad);
}
