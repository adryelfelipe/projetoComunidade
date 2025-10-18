package Arquitetura.Service.Validator;

import Arquitetura.Exception.AutoDeleteException;
import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Enums.Departamento;
import Arquitetura.Model.Usuario;

public class AdministradorValidator {

    // -- Atributos -- //
    private final FuncionarioValidator funcionarioValidator = new FuncionarioValidator();

    // -- Métodos verificadores de regras de negócio -- //
    public void verificaRegrasInsercaoAdm(Administrador administrador) {
        funcionarioValidator.verificarDadosFuncionario(administrador);

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