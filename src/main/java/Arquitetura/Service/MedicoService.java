package Arquitetura.Service;

import Arquitetura.Dao.FuncionarioDAO;
import Arquitetura.Dao.MedicoDAO;
import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Medico;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.Validator.MedicoValidator;
import Arquitetura.Service.Validator.TipoUsuarioValidator;
import Arquitetura.Service.Validator.UsuarioValidator;

public class MedicoService {

    // -- Atributos -- //
    private final MedicoDAO medicoDAO = new MedicoDAO();
    private final FuncionarioService funcionarioService = new FuncionarioService();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private final TipoUsuarioValidator tipoUsuarioValidator = new TipoUsuarioValidator();
    private final UsuarioValidator usuarioValidator = new UsuarioValidator();
    private final MedicoValidator medicoValidator = new MedicoValidator();

    // Construtor -- //
    public MedicoService() {

    }

    // -- Métodos -- //

    // Insere o objeto do tipo Medico no banco de dados
    public void inserirMedico(Usuario usuario, Medico medicoInserido) {
        // Verificações de dados
        tipoUsuarioValidator.temAcessoTotal(usuario);
        usuarioValidator.verificaRegrasInsercaoUsuario(medicoInserido);
        medicoValidator.verificarDadosMedico(medicoInserido);

        // Insere nessa ordem para respeitar as chaves estrangeiras
        usuarioDAO.inserirUsuario(medicoInserido);
        funcionarioDAO.inserirFuncionario(medicoInserido);
        medicoDAO.inserirMedico(medicoInserido);
    }

    // Deleta medico do banco de dados
    public void deletarMedico(Usuario usuario, Medico medicoDeletado) {
        // Verificação de dados
        tipoUsuarioValidator.temAcessoTotal(usuario);
        usuarioValidator.verificaRegrasDelecaoUsuario(medicoDeletado);
        medicoValidator.verificarDadosMedico(medicoDeletado);

        // Deleta nessa ordem para respeitar as chaves estrangeiras
        medicoDAO.deletarMedico(medicoDeletado.getId());
        funcionarioDAO.deletarFuncionario(medicoDeletado.getId());
        usuarioDAO.deletarUsuario(medicoDeletado.getId());
    }
}
