package FlyFinder.com.FlyFinderBackend.Core.useCases.Passagens;

import FlyFinder.com.FlyFinderBackend.Core.Domain.Passagem;

import java.util.Optional;

public interface EditarPassagemUseCase {
    Optional<Passagem> execute(Long id, Passagem passagem);
}
