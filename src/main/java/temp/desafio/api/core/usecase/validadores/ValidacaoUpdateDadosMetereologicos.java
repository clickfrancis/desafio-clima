package temp.desafio.api.core.usecase.validadores;

import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;

public interface ValidacaoUpdateDadosMetereologicos {

    void validar(DadosMetereologicos dados);
}
