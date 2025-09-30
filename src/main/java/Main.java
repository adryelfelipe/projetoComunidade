import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Model.Usuario;
import Arquitetura.View.MenuInicial;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) throws SQLException {

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        ArrayList<Usuario> listaUsuarios = usuarioDAO.findAllUsers();

        for(Usuario entrada : listaUsuarios)
        {
            System.out.println(entrada.getId()+" | "+entrada.getNome()+" | "+entrada.getTipoUsuario());
        }
    }
}