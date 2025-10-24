package Arquitetura.View.MenuUsuarios;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Exception.IdInvalidoException;
import Arquitetura.Exception.TipoUsuarioException;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.UsuarioService;
import Arquitetura.Service.Validator.UsuarioValidator;
import Arquitetura.Utilidades.Ferramentas;

public class MenuUpdate {

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

    public static void menuUpdateNome(Usuario usuario, long id, UsuarioValidator usuarioValidator, UsuarioService usuarioService) {
        boolean verifica = false;

        while(!verifica) {
            System.out.println("Digite o novo Nome: ");
            String nome = Ferramentas.lString();

            try {
                UsuarioValidator.verificaIntegridadeNome(nome);
                usuarioValidator.verificarRegrasNome(nome);
                usuarioService.updateNomeUsuario(usuario, id, nome);
                verifica = true;
            }catch (DadosInvalidosException | TipoUsuarioException e){
                Ferramentas.mensagemErro(e.getMessage());
            }  catch(IdInvalidoException e) {
                Ferramentas.mensagemErro(e.getMessage());
                return;
            }
        }
    }

    public static void menuUpdateSenha(Usuario usuario, long id, UsuarioValidator usuarioValidator, UsuarioService usuarioService) {
        boolean verifica = false;

        while(!verifica) {
            System.out.println("Digite a nova Senha: ");
            String senha = Ferramentas.lString();

            try {
                UsuarioValidator.verificaIntegridadeSenha(senha);
                usuarioValidator.verificarRegrasSenha(senha);
                usuarioService.updateSenhaUsuario(usuario, id, senha);
                verifica = true;
            }catch (DadosInvalidosException | TipoUsuarioException e){
                Ferramentas.mensagemErro(e.getMessage());
            }  catch(IdInvalidoException e) {
                Ferramentas.mensagemErro(e.getMessage());
                return;
            }
        }
    }

    public static void menuUpdateTelefone(UsuarioValidator usuarioValidator) {
        System.out.println("Digite o novo Telefone: ");
        String telefone = Ferramentas.lString();

        try {
            UsuarioValidator.verificaIntegridadeTelefone(telefone);
            usuarioValidator.verificarRegrasEmail(telefone);
        }catch (DadosInvalidosException e){
            Ferramentas.mensagemErro(e.getMessage());
        }
    }
}
