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

    // -- Métodos de análise -- //
    public void podeRealizarExame(Medico medico, Exame exame) {
        if(!medico.getEspecialidade().podeRealizar(exame)) {
            throw new MedicoNaoHabilitadoException("ERRO! O MÉDICO NÃO É HABILITADO PARA REALIZAR ESTE EXAME");
        }
    }

    public void verificarInsercaoDadosMedico(Medico medico) {
        funcionarioValidator.verificaRegrasInsercaoFuncionario(medico);

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

    // - Métodos verificadores de integridade de dados - //
    public void verificaIntegridadeSubespecialidade(String subEspecialidade) {
        if(subEspecialidade.isBlank()) {
            throw new DadosInvalidosException("ERRO! SUBESPECIALIDADE NÃO PODE SER VAZIA");
        }
    }

    public void verificaIntegridadeFormacao(String formacao) {
        if(formacao.isBlank()) {
            throw new DadosInvalidosException("ERRO! FORMAÇÃO NÃO PODE SER VAZIA");
        }
    }
}
