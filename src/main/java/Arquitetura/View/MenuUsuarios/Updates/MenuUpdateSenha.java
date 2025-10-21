package Arquitetura.View.MenuUsuarios.Updates;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Service.Validator.UsuarioValidator;
import Arquitetura.Utilidades.Ferramentas;

public class MenuUpdateSenha {

    public static void menuUpdateSenha(UsuarioValidator usuarioValidator) {
        System.out.println("Digite sua nova Senha: ");
        String senha = Ferramentas.lString();

        try {
            UsuarioValidator.verificaIntegridadeSenha(senha);
            usuarioValidator.verificarRegrasSenha(senha);
        }catch (DadosInvalidosException e){
            Ferramentas.mensagemErro(e.getMessage());
        }
    }
}
