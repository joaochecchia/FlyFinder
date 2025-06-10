package FlyFinder.com.FlyFinderBackend.Infra.Mapper;

import FlyFinder.com.FlyFinderBackend.Core.Request.EncontrarVoosRequest;
import FlyFinder.com.FlyFinderBackend.Core.Responses.EncontrarVoosResponse;
import FlyFinder.com.FlyFinderBackend.Infra.DTO.VoosDTO;
import FlyFinder.com.FlyFinderBackend.Infra.Persistence.entities.VoosModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VoosMapper {

    public static VoosModel toModel(VoosDTO dto) {
        return new VoosModel(
                dto.getId() ,
                dto.getOrigem(),
                dto.getDestino(),
                dto.getDataPartida(),
                dto.getDataRetorno(),
                dto.getPreco(),
                dto.getCompanhiaAerea(),
                dto.getNumeroVoo()
        );
    }

    public static VoosDTO toDto(VoosModel model) {
        return new VoosDTO(
                model.getId(),
                model.getOrigem(),
                model.getDestino(),
                model.getDataPartida(),
                model.getDataRetorno(),
                model.getPreco(),
                model.getCompanhiaAerea(),
                model.getNumeroVoo()
        );
    }

    public static VoosModel toModel(EncontrarVoosResponse response) {
        return new VoosModel(
                null,
                response.originCity(),
                response.destinationCity(),
                response.departureDate().toString(),
                null,
                response.price(),
                response.carrierName(),
                response.direct() ? "Direto" : "Com escalas"
        );
    }

    public static EncontrarVoosResponse toResponse(VoosModel model) {
        return new EncontrarVoosResponse(
                model.getOrigem(),
                "N/A",
                model.getDestino(),
                "N/A",
                model.getCompanhiaAerea(),
                model.getPreco(),
                !"Com escalas".equalsIgnoreCase(model.getNumeroVoo()),
                LocalDateTime.parse(model.getDataPartida(), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
    }
}