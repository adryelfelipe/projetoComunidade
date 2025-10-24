package Arquitetura.Service.Validator;

import Arquitetura.Exception.MedicoNaoHabilitadoException;
import Arquitetura.Model.Enums.Exame;
import Arquitetura.Model.Medico;

public class ExameValidator {

    public void podeRealizarExame(Medico medico, Exame exame) {
        if(!medico.getEspecialidade().podeRealizar(exame)) {
            throw new MedicoNaoHabilitadoException("ERRO! O MÉDICO NÃO É HABILITADO PARA REALIZAR ESTE EXAME");
        }
    }
}
