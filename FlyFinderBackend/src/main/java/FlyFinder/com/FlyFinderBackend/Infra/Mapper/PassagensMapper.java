package FlyFinder.com.FlyFinderBackend.Infra.Mapper;

import FlyFinder.com.FlyFinderBackend.Infra.DTO.PassagensDto;
import FlyFinder.com.FlyFinderBackend.Infra.Persistence.entities.PassagensModel;
import FlyFinder.com.FlyFinderBackend.Core.Domain.Passagem;

public class PassagensMapper {

    public static PassagensModel toModel(PassagensDto dto) {
        return new PassagensModel(
                dto.getId(),
                dto.getOrigem(),
                dto.getDestino(),
                dto.getDataIda(),
                dto.getDataVolta()
        );
    }

    public static PassagensDto toDto(PassagensModel model) {
        return new PassagensDto(
                model.getId(),
                model.getOrigem(),
                model.getDestino(),
                model.getDataIda(),
                model.getDataVolta()
        );
    }

    public static PassagensModel toModel(Passagem domain) {
        return new PassagensModel(
                domain.id(),
                domain.origem(),
                domain.destino(),
                domain.dataIda(),
                domain.dataVolta()
        );
    }

    public static Passagem toDomain(PassagensModel model) {
        return new Passagem(
                model.getId(),
                model.getOrigem(),
                model.getDestino(),
                model.getDataIda(),
                model.getDataVolta()
        );
    }

    public static PassagensDto toDto(Passagem domain) {
        return new PassagensDto(
                domain.id(),
                domain.origem(),
                domain.destino(),
                domain.dataIda(),
                domain.dataVolta()
        );
    }

    public static Passagem toDomain(PassagensDto dto) {
        return new Passagem(
                dto.getId(),
                dto.getOrigem(),
                dto.getDestino(),
                dto.getDataIda(),
                dto.getDataVolta()
        );
    }
}
