package Arquitetura.Service.Validator;

import Arquitetura.Model.Usuario;

public class UsuarioValidator {

    // -- Métodos de verificação -- //
    private void verificarDadosUser(Usuario usuario) {

       if(usuario.getCpf() == null) {
           throw new IllegalArgumentException("ERRO! O CPF NÃO PODE SER NULO");
       }

       if(usuario.getTelefone() == null) {
           throw new IllegalArgumentException("ERRO! O TELEFONE NÃO PODE SER NULO");
       }

       if(usuario.getTipoUsuario() == null) {
           throw new IllegalArgumentException("ERRO! O TIPO USUÁRIO NÃO PODE SER NULO");
       }

       if(usuario.getSexo() == null) {
           throw new IllegalArgumentException("ERRO! O SEXO NÃO PODE SER NULO");
       }

       if(usuario.getNome() == null) {
           throw new IllegalArgumentException("ERRO! O NOME NÃO PODE SER NULO");
       }

       if(usuario.getEmail() == null) {
           throw new IllegalArgumentException("ERRO! O EMAIL NÃO PODE SER NULO");
       }

       if(usuario.getSenha() == null) {
           throw new IllegalArgumentException("ERRO! A SENHA NÃO PODE SER NULA");
       }

       if(usuario.getDataNascimento() == null) {
           throw new IllegalArgumentException("ERRO! A DATA DE NASCIMENTO NÃO PODE SER NULA");
       }
    }
}
