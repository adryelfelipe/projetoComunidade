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
import Arquitetura.Exception.DadosInvalidosException;

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
        if(administradorDao.isUltimoAdmin()) {
            throw new UltimoAdminException("ERRO! NÃO É PERMITIDO DELETAR O ÚLTIMO ADMINISTRADOR DO BANCO DE DADOS");
        }
    }

    /**
     * <p>Este método realiza as seguintes ações: </p>
     *
     * <ol>
     *     <li>Verifica se o usuario inseridor possui acesso para tal </li>
     *     <li>Verifica se o administrador inserido segue as regras gerais de inserção de um usuário </li>
     *     <li>Verifica os dados do administrador a ser inserido</li>
     *     <li>Insere o administrador nas tabelas Usuario, Funcionario e Administrador respectivamente</li>
     * </ol>
     *
     * @param usuario Quem está inserindo
     * @param administradorCriado Quem será inserido
     * @throws TipoUsuarioException Se o usuario não possuir acesso total (necessário para inserção)
     * @throws DadosInvalidosException Se os campos obrigatórios do inserido estiverem inválidos
     */

    public void inserirAdmin(Usuario usuario, Administrador administradorCriado)  {
        // Verificações de dados
        tipoUsuarioValidator.temAcessoTotal(usuario);
        usuarioValidator.verificaRegrasInsercaoUsuario(administradorCriado);
        administradorValidator.verificarDadosAdm(administradorCriado);

        // Insere nessa ordem para respeitar as chaves estrangeiras
        usuarioDAO.inserirUsuario(administradorCriado);
        funcionarioDAO.inserirFuncionario(administradorCriado);
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
     * @throws DadosInvalidosException Se os dados do administradorDeletado estiverem inválidos
     */

    public void deletarAdministrador(Usuario usuario, Administrador administradorDeletado) {
        // Verificações de dados
        tipoUsuarioValidator.temAcessoTotal(usuario);
        usuarioValidator.verificaRegrasDelecaoUsuario(administradorDeletado);
        administradorValidator.verificaAutoDelete(usuario, administradorDeletado);
        isUltimoAdmin(administradorDeletado);

        // Deleta nessa ordem para respeitar as chaves estrangeiras
        administradorDao.deletarAdministrador(administradorDeletado.getId());
        funcionarioDAO.deletarFuncionario(administradorDeletado.getId());
        usuarioDAO.deletarUsuario(administradorDeletado.getId());
    }
}
