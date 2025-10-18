package Arquitetura.Service;

import Arquitetura.Dao.ConsultaDAO;
import Arquitetura.Dao.FuncionarioDAO;
import Arquitetura.Dao.MedicoDAO;
import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Exception.CpfInvalidoException;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Consulta;
import Arquitetura.Model.Medico;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.Validator.MedicoValidator;
import Arquitetura.Service.Validator.TipoUsuarioValidator;
import Arquitetura.Service.Validator.UsuarioValidator;
import Arquitetura.Exception.TipoUsuarioException;
import Arquitetura.Exception.DadosInvalidosException;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
    private final ConsultaDAO consultaDAO = new ConsultaDAO();

    // Construtor -- //
    public MedicoService() {

    }

    // -- Métodos -- //

    /**
     * <p>Este método realiza as seguintes ações: </p>
     *
     * <ol>
     *     <li>Verifica se o usuário possui acesso total</li>
     *     <li>Verifica se o médico inserido segue as regras gerais de inserção de um usuário </li>
     *     <li>Verifica os dados do médico a ser inserido</li>
     *     <li>Insere o médico nas tabelas Usuario, Funcionario e Medico respectivamente</li>
     * </ol>
     * @param usuario Quem está inserindo
     * @param medicoInserido Quem será inserido
     * @throws TipoUsuarioException Se o usuario não possuir acesso total (necessário para inserir)
     * @throws DadosInvalidosException Se os dados do medico não seguirem as regras de negócio
     */
    
    public void inserirMedico(Usuario usuario, Medico medicoInserido) {
        // Verificações de dados
        tipoUsuarioValidator.temAcessoTotal(usuario);
        usuarioValidator.verificaRegrasInsercaoUsuario(medicoInserido);
        medicoValidator.verificarInsercaoDadosMedico(medicoInserido);

        // Insere nessa ordem para respeitar as chaves estrangeiras
        usuarioDAO.inserirUsuario(medicoInserido);
        funcionarioDAO.inserirFuncionario(medicoInserido);
        medicoDAO.inserirMedico(medicoInserido);
    }

    /**
     * <p>Este método realiza as seguintes ações: </p>
     *
     *<ol>
     *     <li>Verifica se o usuario possui acesso total</li>
     *     <li>Verifica se o cpf recebido existe</li>
     *     <li>Verifica se o cpf recebido é de um Medico</li>
     *     <li>Deleta o médico das tabelas Medico, Funcionario e Usuario respectivamente</li>
     *</ol>
     *
     * @param usuario Quem está deletando
     * @param cpfMedicoDeletado cpf de quem será deletado
     * @throws TipoUsuarioException Se o usuario não possuir acesso total (necessário para deletar)
     * @throws CpfInvalidoException Se o cpf do Médico não existir no banco de dados ou se não for de um Medico
     */

    public void deletarMedico(Usuario usuario, String cpfMedicoDeletado) {
        // Verificações de dados
        tipoUsuarioValidator.temAcessoTotal(usuario);

        if(!usuarioService.isCpfExistente(cpfMedicoDeletado)) {
            throw new CpfInvalidoException("ERRO! CPF INVÁLIDO");
        }

        cpfMedicoValidator(cpfMedicoDeletado);

        // Deleta nessa ordem para respeitar as chaves estrangeiras
        medicoDAO.deletarMedico(cpfMedicoDeletado);
        funcionarioDAO.deletarFuncionario(cpfMedicoDeletado);
        usuarioDAO.deletarUsuario(cpfMedicoDeletado);
    }

    public ArrayList<Consulta> ConsultasMedico(Usuario usuario, String cpfmedico)
    {
        // Verificação de dados
        tipoUsuarioValidator.temAcessoModerado(usuario);

        if(!usuarioService.isCpfExistente(cpfmedico)){
            throw new CpfInvalidoException("ERRO! CPF INVÁLIDO");
        }

        // ADICIONAR VERFIFICAÇÃO DE SE O CPF CONDIZ COM UM MÉDICO

        Medico medico = medicoDAO.findByCpf(cpfmedico);

        return consultaDAO.findAllConsultasOfMedico(medico);
    }

    public void cpfMedicoValidator (String cpf)
    {
        if(!isCpfMedico(cpf))
        {
            throw new CpfInvalidoException("ERRO ! CPF NÃO PERTENCE A UM MÉDICO");
        }
    }

    public boolean isCpfMedico(String cpf) {

        return medicoDAO.isCpfMedico(cpf);

    }
}
