package FlyFinder.com.FlyFinderBackend.Core.useCases.Voos;

import FlyFinder.com.FlyFinderBackend.Core.Responses.EncontrarVoosResponse;

import java.util.Optional;

public interface EncontrarVooUseCase {
    Optional<EncontrarVoosResponse> execute(EncontrarVoosResponse request);
}
