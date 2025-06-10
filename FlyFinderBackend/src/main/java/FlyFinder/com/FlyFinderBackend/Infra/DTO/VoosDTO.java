package FlyFinder.com.FlyFinderBackend.Infra.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoosDTO{
    Integer id;
    String origem;
    String destino;
    String dataPartida;
    String dataRetorno;
    Double preco;
    String companhiaAerea;
    String numeroVoo;
}
