package FlyFinder.com.FlyFinderBackend.Core.useCases.Voos;

import FlyFinder.com.FlyFinderBackend.Core.Gateway.VoosGateway;
import FlyFinder.com.FlyFinderBackend.Core.Responses.EncontrarVoosResponse;

import java.util.Optional;

public class SalvarVooUseCaseImp implements SalvarVooUseCase {

    private final VoosGateway voosGateway;

    public SalvarVooUseCaseImp(VoosGateway voosGateway) {
        this.voosGateway = voosGateway;
    }

    @Override
    public Optional<EncontrarVoosResponse> execute(EncontrarVoosResponse request) {
        return voosGateway.salvarVoos(request);
    }
}
