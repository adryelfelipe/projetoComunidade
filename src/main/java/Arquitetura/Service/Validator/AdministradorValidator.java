package Arquitetura.Service.Validator;

import Arquitetura.Exception.AutoDeleteException;
import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Usuario;

public class AdministradorValidator {

    // -- Métodos de verificação -- //
    public void verificarDadosAdm(Administrador administrador) {

        if(administrador.getDepartamento() == null)
        {
            throw new DadosInvalidosException("ERRO! O DEPARTAMENTO NÃO PODE SER NULO");
        }
    }

    public void verificaAutoDelete(Usuario usuarioDeletador, Administrador administradorDeletado) {
        if(usuarioDeletador == administradorDeletado) {
            throw new AutoDeleteException("ERRO! NÃO É PERMITIDO DELETAR A SI MESMO");
        }
    }
}