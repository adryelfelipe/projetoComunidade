package Arquitetura.Service.Validator;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Exception.MedicoNaoHabilitadoException;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Enums.Especialidade;
import Arquitetura.Model.Enums.Exame;
import Arquitetura.Model.Enums.Plantao;
import Arquitetura.Model.Medico;

public class MedicoValidator {

    // -- Atributos -- //
    FuncionarioValidator funcionarioValidator = new FuncionarioValidator();

    // -- Métodos de verificação -- //
    public void verificarInsercaoDadosMedico(Medico medico) {
        funcionarioValidator.verificarDadosFuncionario(medico);

        if(medico.getPlantao() == null)
        {
            throw new DadosInvalidosException("ERRO! O PLANTÃO NÃO PODE SER NULO");
        }

        if(medico.getEspecialidade() == null)
        {
            throw new DadosInvalidosException("ERRO! A ESPECIALIDADE NÃO PODE SER NULA");
        }

        if(medico.getFormacao() == null)
        {
            throw new DadosInvalidosException("ERRO! A FORMAÇÃO NÃO PODE SER NULA");
        }
    }

    public void podeRealizarExame(Medico medico, Exame exame) {
        if(!medico.getEspecialidade().podeRealizar(exame)) {
            throw new MedicoNaoHabilitadoException("ERRO! O MÉDICO NÃO É HABILITADO PARA REALIZAR ESTE EXAME");
        }
    }
}
