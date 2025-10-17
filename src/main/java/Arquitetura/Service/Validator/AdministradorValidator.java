package Arquitetura.Service.Validator;

import Arquitetura.Exception.AutoDeleteException;
import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Usuario;

public class AdministradorValidator {

    // -- Atributos -- //
    private final FuncionarioValidator funcionarioValidator = new FuncionarioValidator();

    // -- Métodos de verificação -- //
    public void verificarDadosAdm(Administrador administrador) {
        funcionarioValidator.verificarDadosFuncionario(administrador);

        if(administrador.getDepartamento() == null)
        {
            throw new DadosInvalidosException("ERRO! O DEPARTAMENTO NÃO PODE SER NULO");
        }
    }

    public void verificaAutoDelete(String cpfUsuarioDeletador, String cpfAdministradorDeletado) {
        if(cpfUsuarioDeletador.equals(cpfAdministradorDeletado)) {
            throw new AutoDeleteException("ERRO! NÃO É PERMITIDO DELETAR A SI MESMO");
        }
    }
}