package FlyFinder.com.FlyFinderBackend.Infra.Persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Passagens")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PassagensModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "origem", nullable = false)
    private String origem;

    @Column(name = "destino", nullable = false)
    private String destino;

    @Column(name = "data_ida", nullable = false)
    private Date dataIda;

    @Column(name = "data_volta")
    private Date dataVolta;
}
