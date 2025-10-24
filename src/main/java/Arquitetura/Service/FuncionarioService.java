package Arquitetura.Service;

import Arquitetura.Dao.AdministradorDAO;
import Arquitetura.Dao.FuncionarioDAO;
import Arquitetura.Dao.MedicoDAO;
import Arquitetura.Exception.CpfInvalidoException;
import Arquitetura.Exception.IdInvalidoException;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Enums.Especialidade;
import Arquitetura.Model.Funcionario;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.Validator.FuncionarioValidator;
import Arquitetura.Service.Validator.MedicoValidator;
import Arquitetura.Service.Validator.TipoUsuarioValidator;
import Arquitetura.Service.Validator.UsuarioValidator;

public class FuncionarioService {

    // -- Atributos -- //
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private final MedicoDAO medicoDAO = new MedicoDAO();
    private final AdministradorDAO administradorDAO = new AdministradorDAO();
    private final UsuarioService usuarioService = new UsuarioService();
    private final FuncionarioValidator funcionarioValidator = new FuncionarioValidator();
    private final UsuarioValidator usuarioValidator = new UsuarioValidator();
    private final TipoUsuarioValidator tipoUsuarioValidator = new TipoUsuarioValidator();

    // -- Métodos -- //
    public void cpfFuncionarioValidator (String cpf) {
        if(!medicoDAO.isCpfMedico(cpf) && !administradorDAO.isCpfAdministrador(cpf)) {
            throw new CpfInvalidoException("ERRO ! CPF NÃO PERTENCE A UM FUNCIONÁRIO");
        }
    }

    public void idFuncionarioValidator(long id) {
        if(!medicoDAO.isIdMedico(id) && !administradorDAO.isIdAdministrador(id)) {
            throw new IdInvalidoException("ERRO! O ID INFORMADO NÃO É DE UM FUNCIONÁRIO");
        }
    }

    public void updateSalario(Usuario usuario, long id, double salario) {
        tipoUsuarioValidator.temAcessoTotal(usuario);
        FuncionarioValidator.verificaIntegridadeSalario(salario);
        funcionarioValidator.verificaRegrasSalario(salario);
        usuarioService.idExistenteValidator(id);
        idFuncionarioValidator(id);;

        if(usuarioValidator.isAutoUpdate(usuario.getId(), id)) {
            ((Administrador) usuario).setSalario(salario);
        }

        funcionarioDAO.updateSalario(id, salario);
    }
}