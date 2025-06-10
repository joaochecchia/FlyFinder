package FlyFinder.com.FlyFinderBackend.Core.useCases;

import FlyFinder.com.FlyFinderBackend.Core.Request.EncontrarVoosRequest;
import FlyFinder.com.FlyFinderBackend.Core.Responses.EncontrarVoosResponse;

import java.util.Optional;

public interface EditarVooUseCase {
    Optional<EncontrarVoosResponse> execute(Long id, EncontrarVoosResponse request);
}
