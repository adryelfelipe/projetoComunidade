package Arquitetura.Service;

import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Model.Usuario;

public class UsuarioService {

    // -- Atributos -- //
    UsuarioDAO usuarioDao = new UsuarioDAO();

    // -- Construtor -- //
    public UsuarioService() {

    }

    // -- Métodos -- //

    // -- Verifica se os atributos não são nulos -- //
    public boolean verificarDados(Usuario usuario) {
        return (usuario.getCpf() != null && usuario.getTelefone() != null && usuario.getTipoUsuario() != null && usuario.getSexo() != null && usuario.getNome() != null && usuario.getDataNascimento() != null && usuario.getEmail() != null && usuario.getSenha() != null);
    }

    // -- Verifica se já existe um email -- //
    public boolean verificarDuplicidade() {
        return true; // alterar para retornar a duplicidade
    }

    public void inserirUsuario(Usuario usuario) {
        if(verificarDados(usuario) && verificarDuplicidade()) {
            usuarioDao.inserirUsuario(usuario);
        }
    }
}
