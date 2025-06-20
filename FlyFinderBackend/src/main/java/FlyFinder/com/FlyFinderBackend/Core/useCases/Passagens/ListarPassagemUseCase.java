package FlyFinder.com.FlyFinderBackend.Core.useCases.Passagens;

import FlyFinder.com.FlyFinderBackend.Core.Domain.Passagem;

import java.util.List;
import java.util.Optional;

public interface ListarPassagemUseCase {
    Optional<List<Passagem>> execute();
}
