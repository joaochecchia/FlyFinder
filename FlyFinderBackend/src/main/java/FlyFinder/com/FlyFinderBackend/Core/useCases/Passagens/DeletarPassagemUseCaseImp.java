package FlyFinder.com.FlyFinderBackend.Core.useCases.Passagens;

import FlyFinder.com.FlyFinderBackend.Core.Gateway.PassagensGateway;

public class DeletarPassagemUseCaseImp implements DeletarPassagemPorId{

    public final PassagensGateway passagensGateway;

    public DeletarPassagemUseCaseImp(PassagensGateway passagensGateway) {
        this.passagensGateway = passagensGateway;
    }

    @Override
    public String execute(Long id) {
        return passagensGateway.deletarPassagem(id);
    }
}
