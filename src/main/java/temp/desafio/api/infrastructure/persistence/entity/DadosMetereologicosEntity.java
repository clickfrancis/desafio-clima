package temp.desafio.api.infrastructure.persistence.entity;


import jakarta.persistence.*;
import lombok.*;
import temp.desafio.api.core.enums.TipoClima;
import temp.desafio.api.core.enums.TipoTurno;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "DadosMetereologicos")
@Table(name = "dados_metereologicos")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class DadosMetereologicosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String cidade;
    LocalDate data;
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
