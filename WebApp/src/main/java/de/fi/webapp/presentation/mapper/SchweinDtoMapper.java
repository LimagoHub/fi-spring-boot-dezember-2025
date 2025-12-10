package de.fi.webapp.presentation.mapper;


import de.fi.webapp.presentation.dto.SchweinDTO;
import de.fi.webapp.service.model.Schwein;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchweinDtoMapper {
    Schwein convert(SchweinDTO schweinDto);
    SchweinDTO convert(Schwein schwein);
    Iterable<SchweinDTO> convert(Iterable<Schwein> schweine);
}
