package FlyFinder.com.FlyFinderBackend.Core.useCases.Passagens;

import FlyFinder.com.FlyFinderBackend.Core.Domain.Passagem;
import FlyFinder.com.FlyFinderBackend.Infra.Mapper.PassagensMapper;

import java.util.Optional;

public interface BuscarPassagemPorId {
    Optional<Passagem> execute(Long id);
}
