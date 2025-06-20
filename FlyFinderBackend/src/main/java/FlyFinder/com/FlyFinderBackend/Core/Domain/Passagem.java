package FlyFinder.com.FlyFinderBackend.Core.Domain;

import java.util.Date;

public record Passagem(
        Integer id,
        String origem,
        String destino,
        Date dataIda,
        Date dataVolta
) {
}
