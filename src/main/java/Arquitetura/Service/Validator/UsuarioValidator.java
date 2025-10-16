package Arquitetura.Service.Validator;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Exception.UsuarioExistenteException;
import Arquitetura.Model.Usuario;

public class UsuarioValidator {

    // -- Métodos de verificação -- //
    public void verificarDadosUser(Usuario usuario) {

       if(usuario.getCpf() == null) {
           throw new DadosInvalidosException("ERRO! O CPF NÃO PODE SER NULO");
       }

       if(usuario.getTelefone() == null) {
           throw new DadosInvalidosException("ERRO! O TELEFONE NÃO PODE SER NULO");
       }

       if(usuario.getTipoUsuario() == null) {
           throw new DadosInvalidosException("ERRO! O TIPO USUÁRIO NÃO PODE SER NULO");
       }

       if(usuario.getSexo() == null) {
           throw new DadosInvalidosException("ERRO! O SEXO NÃO PODE SER NULO");
       }

       if(usuario.getNome() == null) {
           throw new DadosInvalidosException("ERRO! O NOME NÃO PODE SER NULO");
       }

       if(usuario.getEmail() == null) {
           throw new DadosInvalidosException("ERRO! O EMAIL NÃO PODE SER NULO");
       }

       if(usuario.getSenha() == null) {
           throw new DadosInvalidosException("ERRO! A SENHA NÃO PODE SER NULA");
       }

       if(usuario.getDataNascimento() == null) {
           throw new DadosInvalidosException("ERRO! A DATA DE NASCIMENTO NÃO PODE SER NULA");
       }
    }

    public void verificaRegrasInsercaoUsuario(Usuario usuarioInserido) {
        if(usuarioInserido == null) {
            throw new DadosInvalidosException("ERRO! NÃO É POSSÍVEL INSERIR UM USARIO NULO");
        }

        if(usuarioInserido.getId() != 0) {
            throw new UsuarioExistenteException("ERRO! ESTE USUÁRIO JÁ FOI CADASTRADO");
        }
    }

    public void verificaRegrasDelecaoUsuario(Usuario usuarioDeletado) {
        if(usuarioDeletado == null) {
            throw new DadosInvalidosException("ERRO! NÃO É POSSÍVEL DELETAR UM USUARIO NULO");
        }
    }
}
