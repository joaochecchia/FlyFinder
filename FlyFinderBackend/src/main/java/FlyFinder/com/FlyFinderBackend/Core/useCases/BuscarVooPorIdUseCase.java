package FlyFinder.com.FlyFinderBackend.Core.useCases;

import FlyFinder.com.FlyFinderBackend.Core.Responses.EncontrarVoosResponse;

import java.util.Optional;

public interface BuscarVooPorIdUseCase {
    Optional<EncontrarVoosResponse> execute(Long id);
}
