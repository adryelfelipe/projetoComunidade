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
            stmt.setString(2, administrador.getDepartamento());

            stmt.executeUpdate();

        }
        catch (SQLException e)
        {
            System.out.println("Erro ao inserir Administrador : "+e.getMessage());
        }


    }

    // Remoção
    public boolean deletarAdministrador(long id) {
        String querySql = "DELETE FROM Administrador WHERE idAdministrador = ?";

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(querySql)) {
                stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            // Retornar True se conseguiu deletar
            if (linhasAfetadas > 0) {
                return true;
            }
            // E retornara False se não conseguiu ou não existe
            else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar Administrador com ID " + id + ": " + e.getMessage());
            return false;
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
            System.err.println("Erro ao verificar Último Admin: "+ e.getMessage());
            return false;
        }
    }
}
