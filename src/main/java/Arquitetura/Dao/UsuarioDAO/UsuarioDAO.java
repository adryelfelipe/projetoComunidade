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



    // Leitura - verifica a senha está coerente com o cpf
    public boolean verificarSenha(String cpf, String senha)
    {
        String querySql = "SELECT senha FROM Usuario WHERE cpf = ? LIMIT 1";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            //Determina o cpf do Usuário para verificação
            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery())
            {
                rs.next();

                //Recebe e verifica se a senha inserida é igual à senha do Usuário
                boolean isCorrect = senha.equals(rs.getString("senha"));

                //Retorna resposta
                return isCorrect;
            }
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao tentar verificar senha.");
            return false;
        }
    }
    // Leitura - verifica o email está coerente com o cpf
    public boolean verificarEmail(String cpf, String email )
    {
        String querySql = "SELECT email FROM Usuario WHERE cpf = ? LIMIT 1";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setString(1, cpf);

            try( ResultSet resultSet = stmt.executeQuery())
            {
                resultSet.next();

                //Verifica se o email inserido é igual ao email do Usuário
                boolean isCorrect = email.equals(resultSet.getString("email"));

                //Retorna resposta
                return isCorrect;
            }
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao tentar verificar email. ");
            return false;
        }
    }

    //Leitura - verifica se o email existe na tabela Usuario
    public boolean containsEmail(String email)
    {
        //Seleciona todos os Usuarios que possuem tal email
        String querySQL = "SELECT COUNT(*) FROM Usuario WHERE email = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySQL))
        {
            stmt.setString(1, email);

            try( ResultSet resultSet = stmt.executeQuery())
            {
                if(resultSet.next())
                {
                    //Determina quantos Usuarios esão cadastrados com aquele email
                    int contagem = resultSet.getInt(1);

                    //Retorna true caso a contagem for maior que 0, false caso seja igual a zero
                    return contagem > 0;
                }
            }

            return false;
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao verificar existência do email. ");
            return false;
        }
    }
    public boolean containsTelefone(String telefone)
    {
        //Verifica se existe aquele telefone no Banco de Dados
        String querySql = "SELECT "+
                "COUNT(*) "+
                "FROM Usuario "+
                "WHERE telefone = ? ";
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setString(1, telefone);

            try (ResultSet resultSet = stmt.executeQuery();)
            {
                if(resultSet.next())
                {
                    return resultSet.getInt(1) > 0;
                }

                return false;
            }
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao verificar telefone: "+telefone + e);
            return false;
        }
    }
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
    public String cpfByID(long id) {
        String querySql = "SELECT cpf FROM Usuario WHERE idUsuario = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(querySql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("cpf");
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar o CPF: " + e.getMessage());
        }

        return null;
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

    // -- Verificadores de Igualidade -- //

    public boolean isSameNome(long id, String nome)
    {
        String querySql = "SELECT nome FROM Usuario WHERE idusuario = ? ";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareCall(querySql))
        {
            stmt.setLong(1, id);

            try (ResultSet resultSet = stmt.executeQuery())
            {
                if(resultSet.next())
                {
                    if(nome.equals(resultSet.getString(1)))
                    {
                        return true;
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao verificar nome do usuário com ID: "+ id + e);
        }
        return false;
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