package Arquitetura.Dao.UsuarioDAO;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Enums.*;
import Arquitetura.Model.Medico;
import Arquitetura.Model.Paciente;
import Arquitetura.Model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class ReadUsuarioDAO
{
    // Procurar por ID
    public Usuario findById(long idUsuario) {
        String querySQL = "SELECT " +
                "U.idUsuario, U.senha, U.nomeUsuario, U.sexo, U.cpf, U.telefone, U.email, U.dataNascimento, U.tipoUsuario, " +
                "A.idDepartamento, " +
                "P.numeroCadastro, P.contatoEmergencia, P.idStatusPaciente, " +
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
                    int idDepartamento = resultSet.getInt("idDepartamento");

                    Departamento departamento = switch(idDepartamento) {
                        case 1 -> Departamento.FINANCEIRO;
                        case 2 -> Departamento.INFRAESTRUTURA;
                        case 3 -> Departamento.MARKETING;
                        default -> Departamento.RH;
                    };

                    // Dados Paciente
                    String numCadastro = resultSet.getString("numeroCadastro");
                    String contatoEmergencia = resultSet.getString("contatoEmergencia");
                    int statusPaciente = resultSet.getInt("idStatusPaciente");

                    StatusPaciente status = switch (statusPaciente)
                    {
                        case 1 -> StatusPaciente.ATIVO;
                        default -> StatusPaciente.INATIVO;
                    };

                    // Dados Médico
                    int idEspecialidade = resultSet.getInt("idEspecialidade");
                    String subEspecialidade = resultSet.getString("subEspecialidade");
                    String formacao = resultSet.getString("formacao");
                    int idPlantao = resultSet.getInt("idPlantao");

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
                        case 1 -> new Paciente(id, nomeUsuario, cpf, senha, sexo, telefone, email, dataNascimento, contatoEmergencia, numCadastro, status);
                        case 2 -> new Medico(id, nomeUsuario,cpf, senha, sexo, telefone, email, dataNascimento, cargaHorariaSemanal,  salario, plantao, especialidade, formacao, subEspecialidade);
                        default -> new Administrador(nomeUsuario, cpf, senha, sexo, telefone, email, dataNascimento, salario, cargaHorariaSemanal, departamento, id);
                    };
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Usuário pelo ID.");
        }

        return usuario;
    }

    // Procurar por CPF
    public Usuario findByCpf(String cpf) {
        String querySQL = "SELECT " +
                "U.idUsuario, U.senha, U.nomeUsuario, U.sexo, U.cpf, U.telefone, U.email, U.dataNascimento, U.tipoUsuario, " +
                "A.idDepartamento, " +
                "P.numeroCadastro, P.contatoEmergencia, P.idStatusPaciente, " +
                "M.idPlantao, M.idEspecialidade, M.subEspecialidade, M.formacao, " +
                "F.salario, F.cargaHorariaSemanal " +
                "FROM Usuario U " +
                "LEFT JOIN Administrador A ON U.idUsuario = A.idAdministrador " +
                "LEFT JOIN Medico M ON U.idUsuario = M.idMedico " +
                "LEFT JOIN Paciente P ON U.idUsuario = P.idPaciente " +
                "LEFT JOIN Funcionario F ON U.idUsuario = F.idFuncionario " +
                "WHERE U.cpf = ?";

        Usuario usuario = null;

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySQL)) {

            stmt.setString(1, cpf);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    // Dados Gerais do Usuário
                    long id = resultSet.getLong("idUsuario");
                    String senha = resultSet.getString("senha");
                    String nomeUsuario = resultSet.getString("nomeUsuario");
                    int sexoId = resultSet.getInt("sexo");
                    String cpfUsuario = resultSet.getString("cpf");
                    String telefone = resultSet.getString("telefone");
                    String email = resultSet.getString("email");
                    Date dataNascimento = resultSet.getDate("dataNascimento");
                    int tipoUsuario = resultSet.getInt("tipoUsuario");

                    Genero sexo = switch (sexoId) {
                        case 1 -> Genero.MASCULINO;
                        default -> Genero.FEMININO;
                    };

                    // Dados Administrador
                    int idDepartamento = resultSet.getInt("idDepartamento");

                    Departamento departamento = switch(idDepartamento) {
                        case 1 -> Departamento.FINANCEIRO;
                        case 2 -> Departamento.INFRAESTRUTURA;
                        case 3 -> Departamento.MARKETING;
                        default -> Departamento.RH;
                    };

                    // Dados Paciente
                    String numCarteirinha = resultSet.getString("numeroCadastro");
                    String contatoEmergencia = resultSet.getString("contatoEmergencia");
                    int statusPaciente = resultSet.getInt("idStatusPaciente");

                    StatusPaciente status = switch (statusPaciente)
                    {
                        case 1 -> StatusPaciente.ATIVO;
                        default -> StatusPaciente.INATIVO;
                    };

                    // Dados Médico
                    int idEspecialidade = resultSet.getInt("idEspecialidade");
                    String subEspecialidade = resultSet.getString("subEspecialidade");
                    String formacao = resultSet.getString("formacao");
                    int idPlantao = resultSet.getInt("idPlantao");

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
                        case 1 -> new Paciente(id, nomeUsuario, cpfUsuario, senha, sexo, telefone, email, dataNascimento, contatoEmergencia, numCarteirinha, status);
                        case 2 -> new Medico(id, nomeUsuario,cpfUsuario, senha, sexo, telefone, email, dataNascimento, cargaHorariaSemanal,  salario, plantao, especialidade, formacao, subEspecialidade);
                        default -> new Administrador(nomeUsuario, cpfUsuario, senha, sexo, telefone, email, dataNascimento, salario, cargaHorariaSemanal, departamento, id);
                    };
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Usuário pelo CPF. ");
        }
        return usuario;
    }

    // Buscar todos os Usuarios
    public ArrayList<Usuario> findAllUsuarios()
    {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();

        String querySql = "SELECT idUsuario FROM Usuario ";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            try (ResultSet resultSet = stmt.executeQuery())
            {
                while (resultSet.next())
                {
                    Usuario usuario = findById(resultSet.getInt("idUsuario"));

                    if(usuario != null)
                    {
                        listaUsuarios.add(usuario);
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao buscar todos os usuários " + e);
        }
        return listaUsuarios;
    }

    // Desenvolve login do Usuario
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
            System.err.println("Erro ao tentar logar na conta.");
            return null;
        }
        return null;
    }

    // Converte Cpf para ID
    public long getIdOfCpf(String cpf)
    {
        long id = 0;

        String querySql = "SELECT idUsuario FROM Usuario WHERE cpf = ? ";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareCall(querySql))
        {
            stmt.setString(1, cpf);

            try(ResultSet resultSet = stmt.executeQuery())
            {
                if(resultSet.next())
                {
                    id = resultSet.getInt(1);
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao converter para ID o cpf: "+ cpf + e);
        }
        return id;
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
            System.err.println("Erro ao verificar o CPF: "+ cpf);
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
}
