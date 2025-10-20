package Arquitetura.Service.Validator;

import Arquitetura.Exception.AutoDeleteException;
import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Enums.Departamento;
import Arquitetura.Model.Usuario;

public class AdministradorValidator {

    // -- Construtor -- //
    public AdministradorValidator(FuncionarioValidator funcionarioValidator) {
        this.funcionarioValidator = funcionarioValidator;
    }

    // -- Atributos -- //
    private final FuncionarioValidator funcionarioValidator;

    // -- Métodos verificadores de regras de negócio -- //
    public void verificaRegrasInsercaoAdm(Administrador administrador) {
        funcionarioValidator.verificaRegrasInsercaoFuncionario(administrador);

        verificaRegrasDepartamento(administrador.getDepartamento());
    }

    public void verificaAutoDelete(String cpfUsuarioDeletador, String cpfAdministradorDeletado) {
        if(cpfUsuarioDeletador.equals(cpfAdministradorDeletado)) {
            throw new AutoDeleteException("ERRO! NÃO É PERMITIDO DELETAR A SI MESMO");
        }
    }

    public void verificaRegrasDepartamento(Departamento departamento) {
        if(departamento == null) {
            throw new DadosInvalidosException("ERRO! O DEPARTAMENTO NÃO PODE SER NULO");
        }
    }
}