package Arquitetura.Dao;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Consulta;
import Arquitetura.Model.Enums.Exame;
import Arquitetura.Model.Enums.StatusConsulta;
import Arquitetura.Model.Enums.StatusConsulta;
import Arquitetura.Model.Medico;
import Arquitetura.Model.Paciente;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO
{
    public void marcarConsulta(Consulta consulta)
    {
        String querySql = "INSERT INTO Consulta (dataConsulta, horarioConsulta, relatorio, idPaciente, idMedico, idExame, idStatus) VALUES (?,?,?,?,?,?,?) ";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql, Statement.RETURN_GENERATED_KEYS))
        {
            stmt.setDate(1, (Date) consulta.getDataConsulta());
            stmt.setTime(2, (Time) consulta.getHorarioConsulta());
            stmt.setString(3, consulta.getRelatorio());
            stmt.setLong(4, consulta.getPaciente().getId());
            stmt.setLong(5, consulta.getMedico().getId());
            stmt.setLong(6, consulta.getExame().getIdExame());
            stmt.setLong(7,consulta.getStatus().getIdStatus());

            stmt.executeUpdate();

            try(ResultSet resultSet = stmt.getGeneratedKeys())
            {
                if(resultSet.next())
                {
                    consulta.setIdConsulta(resultSet.getLong(1));
                }
            }

        }
        catch (SQLException e)
        {
            System.err.println("Não foi possível marcar uma Consulta.");
        }
    }

    // Remoção
    public void deletarConsulta(long id)
    {

        String querySql = "delete from Consulta where idConsulta = ?";

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(querySql))
        {
            stmt.setLong(1, id);

            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao deletar Consulta com ID: " + id);
        }
    }

    public void deletarConsultaWhereCpfPaciente(String cpfPaciente)
    {
        String querySql = "DELETE c "+
                "FROM Consulta c "+
                "INNER JOIN Usuario u ON c.idPaciente = u.idUsuario "+
                "WHERE u.cpf = ?";
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setString(1,cpfPaciente);

            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println("Não foi possível deletar Consulta onde CPF do Paciente é igual: "+cpfPaciente);
        }
    }

    public void deletarConsultaWhereCpfMedico(String cpfMedico)
    {
        String querySql = "DELETE c "+
                "FROM Consulta c "+
                "INNER JOIN Usuario u ON c.idMedico = u.idUsuario "+
                "WHERE u.cpf = ? ";
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setString(1, cpfMedico);

            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println("Não foi possível deletar Consulta onde CPF do Médico é igual: "+cpfMedico);
        }
    }

    public Consulta findById(long id)
    {
        String querySQL = "SELECT idConsulta, dataConsulta, horarioConsulta, relatorio, idPaciente, idMedico, idExame, idStatus "+
                "FROM Consulta WHERE idConsulta = ? ";

        Consulta consulta = null;

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySQL))
        {
            stmt.setLong(1, id);

            try(ResultSet resultSet = stmt.executeQuery())
            {
                if(resultSet.next())
                {
                    Exame exame = switch (resultSet.getInt("idExame"))
                    {
                        case 1 -> Exame.Hemograma;
                        case 2 -> Exame.Glicemia;
                        case 3 -> Exame.Colesterol;
                        case 4 -> Exame.RaioX;
                        case 5 -> Exame.Eletrocardiograma;
                        case 6 -> Exame.TesteErgometrico;
                        case 7 -> Exame.Audiometria;
                        case 8 -> Exame.Audio;
                        case 9 -> Exame.Visao;
                        default -> Exame.Sangue;
                    };

                    StatusConsulta status = switch (resultSet.getInt("idStatus"))
                    {
                        case 1 -> StatusConsulta.AGENDADA;
                        case 2 -> StatusConsulta.REAGENDADA;
                        case 3 -> StatusConsulta.AGUARDANDO;
                        case 4 -> StatusConsulta.EM_ATENDIMENTO;
                        case 5 -> StatusConsulta.REALIZADA;
                        case 6 -> StatusConsulta.CANCELADA;
                        default -> StatusConsulta.FALTA;
                    };

                    consulta = new Consulta(
                            resultSet.getDate("dataConsulta"),
                            resultSet.getTime("horarioConsulta"),
                            resultSet.getLong("idPaciente"),
                            resultSet.getLong("idMedico"),
                            exame,
                            resultSet.getString("relatorio"),
                            status,
                            resultSet.getLong("idConsulta")

                    );
                }
            }

        }
        catch (SQLException e)
        {
            System.err.println("Não foi possível buscar Consulta. ");
        }

        return consulta;
    }

    public ArrayList<Consulta> findAllConsultas()
    {
        String querySql = "SELECT C.idConsulta FROM Consulta C";

        ArrayList<Consulta> listaConsultas = new ArrayList<>();

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql);
                ResultSet resultSet = stmt.executeQuery())
        {
            while(resultSet.next())
            {
                Consulta consulta = findById(resultSet.getLong("idConsulta"));

                if(consulta != null)
                {
                    listaConsultas.add(consulta);
                }
            }

        }
        catch (SQLException e)
        {
            System.err.println("Não foi possível buscar todas as consultas.");
        }
        return listaConsultas;
    }
    public ArrayList<Consulta> findAllConsultasOfPaciente(Paciente paciente)
    {
        ArrayList<Consulta> listaConsultasPaciente = new ArrayList<>();

        String querySql = "SELECT DISTINCT "+
                "C.idConsulta "+
                "FROM Consulta C "+
                "WHERE C.idPaciente = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setLong(1, paciente.getId());

            try(ResultSet resultSet = stmt.executeQuery())
            {
                while(resultSet.next())
                {
                    Consulta consulta = findById(resultSet.getLong("idConsulta"));

                    if(consulta != null && !listaConsultasPaciente.contains(consulta))
                    {
                        listaConsultasPaciente.add(consulta);
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Não foi possível buscar as listas de Consultas.");
        }

        return listaConsultasPaciente;
    }
    public ArrayList<Consulta> findAllConsultasOfMedico(Medico medico)
    {
        ArrayList<Consulta> listaConsultasMedico = new ArrayList<>();

        String querySql = "SELECT DISTINCT "+
                "C.idConsulta "+
                "FROM Consulta C "+
                "WHERE C.idMedico = ?";
        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setLong(1, medico.getId());

            try(ResultSet resultSet = stmt.executeQuery())
            {
                while(resultSet.next())
                {
                    Consulta consulta = findById(resultSet.getLong("idConsulta"));

                    if(consulta != null && !listaConsultasMedico.contains(consulta))
                    {
                        listaConsultasMedico.add(consulta);
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println("Não foi possível buscar a lista de Consultas.");
        }
        return listaConsultasMedico;
    }
    public ArrayList<Consulta> findAllConsultasByData(Date date)
    {
        Consulta consulta = null;

        ArrayList<Consulta> listaConsultas = new ArrayList<>();

        String querySql = "SELECT "+
                "idConsulta, "+
                "dataConsulta, "+
                "horarioConsulta, "+
                "relatorio, "+
                "idPaciente, "+
                "idMedico, "+
                "idExame,"+
                "isStatus "+
                "FROM Consulta "+
                "WHERE dataConsulta = ?";
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setDate(1, date);

            try(ResultSet resultSet = stmt.executeQuery())
            {
                while (resultSet.next())
                {
                    Exame exame = switch (resultSet.getInt("idExame"))
                    {
                        case 1 -> Exame.Hemograma;
                        case 2 -> Exame.Glicemia;
                        case 3 -> Exame.Colesterol;
                        case 4 -> Exame.RaioX;
                        case 5 -> Exame.Eletrocardiograma;
                        case 6 -> Exame.TesteErgometrico;
                        case 7 -> Exame.Audiometria;
                        case 8 -> Exame.Audio;
                        case 9 -> Exame.Visao;
                        default -> Exame.Sangue;
                    };

                    StatusConsulta status = switch (resultSet.getInt("idStatus"))
                    {
                        case 1 -> StatusConsulta.AGENDADA;
                        case 2 -> StatusConsulta.REAGENDADA;
                        case 3 -> StatusConsulta.AGUARDANDO;
                        case 4 -> StatusConsulta.EM_ATENDIMENTO;
                        case 5 -> StatusConsulta.REALIZADA;
                        case 6 -> StatusConsulta.CANCELADA;
                        default -> StatusConsulta.FALTA;
                    };

                    consulta = new Consulta(
                            resultSet.getDate("dataConsulta"),
                            resultSet.getTime("horarioConsulta"),
                            resultSet.getLong("idPaciente"),
                            resultSet.getLong("idMedico"),
                            exame,
                            resultSet.getString("relatorio"),
                            status,
                            resultSet.getLong("idConsulta")

                    );

                    if (consulta != null && !listaConsultas.contains(consulta))
                    {
                        listaConsultas.add(consulta);
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Não foi possível buscar consultas na data: "+date);
        }
        return listaConsultas;
    }

    // -- UPDATES -- //

    public void updateMedico(long id, long idMedico)
    {
        String querySql = "UPDATE Consulta "+
                "SET idPaciente = ? "+
                "WHERE idConsulta = ? ";
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setLong(1,idMedico);
            stmt.setLong(2, id);

            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao atualizar o médico da Consulta com ID: "+id +e);
        }
    }
    public void updatePaciente(long id, long idPaciente)
    {
        String querySql = "UPDATE Consulta "+
                "SET idPaciente = ? "+
                "WHERE idConsulta = ?";
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(querySql))
        {
            statement.setLong(1, idPaciente);
            statement.setLong(2, id);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao atualizar paciente em consulta com ID: "+id + e);
        }
    }
    public void updateExame(long id, Exame exame)
    {
        String querySql = "UPDATE Consulta "+
                "SET idExame = ? "+
                "WHERE idConsulta = ? ";
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setLong(1, exame.getIdExame());
            stmt.setLong(2, id);

            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao atualizar exame na consulta com ID: "+id + e);
        }
    }
}
