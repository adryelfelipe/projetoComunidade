package Arquitetura.Service;

import Arquitetura.Dao.AdministradorDAO.CreateAdministradorDAO;
import Arquitetura.Dao.AdministradorDAO.DeleteAdministradorDAO;
import Arquitetura.Dao.AdministradorDAO.ReadAdministradorDAO;
import Arquitetura.Dao.AdministradorDAO.UpdateAdministradorDAO;
import Arquitetura.Dao.UsuarioDAO.CreateUsuarioDAO;
import Arquitetura.Dao.UsuarioDAO.DeleteUsuarioDAO;
import Arquitetura.Exception.*;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Enums.Departamento;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.Validator.AdministradorValidator;
import Arquitetura.Service.Validator.TipoUsuarioValidator;
import Arquitetura.Service.Validator.UsuarioValidator;

public class AdministradorService {

    // -- Atributos -- //
    private final CreateUsuarioDAO createUsuarioDAO = new CreateUsuarioDAO();
    private final DeleteUsuarioDAO deleteUsuarioDAO = new DeleteUsuarioDAO();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private final DeleteAdministradorDAO deleteAdministradorDAO = new DeleteAdministradorDAO();
    private final UpdateAdministradorDAO updateAdministradorDAO = new UpdateAdministradorDAO();
    private final CreateAdministradorDAO createAdministradorDAO = new CreateAdministradorDAO();
    private final ReadAdministradorDAO readAdministradorDAO = new ReadAdministradorDAO();
    private final UsuarioService usuarioService = new UsuarioService();
    private final TipoUsuarioValidator tipoUsuarioValidator = new TipoUsuarioValidator();
    private final UsuarioValidator usuarioValidator = new UsuarioValidator();
    private final AdministradorValidator administradorValidator = new AdministradorValidator();

    // -- Métodos -- //
    public void cpfDeAdmValidator (String cpf) {
        if(!readAdministradorDAO.isCpfAdministrador(cpf)) {
            throw new CpfInvalidoException("ERRO ! CPF NÃO PERTENCE A UM ADMINISTRADOR");
        }
    }

    public void idAdministradorValidator(long id) {
        if (!readAdministradorDAO.isIdAdministrador(id)) {
            throw new IdInvalidoException("ERRO! O ID INFORMADO NÃO É DE UM ADMINISTRADOR");
        }
    }

    public void ultimoAdminValidator() {
        if(readAdministradorDAO.isUltimoAdmin()) {
            throw new UltimoAdminException("ERRO! NÃO É PERMITIDO DELETAR O ÚLTIMO ADMINISTRADOR");
        }
    }

    public void validaUpdateAdm(Usuario usuarioInsersor, long id) {
        usuarioService.validaUpdateUsuario(usuarioInsersor, id);
        idAdministradorValidator(id);
    }

    /**
     * <p>Este método realiza as seguintes ações: </p>
     *
     * <ol>
     *     <li>Verifica se o usuario possui acesso total</li>
     *     <li>Verifica os dados do administrador a ser inserido</li>
     *     <li>Insere o administrador nas tabelas Usuario, Funcionario e Administrador respectivamente</li>
     * </ol>
     *
     * @param usuario Quem está inserindo
     * @param administradorCriado Quem será inserido
     * @throws TipoUsuarioException Se o usuario não possuir acesso total (necessário para inserir)
     * @throws DadosInvalidosException Se os dados do administrador não seguirem as regras de negócio
     */

    public void inserirAdmin(Usuario usuario, Administrador administradorCriado)  {
        // Verificações de dados
        administradorValidator.verificaRegrasInsercaoAdm(usuario, administradorCriado);
        tipoUsuarioValidator.temAcessoTotal(usuario);
        usuarioService.validaUsuarioInserido(administradorCriado);

        // Insere nessa ordem para respeitar as chaves estrangeiras
        createUsuarioDAO.inserirUsuario(administradorCriado);
        funcionarioDAO.inserirFuncionario(administradorCriado);
        createAdministradorDAO.inserirAdmin(administradorCriado);
    }

    /**
     *<p>Este método realiza as seguintes ações: </p>
     *
     *<ol>
     *      <li>Verifica se o usuário possui acesso total</li>
     *      <li>Verifica se o usuário está tentando deletar a si mesmo</li>
     *      <li>Verifica se o cpf recebido existe</li>
     *      <li>Verifica se o cpf é de um Administrador</li>
     *      <li>Verifica se o administradorDeletado não é o último ADM do DB</li>
     *      <li>Deleta o ADM das tabelas Administrador, Funcionario e Usuario respectivamente</li>
     *</ol>
     *
     * @param usuario Quem está deletando
     * @param cpfAdministradorDeletado cpf de quem será deletado
     * @throws TipoUsuarioException Se o usuário não possuir acesso total (necessário para deletar)
     * @throws AutoDeleteException Se o usuário tentar deletar a si mesmo
     * @throws CpfInvalidoException Se o cpf do administrador não existir no banco de dados ou se não for Administrador
     * @throws UltimoAdminException Se o administrador deletado for o último do banco de dados;
     */

    public void deletarAdministrador(Usuario usuario, String cpfAdministradorDeletado) {
        // Verificações de dados
        tipoUsuarioValidator.temAcessoTotal(usuario);
        administradorValidator.verificaAutoDelete(usuario.getCpf(), cpfAdministradorDeletado);
        usuarioService.cpfExistenteValidator(cpfAdministradorDeletado);
        cpfDeAdmValidator(cpfAdministradorDeletado);
        ultimoAdminValidator();

        // Deleta nessa ordem para respeitar as chaves estrangeiras
        deleteAdministradorDAO.deletarAdministrador(cpfAdministradorDeletado);
        funcionarioDAO.deletarFuncionario(cpfAdministradorDeletado);
        deleteUsuarioDAO.deletarUsuario(cpfAdministradorDeletado);
    }

    public void updateDepartamento(Usuario usuario, long id, Departamento departamento) {
        validaUpdateAdm(usuario, id);
        tipoUsuarioValidator.temAcessoTotal(usuario);
        administradorValidator.verificaRegrasDepartamento(departamento);

        updateAdministradorDAO.updateDepartamento(id, departamento);

        if(usuarioValidator.isAutoUpdate(usuario.getId(), id)) {
            ((Administrador) usuario).setDepartamento(departamento);
        }
    }
}
