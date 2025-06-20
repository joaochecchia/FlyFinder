package FlyFinder.com.FlyFinderBackend.Core.useCases.Passagens;

import FlyFinder.com.FlyFinderBackend.Core.Domain.Passagem;
import FlyFinder.com.FlyFinderBackend.Core.Gateway.PassagensGateway;

import java.util.Optional;

public class SalvarPassagemUseCaseImp implements SalvarPassagemUseCase{

    private final PassagensGateway passagensGateway;

    public SalvarPassagemUseCaseImp(PassagensGateway passagensGateway) {
        this.passagensGateway = passagensGateway;
    }

    @Override
    public Optional<Passagem> execute(Passagem passagem) {
        return passagensGateway.salvarPassagem(passagem);
    }
}
