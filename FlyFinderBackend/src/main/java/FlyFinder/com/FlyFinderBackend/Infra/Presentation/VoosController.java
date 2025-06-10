package FlyFinder.com.FlyFinderBackend.Infra.Presentation;

import FlyFinder.com.FlyFinderBackend.Core.Gateway.VoosGateway;
import FlyFinder.com.FlyFinderBackend.Core.Request.EncontrarVoosRequest;
import FlyFinder.com.FlyFinderBackend.Core.Responses.EncontrarVoosResponse;
import FlyFinder.com.FlyFinderBackend.Core.useCases.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/voos")
@RequiredArgsConstructor
public class VoosController{

    @Autowired
    public final EncontrarVooUseCase encontrarVooUseCase;

    @Autowired
    public final SalvarVooUseCase salvarVooUseCase;

    @Autowired
    public final BuscarVooPorIdUseCase buscarVooPorIdUseCase;

    @Autowired
    public final EditarVooUseCase editarVooUseCase;

    @Autowired
    public final DeletarVooUseCase deletarVooUseCase;

    @PostMapping("/find")
    public ResponseEntity<Map<String, Object>> encontrarVoos(@RequestBody EncontrarVoosResponse request){
        Optional<EncontrarVoosResponse> voo = encontrarVooUseCase.execute(request);

        Map<String, Object> response = new HashMap<>();
        response.put("Message: ", "Voo encontrado.");
        response.put("Body: ", voo.get());

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(response);
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> salvarVoos(@RequestBody EncontrarVoosResponse request){
        Optional<EncontrarVoosResponse> voo  = salvarVooUseCase.execute(request);

        Map<String, Object> response = new HashMap<>();
        response.put("Message: ", "Voo salvo.");
        response.put("Body: ", voo.get());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Map<String, Object>> encontrarVoos(@PathVariable Long id){
        Optional<EncontrarVoosResponse> voo = buscarVooPorIdUseCase.execute(id);

        Map<String, Object> response = new HashMap<>();
        response.put("Message: ", "Voo encontrado com sucesso.");
        response.put("Body: ", voo.get());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<Map<String, Object>> editarVoos(@PathVariable Long id, @RequestBody EncontrarVoosResponse request){
        Optional<EncontrarVoosResponse> voo = editarVooUseCase.execute(id, request);

        Map<String, Object> response = new HashMap<>();
        response.put("Message: ", "Voo editado com sucesso.");
        response.put("Body: ", voo.get());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletarVoos(@PathVariable Long id){
        String response = deletarVooUseCase.execute(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

}
