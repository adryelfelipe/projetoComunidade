package Arquitetura.Service.Validator;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Enums.Especialidade;
import Arquitetura.Model.Enums.Plantao;
import Arquitetura.Model.Medico;

public class MedicoValidator {

    // -- Métodos de verificação -- //
    public void verificarDadosMedico(Medico medico) {

        if(medico.getPlantao() == null)
        {
            throw new DadosInvalidosException("ERRO! O PLANTÃO NÃO PODE SER NULO");
        }

        if(medico.getEspecialidade() == null)
        {
            throw new DadosInvalidosException("ERRO! A ESPECIALIDADE NÃO PODE SER NULO");
        }

        if(medico.getSubEspecialidade() == null)
        {
            throw  new DadosInvalidosException("ERRO! A SUBESPECIALIDADE NÃO PODE SER NULO");
        }

        if(medico.getFormacao() == null)
        {
            throw new DadosInvalidosException("ERRO! A FORMAÇÃO NÃO PODE SER NULO");
        }
    }
}
