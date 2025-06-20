package FlyFinder.com.FlyFinderBackend.Core.useCases.Passagens;

import FlyFinder.com.FlyFinderBackend.Core.Domain.Passagem;
import FlyFinder.com.FlyFinderBackend.Core.Gateway.PassagensGateway;

import java.util.List;
import java.util.Optional;

public class ListarPassagemUseCaseImp implements ListarPassagemUseCase{

    private final PassagensGateway passagensGateway;

    public ListarPassagemUseCaseImp(PassagensGateway passagensGateway) {
        this.passagensGateway = passagensGateway;
    }

    @Override
    public Optional<List<Passagem>> execute() {
        return passagensGateway.ListarPassagem();
    }
}
