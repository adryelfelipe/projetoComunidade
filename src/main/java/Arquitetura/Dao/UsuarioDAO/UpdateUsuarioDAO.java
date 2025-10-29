package Arquitetura.Dao.UsuarioDAO;

import Arquitetura.Config.ConnectionFactory;

import java.sql.*;

public class UpdateUsuarioDAO
{
    public void updateNomeUsuario (long id, String novoNome)
    {
        String queySql = "UPDATE Usuario " +
                "SET nomeUsuario = ? " +
                "WHERE idUsuario = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(queySql))
        {

            stmt.setString(1, novoNome);
            stmt.setLong(2, id);

            stmt.executeUpdate();

        }
        catch (SQLException e)
        {
            System.err.println("Erro ao tentar atualizar o nome do usuário. ");
        }
    }

    public void updateSenhaUsuario (long id, String novaSenha)
    {
        String queySql = "UPDATE Usuario " +
                "SET senha = ? " +
                "WHERE idUsuario = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(queySql))
        {

            stmt.setString(1, novaSenha);
            stmt.setLong(2, id);

            stmt.executeUpdate();

        } catch (SQLException e)
        {
            System.err.println("Erro ao tentar atualizar a senha do usuário. ");
        }
    }

    public void updateCpf (long id, String novoCpf)
    {
        String qurySql = "UPDATE Usuario " +
                "SET cpf = ? " +
                "WHERE idUsuario = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(qurySql))
        {

            stmt.setString(1, novoCpf);
            stmt.setLong(2, id);

            stmt.executeUpdate();

        }
        catch (SQLException e)
        {
            System.err.println("Erro ao tentar atualizar o Cpf do usuário.");
        }
    }

    public void updateEmail (long id, String email)
    {
        String querySql = "UPDATE Usuario"+
                "SET email = ? "+
                "WHERE idusuario = ? ";
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setString(1, email);
            stmt.setLong(2, id);

            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao atualizar o email do usuário com ID: "+id + e);
        }
    }


    public void updateTelefone (long id, String telefone)
    {
        String qurySql = "UPDATE Usuario " +
                "SET telefone = ? " +
                "WHERE idUsuario = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(qurySql))
        {

            stmt.setString(1, telefone);
            stmt.setLong(2, id);

            stmt.executeUpdate();

        }
        catch (SQLException e)
        {
            System.err.println("Erro ao tentar atualizar o Telefone do usuário.");
        }
    }

    public void updateDataNascimento (long id, Date dataNascimento)
    {
        String qurySql = "UPDATE Usuario " +
                "SET dataNascimento = ? " +
                "WHERE idUsuario = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(qurySql))
        {

            stmt.setDate(1, dataNascimento);
            stmt.setLong(2, id);

            stmt.executeUpdate();

        }
        catch (SQLException e)
        {
            System.err.println("Erro ao tentar atualizar a Data de Nascimento do usuário.");
        }
    }

    public void updateTipoUsuario (long id, long tipoUsuario)
    {
        String qurySql = "UPDATE Usuario " +
                "SET tipoUsuario = ? " +
                "WHERE idUsuario = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(qurySql))
        {

            stmt.setLong(1, tipoUsuario);
            stmt.setLong(2, id);

            stmt.executeUpdate();

        }
        catch (SQLException e)
        {
            System.err.println("Erro ao tentar atualizar o tipo do Usuário.");
        }
    }

    public void updateSexo (long id, long sexo)
    {
        String qurySql = "UPDATE Usuario " +
                "SET sexo = ? " +
                "WHERE idUsuario = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(qurySql))
        {

            stmt.setLong(1, sexo);
            stmt.setLong(2, id);

            stmt.executeUpdate();

        }
        catch (SQLException e)
        {
            System.err.println("Erro ao tentar atualizar o sexo do Usuário.");
        }
    }
}