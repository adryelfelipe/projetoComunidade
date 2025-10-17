package Arquitetura.Service;

import Arquitetura.Dao.PacienteDAO;
import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Exception.CpfInvalidoException;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Paciente;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.Validator.PacienteValidator;
import Arquitetura.Service.Validator.TipoUsuarioValidator;
import Arquitetura.Service.Validator.UsuarioValidator;
import Arquitetura.Exception.TipoUsuarioException;
import Arquitetura.Exception.DadosInvalidosException;

public class PacienteService {

    // -- Atributos -- //
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final UsuarioService usuarioService = new UsuarioService();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final TipoUsuarioValidator tipoUsuarioValidator = new TipoUsuarioValidator();
    private final PacienteValidator pacienteValidator = new PacienteValidator();
    private final UsuarioValidator usuarioValidator = new UsuarioValidator();

    // -- Construtor -- //
    public PacienteService() {

    }

    // -- Métodos -- //

    // Verifica a veracidade dos atributos específicos de Paciente
    private boolean verificarDadosPac(Paciente paciente) {
        return (paciente.getStatusPaciente() != null && paciente.getContatoEmergencia() != null && paciente.getNumeroCarterinha() != null);
    }

    /**<p>Este método realiza as seguintes ações: </p>
     * <ol>
     *     <li>Verifica se o usuario inseridor possui acesso total</li>
     *     <li>Verifica se o médico inserido segue as regras gerais de inserção de um usuário</li>
     *     <li>Verifica os dados do paciente a ser inserido</li>
     *     <li>Insere o médico nas tabelas: Usuario e Paciente respectivamente</li>
     * </ol>
     *
     * @param usuario Quem insere
     * @param pacienteInserido Quem é inserido
     * @throws TipoUsuarioException Se o usuário não possuir acesso total (necessário para inserir)
     * @throws DadosInvalidosException Se os campos obrigatórios do inserido estiverem inválidos
     */
    public void inserirPaciente(Usuario usuario, Paciente pacienteInserido) {
        // Verificações de dados
        tipoUsuarioValidator.temAcessoTotal(usuario);
        usuarioValidator.verificaRegrasInsercaoUsuario(paciente);
        pacienteValidator.verificarDadosPaciente(paciente);

        // Insere nessa ordem para respeitar as chaves estrangeiras
        usuarioDAO.inserirUsuario(paciente);
        pacienteDAO.inserirPaciente(paciente);
    }

    public void deletarPaciente(Usuario usuario, String cpfPacienteDeletado) {
       // Verificações de dados
        tipoUsuarioValidator.temAcessoTotal(usuario);

        if(!usuarioService.isCpfExistente(cpfPacienteDeletado)) {
            throw new CpfInvalidoException("ERRO! CPF INVÁLIDO");
        }

        // Deleta nessa ordem para respeitar as chaves estrangeiras
        pacienteDAO.deletarPaciente(cpfPacienteDeletado);
        usuarioDAO.deletarUsuario(cpfPacienteDeletado);
    }
}
