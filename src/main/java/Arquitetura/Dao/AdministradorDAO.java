package Arquitetura.Dao;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Enums.Departamento;
import Arquitetura.Model.Enums.Genero;
import Arquitetura.Model.Enums.Plantao;
import Arquitetura.Model.Enums.TipoUsuario;

import java.sql.*;
import java.util.ArrayList;

public class AdministradorDAO {

    // -- CRUD -- //
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    // Inserção
    public void inserirAdmin(Administrador administrador)
    {
        String querySQL = "insert into Administrador (idAdministrador, idDepartamento) values (?, ?)";

        try(Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(querySQL))
        {
            stmt.setLong(1, administrador.getId());
            stmt.setLong(2, administrador.getDepartamento().getIdDepartamento());

            stmt.executeUpdate();

        }
        catch (SQLException e)
        {
            System.out.println("Erro ao inserir Administrador.");
        }
    }

    //Busca todos os Administradores
    public ArrayList<Administrador> findAllAdministradores()
    {
        ArrayList<Administrador> listaAdministradores = new ArrayList<>();

        String querySql = "SELECT "+
                "U.idUsuario, U.senha, U.nomeUsuario, U.sexo, U.cpf, U.telefone, U.email, U.dataNascimento, U.tipoUsuario, "+
                "A.idDepartamento, "+
                "F.idFuncionario, F.salario, F.cargaHorariaSemanal "+
                "FROM Usuario U "+
                "INNER JOIN Administrador A ON U.idUsuario = A.idAdministrador "+
                "INNER JOIN Funcionario F ON A.idAdministrador = F.idFuncionario ";
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            try (ResultSet resultSet = stmt.executeQuery())
            {
                while (resultSet.next())
                {
                    long idAdministrador = resultSet.getInt("idUsuario");
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
                    TipoUsuario tipoUsuario = TipoUsuario.MEDICO;

                    //Atributos Funcionario
                    double salario = resultSet.getDouble("salario");
                    int cargaHorariaSemanal = resultSet.getInt("cargaHorariaSemanal");

                    //Atributos Administrador
                    int idDepartamento = resultSet.getInt("idDepartamento");
                    Departamento departamento = switch (idDepartamento)
                    {
                        case 1 -> Departamento.FINANCEIRO;
                        case 2 -> Departamento.INFRAESTRUTURA;
                        case 3 -> Departamento.MARKETING;
                        default -> Departamento.RH;
                    };
                    Administrador administrador = new Administrador(nome, cpf, senha, genero, telefone, email, datanascimento, salario, cargaHorariaSemanal, departamento, idAdministrador);

                    if(administrador != null && !listaAdministradores.contains(administrador))
                    {
                        listaAdministradores.add(administrador);
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Não foi possível buscar todos os administradores: "+e);
        }
        return listaAdministradores;
    }
    // Remoção
    public void deletarAdministrador(String cpf) {
        String querySql = "DELETE a " +
                "FROM Administrador a " +
                "JOIN Usuario u ON u.idUsuario = a.idAdministrador " +
                "WHERE u.cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(querySql))
        {

            stmt.setString(1, cpf);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao deletar Administrador com o CPF: " + cpf);
        }
    }

    //Leitura - Verifica quantos se existe algum administrador no sistema
    public boolean isUltimoAdmin()
    {
        String querySQl = "SELECT COUNT(*) FROM Administrador";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySQl))
        {
            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next())
            {
                int cont = resultSet.getInt(1);

                return cont == 1;
            }
            return false;
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao verificar se é Último Admin. ");
            return false;
        }
    }

    // -- UPDATES -- //

    public void updateDepartamento(long id, Departamento departamento)
    {
        String querySql = "UPDATE Administrador "+
                "SET idDepartamento = ? "+
                "WHERE idAdministrador = ? ";
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setLong(1, departamento.getIdDepartamento());
            stmt.setLong(2, id);

            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao atualizar  departamento do administrador com ID: "+ id + e);
        }
    }

    public boolean isCpfAdministrador(String cpf) {
        String querySql = "SELECT tipoUsuario FROM Usuario WHERE cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(querySql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                long tipo = rs.getLong("tipoUsuario");
                return tipo == 3;
            }

        } catch (SQLException e)
        {
            System.err.println("Erro ao verificar o CPF do Administrador. ");
        }
        return false;
    }

    public boolean isIdAdministrador(long id)
    {
        String querySql = "SELECT tipoUsuario FROM Usuario WHERE cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(querySql))
        {

            String cpf = usuarioDAO.cpfByID(id);

            if (cpf == null) {
                return false;
            }

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int tipo = rs.getInt("tipoUsuario");
                    return tipo == 3;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao verificar o ID do Administrador.");
        }

        return false;
    }

    // -- Verificadores isSame -- //

    public boolean isSameDepartamento(long id, Departamento departamento)
    {
        String querySql = "SELECT idDepartamento FROM Administrador WHERE idAdministrador = ? ";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql)) {
            stmt.setLong(1, id);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    if (departamento.getIdDepartamento() == resultSet.getInt(1)) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar o departamento  do administrador com ID: " + id + e);
        }
        return false;
    }
}
