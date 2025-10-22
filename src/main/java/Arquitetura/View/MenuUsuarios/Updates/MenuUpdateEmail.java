package Arquitetura.View.MenuUsuarios.Updates;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Service.Validator.UsuarioValidator;
import Arquitetura.Utilidades.Ferramentas;

public class MenuUpdateEmail {

    public static void menuUpdateEmail(UsuarioValidator usuarioValidator) {
        System.out.println("Digite o novo Email: ");
        String email = Ferramentas.lString();

        try {
            UsuarioValidator.verificaIntegridadeEmail(email);
            usuarioValidator.verificarRegrasEmail(email);
        }catch (DadosInvalidosException e){
            Ferramentas.mensagemErro(e.getMessage());
        }
    }
}
