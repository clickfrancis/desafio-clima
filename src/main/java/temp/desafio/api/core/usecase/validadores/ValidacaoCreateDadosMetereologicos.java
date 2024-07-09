package temp.desafio.api.core.usecase.validadores;

import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicosDTO;

public interface ValidacaoCreateDadosMetereologicos {

    void validar(DadosMetereologicosDTO dados);
}
