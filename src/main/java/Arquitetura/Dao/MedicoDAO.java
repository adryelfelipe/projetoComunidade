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
            System.out.println("Erro ao inserir Médico : " + e.getMessage());
        }
    }

    // Remoção
    public boolean deletarMedico(long id) {
        String querySql = "DELETE FROM Medico WHERE idMedico = ?";

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(querySql)) {
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
            System.err.println("Erro ao deletar Médico com ID " + id + ": " + e.getMessage());
            return false;
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
            System.err.println("Erro ao buscar o Médico : "+e.getMessage());
            return medico;
        }

    }
}
