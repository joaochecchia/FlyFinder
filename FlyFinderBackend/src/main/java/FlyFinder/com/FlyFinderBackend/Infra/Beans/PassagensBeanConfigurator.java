package FlyFinder.com.FlyFinderBackend.Infra.Beans;

import FlyFinder.com.FlyFinderBackend.Core.Gateway.PassagensGateway;
import FlyFinder.com.FlyFinderBackend.Core.Gateway.VoosGateway;
import FlyFinder.com.FlyFinderBackend.Core.useCases.Passagens.*;
import FlyFinder.com.FlyFinderBackend.Core.useCases.Voos.*;
import jakarta.persistence.Column;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PassagensBeanConfigurator {

    @Bean
    public BuscarPassagemPorId encontrarPassagem(PassagensGateway passagensGateway){
        return new BuscarPassagemPorIdUseCaseImp(passagensGateway);
    }

    @Bean
    public SalvarPassagemUseCase salvarPassagensUseCase(PassagensGateway passagensGateway){
        return new SalvarPassagemUseCaseImp(passagensGateway);
    }

    @Bean
    public ListarPassagemUseCase buscarVooPorId(PassagensGateway passagemGateway){
        return new ListarPassagemUseCaseImp(passagemGateway);
    }

    @Bean
    public EditarPassagemUseCase editarVooUseCase(PassagensGateway passagensGateway){
        return new EditarPassagemUseCaseImp(passagensGateway);
    }

    @Bean
    public DeletarPassagemPorId deletarVooPorId(PassagensGateway passagensGateway){
        return new DeletarPassagemUseCaseImp(passagensGateway);
    }
}
