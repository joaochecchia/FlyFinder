package FlyFinder.com.FlyFinderBackend.Core.useCases;

import FlyFinder.com.FlyFinderBackend.Core.Request.EncontrarVoosRequest;
import FlyFinder.com.FlyFinderBackend.Core.Responses.EncontrarVoosResponse;

import java.util.Optional;

public interface EncontrarVooUseCase {
    Optional<EncontrarVoosResponse> execute(EncontrarVoosResponse request);
}
