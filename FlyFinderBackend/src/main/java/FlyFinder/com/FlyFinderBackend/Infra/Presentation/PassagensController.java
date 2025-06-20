package FlyFinder.com.FlyFinderBackend.Infra.Presentation;

import FlyFinder.com.FlyFinderBackend.Core.Domain.Passagem;
import FlyFinder.com.FlyFinderBackend.Core.useCases.Passagens.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/passagens")
@RequiredArgsConstructor
public class PassagensController {

    private final BuscarPassagemPorId buscarPassagemPorId;
    private final SalvarPassagemUseCase salvarPassagemUseCase;
    private final ListarPassagemUseCase listarPassagemUseCase;
    private final EditarPassagemUseCase editarPassagemUseCase;
    private final DeletarPassagemPorId deletarPassagemUseCase;

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> salvarPassagem(@RequestBody Passagem request) {
        Optional<Passagem> passagem = salvarPassagemUseCase.execute(request);

        Map<String, Object> response = new HashMap<>();
        response.put("Message", "Passagem salva com sucesso.");
        response.put("Body", passagem.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Map<String, Object>> buscarPassagemPorId(@PathVariable Long id) {
        Optional<Passagem> passagem = buscarPassagemPorId.execute(id);

        Map<String, Object> response = new HashMap<>();
        response.put("Message", "Passagem encontrada com sucesso.");
        response.put("Body", passagem.get());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> listarPassagens() {
        Optional<List<Passagem>> passagens = listarPassagemUseCase.execute();

        Map<String, Object> response = new HashMap<>();
        response.put("Message", "Lista de passagens recuperada com sucesso.");
        response.put("Body", passagens.get());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Map<String, Object>> editarPassagem(@PathVariable Long id, @RequestBody Passagem request) {
        Optional<Passagem> passagem = editarPassagemUseCase.execute(id, request);

        Map<String, Object> response = new HashMap<>();
        response.put("Message", "Passagem editada com sucesso.");
        response.put("Body", passagem.get());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletarPassagem(@PathVariable Long id) {
        String mensagem = deletarPassagemUseCase.execute(id);

        return ResponseEntity.status(HttpStatus.OK).body(mensagem);
    }
}

