package Arquitetura.Dao;

import Arquitetura.Config.ConnectionFactory;
import Arquitetura.Model.Usuario;

import java.sql.*;

public class UsuarioDAO {

    public void inserirUsuario(Usuario usuario){

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
            System.out.println("Erro ao inserir o Usuario: "+e.getMessage());
        }
    }
}