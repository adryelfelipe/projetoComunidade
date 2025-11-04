package Arquitetura.Dao.FuncionarioDAO;

import Arquitetura.Config.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteFuncionarioDAO
{
    // Remoção
    public void deletarFuncionario(String cpf) {
        String querySql = "DELETE f " +
                "FROM Funcionario f " +
                "JOIN Usuario u ON u.idUsuario = f.idFuncionario " +
                "WHERE u.cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(querySql))
        {
            stmt.setString(1, cpf);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao deletar Funcionário com o CPF: " + cpf);
        }
    }
}
