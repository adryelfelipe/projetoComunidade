package Arquitetura.Dao;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Medico;
import Arquitetura.Model.Paciente;
import Arquitetura.Model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO {

    public void inserirUsuario(Usuario usuario) {

        String sql = "INSERT INTO Usuario (senha, nomeUsuario, sexo, cpf, telefone, tipoUsuario, email, dataNascimento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int idGerado = -1;


        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            //Configura os parâmetros (usando os getters do objeto)
            stmt.setString(1, usuario.getSenha());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getSexo());
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getTelefone());
            stmt.setString(6, usuario.getTipoUsuario());
            stmt.setString(7, usuario.getEmail());
            stmt.setDate(8, usuario.getDataNascimento());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                //Obtem o ResultSet das chaves geradas
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        //Pega o ID gerado pelo banco
                        idGerado = rs.getInt(1);
                        usuario.setId(idGerado);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir o Usuario: " + e.getMessage());
        }
    }


    public Usuario findById(long idUsuario) {
        String querySQL = "SELECT " +
                "U.idUsuario, U.senha, U.nomeUsuario, U.sexo, U.cpf, U.telefone, U.email, U.dataNascimento, U.tipoUsuario, " +
                "A.salario AS salarioAdmin, A.cargaHoraria AS cargaHorariaAdmin, " +
                "P.numeroCarteirinha, P.contatoEmergencia, P.statusPaciente, " +
                "M.salario AS salarioMedico, M.cargaHorariaSemanal, M.plantao, M.especialidade, M.subEspecialidade, M.formacao " +
                "FROM Usuario U " +
                "LEFT JOIN Administrador A ON U.idUsuario = A.idAdministrador " +
                "LEFT JOIN Medico M ON U.idUsuario = M.idMedico " +
                "LEFT JOIN Paciente P ON U.idUsuario = P.idPaciente " +
                "WHERE U.idUsuario = ?";

        Usuario usuario = null;

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySQL)) {

            stmt.setLong(1, idUsuario);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    // Dados Gerais do Usuário
                    long id = resultSet.getLong("idUsuario");
                    String senha = resultSet.getString("senha");
                    String nomeUsuario = resultSet.getString("nomeUsuario");
                    String sexo = resultSet.getString("sexo");
                    String cpf = resultSet.getString("cpf");
                    String telefone = resultSet.getString("telefone");
                    String email = resultSet.getString("email");
                    Date dataNascimento = resultSet.getDate("dataNascimento");
                    String tipoUsuario = resultSet.getString("tipoUsuario");

                    // Dados Administrador
                    double salarioAdmin = resultSet.getDouble("salarioAdmin");
                    int cargaHorariaAdmin = resultSet.getInt("cargaHorariaAdmin");

                    // Dados Paciente
                    String numCarteirinha = resultSet.getString("numeroCarteirinha");
                    String contatoEmergencia = resultSet.getString("contatoEmergencia");
                    String statusPaciente = resultSet.getString("statusPaciente");

                    // Dados Médico
                    double salarioMedico = resultSet.getDouble("salarioMedico");
                    int cargaHorariaSemanal = resultSet.getInt("cargaHorariaSemanal");
                    String plantao = resultSet.getString("plantao");
                    String especialidade = resultSet.getString("especialidade");
                    String subEspecialidade = resultSet.getString("subEspecialidade");
                    String formacao = resultSet.getString("formacao");

                    // Cria o objeto correto de acordo com o tipo de usuário
                    if (tipoUsuario.equalsIgnoreCase("Administrador")) {
                        usuario = new Administrador(nomeUsuario, cpf, senha, sexo, telefone, email, dataNascimento, salarioAdmin, cargaHorariaAdmin, id);
                    } else if (tipoUsuario.equalsIgnoreCase("Médico")) {
                        usuario = new Medico(id, nomeUsuario, cpf, senha, sexo, telefone, email, dataNascimento,
                                cargaHorariaSemanal, salarioMedico, plantao, especialidade, formacao, subEspecialidade);
                    } else if (tipoUsuario.equalsIgnoreCase("Paciente")) {
                        usuario = new Paciente(id, nomeUsuario, cpf, senha, sexo, telefone, email, dataNascimento, contatoEmergencia, numCarteirinha);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar Usuário por ID: " + e.getMessage());
        }

        return usuario;
    }

    public boolean deletarUsuario(long id) {
        String querySql = "DELETE FROM Usuario WHERE idUsuario = ?";

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
            System.err.println("Erro ao deletar usuário com ID " + id + ": " + e.getMessage());
            return false;
        }
    }
    public ArrayList<Usuario> findAllUsers()
    {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();

        String querySql = "SELECT " +
                "U.idUsuario, U.senha, U.nomeUsuario, U.sexo, U.cpf, U.telefone, U.email, U.dataNascimento, U.tipoUsuario, " +
                "A.salario AS adminSalario, A.cargaHoraria AS adminCargaHoraria, " +
                "P.numeroCarteirinha, P.contatoEmergencia, P.statusPaciente, " +
                "M.salario AS medicoSalario, M.cargaHorariaSemanal, M.plantao, M.especialidade, M.subEspecialidade, M.formacao " +
                "FROM Usuario U " +
                "LEFT JOIN Administrador A ON U.idUsuario = A.idAdministrador " +
                "LEFT JOIN Medico M ON U.idUsuario = M.idMedico " +
                "LEFT JOIN Paciente P ON U.idUsuario = P.idPaciente";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql);
                ResultSet resultSet = stmt.executeQuery())
        {
            while (resultSet.next())
            {

                //Cria um objeto usuário pelo idUsuario recebido da query
                Usuario usuario = findById(resultSet.getLong("idUsuario"));

                //Verifica se o objeto usuário não é vazio
                if(usuario != null)
                {
                    //Adiciona o objeto usuário na lista de usuários
                    listaUsuarios.add(usuario);
                }
            }
        }
        catch (SQLException e)
        {
               System.out.println("Erro ao buscar todos os Usuários: ");
        }

        // Retorna lista de usuários completa
        return listaUsuarios;
    }

    public boolean verificarCpf(String cpf) {
        String querySql = "SELECT 1 FROM Usuario WHERE cpf = ? LIMIT 1";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(querySql))
        {
            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {

                //Retorna a resposta caso o cpf exista ou não
                return rs.next();
            }

        } catch (SQLException e) {
            System.err.println("Erro ao verificar CPF: " + e.getMessage());
            return false;
        }
    }
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

                return isCorrect;
            }
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao verificar senha: "+ e.getMessage());
            return false;
        }
    }
}