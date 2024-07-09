package temp.desafio.api.infrastructure.persistence.entity;


import jakarta.persistence.*;
import lombok.*;
import temp.desafio.api.core.enums.TipoClima;
import temp.desafio.api.core.enums.TipoTurno;
import java.time.LocalDate;

@Entity(name = "DadosMetereologicos")
@Table(name = "dados_metereologicos")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class DadosMetereologicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String cidade;
    LocalDate data;
    String tempMin;
    String tempMax;

    @Enumerated(EnumType.STRING)
    TipoTurno turno;

    @Enumerated(EnumType.STRING)
    TipoClima clima;

    String precipitacao;
    String umidade;
    String velDoVento;
}
