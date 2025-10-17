package Arquitetura.Service.Validator;

import Arquitetura.Exception.TipoUsuarioException;
import Arquitetura.Model.Usuario;

public class TipoUsuarioValidator {

    // -- Métodos verificadores -- //
    public void temAcessoTotal(Usuario usuario) {
        if(!usuario.getTipoUsuario().getNivelAcesso().temAcessoTotal()) {
            throw new TipoUsuarioException("ERRO! É NECESSÁRIO TER ACESSO TOTAL");
        }
    }

    public void temAcessoModerado(Usuario usuario) {
        if(!usuario.getTipoUsuario().getNivelAcesso().temAcessoModerado()) {
            throw new TipoUsuarioException("ERRO! É NECESSÁRIO TER ACESSO MODERADO");
        }
    }

    public void temAcessoBaixo(Usuario usuario) {
        if(!usuario.getTipoUsuario().getNivelAcesso().temAcessoBaixo()) {
            throw new TipoUsuarioException("ERRO! É NECESSÁRIO TER ACESSO BAIXO");
        }
    }
}
