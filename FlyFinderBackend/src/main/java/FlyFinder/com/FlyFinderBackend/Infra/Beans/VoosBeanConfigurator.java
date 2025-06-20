package FlyFinder.com.FlyFinderBackend.Infra.Beans;

import FlyFinder.com.FlyFinderBackend.Core.Gateway.VoosGateway;
import FlyFinder.com.FlyFinderBackend.Core.useCases.Voos.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VoosBeanConfigurator {

    @Bean
    public EncontrarVooUseCase encontrarVoo(VoosGateway voosGateway){
        return new EncontrarVooUseCaseImp(voosGateway);
    }

    @Bean
    public SalvarVooUseCase salvarVooUseCase(VoosGateway voosGateway){
        return new SalvarVooUseCaseImp(voosGateway);
    }

    @Bean
    public BuscarVooPorIdUseCaseImp buscarVooPorId(VoosGateway voosGateway){
        return new BuscarVooPorIdUseCaseImp(voosGateway);
    }

    @Bean
    public EditarVooUseCase editarVooUseCase(VoosGateway voosGateway){
        return new EditarVooUseCaseImp(voosGateway);
    }

    @Bean
    public DeletarVooUseCase deletarVooPorId(VoosGateway voosGateway){
        return new DeletarVooUseCaseImp(voosGateway);
    }
}

