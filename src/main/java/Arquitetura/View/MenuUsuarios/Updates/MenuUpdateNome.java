package Arquitetura.View.MenuUsuarios.Updates;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Exception.IdInvalidoException;
import Arquitetura.Exception.TipoUsuarioException;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.UsuarioService;
import Arquitetura.Service.Validator.UsuarioValidator;
import Arquitetura.Utilidades.Ferramentas;

public class MenuUpdateNome {

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
}
