package Arquitetura.Service;

import Arquitetura.Dao.*;
import Arquitetura.Exception.CpfInvalidoException;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Paciente;
import Arquitetura.Model.Usuario;

import java.util.AbstractMap;
import java.util.ArrayList;

public class UsuarioService {

    // -- Atributos -- //
    private final UsuarioDAO usuarioDao = new UsuarioDAO();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final MedicoDAO medicoDAO = new MedicoDAO();
    private final AdministradorDAO administradorDAO = new AdministradorDAO();

    // -- Construtor -- //
    public UsuarioService() {

    }

    // -- Métodos -- //

    // Verifica se existe um usuario com o id igual ao parâmetro
    private boolean isIdExistente(long id) {
        return !(usuarioDao.findById(id) == null);
    }

    // Verifica se já existe um email igual ao parâmetro
    private boolean isEmailExistente(String email) {
        return usuarioDao.containsEmail(email); // alterar para retornar a duplicidade
    }

    // Verifica se já existe um cpf igual ao parâmetro
    public boolean isCpfExistente(String cpf) {
        return usuarioDao.verificarCpf(cpf);
    }

    // Faz procura no banco de dados por Id
    public Usuario findById(Usuario usuario, long id) {
        if(usuario.getTipoUsuario().getNivelAcesso().temAcessoTotal()) {

            return usuarioDao.findById(id);
        }

        return null;
    }

    // Retorna uma ArrayList contendo todos os usuários do banco de dados
    public ArrayList<Usuario> findAllUsers(Usuario usuario) {
        if(usuario.getTipoUsuario().getNivelAcesso().temAcessoTotal()) {
            return usuarioDao.findAllUsers();
        }

        return null;
    }

    public Usuario loginUsuario(String cpf, String senha) {
        if(!isCpfExistente(cpf)) {
            throw new CpfInvalidoException("ERRO! CPF INVÁLIDO");
        }

        return usuarioDao.loginUsuario(cpf,senha);
    }

    public int cpfParaTipoUsuario(String cpf) {
        if(!isCpfExistente(cpf)) {
            throw new CpfInvalidoException("ERRO! CPF INVÁLIDO");
        }

        if(administradorDAO.isCpfAdministrador(cpf)) {
            return 3;
        }

        if(medicoDAO.isCpfMedico(cpf)) {
            return 2;
        }

        // CPF DO PACIENTE
        return 1;
    }
}
