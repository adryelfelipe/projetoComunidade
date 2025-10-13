package Arquitetura.Service.Validator;

import Arquitetura.Model.Administrador;

public class AdministradorValidator {

    // -- Métodos de verificação -- //
    public void verificarDadosAdm(Administrador administrador) {

        if(administrador.getDepartamento() == null)
        {
            throw new IllegalArgumentException("ERRO! O DEPARTAMENTO NÃO PODE SER NULO");
        }
    }
}