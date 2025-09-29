package Arquitetura.Service;

import Arquitetura.Dao.AdministradorDAO;
import Arquitetura.Model.Administrador;

import java.sql.SQLException;

public class AdministradorService {

    // -- Atributos -- //
    final AdministradorDAO administradorDao = new AdministradorDAO();
    final UsuarioService usuarioService = new UsuarioService();

    // -- Construtor -- //
    public AdministradorService() {

    }

    // -- Métodos -- //

    // Verifica a veracidade dos atributos específicos de Admin
    public boolean verificarDadosAdm(Administrador administrador) {
        return (administrador.getCargaHoraria() >= 40);
    }

    // Insere o objeto do tipo Administrador no banco de dados
    public void inserirAdmin(Administrador admCriador, Administrador admCriado) {
        if(verificarDadosAdm(admCriado)) {
            usuarioService.inserirUsuario(admCriador, admCriado);
            administradorDao.inserirAdmin(admCriado);
        }
    }
}
