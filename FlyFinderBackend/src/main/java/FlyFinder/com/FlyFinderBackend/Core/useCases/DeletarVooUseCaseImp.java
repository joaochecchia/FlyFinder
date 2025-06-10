package FlyFinder.com.FlyFinderBackend.Core.useCases;

import FlyFinder.com.FlyFinderBackend.Core.Gateway.VoosGateway;
import FlyFinder.com.FlyFinderBackend.Infra.DTO.VoosDTO;

public class DeletarVooUseCaseImp implements DeletarVooUseCase{

    public final VoosGateway voosGateway;

    public DeletarVooUseCaseImp(VoosGateway voosGateway) {
        this.voosGateway = voosGateway;
    }

    @Override
    public String execute(Long id) {
        return voosGateway.deletarVoos(id);
    }
}
