package FlyFinder.com.FlyFinderBackend.Core.useCases.Voos;

import FlyFinder.com.FlyFinderBackend.Core.Gateway.VoosGateway;
import FlyFinder.com.FlyFinderBackend.Core.Responses.EncontrarVoosResponse;

import java.util.Optional;

public class EncontrarVooUseCaseImp implements EncontrarVooUseCase{

    public final VoosGateway voosGateway;

    public EncontrarVooUseCaseImp(VoosGateway voosGateway) {
        this.voosGateway = voosGateway;
    }

    @Override
    public Optional<EncontrarVoosResponse> execute(EncontrarVoosResponse request) {
        return Optional.empty();
    }
}
