package Arquitetura.Dao;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Administrador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdministradorDao {

    public void inserirAdmin(Administrador administrador) throws SQLException
    {
        String querySQL = "insert into Administrador (idAdministrador, salario, cargaHoraria) values (?, ?, ?)";

        try(Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(querySQL))
        {
            stmt.setLong(1, administrador.getId());
            stmt.setDouble(2, administrador.getSalario());
            stmt.setInt(3, administrador.getCargaHoraria());

            stmt.executeUpdate();

        }
        catch (SQLException e)
        {
            System.out.println("Erro ao inserir Administrador : "+e.getMessage());
        }


    }
}
