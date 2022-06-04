package marqui.matheus.bulario.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Bula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 3000)
    private String conteudo;
}
