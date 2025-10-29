package Arquitetura.Dao.AdministradorDAO;

import Arquitetura.Config.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAdministradorDAO
{
    // Remove o administrador pelo cpf
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
}
