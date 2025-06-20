package FlyFinder.com.FlyFinderBackend.Core.useCases.Passagens;

import FlyFinder.com.FlyFinderBackend.Core.Domain.Passagem;
import FlyFinder.com.FlyFinderBackend.Core.Gateway.PassagensGateway;
import FlyFinder.com.FlyFinderBackend.Core.Gateway.VoosGateway;
import FlyFinder.com.FlyFinderBackend.Infra.Mapper.PassagensMapper;
import FlyFinder.com.FlyFinderBackend.Infra.Persistence.repository.VoosRepository;

import java.util.Optional;

public class BuscarPassagemPorIdUseCaseImp implements  BuscarPassagemPorId{

    public final PassagensGateway passagensGateway;

    public BuscarPassagemPorIdUseCaseImp(PassagensGateway passagensGateway) {
        this.passagensGateway = passagensGateway;
    }

    @Override
    public Optional<Passagem> execute(Long id) {
        return passagensGateway.encontrarPassagem(id);
    }
}
