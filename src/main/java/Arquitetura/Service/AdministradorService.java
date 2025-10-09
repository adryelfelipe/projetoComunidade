package Arquitetura.Service;

import Arquitetura.Dao.AdministradorDAO;
import Arquitetura.Dao.FuncionarioDAO;
import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Usuario;

public class AdministradorService {

    // -- Atributos -- //
    private final AdministradorDAO administradorDao = new AdministradorDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final FuncionarioService funcionarioService = new FuncionarioService();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    // -- Construtor -- //
    public AdministradorService() {

    }

    // -- Métodos -- //

    // Verifica se é o último administrador do banco de dados
    private boolean isUltimoAdmin(Administrador administrador) {
        return administradorDao.isUltimoAdmin();
    }

    // Verifica a veracidade dos atributos específicos de Admin
    private boolean verificarDadosAdm(Administrador administrador) {
       return true; // provisório
    }

    // Insere o objeto do tipo Administrador no banco de dados
    public boolean inserirAdmin(Usuario usuario, Administrador admCriado) { // Verifica as regras para inserir um Administrador
       if(usuario.getTipoUsuario().getNivelAcesso().temAcessoTotal()) {
               if(verificarDadosAdm(admCriado)) {
                   if(funcionarioService.inserirFuncionario(usuario, admCriado)) {
                       administradorDao.inserirAdmin(admCriado);

                       return true;
                   }
               }
       }

        return false;
    }

    // Deleta um administrador do banco de dados
    public boolean deletarAdministrador(Usuario usuario, Administrador adminDeletado) {
        if(usuario.getTipoUsuario().getNivelAcesso().temAcessoTotal() && usuario != adminDeletado && !isUltimoAdmin(adminDeletado)) {
            if(funcionarioService.deletarFuncionario(adminDeletado.getId())) {
                administradorDao.deletarAdministrador(adminDeletado.getId());
                funcionarioDAO.deletarFuncionario(adminDeletado.getId());
                usuarioDAO.deletarUsuario(adminDeletado.getId());

                return true;
            }
        }

        return false;
    }
}
