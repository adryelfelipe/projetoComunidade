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

    // -- Construtor -- //
    public AdministradorService() {

    }

    // -- Métodos -- //

    // Verifica a veracidade dos atributos específicos de Admin
    public boolean verificarDadosAdm(Administrador administrador) {
        return (administrador.getCargaHoraria() >= 40);
    }

    // Insere o objeto do tipo Administrador no banco de dados
    public boolean inserirAdmin(Administrador admCriador, Administrador admCriado) {
        if(verificarDadosAdm(admCriado)) {
            if(usuarioService.inserirUsuario(admCriador, admCriado)) {
                administradorDao.inserirAdmin(admCriado);

                return true;
            }
        }

        return false;
    }

    // Deleta um administrador do banco de dados
    public boolean deletarAdministrador(Administrador adminUsado, Administrador adminDeletado) {
        if(!(adminUsado == adminDeletado)) {
            if(usuarioDAO.deletarUsuario(adminDeletado.getId())) {
                // deleta chamando admDAO

                //return true;
            }
        }

        return false;
    }
}
