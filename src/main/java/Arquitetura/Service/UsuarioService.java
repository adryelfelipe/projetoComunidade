package Arquitetura.Service;

import Arquitetura.Dao.*;
import Arquitetura.Exception.CpfInvalidoException;
import Arquitetura.Exception.IdInvalidoException;
import Arquitetura.Exception.SenhaInvalidaException;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.Validator.TipoUsuarioValidator;
import Arquitetura.Service.Validator.UsuarioValidator;

import java.sql.Date;
import java.util.ArrayList;
import java.util.EnumMap;

public class UsuarioService {

    // -- Atributos -- //
    private final UsuarioDAO usuarioDao = new UsuarioDAO();
    private final MedicoDAO medicoDAO = new MedicoDAO();
    private final AdministradorDAO administradorDAO = new AdministradorDAO();
    TipoUsuarioValidator tipoUsuarioValidator = new TipoUsuarioValidator();
    UsuarioValidator usuarioValidator = new UsuarioValidator(this);

    // -- Construtor -- //
    public UsuarioService() {
    }

    // -- Métodos -- //
    private void cpfExistenteValidator(String cpf) {
        if(!usuarioDao.verificarCpf(cpf)) {
            throw new CpfInvalidoException("ERRO! O CPF NÃO FOI ENCONTRADO");
        }
    }

    public void idExistenteValidator(long id) {
        if(!isIdExistente(id)) {
            throw new IdInvalidoException("ERRO! ID NÃO ENCONTRADO");
        }
    }

    public void cpfUtilizadoValidator(String cpf) {
        if(usuarioDao.verificarCpf(cpf)) {
            throw new CpfInvalidoException("ERRO! CPF JÁ UTILIZADO");
        }
    }

    // Verifica se existe um usuario com o id igual ao parâmetro
    public boolean isIdExistente(long id) {
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

    private void senhaUsuarioValidator(String cpf, String senha) {
        if(!usuarioDao.verificarSenha(cpf, senha)) {
            throw new SenhaInvalidaException("ERRO! SENHA INCORRETA");
        }
    }

    // Retorna uma ArrayList contendo todos os usuários do banco de dados
    public ArrayList<Usuario> findAllUsers(Usuario usuario) {
        if(usuario.getTipoUsuario().getNivelAcesso().temAcessoTotal()) {
            return usuarioDao.findAllUsers();
        }

        return null;
    }

    public Usuario loginUsuario(String cpf, String senha) {
        cpfExistenteValidator(cpf);
        senhaUsuarioValidator(cpf, senha);

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

    public void updateCpf(Usuario usuario, long id, String cpf) {
        tipoUsuarioValidator.temAcessoTotal(usuario);
        UsuarioValidator.verificaIntegridadeCpf(cpf);
        usuarioValidator.verificarRegrasCpf(cpf);
        idExistenteValidator(id);
        cpfUtilizadoValidator(cpf);
        usuarioDao.updateCpf(id, cpf);

        if(usuarioValidator.isAutoUpdate(usuario.getId(), id)) {
            usuario.setCpf(cpf);
        }
    }

    public void updateNomeUsuario(Usuario usuario, long id, String nome) {
        tipoUsuarioValidator.temAcessoTotal(usuario);
        UsuarioValidator.verificaIntegridadeNome(nome);
        usuarioValidator.verificarRegrasNome(nome);
        idExistenteValidator(id);
        usuarioDao.updateNomeUsuario(id, nome);

        if(usuarioValidator.isAutoUpdate(usuario.getId(), id)) {
            usuario.setNome(nome);
        }
    }

    public void updateTelefoneUsuario(Usuario usuario, long id, String telefone) {
        tipoUsuarioValidator.temAcessoBaixo(usuario);
        UsuarioValidator.verificaIntegridadeTelefone(telefone);
        usuarioValidator.verificarRegrasTelefone(telefone);
        idExistenteValidator(id);
        usuarioDao.updateTelefone(id, telefone);

        if(usuarioValidator.isAutoUpdate(usuario.getId(), id)) {
            usuario.setTelefone(telefone);
        }
    }

    public void updateEmailUsuario(Usuario usuario, long id, String email) {
        tipoUsuarioValidator.temAcessoBaixo(usuario);
        UsuarioValidator.verificaIntegridadeEmail(email);
        usuarioValidator.verificarRegrasEmail(email);
        idExistenteValidator(id);
        usuarioDao.updateEmail(id, email);

        if(usuarioValidator.isAutoUpdate(usuario.getId(), id)) {
            usuario.setEmail(email);
        }
    }

    public void updateSenhaUsuario(Usuario usuario, long id, String senha) {
        tipoUsuarioValidator.temAcessoBaixo(usuario);
        UsuarioValidator.verificaIntegridadeSenha(senha);
        usuarioValidator.verificarRegrasSenha(senha);
        idExistenteValidator(id);
        usuarioDao.updateSenhaUsuario(id, senha);

        if(usuarioValidator.isAutoUpdate(usuario.getId(), id)) {
            usuario.setSenha(senha);
        }
    }

    public void updateDataNascimentoUsuario(Usuario usuario, long id, Date dataNascimento) {
        tipoUsuarioValidator.temAcessoTotal(usuario);
        usuarioValidator.verificarRegrasDataNascimento(dataNascimento);
        idExistenteValidator(id);
        usuarioDao.updateDataNascimento(id, dataNascimento);

        if(usuarioValidator.isAutoUpdate(usuario.getId(), id)) {
            usuario.setDataNascimento(dataNascimento);
        }
    }
}
