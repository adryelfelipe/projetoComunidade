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

    // -- Construtor -- //
    public MedicoValidator(FuncionarioValidator funcionarioValidator, MedicoService medicoService) {
        this.funcionarioValidator = funcionarioValidator;
        this.medicoService = medicoService;
    }

    // -- Atributos -- //
    private final FuncionarioValidator funcionarioValidator;
    private final MedicoService medicoService;

    // -- Métodos de análise -- //
    public void idMedicoValidator(long id) {
        if (!medicoService.isIdMedico(id)) {
            throw new IdInvalidoException("ERRO! O ID INFORMADO NÃO É DE UM MEDICO");
        }
    }

    public void cpfMedicoValidator (String cpf)
    {
        if(!medicoService.isCpfMedico(cpf))
        {
            throw new CpfInvalidoException("ERRO ! CPF NÃO PERTENCE A UM MÉDICO");
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

    public void podeRealizarExame(Medico medico, Exame exame) {
        if(!medico.getEspecialidade().podeRealizar(exame)) {
            throw new MedicoNaoHabilitadoException("ERRO! O MÉDICO NÃO É HABILITADO PARA REALIZAR ESTE EXAME");
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
