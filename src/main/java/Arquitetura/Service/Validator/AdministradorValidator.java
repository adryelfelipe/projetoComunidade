package Arquitetura.Service.Validator;

import Arquitetura.Exception.AutoDeleteException;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Usuario;

public class AdministradorValidator {

    // -- Métodos de verificação -- //
    public void verificarDadosAdm(Administrador administrador) {

        if(administrador.getDepartamento() == null)
        {
            throw new IllegalArgumentException("ERRO! O DEPARTAMENTO NÃO PODE SER NULO");
        }
    }

    public void verificaAutoDelete(Usuario usuarioDeletador, Administrador administradorDeletado) {
        if(usuarioDeletador == administradorDeletado) {
            throw new AutoDeleteException("ERRO! NÃO É PERMITIDO DELETAR A SI MESMO");
        }
    }
}