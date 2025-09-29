package Arquitetura.Service;

import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Model.Administrador;
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
    public boolean isEmailExistente() {
        return true; // alterar para retornar a duplicidade
    }

    // Insere os atributos gerais de Usuario na tabela Usuario do banco de dados
    public void inserirUsuario(Administrador administrador, Usuario usuario) {
        if(!(administrador == usuario))
        {
            if(verificarDados(usuario) && isEmailExistente()) {
                usuarioDao.inserirUsuario(usuario);
            }
        }
    }

    // Faz procura no banco de dados por Id
    public Usuario findById(Administrador administrador, long id) {
        return usuarioDao.findById(id);
    }
}
