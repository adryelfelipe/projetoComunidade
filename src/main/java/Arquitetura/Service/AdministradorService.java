package Arquitetura.Service;

import Arquitetura.Dao.AdministradorDAO;
import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Model.Administrador;

import java.sql.SQLException;

public class AdministradorService {

    // -- Atributos -- //
    final AdministradorDAO administradorDao = new AdministradorDAO();
    final UsuarioService usuarioService = new UsuarioService();
    final UsuarioDAO usuarioDAO = new UsuarioDAO();
    final FuncionarioService funcionarioService = new FuncionarioService();

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
            if(usuarioService.deletarUsuario(adminUsado,adminDeletado.getId())) {

                return administradorDao.deletarAdministrador(adminDeletado.getId());
            }
        }

        return false;
    }
}
