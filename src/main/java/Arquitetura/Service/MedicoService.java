package Arquitetura.Service;

import Arquitetura.Dao.ConsultaDAO;
import Arquitetura.Dao.FuncionarioDAO;
import Arquitetura.Dao.MedicoDAO;
import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Exception.CpfInvalidoException;
import Arquitetura.Exception.IdInvalidoException;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Consulta;
import Arquitetura.Model.Enums.Especialidade;
import Arquitetura.Model.Enums.Plantao;
import Arquitetura.Model.Medico;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.Validator.FuncionarioValidator;
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
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final ConsultaDAO consultaDAO = new ConsultaDAO();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private final UsuarioService usuarioService = new UsuarioService();
    private final TipoUsuarioValidator tipoUsuarioValidator = new TipoUsuarioValidator();
    private final MedicoValidator medicoValidator = new MedicoValidator();

    // -- Métodos -- //
    public void idMedicoValidator(long id) {
        if (!medicoDAO.isIdMedico(id)) {
            throw new IdInvalidoException("ERRO! O ID INFORMADO NÃO É DE UM MEDICO");
        }
    }

    public void cpfMedicoValidator (String cpf) {
        if(!medicoDAO.isCpfMedico(cpf)) {
            throw new CpfInvalidoException("ERRO ! CPF NÃO PERTENCE A UM MÉDICO");
        }
    }

    /**
     * <p>Este método realiza as seguintes ações: </p>
     *
     * <ol>
     *     <li>Verifica se o usuário possui acesso total</li>
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
        medicoValidator.verificaRegrasInsercaoMedico(medicoInserido);
        usuarioService.validaUsuarioInserido(medicoInserido);

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
        usuarioService.cpfExistenteValidator(cpfMedicoDeletado);
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
        usuarioService.cpfExistenteValidator(cpfmedico);
        cpfMedicoValidator(cpfmedico);

        return consultaDAO.findAllConsultasOfMedico(usuarioDAO.getIdOfCpf(cpfmedico));
    }


    public void updateEspecialidadeMedico(Usuario usuario, long id, Especialidade especialidade) {
        tipoUsuarioValidator.temAcessoTotal(usuario);
        medicoValidator.verificaRegrasEspecialidade(especialidade);
        usuarioService.idExistenteValidator(id);
        idMedicoValidator(id);;

        medicoDAO.updateEspecialidade(id, especialidade);
    }

    public void updateSubEspecialidadeMedico(Usuario usuario, long id, String subEspecialidade) {
        tipoUsuarioValidator.temAcessoTotal(usuario);
        MedicoValidator.verificaIntegridadeSubespecialidade(subEspecialidade);
        usuarioService.idExistenteValidator(id);
        idMedicoValidator(id);

        medicoDAO.updateSubEspecialidade(id, subEspecialidade);
    }

    public void updateFormacaoMedico(Usuario usuario, long id, String formacao) {
        tipoUsuarioValidator.temAcessoTotal(usuario);
        MedicoValidator.verificaIntegridadeFormacao(formacao);
        medicoValidator.verificaRegrasFormacao(formacao);
        usuarioService.idExistenteValidator(id);
        idMedicoValidator(id);

        medicoDAO.updateFormacao(id, formacao);
    }

    public void updatePlantaoMedico(Usuario usuario, long id, Plantao plantao) {
        tipoUsuarioValidator.temAcessoTotal(usuario);
        medicoValidator.verificaRegrasPlantao(plantao);
        usuarioService.idExistenteValidator(id);
        idMedicoValidator(id);

        medicoDAO.updatePlantao(id, plantao);
    }
}
