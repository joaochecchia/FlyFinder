package FlyFinder.com.FlyFinderBackend.Core.useCases;

import FlyFinder.com.FlyFinderBackend.Core.Gateway.VoosGateway;
import FlyFinder.com.FlyFinderBackend.Core.Request.EncontrarVoosRequest;
import FlyFinder.com.FlyFinderBackend.Core.Responses.EncontrarVoosResponse;

import java.util.Optional;

public class EditarVooUseCaseImp implements EditarVooUseCase {

    public final VoosGateway voosGateway;

    public EditarVooUseCaseImp(VoosGateway voosGateway) {
        this.voosGateway = voosGateway;
    }

    @Override
    public Optional<EncontrarVoosResponse> execute(Long id, EncontrarVoosResponse request) {
        return voosGateway.editarVoos(id, request);
    }
}
