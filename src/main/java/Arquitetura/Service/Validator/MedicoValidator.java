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

    // -- Métodos verificadores de regras de negócio -- //
    public void verificaRegrasInsercaoMedico(Medico medico) {
        funcionarioValidator.verificaRegrasInsercaoFuncionario(medico);
        verificaRegrasEspecialidade(medico.getEspecialidade());
        verificaRegrasFormacao(medico.getFormacao());
        verificaRegrasPlantao(medico.getPlantao());
    }

    public void verificaRegrasFormacao(String formacao) {
        if(formacao == null) {
            throw new DadosInvalidosException("ERRO! A FORMAÇÃO NÃO PODE SER NULA");
        }
    }

    public void verificaRegrasPlantao(Plantao plantao) {
        if(plantao == null) {
            throw new DadosInvalidosException("ERRO! O PLANTÃO NÃO PODE SER NULO");
        }
    }

    public void verificaRegrasEspecialidade(Especialidade especialidade) {
        if(especialidade == null) {
            throw new DadosInvalidosException("ERRO! A ESPECIALIDADE NÃO PODE SER NULA");
        }
    }

    // -- Métodos verificadores de integridade de dados -- //
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
