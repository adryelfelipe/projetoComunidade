package Arquitetura.Service.Validator;

import Arquitetura.Exception.CpfInvalidoException;
import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Exception.IdInvalidoException;
import Arquitetura.Exception.UsuarioInvalidoException;
import Arquitetura.Model.Enums.Genero;
import Arquitetura.Model.Enums.TipoUsuario;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.UsuarioService;

import java.util.Date;

public class UsuarioValidator {

    // -- Métodos de análise -- //
    public boolean isUsuarioExistente(Usuario usuario) {
        return usuario.getId() != 0;
    }

    public boolean isAutoUpdate(long idUsuario, long idAtualizado) {
        return idUsuario == idAtualizado;
    }

    // -- Métodos verificadores de regras de negócio -- //
    public void verificaRegrasInsercaoUsuario(Usuario usuario) {
        verificarRegrasObjeto(usuario);
        verificarRegrasCpf(usuario.getCpf());
        verificarRegrasEmail(usuario.getEmail());
        verificarRegrasNome(usuario.getNome());
        verificarRegrasTelefone(usuario.getTelefone());
        verificarRegrasTipoUsuario(usuario.getTipoUsuario());
        verificarRegrasSexo(usuario.getSexo());
        verificarRegrasSenha(usuario.getSenha());
        verificarRegrasDataNascimento(usuario.getDataNascimento());
        verificaExistenciaUsuario(usuario);
    }

    public void verificaExistenciaUsuario(Usuario usuario) {
        if(isUsuarioExistente(usuario)) {
            throw new UsuarioInvalidoException("ERRO! ESTE USUÁRIO JÁ FOI CADASTRADO");
        }
    }

    public void verificarRegrasObjeto(Usuario usuario) {
        if(usuario == null) {
            throw new DadosInvalidosException("ERRO! O USUÁRIO NÃO PODE SER NULO");
        }
    }

    public void verificarRegrasCpf(String cpf) {
        if(cpf == null) {
            throw new DadosInvalidosException("ERRO! O CPF NÃO PODE SER NULO");
        }
    }

    public void verificarRegrasTelefone(String telefone) {
        if(telefone == null) {
            throw new DadosInvalidosException("ERRO! O TELEFONE NÃO PODE SER NULO");
        }
    }

    public void verificarRegrasTipoUsuario(TipoUsuario tipoUsuario) {
        if(tipoUsuario == null) {
            throw new DadosInvalidosException("ERRO! O TIPO USUÁRIO NÃO PODE SER NULO");
        }
    }

    public void verificarRegrasSexo(Genero sexo) {
        if(sexo == null) {
            throw new DadosInvalidosException("ERRO! O SEXO NÃO PODE SER NULO");
        }
    }

    public void verificarRegrasNome(String nome) {
        if(nome == null) {
            throw new DadosInvalidosException("ERRO! O NOME NÃO PODE SER NULO");
        }

        if(nome.length() < 2) {
            throw new DadosInvalidosException("ERRO! O NOME DEVE CONTER MAIS DE 1 CARACTER");
        }
    }

    public void verificarRegrasEmail(String email) {
        if(email == null) {
            throw new DadosInvalidosException("ERRO! O EMAIL NÃO PODE SER NULO");
        }

        if(email.length() < 6) {
            throw new DadosInvalidosException("ERRO! O EMAIL DEVE CONTER MAIS DE 5 CARACTERES");
        }

        if(!email.contains(".")) {
            throw new DadosInvalidosException("ERRO! O EMAIL DEVE CONTER UM DOMÍNIO");
        }
    }

    public void verificarRegrasSenha(String senha) {
        if(senha == null) {
            throw new DadosInvalidosException("ERRO! A SENHA NÃO PODE SER NULA");
        }

        if(senha.length() < 6) {
            throw new DadosInvalidosException("ERRO! A SENHA DEVE CONTER MAIS DE 5 CARACTERES");
        }
    }

    public void verificarRegrasDataNascimento(Date dataNascimento) {
        if(dataNascimento == null) {
            throw new DadosInvalidosException("ERRO! A DATA DE NASCIMENTO NÃO PODE SER NULA");
        }
    }

    // -- Métodos verificadores de integridade de dados -- //
    public static void verificaIntegridadeCpf(String cpf) {
        if(cpf.isBlank()) {
            throw new DadosInvalidosException("ERRO! O CPF NÃO PODE SER VAZIO");
        }

        if(cpf.length() != 11) {
            throw new DadosInvalidosException("ERRO! O CPF DEVE TER 11 DÍGITOS");
        }
    }

    public static void verificaIntegridadeTelefone(String telefone) {
        if(telefone.isBlank()) {
            throw new DadosInvalidosException("ERRO! O TELEFONE NÃO PODE SER VAZIO");
        }

        if(telefone.length() != 11) {
            throw new DadosInvalidosException("ERRO! O TELEFONE DEVE TER 11 DÍGITOS");
        }
    }

    public static void verificaIntegridadeNome(String nome) {
        if(nome.isBlank()) {
            throw new DadosInvalidosException("ERRO! O NOME NÃO PODE SER VAZIO");
        }
    }

    public static void verificaIntegridadeSenha(String senha) {
        if(senha.isBlank()) {
            throw new DadosInvalidosException("ERRO! A SENHA NÃO PODE SER VAZIA");
        }
    }

    public static void verificaIntegridadeEmail(String email) {
        if(email.isBlank()) {
            throw new DadosInvalidosException("ERRO! O EMAIL NÃO PODE SER VAZIO");
        }

        if(!email.contains("@")) {
            throw new DadosInvalidosException("ERRO! O EMAIL DEVE CONTER '@'");
        }
    }

    public static void verificaIntegridadeId(long id) {
        if(id < 0) {
            throw new DadosInvalidosException("ERRO! O ID NÃO PODE SER MENOR QUE 0");
        }
    }
}
