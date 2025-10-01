package Arquitetura.Dao;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Administrador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdministradorDAO {

    // -- CRUD -- //

    // Inserção
    public void inserirAdmin(Administrador administrador)
    {
        String querySQL = "insert into Administrador (idAdministrador, departamento) values (?, ?)";

        try(Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(querySQL))
        {
            stmt.setLong(1, administrador.getId());
            stmt.setString(2, administrador.getDepartamento());

            stmt.executeUpdate();

        }
        catch (SQLException e)
        {
            System.out.println("Erro ao inserir Administrador : "+e.getMessage());
        }


    }
}
