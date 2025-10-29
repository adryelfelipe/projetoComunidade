package Arquitetura.Dao.AdministradorDAO;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Enums.Departamento;
import Arquitetura.Model.Enums.Genero;
import Arquitetura.Model.Enums.TipoUsuario;

import java.sql.*;
import java.util.ArrayList;

public class ReadAdministradorDAO
{
    // Leitura - Busca todos os Administradores
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

    // Leitura - Verifica se o cpf pertence à um Administrador
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
}
