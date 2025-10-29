package Arquitetura.Dao.AdministradorDAO;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Dao.UsuarioDAO.UpdateUsuarioDAO;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Enums.Departamento;
import Arquitetura.Model.Enums.Genero;
import Arquitetura.Model.Enums.TipoUsuario;

import java.sql.*;
import java.util.ArrayList;

public class AdministradorDAO {

    // -- CRUD -- //
    UpdateUsuarioDAO usuarioDAO = new UpdateUsuarioDAO();



    //Busca todos os Administradores





    // -- UPDATES -- //

    public void updateDepartamento(long id, Departamento departamento)
    {
        String querySql = "UPDATE Administrador "+
                "SET idDepartamento = ? "+
                "WHERE idAdministrador = ? ";
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setLong(1, departamento.getIdDepartamento());
            stmt.setLong(2, id);

            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao atualizar  departamento do administrador com ID: "+ id + e);
        }
    }





    // -- Verificadores isSame -- //


}
