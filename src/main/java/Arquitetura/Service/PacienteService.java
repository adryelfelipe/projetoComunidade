package Arquitetura.Service;

import Arquitetura.Dao.PacienteDAO;
import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Exception.CpfInvalidoException;
import Arquitetura.Exception.IdInvalidoException;
import Arquitetura.Model.Enums.StatusPaciente;
import Arquitetura.Model.Paciente;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.Validator.PacienteValidator;
import Arquitetura.Service.Validator.TipoUsuarioValidator;
import Arquitetura.Service.Validator.UsuarioValidator;
import Arquitetura.Exception.TipoUsuarioException;
import Arquitetura.Exception.DadosInvalidosException;

public class PacienteService {

    // -- Atributos -- //
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final UsuarioService usuarioService = new UsuarioService();
    private final TipoUsuarioValidator tipoUsuarioValidator = new TipoUsuarioValidator();
    private final UsuarioValidator usuarioValidator = new UsuarioValidator(usuarioService);
    private final PacienteValidator pacienteValidator = new PacienteValidator(usuarioValidator, this);

    // -- Construtor -- //
    public PacienteService() {

    }

    // -- Métodos -- //

    public boolean isIdPaciente(long id) {
        return pacienteDAO.isIdPaciente(id);
    }

    /**<p>Este método realiza as seguintes ações: </p>
     * <ol>
     *     <li>Verifica se o usuario possui acesso total</li>
     *     <li>Verifica os dados do paciente a ser inserido</li>
     *     <li>Insere o médico nas tabelas Usuario e Paciente respectivamente</li>
     * </ol>
     *
     * @param usuario Quem está inserindo
     * @param pacienteInserido Quem será inserido
     * @throws TipoUsuarioException Se o usuário não possuir acesso total (necessário para inserir)
     * @throws DadosInvalidosException Se os dados do paciente não seguirem as regras de negócio
     */
    public void inserirPaciente(Usuario usuario, Paciente pacienteInserido) {
        // Verificações de dados
        tipoUsuarioValidator.temAcessoTotal(usuario);
        pacienteValidator.verificaRegrasInsercaoPaciente(pacienteInserido);

        // Insere nessa ordem para respeitar as chaves estrangeiras
        usuarioDAO.inserirUsuario(pacienteInserido);
        pacienteDAO.inserirPaciente(pacienteInserido);
    }

    /**
     * <p>Este método realiza as seguintes ações: </p>
     *
     * <ol>
     *     <li>Verifica se o usuario possui acesso total </li>
     *     <li>Verifica se o cpf recebido existe </li>
     *     <li>Verifica se o cpf recebido é de um Paciente</li>
     *     <li>Deleta o paciente das tabelas Paciente e Usuario respectivamente </li>
     * </ol>
     * @param usuario Quem está deletando
     * @param cpfPacienteDeletado cpf de quem será deletado
     * @throws TipoUsuarioException Se o usuario não possuir acesso total (necessário para deletar)
     * @throws CpfInvalidoException Se o cpf do Paciente não existir no banco de dados ou se não for um Paciente
     */

    public void deletarPaciente(Usuario usuario, String cpfPacienteDeletado) {
       // Verificações de dados
        tipoUsuarioValidator.temAcessoTotal(usuario);

        if(!usuarioDAO.verificarCpf(cpfPacienteDeletado)) {
            throw new CpfInvalidoException("ERRO! CPF INVÁLIDO");
        }

        pacienteValidator.cpfPacienteValidator(cpfPacienteDeletado);

        // Deleta nessa ordem para respeitar as chaves estrangeiras
        pacienteDAO.deletarPaciente(cpfPacienteDeletado);
        usuarioDAO.deletarUsuario(cpfPacienteDeletado);
    }

    public boolean isCpfPaciente(String cpf) {

        return pacienteDAO.isCpfPaciente(cpf);

    }

    public void updateContatoEmergencia(Usuario usuario, long id, String contatoEmergencia) {
        // Verificações de dados
        tipoUsuarioValidator.temAcessoBaixo(usuario);
        PacienteValidator.verificaIntegridadeContatoEmerg(contatoEmergencia);
        pacienteValidator.verificaRegrasContatoEmergencia(contatoEmergencia);
        usuarioService.idExistenteValidator(id);
        pacienteValidator.idPacienteValidator(id);

        // Updates
        pacienteDAO.updateContatoEmergencia(id, contatoEmergencia);
        if(usuarioValidator.isAutoUpdate(usuario.getId(), id)) {
            ((Paciente) usuario).setContatoEmergencia(contatoEmergencia);
        }
    }

    public void updateStatusPaciente(Usuario usuario, long id, StatusPaciente statusPaciente) {
        // Verificações de dados
        tipoUsuarioValidator.temAcessoTotal(usuario);
        pacienteValidator.verificaRegrasStatusPaciente(statusPaciente);
        usuarioService.idExistenteValidator(id);
        pacienteValidator.idPacienteValidator(id);

        // Updates
        pacienteDAO.updateStatusPaciente(id, statusPaciente);
    }

    public void updateNumeroCarteirinha(Usuario usuario, long id, String numeroCarteirinha) {
        // Verificações de dados
        tipoUsuarioValidator.temAcessoTotal(usuario);
        PacienteValidator.verificaIntegridadeNumeroCarterinha(numeroCarteirinha);
        pacienteValidator.verificaRegrasNumeroCarterinha(numeroCarteirinha);
        usuarioService.idExistenteValidator(id);
        pacienteValidator.idPacienteValidator(id);

        // Updates
        pacienteDAO.updateNumeroCarteirinha(id, numeroCarteirinha);
    }
}
