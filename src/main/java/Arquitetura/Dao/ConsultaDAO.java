package Arquitetura.Dao;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Consulta;
import Arquitetura.Model.Enums.Exame;
import Arquitetura.Model.Enums.Status;
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
            System.err.println("Não foi possível marcar uma Consulta: "+e.getMessage());
        }
    }

    // Remoção
    public boolean deletarConsulta(long id)
    {

        String querySql = "delete from Consulta where idConsulta = ?";

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(querySql))
        {
            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            // Retornar True se conseguiu deletar
            if (linhasAfetadas > 0) {
                return true;
            }
            // E retornara False se não conseguiu ou não existe
            else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar Consulta com ID " + id + ": " + e.getMessage());
            return false;
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

                    Status status = switch (resultSet.getInt("idStatus"))
                    {
                        case 1 -> Status.AGENDADA;
                        case 2 -> Status.REAGENDADA;
                        case 3 -> Status.AGUARDANDO;
                        case 4 -> Status.EM_ATENDIMENTO;
                        case 5 -> Status.REALIZADA;
                        case 6 -> Status.CANCELADA;
                        default -> Status.FALTA;
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
            System.err.println("Não foi possível buscar Consulta: "+ e.getMessage());
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
            System.err.println("Não foi possível buscar todas as consultas: "+e.getMessage());
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
            System.err.println("Não possível buscar lista de COnsultas: "+e.getMessage());
        }

        return listaConsultasPaciente;
    }
}
