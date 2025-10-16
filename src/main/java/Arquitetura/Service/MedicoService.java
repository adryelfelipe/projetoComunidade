package Arquitetura.Service;

import Arquitetura.Dao.FuncionarioDAO;
import Arquitetura.Dao.MedicoDAO;
import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Exception.CpfInvalidoException;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Medico;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.Validator.MedicoValidator;
import Arquitetura.Service.Validator.TipoUsuarioValidator;
import Arquitetura.Service.Validator.UsuarioValidator;
import Arquitetura.Exception.TipoUsuarioException;
import Arquitetura.Exception.DadosInvalidosException;

public class MedicoService {

    // -- Atributos -- //
    private final MedicoDAO medicoDAO = new MedicoDAO();
    private final FuncionarioService funcionarioService = new FuncionarioService();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private final TipoUsuarioValidator tipoUsuarioValidator = new TipoUsuarioValidator();
    private final UsuarioValidator usuarioValidator = new UsuarioValidator();
    private final MedicoValidator medicoValidator = new MedicoValidator();
    private final UsuarioService usuarioService = new UsuarioService();

    // Construtor -- //
    public MedicoService() {

    }

    // -- Métodos -- //

    /**
     * <p>Este método realiza as seguintes ações: </p>
     *
     * <ol>
     *     <li>Verifica se o usuário possui permissão para inserir</li>
     *     <li>Verifica se o médico inserido segue as regras gerais de inserção de um usuário </li>
     *     <li>Verifica se os dados do médico</li>
     *     <li>Insere o médico nas tabelas: Usuario, Funcionario e Medico respectivamente</li>
     * </ol>
     * @param usuario Quem está inserindo
     * @param medicoInserido Quem será inserido
     * @throws TipoUsuarioException Se o usuario não possuir acesso total (necessário para inserção)
     * @throws DadosInvalidosException Se os dados do medico não seguirem as regras de negócio
     */
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

    /**
     * <p>Este método realiza as seguintes ações: </p>
     *
     *<ol>
     *     <li>Verifica se o usuario possui acesso para deletar</li>
     *     <li>Verifica se o CPF inserido existe</li>
     *     <li>Deleta o médico das tabelas Medico, Funcionario e Usuario do banco de dados</li>
     *</ol>
     *
     * @param usuario Quem está deletando
     * @param cpfMedicoDeletado CPF de quem será deletado
     * @throws TipoUsuarioException Se o usuario não possuir acesso total (necessário para deletar)
     * @throws CpfInvalidoException Se o cpf não existir no banco de dados
     */
    // Deleta medico do banco de dados
    public void deletarMedico(Usuario usuario, String cpfMedicoDeletado) {
        // Verificação de dados
        tipoUsuarioValidator.temAcessoTotal(usuario);

        if(!usuarioService.isCpfExistente(cpfMedicoDeletado)) {
            throw new CpfInvalidoException("ERRO! CPF INVÁLIDO");
        }

        // ADICIONAR VERFIFICAÇÃO DE SE O CPF CONDIZ COM UM MÉDICO

        // Deleta nessa ordem para respeitar as chaves estrangeiras
        medicoDAO.deletarMedico(cpfMedicoDeletado);
        funcionarioDAO.deletarFuncionario(cpfMedicoDeletado);
        usuarioDAO.deletarUsuario(cpfMedicoDeletado);
    }
}
