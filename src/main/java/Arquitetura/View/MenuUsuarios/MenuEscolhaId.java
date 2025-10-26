package Arquitetura.View.MenuUsuarios;

import Arquitetura.Exception.IdInvalidoException;
import Arquitetura.Service.UsuarioService;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.MenuDefault;

import java.util.InputMismatchException;

public class MenuEscolhaId {

    public static long escolhaIdUpdate() {
        UsuarioService usuarioService = new UsuarioService();

        while(true) {
            System.out.print("DIGITE O ID: ");

            try {
                long id = Ferramentas.lInteiro();
                usuarioService.idExistenteValidator(id);
                return id;
            } catch (InputMismatchException e) {
                MenuDefault.menuDefault();
            }
        }
    }
}
