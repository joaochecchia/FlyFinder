package FlyFinder.com.FlyFinderBackend.Core.useCases.Voos;

import FlyFinder.com.FlyFinderBackend.Core.Responses.EncontrarVoosResponse;

import java.util.Optional;

public interface EditarVooUseCase {
    Optional<EncontrarVoosResponse> execute(Long id, EncontrarVoosResponse request);
}
