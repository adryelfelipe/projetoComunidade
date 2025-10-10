package Arquitetura.Service.Validator;

import Arquitetura.Model.Administrador;
import Arquitetura.Model.Enums.Especialidade;
import Arquitetura.Model.Enums.Plantao;
import Arquitetura.Model.Medico;

public class MedicoValidator {

    // -- Métodos de verificação -- //
    public void verificarDadosMedico(Medico medico) {

        if(medico.getPlantao() == null)
        {
            throw new IllegalArgumentException("ERRO! O PLANTÃO NÃO PODE SER NULO");
        }

        if(medico.getEspecialidade() == null)
        {
            throw new IllegalArgumentException("ERRO! A ESPECIALIDADE NÃO PODE SER NULO");
        }

        if(medico.getSubEspecialidade() == null)
        {
            throw  new IllegalArgumentException("ERRO! A SUBESPECIALIDADE NÃO PODE SER NULO");
        }

        if(medico.getFormacao() == null)
        {
            throw new IllegalArgumentException("ERRO! A FORMAÇÃO NÃO PODE SER NULO");
        }
    }
}
