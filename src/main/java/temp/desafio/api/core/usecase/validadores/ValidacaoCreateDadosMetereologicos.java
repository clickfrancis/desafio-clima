package temp.desafio.api.core.usecase.validadores;

import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;

public interface ValidacaoCreateDadosMetereologicos {

    void validar(DadosMetereologicos dados);
}
