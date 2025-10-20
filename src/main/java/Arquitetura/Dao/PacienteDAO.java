package Arquitetura.Dao;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Enums.Genero;
import Arquitetura.Model.Enums.StatusPaciente;
import Arquitetura.Model.Enums.TipoUsuario;
import Arquitetura.Model.Paciente;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PacienteDAO {

    // -- CRUD -- //

    // Inserção
    public void inserirPaciente (Paciente paciente)
    {
        String querySql = "insert into Paciente (idPaciente, numeroCarteirinha, contatoEmergencia, statusPaciente) values (?, ?, ?, ?)";

        try(Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(querySql))
        {
            stmt.setLong(1, paciente.getId());
            stmt.setString(2, paciente.getNumeroCarterinha() );
            stmt.setString(3, paciente.getContatoEmergencia());
            stmt.setLong(4, paciente.getStatusPaciente().getIdPaciente());

            stmt.executeUpdate();

        }
        catch (SQLException e)
        {
            System.out.println("Erro ao inserir o Paciente.");
        }
    }

    public ArrayList<Paciente> findAllPacientes()
    {
        ArrayList<Paciente> listaPacientes = new ArrayList<>();

        String querySql = "SELECT "+
                "U.idUsuario, U.senha, U.nomeUsuario, U.sexo, U.cpf, U.telefone, U.email, U.dataNascimento, U.tipoUsuario, "+
                "P.numeroCarteirinha, P.contatoEmergencia, P.idStatusPaciente, "+
                "FROM Usuario U "+
                "JOIN Paciente P ON U.idUsuario = P.idPaciente ";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            try (ResultSet resultSet = stmt.executeQuery())
            {
                while (resultSet.next())
                {
                    //long id, String nome, String cpf, String senha, Genero sexo, String telefone, String email, Date dataNascimento, String contatoEmergencia, String numeroCarterinha, StatusPaciente statusPaciente
                    long idPaciente = resultSet.getInt("idUsuario");
                    String nome = resultSet.getString("nomeUsuario");
                    String cpf = resultSet.getString("cpf");
                    String senha = resultSet.getString("senha");
                    int idGenero = resultSet.getInt("sexo");
                    Genero genero = switch (idGenero)
                    {
                        case 1 -> Genero.MASCULINO;
                        default -> Genero.FEMININO;
                    };
                    String telefone = resultSet.getString("telefone");
                    String email = resultSet.getString("email");
                    Date datanascimento = resultSet.getDate("dataNascimento");
                    TipoUsuario tipoUsuario = TipoUsuario.PACIENTE;

                    //Atributos Paciente
                    String contatoEmergencia = resultSet.getString("contatoEmergencia");
                    String numCarteirinha = resultSet.getString("numeroCarteirinha");
                    int idStatusPaciente = resultSet.getInt("idStatusPaciente");
                    StatusPaciente statusPaciente = switch (idStatusPaciente)
                    {
                        case 1 -> StatusPaciente.ATIVO;
                        default -> StatusPaciente.INATIVO;
                    };

                    Paciente paciente = new Paciente(idPaciente,nome,cpf, senha,genero, telefone, email, datanascimento, contatoEmergencia, numCarteirinha, statusPaciente);

                    if(paciente != null)
                    {
                        listaPacientes.add(paciente);
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao listar todos os pacientes!");
        }
        return listaPacientes;
    }
    // Remoção
    public void deletarPaciente(String cpf) {
        String querySql = "DELETE p " +
                          "FROM Paciente p " +
                          "JOIN Usuario u ON p.idPaciente = u.idUsuario " +
                          "WHERE u.cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(querySql))
        {

            stmt.setString(1, cpf);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao deletar Paciente com o CPF: " + cpf);
        }
    }

    public boolean isCpfPaciente(String cpf) {
        String querySql = "SELECT tipoUsuario FROM Usuario WHERE cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(querySql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                long tipo = rs.getLong("tipoUsuario");
                return tipo == 1;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao verificar o CPF do Paciente.");
        }
        return false;
    }

    public void updateNumeroCarteirinha(String cpf, String numCarteirinha )
    {
        String querySql = "UPDATE Paciente p "+
                "INNER JOIN Usuario u ON p.idPaciente = u.idUsuario "+
                "SET numeroCarteirinha = ? "+
                "WHERE cpf = ? ";
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setString(1, numCarteirinha);
            stmt.setString(2, cpf);

            stmt.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("Erro ao atualizar número da carteirinha do paciente com Cpf: "+cpf);
        }
    }

    public void updateContatoEmergencia(String cpf, String contatoEmergencia)
    {
        String querySql = "UPDATE Paciente p "+
                "INNER JOIN Usuario u ON p.idPaciente = u.idUsuario "+
                "SET contatoCarteirinha = ? "+
                "WHERE cpf = ? ";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setString(1, contatoEmergencia);
            stmt.setString(2, cpf);

            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao atualizar o contato de emergência do paciente com o Cpf: "+cpf);
        }
    }
    public void updateStatusPaciente(String cpf, StatusPaciente statusPaciente)
    {
        String querySql = "UPDATE Paciente p "+
                "INNER JOIN Usuario u ON p.idPaciente = u.idUsuario "+
                "SET idStatusPaciente = ? "+
                "WHERE cpf = ? ";
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setLong(1, statusPaciente.getIdPaciente());
            stmt.setString(2, cpf);

            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao atualizar o status do paciente com o Cpf: "+cpf);
        }
    }
}
