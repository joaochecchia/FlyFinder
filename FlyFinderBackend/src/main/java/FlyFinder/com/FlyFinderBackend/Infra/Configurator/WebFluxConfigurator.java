package FlyFinder.com.FlyFinderBackend.Infra.Configurator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebFluxConfigurator {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}