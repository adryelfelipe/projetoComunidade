package Arquitetura.Dao.UsuarioDAO;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Usuario;

import java.sql.*;

public class CreateUsuarioDAO
{
    //Insercao de Usuarios
    public void inserirUsuario(Usuario usuario)
    {
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
            System.err.println("Erro ao inserir o Usuario.");
        }
    }
}
