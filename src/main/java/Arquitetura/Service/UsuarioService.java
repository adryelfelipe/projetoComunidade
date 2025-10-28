package Arquitetura.Service;

import Arquitetura.Dao.*;
import Arquitetura.Dao.UsuarioDAO.ReadUsuarioDAO;
import Arquitetura.Dao.UsuarioDAO.UpdateUsuarioDAO;
import Arquitetura.Exception.*;
import Arquitetura.Model.Enums.Genero;
import Arquitetura.Model.Enums.TipoUsuario;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.Validator.TipoUsuarioValidator;
import Arquitetura.Service.Validator.UsuarioValidator;

import java.sql.Date;
import java.util.ArrayList;

public class UsuarioService {

    // -- Atributos -- //
    private final ReadUsuarioDAO readUsuarioDAO = new ReadUsuarioDAO();
    private final MedicoDAO medicoDAO = new MedicoDAO();
    private final AdministradorDAO administradorDAO = new AdministradorDAO();
    TipoUsuarioValidator tipoUsuarioValidator = new TipoUsuarioValidator();
    UsuarioValidator usuarioValidator = new UsuarioValidator();

    // -- Construtor -- //
    public UsuarioService() {
    }

    // -- Métodos -- //
    public void validaUpdateUsuario(Usuario usuarioInsersor, long idUsuario) {
        usuarioValidator.verificaRegrasUsuarioInsersor(usuarioInsersor);
        idExistenteValidator(idUsuario);
    }

    public void telefoneUtilizadoValidator(String telefone) {
        if(readUsuarioDAO.containsTelefone(telefone)) {
            throw new TelefoneInvalidoException("ERRO! TELEFONE JÁ UTILIZADO");
        }
    }

    public void cpfExistenteValidator(String cpf) {
        if(!readUsuarioDAO.verificarCpf(cpf)) {
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


    public void emailUtilizadoValidator(String email) {
        if(usuarioDao.containsEmail(email)) {
            throw new EmailInvalidoException("ERRO! EMAIL JÁ UTILIZADO");
        }
    }

    // Faz procura no banco de dados por Id
    public Usuario findById(Usuario usuario, long id) {
        if(usuario.getTipoUsuario().getNivelAcesso().temAcessoTotal()) {
        idExistenteValidator(id);

            return usuarioDao.findById(id);
        }

        return null;
    }

    public void validaUsuarioInserido(Usuario usuario) {
        cpfUtilizadoValidator(usuario.getCpf());
        telefoneUtilizadoValidator(usuario.getTelefone());
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
        if(!usuarioDao.verificarCpf(cpf)) {
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
        validaUpdateUsuario(usuario, id);
        tipoUsuarioValidator.temAcessoTotal(usuario);
        UsuarioValidator.verificaIntegridadeCpf(cpf);
        usuarioValidator.verificarRegrasCpf(cpf);
        cpfUtilizadoValidator(cpf);
        usuarioDao.updateCpf(id, cpf);

        if(usuarioValidator.isAutoUpdate(usuario.getId(), id)) {
            usuario.setCpf(cpf);
        }
    }

    public void updateNomeUsuario(Usuario usuario, long id, String nome) {
        validaUpdateUsuario(usuario, id);
        tipoUsuarioValidator.temAcessoTotal(usuario);
        UsuarioValidator.verificaIntegridadeNome(nome);
        usuarioValidator.verificarRegrasNome(nome);
        usuarioDao.updateNomeUsuario(id, nome);

        if(usuarioValidator.isAutoUpdate(usuario.getId(), id)) {
            usuario.setNome(nome);
        }
    }

    public void updateTelefoneUsuario(Usuario usuario, long id, String telefone) {
        validaUpdateUsuario(usuario, id);
        tipoUsuarioValidator.temAcessoBaixo(usuario);
        UsuarioValidator.verificaIntegridadeTelefone(telefone);
        usuarioValidator.verificarRegrasTelefone(telefone);
        telefoneUtilizadoValidator(telefone);
        usuarioDao.updateTelefone(id, telefone);

        if(usuarioValidator.isAutoUpdate(usuario.getId(), id)) {
            usuario.setTelefone(telefone);
        }
    }

    public void updateEmailUsuario(Usuario usuario, long id, String email) {
        validaUpdateUsuario(usuario, id);
        tipoUsuarioValidator.temAcessoBaixo(usuario);
        UsuarioValidator.verificaIntegridadeEmail(email);
        usuarioValidator.verificarRegrasEmail(email);
        emailUtilizadoValidator(email);
        usuarioDao.updateEmail(id, email);

        if(usuarioValidator.isAutoUpdate(usuario.getId(), id)) {
            usuario.setEmail(email);
        }
    }

    public void updateSenhaUsuario(Usuario usuario, long id, String senha) {
        validaUpdateUsuario(usuario, id);
        tipoUsuarioValidator.temAcessoBaixo(usuario);
        UsuarioValidator.verificaIntegridadeSenha(senha);
        usuarioValidator.verificarRegrasSenha(senha);
        usuarioDao.updateSenhaUsuario(id, senha);

        if(usuarioValidator.isAutoUpdate(usuario.getId(), id)) {
            usuario.setSenha(senha);
        }
    }


    public void updateTipoUsuario(Usuario usuario,long id, TipoUsuario tipoUsuario) {
        validaUpdateUsuario(usuario, id);
        tipoUsuarioValidator.temAcessoTotal(usuario);
        usuarioValidator.verificarRegrasTipoUsuario(tipoUsuario);
        usuarioDao.updateTipoUsuario(id, tipoUsuario.getIdTipoUsuario());

        if(usuarioValidator.isAutoUpdate(usuario.getId(), id)) {
            usuario.setTipoUsuario(tipoUsuario);
        }
    }

    public void updateSexo(Usuario usuario,long id, Genero sexo) {
        validaUpdateUsuario(usuario, id);
        tipoUsuarioValidator.temAcessoTotal(usuario);
        usuarioValidator.verificarRegrasSexo(sexo);
        usuarioDao.updateSexo(id, sexo.getIdGenero());

        if(usuarioValidator.isAutoUpdate(usuario.getId(), id)) {
            usuario.setSexo(sexo);
        }
    }

    public void updateDataNascimento(Usuario usuario, long id, Date dataNascimento) {
        validaUpdateUsuario(usuario, id);
        tipoUsuarioValidator.temAcessoTotal(usuario);
        usuarioValidator.verificarRegrasDataNascimento(dataNascimento);
        usuarioDao.updateDataNascimento(id, dataNascimento);

        if(usuarioValidator.isAutoUpdate(usuario.getId(), id)) {
            usuario.setDataNascimento(dataNascimento);
        }
    }
}
