package temp.desafio.api.infrastructure.persistence.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import temp.desafio.api.core.enums.TipoTurno;
import temp.desafio.api.infrastructure.persistence.entity.DadosMetereologicosEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional
public interface DadosMetereologicosRepository extends JpaRepository<DadosMetereologicosEntity, Long> {

    Optional<DadosMetereologicosEntity> findByCidade(String cidade);

    Optional<DadosMetereologicosEntity> findByData(LocalDateTime dateTime);

    Optional<DadosMetereologicosEntity> findByCidadeAndDataAndTurno(String cidade, LocalDate data, TipoTurno turno);

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
