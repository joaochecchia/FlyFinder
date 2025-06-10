package FlyFinder.com.FlyFinderBackend.Core.Domain;

import FlyFinder.com.FlyFinderBackend.Core.Enums.Classes;
import FlyFinder.com.FlyFinderBackend.Core.Enums.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public record Passagem(
        UUID id,
        String assento,
        LocalDateTime dataCompra,
        Status status,
        Double valorFinal,
        Classes classes,
        UUID passageiroId,
        UUID viajemId
) { }
