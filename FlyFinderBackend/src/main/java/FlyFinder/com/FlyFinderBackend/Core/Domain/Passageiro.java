package FlyFinder.com.FlyFinderBackend.Core.Domain;

import java.util.List;
import java.util.UUID;

public record Passageiro(
    UUID id,
    String nome,
    String cpf,
    String email,
    String telefone,
    List<Passagem> passagens
) { }
