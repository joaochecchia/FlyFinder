package FlyFinder.com.FlyFinderBackend.Infra.Persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Voos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "origem", nullable = false)
    private String origem;

    @Column(name = "destino", nullable = false)
    private String destino;

    @Column(name = "data_partida", nullable = false)
    private String dataPartida;

    @Column(name = "data_retorno")
    private String dataRetorno;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "companhia_aerea")
    private String companhiaAerea;

    @Column(name = "numero_voo")
    private String numeroVoo;
}
