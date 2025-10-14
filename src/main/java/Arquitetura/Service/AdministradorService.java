package Arquitetura.Service;

import Arquitetura.Dao.AdministradorDAO;
import Arquitetura.Dao.FuncionarioDAO;
import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Exception.TipoUsuarioException;
import Arquitetura.Exception.UltimoAdminException;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.Validator.AdministradorValidator;
import Arquitetura.Service.Validator.FuncionarioValidator;
import Arquitetura.Service.Validator.TipoUsuarioValidator;
import Arquitetura.Service.Validator.UsuarioValidator;

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

    // -- Construtor -- //
    public AdministradorService() {

    }

    // -- Métodos -- //

    // Verifica se é o último administrador do banco de dados
    private void isUltimoAdmin(Administrador administrador){
        throw new UltimoAdminException("ERRO! NÃO É PERMITIDO DELETAR O ÚLTIMO ADMINISTRADOR DO BANCO DE DADOS");
    }

    /**
     * <p>Este método realiza as seguintes ações: </p>
     *
     * <ol>
     *     <li>Verifica se o administrador inserido não é nulo </li>
     *     <li>Verifica se o usuário que está inserindo possui acesso para tal </li>
     *     <li>Verifica os dados do administrador a ser inserido</li>
     *     <li>Insere o administrador nas tabelas Usuario e Funcionario respectivamente</li>
     *     <li>Insere o administrador na tabela Administrador</li>
     * </ol>
     *
     * @param usuario Quem está inserindo
     * @param administradorCriado Quem será inserido
     * @throws TipoUsuarioException Se o usuario não possuir acesso total (necessário para inserção)
     * @throws IllegalArgumentException Se os campos obrigatórios do inserido estiverem inválidos
     */

    public void inserirAdmin(Usuario usuario, Administrador administradorCriado)  {
        usuarioValidator.verificaRegrasInsercaoUsuario(administradorCriado);
        tipoUsuarioValidator.temAcessoTotal(usuario);

        // Verifica os dados específicos de um Administrador
        administradorValidator.verificarDadosAdm(administradorCriado);

        // Verifica os dados gerais (Funcionario + Usuario)
        funcionarioService.inserirFuncionario(usuario, administradorCriado);

        administradorDao.inserirAdmin(administradorCriado);
    }

    /**
     *<p>Este método realiza as seguintes ações: </p>
     *
     *<ol>
     *      <li>Verifica as regras de negócio gerais para deletar um usuário</li>
     *      <li>Verifica se o administrador não está deletando a si mesmo</li>
     *      <li>Verifica se o administradorDeletado não é o último ADM do DB</li>
     *      <li>Deleta o ADM das tabelas Administrador, Funcionario e Usuario do banco de dados, respectivamente</li>
     *</ol>
     *
     * @param usuario Quem está deletando
     * @param administradorDeletado Quem será deletado
     * @throws TipoUsuarioException Se o usuário não possuir acesso total (necessário para deletar)
     * @throws IllegalArgumentException Se os dados do administradorDeletado estiverem inválidos
     */

    public void deletarAdministrador(Usuario usuario, Administrador administradorDeletado) {
        usuarioValidator.verificaRegrasDelecaoUsuario(administradorDeletado);
        administradorValidator.verificaAutoDelecao(usuario, administradorDeletado);
        isUltimoAdmin(administradorDeletado);

        // Deleta nessa ordem para respeitar as chaves estrangeiras
        administradorDao.deletarAdministrador(administradorDeletado.getId());
        funcionarioDAO.deletarFuncionario(administradorDeletado.getId());
        usuarioDAO.deletarUsuario(administradorDeletado.getId());
    }
}
