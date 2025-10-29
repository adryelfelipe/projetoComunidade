package Arquitetura.Dao.AdministradorDAO;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Administrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateAdministradorDAO
{
    // Inserção
    public void inserirAdmin(Administrador administrador)
    {
        String querySQL = "insert into Administrador (idAdministrador, idDepartamento) values (?, ?)";

        try(Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(querySQL))
        {
            stmt.setLong(1, administrador.getId());
            stmt.setLong(2, administrador.getDepartamento().getIdDepartamento());

            stmt.executeUpdate();

        }
        catch (SQLException e)
        {
            System.out.println("Erro ao inserir Administrador.");
        }
    }
}
