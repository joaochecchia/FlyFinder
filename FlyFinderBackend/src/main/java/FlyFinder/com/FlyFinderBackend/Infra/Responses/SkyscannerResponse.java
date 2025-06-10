package FlyFinder.com.FlyFinderBackend.Infra.Responses;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public record SkyscannerResponse(
        List<Itinerary> itineraries,
        List<Leg> legs
) {
    public record Itinerary(
            String id,
            Map<String, String> price
    ) {}

    public record Leg(
            String id,
            String departure,
            Map<String, String> origin,
            Map<String, String> destination,
            List<Map<String, String>> carriers,
            int stopCount
    ) {}
}

