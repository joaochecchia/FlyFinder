package FlyFinder.com.FlyFinderBackend.Infra.Gateway;

import FlyFinder.com.FlyFinderBackend.Core.Gateway.VoosGateway;
import FlyFinder.com.FlyFinderBackend.Core.Request.EncontrarVoosRequest;
import FlyFinder.com.FlyFinderBackend.Core.Responses.EncontrarVoosResponse;
import FlyFinder.com.FlyFinderBackend.Core.useCases.Voos.EncontrarVooUseCaseImp;
import FlyFinder.com.FlyFinderBackend.Infra.Mapper.VoosMapper;
import FlyFinder.com.FlyFinderBackend.Infra.Persistence.entities.VoosModel;
import FlyFinder.com.FlyFinderBackend.Infra.Persistence.repository.VoosRepository;
import FlyFinder.com.FlyFinderBackend.Infra.Responses.SkyscannerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VoosRepositoryGateway implements VoosGateway {

    public final WebClient webClient;

    public final EncontrarVooUseCaseImp encontrarVooUseCaseImp;

    public final VoosRepository voosRepository;

    public final VoosMapper voosMapper;

    @Autowired
    public VoosRepositoryGateway(WebClient.Builder builder, EncontrarVooUseCaseImp encontrarVooUseCaseImp, VoosRepository voosRepository, VoosMapper voosMapper) {
        this.webClient = builder.build();
        this.encontrarVooUseCaseImp = encontrarVooUseCaseImp;
        this.voosRepository = voosRepository;
        this.voosMapper = voosMapper;
    }

    @Override
    public Optional<EncontrarVoosResponse> encontrarVoos(EncontrarVoosRequest request) {
        String origem = request.partida();
        String destino = request.destino();
        String data = request.dataIda();

        try {
            Mono<EncontrarVoosResponse> responseMono = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("https")
                            .host("skyscanner44.p.rapidapi.com")
                            .path("/search")
                            .queryParam("from", origem)
                            .queryParam("to", destino)
                            .queryParam("departDate", data)
                            .queryParam("adults", "1")
                            .build())
                    .header("X-RapidAPI-Key", "SUA_CHAVE_RAPIDAPI")
                    .header("X-RapidAPI-Host", "skyscanner44.p.rapidapi.com")
                    .retrieve()
                    .bodyToMono(SkyscannerResponse.class)
                    .map(skyscanner -> {
                        var itinerary = skyscanner.itineraries().get(0);
                        var leg = skyscanner.legs().get(0);
                        var price = itinerary.price().get("amount");

                        return new EncontrarVoosResponse(
                                leg.origin().get("name"),
                                leg.origin().get("id"),
                                leg.destination().get("name"),
                                leg.destination().get("id"),
                                leg.carriers().get(0).get("name"),
                                Double.parseDouble(price),
                                leg.stopCount() == 0,
                                LocalDateTime.parse(leg.departure())
                        );
                    });

            return Optional.ofNullable(responseMono.block());

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<EncontrarVoosResponse> salvarVoos(EncontrarVoosResponse request) {
        VoosModel novoVoo = voosRepository.save(voosMapper.toModel(request));

        return Optional.of(voosMapper.toResponse(novoVoo));
    }

    @Override
    public Optional<EncontrarVoosResponse> encontrarVooPorId(Long id) {
        Optional<VoosModel> voo = voosRepository.findById(id);

        return Optional.of(voosMapper.toResponse(voo.get()));
    }

    @Override
    public Optional<EncontrarVoosResponse> editarVoos(Long id, EncontrarVoosResponse request) {
        Optional<VoosModel> vooExistente = voosRepository.findById(id);

        if (vooExistente.isPresent()) {
            VoosModel model = vooExistente.get();

            model.setOrigem(request.originCity());
            model.setDestino(request.destinationCity());
            model.setCompanhiaAerea(request.carrierName());
            model.setPreco(request.price());
            model.setDataPartida(request.departureDate().toString());
            model.setNumeroVoo(request.direct() ? "Direto" : "Com escalas");

            VoosModel atualizado = voosRepository.save(model);
            return Optional.of(voosMapper.toResponse(atualizado));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String deletarVoos(Long id) {
        Optional<VoosModel> voo = voosRepository.findById(id);

        if (voo.isPresent()) {
            voosRepository.deleteById(id);
            return "Voo deletado com sucesso.";
        } else {
            return "Voo não encontrado.";
        }
    }
}
