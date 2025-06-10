package FlyFinder.com.FlyFinderBackend.Core.useCases;

import FlyFinder.com.FlyFinderBackend.Core.Gateway.VoosGateway;
import FlyFinder.com.FlyFinderBackend.Core.Responses.EncontrarVoosResponse;

import java.util.Optional;

public class BuscarVooPorIdUseCaseImp implements BuscarVooPorIdUseCase{

    public final VoosGateway voosGateway;

    public BuscarVooPorIdUseCaseImp(VoosGateway voosGateway) {
        this.voosGateway = voosGateway;
    }

    @Override
    public Optional<EncontrarVoosResponse> execute(Long id) {
        return voosGateway.encontrarVooPorId(id);
    }

}
