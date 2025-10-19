package Arquitetura.Dao;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Enums.Departamento;
import Arquitetura.Model.Enums.Especialidade;
import Arquitetura.Model.Enums.Genero;
import Arquitetura.Model.Enums.Plantao;
import Arquitetura.Model.Medico;

import java.sql.*;

public class MedicoDAO {

    // -- CRUD -- //

    // Inserção
    public void inserirMedico(Medico medico) {
        String querySQL = "INSERT INTO Medico (idMedico, idPlantao, especialidade, subEspecialidade, formacao) values (?, ?, ?, ?, ?)";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySQL)) {
            stmt.setLong(1, medico.getId());
            stmt.setLong(2, medico.getPlantao().getIdPlantao());
            stmt.setLong(3, medico.getEspecialidade().getIdEspecialidade());
            stmt.setString(4, medico.getSubEspecialidade());
            stmt.setString(5, medico.getFormacao());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir o Médico.");
        }
    }

    // Remoção
    public void deletarMedico(String cpf) {
        String querySql =
                "DELETE m " +
                        "FROM Medico m " +
                        "JOIN Usuario u ON m.idMedico = u.idUsuario " +
                        "WHERE u.cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(querySql)) {

            stmt.setString(1, cpf);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao deletar o Médico com CPF: " + cpf);
        }
    }
    public Medico findByID(long id) {

        String querySQL = "SELECT " +
                "M.idMedico, M.idEspecialidade, M.subEspecialidade, M.formacao, M.idPlantao, " +
                "U.idUsuario, U.senha, U.nomeUsuario, U.sexo, U.cpf, U.telefone, U.email, U.dataNascimento, U.tipoUsuario, "+
                "F.idFuncionario, F.salario, F.cargaHorariaSemanal "+
                "FROM Medico M "+
                "LEFT JOIN Usuario U ON M.idMedico = U.idUsuario "+
                "LEFT JOIN Funcionario F ON M.idMedico = F.idFuncionario " +
                "WHERE M.idMedico = ? ";


        Medico medico = null;

        try
        (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(querySQL))
        {
            stmt.setLong(1,id);

            try(ResultSet resultSet = stmt.executeQuery())
            {
                if(resultSet.next()) {

                    // Dados Gerais do Usuário
                    long idU = resultSet.getLong("idUsuario");
                    String senha = resultSet.getString("senha");
                    String nomeUsuario = resultSet.getString("nomeUsuario");
                    int sexoId = resultSet.getInt("sexo");
                    String cpf = resultSet.getString("cpf");
                    String telefone = resultSet.getString("telefone");
                    String email = resultSet.getString("email");
                    Date dataNascimento = resultSet.getDate("dataNascimento");
                    int tipoUsuario = resultSet.getInt("tipoUsuario");
                    Genero sexo = switch (sexoId) {
                        case 1 -> Genero.MASCULINO;
                        default -> Genero.FEMININO;
                    };


                    // Dados Médico
                    long idM = resultSet.getLong("idMedico");
                    int idEspecialidade = resultSet.getInt("idEspecialidade");
                    String subEspecialidade = resultSet.getString("subEspecialidade");
                    String formacao = resultSet.getString("formacao");
                    int idPlantao = resultSet.getInt("idPlantao");

                    // Enum Especialidade
                    Especialidade especialidade = switch (idEspecialidade)
                    {
                        case 1 -> Especialidade.CLINICO_GERAL;
                        case 2 -> Especialidade.CARDIOLOGISTA;
                        case 3 -> Especialidade.RADIOLOGISTA;
                        case 4 -> Especialidade.OTORRINOLARINGOLOGISTA;
                        case 5 -> Especialidade.OFTALMOLOGISTA;
                        case 6 -> Especialidade.ENDOCRINOLOGISTA;
                        default -> Especialidade.HEMATOLOGISTA;
                    };

                    // Enum Plantão
                    Plantao plantao = switch(idPlantao) {
                        case 1 -> Plantao.MATUTINO;
                        case 2 -> Plantao.VERPERTINO;
                        default -> Plantao.NOTURNO;
                    };

                    // Dados Funcionario
                    double salario = resultSet.getDouble("salario");
                    int cargaHorariaSemanal = resultSet.getInt("cargaHorariaSemanal");

                    if (subEspecialidade.isEmpty())
                    {
                        medico = new Medico(
                                idM,
                                nomeUsuario,
                                cpf,
                                senha,
                                sexo,
                                telefone,
                                email,
                                dataNascimento,
                                cargaHorariaSemanal,
                                salario,
                                plantao,
                                especialidade,
                                formacao
                        );
                    }

                    else
                    {
                        medico = new Medico(
                                idM,
                                nomeUsuario,
                                cpf,
                                senha,
                                sexo,
                                telefone,
                                email,
                                dataNascimento,
                                cargaHorariaSemanal,
                                salario,
                                plantao,
                                especialidade,
                                formacao,
                                subEspecialidade
                        );
                    }
                }
            }
            return medico;
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao buscar o Médico. ");
            return medico;
        }

    }
    public Medico findByCpf(String cpf) {

        String querySQL = "SELECT " +
                "M.idMedico, M.idEspecialidade, M.subEspecialidade, M.formacao, M.idPlantao, " +
                "U.idUsuario, U.senha, U.nomeUsuario, U.sexo, U.cpf, U.telefone, U.email, U.dataNascimento, U.tipoUsuario, "+
                "F.idFuncionario, F.salario, F.cargaHorariaSemanal "+
                "FROM Medico M "+
                "LEFT JOIN Usuario U ON M.idMedico = U.idUsuario "+
                "LEFT JOIN Funcionario F ON M.idMedico = F.idFuncionario " +
                "WHERE M.idMedico = ? ";


        Medico medico = null;

        try
                (Connection conn = ConnectionFactory.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(querySQL))
        {
            stmt.setString(1,cpf);

            try(ResultSet resultSet = stmt.executeQuery())
            {
                if(resultSet.next()) {

                    // Dados Gerais do Usuário
                    long idU = resultSet.getLong("idUsuario");
                    String senha = resultSet.getString("senha");
                    String nomeUsuario = resultSet.getString("nomeUsuario");
                    int sexoId = resultSet.getInt("sexo");
                    String cpfMedico = resultSet.getString("cpf");
                    String telefone = resultSet.getString("telefone");
                    String email = resultSet.getString("email");
                    Date dataNascimento = resultSet.getDate("dataNascimento");
                    int tipoUsuario = resultSet.getInt("tipoUsuario");
                    Genero sexo = switch (sexoId) {
                        case 1 -> Genero.MASCULINO;
                        default -> Genero.FEMININO;
                    };


                    // Dados Médico
                    long idM = resultSet.getLong("idMedico");
                    int idEspecialidade = resultSet.getInt("idEspecialidade");
                    String subEspecialidade = resultSet.getString("subEspecialidade");
                    String formacao = resultSet.getString("formacao");
                    int idPlantao = resultSet.getInt("idPlantao");

                    // Enum Especialidade
                    Especialidade especialidade = switch (idEspecialidade)
                    {
                        case 1 -> Especialidade.CLINICO_GERAL;
                        case 2 -> Especialidade.CARDIOLOGISTA;
                        case 3 -> Especialidade.RADIOLOGISTA;
                        case 4 -> Especialidade.OTORRINOLARINGOLOGISTA;
                        case 5 -> Especialidade.OFTALMOLOGISTA;
                        case 6 -> Especialidade.ENDOCRINOLOGISTA;
                        default -> Especialidade.HEMATOLOGISTA;
                    };

                    // Enum Plantão
                    Plantao plantao = switch(idPlantao) {
                        case 1 -> Plantao.MATUTINO;
                        case 2 -> Plantao.VERPERTINO;
                        default -> Plantao.NOTURNO;
                    };

                    // Dados Funcionario
                    double salario = resultSet.getDouble("salario");
                    int cargaHorariaSemanal = resultSet.getInt("cargaHorariaSemanal");

                    if (subEspecialidade.isEmpty())
                    {
                        medico = new Medico(
                                idM,
                                nomeUsuario,
                                cpf,
                                senha,
                                sexo,
                                telefone,
                                email,
                                dataNascimento,
                                cargaHorariaSemanal,
                                salario,
                                plantao,
                                especialidade,
                                formacao
                        );
                    }

                    else
                    {
                        medico = new Medico(
                                idM,
                                nomeUsuario,
                                cpf,
                                senha,
                                sexo,
                                telefone,
                                email,
                                dataNascimento,
                                cargaHorariaSemanal,
                                salario,
                                plantao,
                                especialidade,
                                formacao,
                                subEspecialidade
                        );
                    }
                }
            }
            return medico;
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao buscar o Médico com CPF: "+cpf);
            return medico;
        }

    }

    public boolean isCpfMedico(String cpf) {
        String querySql = "SELECT tipoUsuario FROM Usuario WHERE cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(querySql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                long tipo = rs.getLong("tipoUsuario");
                return tipo == 2;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao verificar o CPF do Medico. ");
        }
        return false;
    }

    public void updateEspecialidade(String cpf, Especialidade especialidade)
    {
        String querySql = "UPDATE Medico m "+
                "INNER JOIN Usuario u ON m.idMedico = u.idUsuario "+
                "SET idEspecialidade = ?"+
                "WHERE cpf = ?";
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setLong(1, especialidade.getIdEspecialidade());
            stmt.setString(2, cpf);

            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao atualizar a especialidade do médico com CPF: "+cpf);
        }
    }

    public void updateSubEspecialidade(String cpf, String subEspecialidade)
    {
        String querySql = "UPDATE Medico m "+
                "INNER JOIN Usuario u ON m.idMedico = u.idUsuario "+
                "SET subEspecialidade = ? "+
                "WHERE cpf = ? ";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setString(1, subEspecialidade);
            stmt.setString(2, cpf);

            stmt.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Erro ao atualizar subespecialidade do médico com CPF: "+cpf);
        }
    }

    public void updateFormacao(String cpf, String formacao)
    {
        String querySql = "UPDATE Medico m "+
                "INNER JOIN Usuario u ON m.idMedico = u.idUsuario "+
                "SET formacao = ? "+
                "WHERE cpf = ?";
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setString(1, formacao);
            stmt.setString(2, cpf);

            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao atualizar formação do médico com CPF: "+cpf);
        }
    }

    public void updatePlantao(String cpf, Plantao plantao)
    {
        String querySql = "UPDATE Medico m "+
                "INNER JOIN Usuario u ON m.idMedico = u.idUsuario "+
                "SET idPlantao = ? "+
                "WHERE cpf = ? ";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setLong(1, plantao.getIdPlantao());
            stmt.setString(2, cpf);

            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao atualizar plantão do médico com CPF: "+cpf);
        }

    }

}
