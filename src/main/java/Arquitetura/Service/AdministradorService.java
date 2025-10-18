package Arquitetura.Service;

import Arquitetura.Dao.AdministradorDAO;
import Arquitetura.Dao.FuncionarioDAO;
import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Exception.CpfInvalidoException;
import Arquitetura.Exception.TipoUsuarioException;
import Arquitetura.Exception.UltimoAdminException;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.Validator.AdministradorValidator;
import Arquitetura.Service.Validator.FuncionarioValidator;
import Arquitetura.Service.Validator.TipoUsuarioValidator;
import Arquitetura.Service.Validator.UsuarioValidator;
import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Exception.AutoDeleteException;

public class AdministradorService {

    // -- Atributos -- //
    private final AdministradorDAO administradorDao = new AdministradorDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final FuncionarioService funcionarioService = new FuncionarioService();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private final TipoUsuarioValidator tipoUsuarioValidator = new TipoUsuarioValidator();
    private final AdministradorValidator administradorValidator = new AdministradorValidator();
    private final FuncionarioValidator funcionarioValidator = new FuncionarioValidator();
    private final UsuarioValidator usuarioValidator = new UsuarioValidator();
    private final UsuarioService usuarioService = new UsuarioService();

    // -- Construtor -- //
    public AdministradorService() {

    }

    // -- Métodos -- //

    // Verifica se é o último administrador do banco de dados
    private boolean isUltimoAdmin(){
        return administradorDao.isUltimoAdmin();
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
        tipoUsuarioValidator.temAcessoTotal(usuario);
        administradorValidator.verificaRegrasInsercaoAdm(administradorCriado);

        // Insere nessa ordem para respeitar as chaves estrangeiras
        usuarioDAO.inserirUsuario(administradorCriado);
        funcionarioDAO.inserirFuncionario(administradorCriado);
        administradorDao.inserirAdmin(administradorCriado);
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

        if(!usuarioService.isCpfExistente(cpfAdministradorDeletado)) {
            throw new CpfInvalidoException("ERRO! CPF INVÁLIDO");
        }

        cpfDeAdmValidator(cpfAdministradorDeletado);

        if(isUltimoAdmin()) {
            throw new UltimoAdminException("ERRO! NÃO É PERMITIDO DELETAR ESTE ADMINISTRADOR");
        }

        // Deleta nessa ordem para respeitar as chaves estrangeiras
        administradorDao.deletarAdministrador(cpfAdministradorDeletado);
        funcionarioDAO.deletarFuncionario(cpfAdministradorDeletado);
        usuarioDAO.deletarUsuario(cpfAdministradorDeletado);
    }

    public void cpfDeAdmValidator (String cpf)
    {
        if(!isCpfAdmin(cpf))
        {
            throw new CpfInvalidoException("ERRO ! CPF NÃO PERTENCE A UM ADMINISTRADOR");
        }
    }

    public boolean isCpfAdmin(String cpf) {

        return administradorDao.isCpfAdministrador(cpf);

    }
}
