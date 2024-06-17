package temp.desafio.api.core.dadosMetereologico.repositories;

import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;

import java.util.List;

public interface IDadosMetereologicos {

    DadosMetereologicos createDadosMetereologicos(DadosMetereologicos dadosMetereologicos);

    DadosMetereologicos findByCidade(String cidade);

    List<DadosMetereologicos> getAllDadosMetereologicos();

    void deleteDadosMeterologicos(DadosMetereologicos dadosMetereologicos);

    DadosMetereologicos updateDadosMeterologicos(DadosMetereologicos dadosMetereologicos);

}
