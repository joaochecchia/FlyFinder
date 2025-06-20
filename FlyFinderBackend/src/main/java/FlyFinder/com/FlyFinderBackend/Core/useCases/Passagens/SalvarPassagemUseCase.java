package FlyFinder.com.FlyFinderBackend.Core.useCases.Passagens;

import FlyFinder.com.FlyFinderBackend.Core.Domain.Passagem;

import java.util.Optional;

public interface SalvarPassagemUseCase {
    Optional<Passagem> execute(Passagem passagem);
}
