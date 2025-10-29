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

    public boolean isSameDepartamento(long id, Departamento departamento)
    {
        String querySql = "SELECT idDepartamento FROM Administrador WHERE idAdministrador = ? ";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql)) {
            stmt.setLong(1, id);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    if (departamento.getIdDepartamento() == resultSet.getInt(1)) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar o departamento  do administrador com ID: " + id + e);
        }
        return false;
    }
}
