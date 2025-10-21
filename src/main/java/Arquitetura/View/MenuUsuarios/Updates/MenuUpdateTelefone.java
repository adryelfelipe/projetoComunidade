package Arquitetura.View.MenuUsuarios.Updates;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Service.Validator.UsuarioValidator;
import Arquitetura.Utilidades.Ferramentas;

public class MenuUpdateTelefone {

    public static void menuUpdateTelefone(UsuarioValidator usuarioValidator) {
        System.out.println("Digite seu novo Telefone: ");
        String telefone = Ferramentas.lString();

        try {
            UsuarioValidator.verificaIntegridadeTelefone(telefone);
            usuarioValidator.verificarRegrasEmail(telefone);
        }catch (DadosInvalidosException e){
            Ferramentas.mensagemErro(e.getMessage());
        }
    }
}
