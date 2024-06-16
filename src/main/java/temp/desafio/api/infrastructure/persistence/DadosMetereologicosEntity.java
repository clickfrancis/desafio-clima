package temp.desafio.api.infrastructure.persistence;


import jakarta.persistence.*;
import lombok.*;
import temp.desafio.api.core.enums.TipoClima;
import temp.desafio.api.core.enums.TipoTurno;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class DadosMetereologicosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String cidade;
    Date data;
    double tempMin;
    double tempMax;
    @Enumerated(EnumType.STRING)
    TipoTurno turno;
    @Enumerated(EnumType.STRING)
    TipoClima clima;
    double precipitacao;
    double umidade;
    double velDoVento;
}
