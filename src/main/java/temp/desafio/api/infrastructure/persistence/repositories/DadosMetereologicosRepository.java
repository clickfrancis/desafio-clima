package temp.desafio.api.infrastructure.persistence.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import temp.desafio.api.core.enums.TipoTurno;
import temp.desafio.api.infrastructure.persistence.entity.DadosMetereologicos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional
public interface DadosMetereologicosRepository extends JpaRepository<DadosMetereologicos, Long> {

    Optional<DadosMetereologicos> findByCidade(String cidade);

    Optional<DadosMetereologicos> findByData(LocalDateTime dateTime);

    Optional<DadosMetereologicos> findByCidadeAndDataAndTurno(String cidade, LocalDate data, TipoTurno turno);

    @Query("""
            select count(d) > 0
            from DadosMetereologicos d
            where
            d.cidade = :cidade
            and
            d.data = :data
            and
            d.turno = :turno
            """)
    Boolean findClimaAtivoByCidadeAndDataAndTuno(String cidade, LocalDate data, TipoTurno turno);
 }
