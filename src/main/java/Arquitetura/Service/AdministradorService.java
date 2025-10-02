package Arquitetura.Service;

import Arquitetura.Dao.AdministradorDAO;
import Arquitetura.Dao.FuncionarioDAO;
import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Model.Administrador;

import java.sql.SQLException;

public class AdministradorService {

    // -- Atributos -- //
    private final AdministradorDAO administradorDao = new AdministradorDAO();
    private final UsuarioService usuarioService = new UsuarioService();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final FuncionarioService funcionarioService = new FuncionarioService();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    // -- Construtor -- //
    public AdministradorService() {

    }

    // -- Métodos -- //

    // Verifica a veracidade dos atributos específicos de Admin
    public boolean verificarDadosAdm(Administrador administrador) {
        return true; //provisório
    }

    // Insere o objeto do tipo Administrador no banco de dados
    public boolean inserirAdmin(Administrador admCriador, Administrador admCriado) { // Verifica as regras para inserir um Administrador
        if(verificarDadosAdm(admCriado)) {
            if(funcionarioService.inserirFuncionario(admCriador, admCriado)) {
                administradorDao.inserirAdmin(admCriado);

                return true;
            }
        }

        return false;
    }

    // Deleta um administrador do banco de dados
    public boolean deletarAdministrador(Administrador adminUsado, Administrador adminDeletado) {
        if(!(adminUsado == adminDeletado)) {
            if(funcionarioService.deletarFuncionario(adminDeletado.getId())) {
                administradorDao.deletarAdministrador(adminDeletado.getId());
                funcionarioDAO.deletarFuncionario(adminDeletado.getId());
                usuarioDAO.deletarUsuario(adminUsado.getId());

                return true;
            }
        }

        return false;
    }
}
