package temp.desafio.api.infrastructure.persistence.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import temp.desafio.api.infrastructure.persistence.entity.DadosMetereologicosEntity;

import java.util.Optional;

@Repository
@Transactional
public interface DadosMetereologicosRepository extends JpaRepository<DadosMetereologicosEntity, Long> {

    Optional<DadosMetereologicosEntity> findByCidade(String cidade);

    }
