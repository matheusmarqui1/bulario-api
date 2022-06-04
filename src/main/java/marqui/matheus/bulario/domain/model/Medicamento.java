package marqui.matheus.bulario.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column
    private BigDecimal dosagem;

    @Column
    private BigDecimal peso;

    @Column(length = 60)
    private String ms;

    @Column(length = 60)
    private String ean;

    @Column(length = 60)
    private String fabricante;

    @OneToOne
    private Bula bula;


}
