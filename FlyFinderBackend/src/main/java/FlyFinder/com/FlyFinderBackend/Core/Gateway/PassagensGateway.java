package FlyFinder.com.FlyFinderBackend.Core.Gateway;

import FlyFinder.com.FlyFinderBackend.Core.Domain.Passagem;
import FlyFinder.com.FlyFinderBackend.Core.Responses.EncontrarVoosResponse;

import java.util.List;
import java.util.Optional;

public interface PassagensGateway {
    Optional<Passagem> encontrarPassagem(Long id);
    Optional<Passagem> salvarPassagem(Passagem request);
    Optional<List<Passagem>> ListarPassagem();
    Optional<Passagem> editarPassagem(Long id, Passagem request);
    String deletarPassagem(Long id);
}
