package FlyFinder.com.FlyFinderBackend.Core.Gateway;

import FlyFinder.com.FlyFinderBackend.Core.Request.EncontrarVoosRequest;
import FlyFinder.com.FlyFinderBackend.Core.Responses.EncontrarVoosResponse;
import FlyFinder.com.FlyFinderBackend.Core.useCases.EncontrarVooUseCaseImp;

import java.util.Optional;

public interface VoosGateway {
    Optional<EncontrarVoosResponse> encontrarVoos(EncontrarVoosRequest request);
    Optional<EncontrarVoosResponse> salvarVoos(EncontrarVoosResponse request);
    Optional<EncontrarVoosResponse> encontrarVooPorId(Long id);
    Optional<EncontrarVoosResponse> editarVoos(Long id, EncontrarVoosResponse request);
    String deletarVoos(Long id);
}
