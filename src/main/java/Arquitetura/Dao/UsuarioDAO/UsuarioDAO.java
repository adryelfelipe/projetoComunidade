package Arquitetura.Dao.UsuarioDAO;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Enums.*;
import Arquitetura.Model.Medico;
import Arquitetura.Model.Paciente;
import Arquitetura.Model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO {

    // -- UPDATES -- //

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

    public boolean isSameCpf(long id, String cpf)
    {
        String querySql = "SELECT cpf FROM Usuario WHERE idUsuario = ? ";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareCall(querySql))
        {
            stmt.setLong(1, id);

            try (ResultSet resultSet = stmt.executeQuery())
            {
                if(resultSet.next())
                {
                    if(cpf.equals(resultSet.getString(1)))
                    {
                        return true;
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao verificar cpf de usuário com ID: "+ id + e);
        }
        return  false;
    }
    public boolean isSameSenha(long id, String senhaUsuario)
    {
        String querySql = "SELECT senha FROM Usuario WHERE idUsuario = ? ";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareCall(querySql))
        {
            stmt.setLong(1, id);

            try (ResultSet resultSet = stmt.executeQuery())
            {
                if(resultSet.next())
                {
                    if(senhaUsuario.equals(resultSet.getString(1)))
                    {
                        return true;
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao verificar senha de usuário com ID: "+ id + e);
        }
        return  false;
    }
    public boolean isSameEmail(long id, String email)
    {
        String querySql = "SELECT email FROM Usuario WHERE idUsuario = ? ";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setLong(1, id);

            try (ResultSet resultSet = stmt.executeQuery())
            {
                if(resultSet.next())
                {
                    if(email.equals(resultSet.getString(1)))
                    {
                        return true;
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao verificar email do usuario com ID: "+ id + e);
        }
        return false;
    }
    public boolean isSameTelefone(long id, String telefone)
    {
        String querySql = "SELECT telefone FROM Usuario WHERE idUsuario = ? ";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setLong(1,id);

            try (ResultSet resultSet = stmt.executeQuery())
            {
                if(resultSet.next())
                {
                    if(telefone.equals(resultSet.getString(1)))
                    {
                        return  true;
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao verificar telefone do usuário com ID: "+ id + e);
        }
        return false;
    }
    public boolean isSameDataNascimento(long id, Date dataNascimento)
    {
        String querySql = "SELECT dataNascimento FROM Usuario WHERE idUsuario = ? ";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setLong(1,id);

            try (ResultSet resultSet = stmt.executeQuery())
            {
                if(resultSet.next())
                {
                    if(dataNascimento.equals(resultSet.getDate(1)))
                    {
                        return  true;
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao verificar telefone do usuário com ID: "+ id + e);
        }
        return false;
    }
    public boolean isSameTipoUsuario(long id, TipoUsuario tipoUsuario)
    {
        String querySql = "SELECT tipoUsuario FROM Usuario WHERE idUsuario = ? ";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setLong(1,id);

            try (ResultSet resultSet = stmt.executeQuery())
            {
                if(resultSet.next())
                {
                    if(tipoUsuario.getIdTipoUsuario() == resultSet.getInt(1))
                    {
                        return  true;
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao verificar telefone do usuário com ID: "+ id + e);
        }
        return false;
    }
    public boolean isSameSexo(long id, Genero genero)
    {
        String querySql = "SELECT sexo FROM Usuario WHERE idUsuario = ? ";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setLong(1,id);

            try (ResultSet resultSet = stmt.executeQuery())
            {
                if(resultSet.next())
                {
                    if(genero.getIdGenero() == resultSet.getInt(1))
                    {
                        return  true;
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao verificar telefone do usuário com ID: "+ id + e);
        }
        return false;
    }
}