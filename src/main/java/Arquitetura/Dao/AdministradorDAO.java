package Arquitetura.Dao;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Administrador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            stmt.setLong(2, administrador.getDepartamento().getIdDepartamento());

            stmt.executeUpdate();

        }
        catch (SQLException e)
        {
            System.out.println("Erro ao inserir Administrador.");
        }


    }

    // Remoção
    public void deletarAdministrador(String cpf) {
        String querySql = "DELETE a " +
                "FROM Administrador a " +
                "JOIN Usuario u ON u.idUsuario = a.idAdministrador " +
                "WHERE u.cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(querySql))
        {

            stmt.setString(1, cpf);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao deletar Administrador com o CPF: " + cpf);
        }
    }

    //Leitura - Verifica quantos se existe algum administrador no sistema
    public boolean isUltimoAdmin()
    {
        String querySQl = "SELECT COUNT(*) FROM Administrador";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySQl))
        {
            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next())
            {
                int cont = resultSet.getInt(1);

                return cont == 1;
            }
            return false;
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao verificar se é Último Admin. ");
            return false;
        }
    }

    public boolean isCpfAdministrador(String cpf) {
        String querySql = "SELECT tipoUsuario FROM Usuario WHERE cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(querySql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                long tipo = rs.getLong("tipoUsuario");
                return tipo == 3;
            }

        } catch (SQLException e)
        {
            System.err.println("Erro ao verificar o CPF do Administrador. ");
        }
        return false;
    }
}
