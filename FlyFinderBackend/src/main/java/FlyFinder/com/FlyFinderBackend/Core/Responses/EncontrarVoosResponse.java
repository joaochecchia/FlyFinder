package FlyFinder.com.FlyFinderBackend.Core.Responses;
import java.time.LocalDateTime;

public record EncontrarVoosResponse(
        String originCity,
        String originCode,
        String destinationCity,
        String destinationCode,
        String carrierName,
        double price,
        boolean direct,
        LocalDateTime departureDate
) { }
