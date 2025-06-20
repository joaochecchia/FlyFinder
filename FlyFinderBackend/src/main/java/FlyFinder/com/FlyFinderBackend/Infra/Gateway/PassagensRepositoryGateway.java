package FlyFinder.com.FlyFinderBackend.Infra.Gateway;

import FlyFinder.com.FlyFinderBackend.Core.Domain.Passagem;
import FlyFinder.com.FlyFinderBackend.Core.Gateway.PassagensGateway;
import FlyFinder.com.FlyFinderBackend.Infra.Mapper.PassagensMapper;
import FlyFinder.com.FlyFinderBackend.Infra.Persistence.entities.PassagensModel;
import FlyFinder.com.FlyFinderBackend.Infra.Persistence.repository.PassagensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PassagensRepositoryGateway implements PassagensGateway {

    public final PassagensRepository passagensRepository;
    public final PassagensMapper passagensMapper;

    @Autowired
    public PassagensRepositoryGateway(PassagensRepository passagensRepository, PassagensMapper passagensMapper) {
        this.passagensRepository = passagensRepository;
        this.passagensMapper = passagensMapper;
    }

    @Override
    public Optional<Passagem> encontrarPassagem(Long id) {
        Optional<PassagensModel> passagemModel = passagensRepository.findById(id);
        return passagemModel.map(PassagensMapper::toDomain);
    }

    @Override
    public Optional<Passagem> salvarPassagem(Passagem request) {
        PassagensModel model = PassagensMapper.toModel(request);
        PassagensModel salvo = passagensRepository.save(model);
        return Optional.of(PassagensMapper.toDomain(salvo));
    }

    @Override
    public Optional<List<Passagem>> ListarPassagem() {
        List<PassagensModel> passagemModel = passagensRepository.findAll();

        List<Passagem> passagens = passagemModel.stream()
                .map(PassagensMapper::toDomain)
                .collect(Collectors.toList());

        return Optional.of(passagens);
    }

    @Override
    public Optional<Passagem> editarPassagem(Long id, Passagem request) {
        Optional<PassagensModel> passagemExistente = passagensRepository.findById(id);

        if (passagemExistente.isPresent()) {
            PassagensModel model = passagemExistente.get();

            model.setOrigem(request.origem());
            model.setDestino(request.destino());
            model.setDataIda(request.dataIda());

            PassagensModel atualizado = passagensRepository.save(model);
            return Optional.of(PassagensMapper.toDomain(atualizado));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String deletarPassagem(Long id) {
        Optional<PassagensModel> passagem = passagensRepository.findById(id);

        if (passagem.isPresent()) {
            passagensRepository.deleteById(id);
            return "Passagem deletada com sucesso.";
        } else {
            return "Passagem não encontrada.";
        }
    }
}
