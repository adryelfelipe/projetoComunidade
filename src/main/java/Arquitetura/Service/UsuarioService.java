package Arquitetura.Service;

import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Usuario;
import java.util.ArrayList;

public class UsuarioService {

    // -- Atributos -- //
    private final UsuarioDAO usuarioDao = new UsuarioDAO();

    // -- Construtor -- //
    public UsuarioService() {

    }

    // -- Métodos -- //

    // Verifica se os atributos não são nulos
    private boolean verificarDadosUser(Usuario usuario) {
        return (usuario.getCpf() != null && usuario.getTelefone() != null
                && usuario.getTipoUsuario() != null && usuario.getSexo() != null
                && usuario.getNome() != null && usuario.getDataNascimento() != null
                && usuario.getEmail() != null && usuario.getSenha() != null);
    }

    // Verifica se existe um usuario com o id igual ao parâmetro
    private boolean isIdExistente(long id) {
        return !(usuarioDao.findById(id) == null);
    }

    // Verifica se já existe um email igual ao parâmetro
    private boolean isEmailExistente(String email) {
        return usuarioDao.containsEmail(email); // alterar para retornar a duplicidade
    }

    // Verifica se já existe um cpf igual ao parâmetro
    private boolean isCpfExistente(String cpf) {
        return usuarioDao.verificarCpf(cpf);
    }

    // Insere os atributos gerais de Usuario na tabela Usuario do banco de dados
    public boolean inserirUsuario(Administrador administrador, Usuario usuario) { // Verifica as regras para inserir um Usuario
        if(!(administrador == usuario))
        {
            if(verificarDadosUser(usuario) && !isEmailExistente(usuario.getEmail()) && !isCpfExistente(usuario.getCpf())){
                usuarioDao.inserirUsuario(usuario);

                return true;
            }
        }

        return false;
    }

    // Faz procura no banco de dados por Id
    public Usuario findById(Administrador administrador, long id) {
        return usuarioDao.findById(id);
    }

    // Retorna uma ArrayList contendo todos os usuários do banco de dados
    public ArrayList<Usuario> findAllUsers(Administrador administrador) {

        return usuarioDao.findAllUsers();
    }

    // Cojunto de regras de negócio gerais para deletar qualquer tipo de usuario
    public boolean deletarUsuario(long id) {

        return isIdExistente(id);
    }
}
