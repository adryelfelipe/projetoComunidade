package Arquitetura.View.MenuUsuarios.Updates;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Exception.IdInvalidoException;
import Arquitetura.Exception.TipoUsuarioException;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.UsuarioService;
import Arquitetura.Service.Validator.UsuarioValidator;
import Arquitetura.Utilidades.Ferramentas;

public class MenuUpdateSenha {

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
}
