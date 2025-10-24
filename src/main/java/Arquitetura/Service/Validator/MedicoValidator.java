package Arquitetura.Service.Validator;

import Arquitetura.Exception.CpfInvalidoException;
import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Exception.IdInvalidoException;
import Arquitetura.Exception.MedicoNaoHabilitadoException;
import Arquitetura.Model.Enums.Especialidade;
import Arquitetura.Model.Enums.Exame;
import Arquitetura.Model.Enums.Plantao;
import Arquitetura.Model.Medico;
import Arquitetura.Service.MedicoService;

public class MedicoValidator {

    // -- Atributos -- //
    private final FuncionarioValidator funcionarioValidator = new FuncionarioValidator();


    // -- Métodos verificadores de regras de negócio -- //
    public void verificaRegrasInsercaoMedico(Medico medico) {
        funcionarioValidator.verificaRegrasInsercaoFuncionario(medico);
        verificaRegrasEspecialidade(medico.getEspecialidade());
        verificaRegrasFormacao(medico.getFormacao());
        verificaRegrasPlantao(medico.getPlantao());
    }

    public void verificaRegrasSubEspecialidade(String subEspecialidade) {
        if(subEspecialidade == null) {
            return;
        }

        if(subEspecialidade.length() < 6) {
            throw new DadosInvalidosException("ERRO! SUBESPECIALIDADE DEVE CONTER MAIS DE 5 CARACTERES");
        }
    }

    public void verificaRegrasFormacao(String formacao) {
        if(formacao == null) {
            throw new DadosInvalidosException("ERRO! A FORMAÇÃO NÃO PODE SER NULA");
        }

        if(formacao.length() < 6) {
            throw new DadosInvalidosException("ERRO! A FORMAÇÃO DEVE CONTER MAIS DE 5 DÍGITOS");
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

    public void podeRealizarExame(Medico medico, Exame exame) {
        if(!medico.getEspecialidade().podeRealizar(exame)) {
            throw new MedicoNaoHabilitadoException("ERRO! O MÉDICO NÃO É HABILITADO PARA REALIZAR ESTE EXAME");
        }
    }

    // -- Métodos verificadores de integridade de dados -- //
    public static void verificaIntegridadeSubespecialidade(String subEspecialidade) {
        if(subEspecialidade.isBlank()) {
            throw new DadosInvalidosException("ERRO! SUBESPECIALIDADE NÃO PODE SER VAZIA");
        }
    }

    public static void verificaIntegridadeFormacao(String formacao) {
        if(formacao.isBlank()) {
            throw new DadosInvalidosException("ERRO! FORMAÇÃO NÃO PODE SER VAZIA");
        }
    }
}
