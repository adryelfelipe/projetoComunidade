package Arquitetura.Dao;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Enums.Departamento;
import Arquitetura.Model.Enums.Especialidade;
import Arquitetura.Model.Enums.Genero;
import Arquitetura.Model.Enums.Plantao;
import Arquitetura.Model.Medico;
import Arquitetura.Model.Paciente;
import Arquitetura.Model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO {

    // -- CRUD -- //

    // Inserção
    public void inserirUsuario(Usuario usuario) {

        String sql = "INSERT INTO Usuario (senha, nomeUsuario, sexo, cpf, telefone, tipoUsuario, email, dataNascimento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int idGerado = -1;


        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            //Configura os parâmetros (usando os getters do objeto)
            stmt.setString(1, usuario.getSenha());
            stmt.setString(2, usuario.getNome());
            stmt.setLong(3, usuario.getSexo().getIdGenero());
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getTelefone());
            stmt.setLong(6, usuario.getTipoUsuario().getIdTipoUsuario());
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

    // Leitura por ID
    public Usuario findById(long idUsuario) {
        String querySQL = "SELECT " +
                "U.idUsuario, U.senha, U.nomeUsuario, U.sexo, U.cpf, U.telefone, U.email, U.dataNascimento, U.tipoUsuario, " +
                "A.idDepartamento, " +
                "P.numeroCarteirinha, P.contatoEmergencia, P.statusPaciente, " +
                "M.idPlantao, M.idEspecialidade, M.subEspecialidade, M.formacao, " +
                "F.salario, F.cargaHorariaSemanal " +
                "FROM Usuario U " +
                "LEFT JOIN Administrador A ON U.idUsuario = A.idAdministrador " +
                "LEFT JOIN Medico M ON U.idUsuario = M.idMedico " +
                "LEFT JOIN Paciente P ON U.idUsuario = P.idPaciente " +
                "LEFT JOIN Funcionario F ON U.idUsuario = F.idFuncionario " +
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

                    // Dados Administrador
                    int idDepartamento = resultSet.getInt("departamento");

                    Departamento departamento = switch(idDepartamento) {
                        case 1 -> Departamento.FINANCEIRO;
                        case 2 -> Departamento.INFRAESTRUTURA;
                        case 3 -> Departamento.MARKETING;
                        default -> Departamento.RH;
                    };

                    // Dados Paciente
                    String numCarteirinha = resultSet.getString("numeroCarteirinha");
                    String contatoEmergencia = resultSet.getString("contatoEmergencia");
                    String statusPaciente = resultSet.getString("statusPaciente");

                    // Dados Médico
                    int idEspecialidade = resultSet.getInt("idEspecialidade");
                    String subEspecialidade = resultSet.getString("subEspecialidade");
                    String formacao = resultSet.getString("formacao");
                    int idPlantao = resultSet.getInt("plantao");

                    Plantao plantao = switch(idPlantao) {
                        case 1 -> Plantao.MATUTINO;
                        case 2 -> Plantao.VERPERTINO;
                        default -> Plantao.NOTURNO;
                    };

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

                    // Dados Funcionario
                    double salario = resultSet.getDouble("salario");
                    int cargaHorariaSemanal = resultSet.getInt("cargaHorariaSemanal");

                    // Cria o objeto correto de acordo com o tipo de usuário
                    usuario = switch (tipoUsuario)
                    {
                        case 1 -> new Paciente(nomeUsuario, cpf, senha, sexo, telefone, email, dataNascimento, contatoEmergencia, numCarteirinha);
                        case 2 -> new Medico(nomeUsuario,cpf, senha, sexo, telefone, email, dataNascimento, cargaHorariaSemanal,  salario, plantao, especialidade, formacao, subEspecialidade);
                        default -> new Administrador(nomeUsuario, cpf, senha, sexo, telefone, email, dataNascimento, salario, cargaHorariaSemanal, departamento);
                    };
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar Usuário por ID: " + e.getMessage());
        }

        return usuario;
    }

    // Remoção
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

    // Leitura de todos os usuários
    public ArrayList<Usuario> findAllUsers()
    {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();

        String querySql = "SELECT " +
                "U.idUsuario, U.senha, U.nomeUsuario, U.sexo, U.cpf, U.telefone, U.email, U.dataNascimento, U.tipoUsuario, " +
                "A.idDepartamento, " +
                "P.numeroCarteirinha, P.contatoEmergencia, P.statusPaciente, " +
                "M.idPlantao, M.idEspecialidade, M.subEspecialidade, M.formacao, " +
                "F.salario, F.cargaHorariaSemanal "+
                "FROM Usuario U " +
                "LEFT JOIN Administrador A ON U.idUsuario = A.idAdministrador " +
                "LEFT JOIN Medico M ON U.idUsuario = M.idMedico " +
                "LEFT JOIN Paciente P ON U.idUsuario = P.idPaciente " +
                "LEFT JOIN Funcionario F ON U.idUsuario = F.idFuncionario";

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

    // Leitura - verifica se existe um cpf igual ao do parâmetro no banco de dados
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
            System.err.println("Erro ao verificar senha: "+ e.getMessage());
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
            System.err.println("Erro ao verificar email: "+ e.getMessage());
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
            System.err.println("Erro ao verificar existência de email: "+ e.getMessage());
            return false;
        }
    }
    public Usuario loginUsuario(String cpf, String senha)
    {
        String querySQL = "SELECT idUsuario, senha FROM Usuario WHERE cpf = ? LIMIT 1";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(querySQL))
        {
            stmt.setString(1, cpf);

            try (ResultSet resultSet = stmt.executeQuery())
            {
                if(resultSet.next())
                {
                    if(senha.equals(resultSet.getString("senha")))
                    {
                        long idUsuario = resultSet.getLong("idUsuario");

                        return findById(idUsuario);
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao iniciar login com o usuário: "+ e.getMessage());
            return null;
        }
        return null;
    }
}