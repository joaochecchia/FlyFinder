package FlyFinder.com.FlyFinderBackend.Infra.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PassagensDto {
    private Integer id;
    private String origem;
    private String destino;
    private Date dataIda;
    private Date dataVolta;
}
