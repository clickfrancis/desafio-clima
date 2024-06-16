package temp.desafio.api.infrastructure.persistence;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import temp.desafio.api.core.dadosMetereologico.entities.DadosMetereologicos;

import java.util.Optional;

@Repository
@Transactional
public interface DadosMetereologicosRepository extends JpaRepository<DadosMetereologicosEntity, Long> {

    Optional<DadosMetereologicosEntity> findByCidade(String cidade);

    }
