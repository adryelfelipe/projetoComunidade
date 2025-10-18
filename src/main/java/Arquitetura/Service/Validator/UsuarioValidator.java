package Arquitetura.Service.Validator;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Exception.UsuarioExistenteException;
import Arquitetura.Model.Usuario;

public class UsuarioValidator {

    // -- Métodos de verificação -- //
    public void verificarDadosUser(Usuario usuario) {
        if(usuario == null) {
            throw new DadosInvalidosException("ERRO! O USUÁRIO NÃO PODE SER NULO");
        }

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
        if(usuarioInserido.getId() != 0) {
            throw new UsuarioExistenteException("ERRO! ESTE USUÁRIO JÁ FOI CADASTRADO");
        }
    }

    // - Verificações de integridade - //
    public void verificaIntegridadeCpf(String cpf) {
        if(cpf.isBlank()) {
            throw new DadosInvalidosException("ERRO! O CPF NÃO PODE SER VAZIO");
        }

        if(cpf.length() != 11) {
            throw new DadosInvalidosException("ERRO! O CPF DEVE TER 11 DÍGITOS");
        }
    }

    public void verificaIntegridadeTelefone(String telefone) {
        if(telefone.isBlank()) {
            throw new DadosInvalidosException("ERRO! O TELEFONE NÃO PODE SER VAZIO");
        }

        if(telefone.length() != 11) {
            throw new DadosInvalidosException("ERRO! O TELEFONE DEVE TER 11 DÍGITOS");
        }
    }

    public void verificaIntegridadeNome(String nome) {
        if(nome.isBlank()) {
            throw new DadosInvalidosException("ERRO! O NOME NÃO PODE SER VAZIO");
        }
    }

    public void verificaIntegridadeSenha(String senha) {
        if(senha.isBlank()) {
            throw new DadosInvalidosException("ERRO! A SENHA NÃO PODE SER VAZIA");
        }
    }

    public void verificaIntegridadeEmail(String email) {
        if(email.isBlank()) {
            throw new DadosInvalidosException("ERRO! O EMAIL NÃO PODE SER VAZIO");
        }

        if(!email.contains("@")) {
            throw new DadosInvalidosException("ERRO! EMAIL INVÁLIDO");
        }
    }

    public void verificaIntegridadeId(long id) {
        if(id < 0) {
            throw new DadosInvalidosException("ERRO! O ID NÃO PODE SER MENOR QUE 0");
        }
    }
}
