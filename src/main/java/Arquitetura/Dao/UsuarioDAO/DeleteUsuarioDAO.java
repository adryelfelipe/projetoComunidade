package Arquitetura.Dao.UsuarioDAO;

import Arquitetura.Config.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteUsuarioDAO
{
    // Remove o usuário pelo CPF
    public void deletarUsuario(String cpf) {
        String querySql = "DELETE FROM Usuario WHERE cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(querySql))
        {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao deletar usuário com o CPF: "+cpf);
        }
    }
}
