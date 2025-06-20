package FlyFinder.com.FlyFinderBackend.Core.useCases.Passagens;

import FlyFinder.com.FlyFinderBackend.Core.Domain.Passagem;
import FlyFinder.com.FlyFinderBackend.Core.Gateway.PassagensGateway;

import java.util.Optional;

public class EditarPassagemUseCaseImp implements EditarPassagemUseCase{

    final private PassagensGateway passagensGateway;

    public EditarPassagemUseCaseImp(PassagensGateway passagensGateway) {
        this.passagensGateway = passagensGateway;
    }

    @Override
    public Optional<Passagem> execute(Long id, Passagem passagem) {
        return passagensGateway.editarPassagem(id, passagem);
    }
}
