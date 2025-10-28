package Arquitetura.Service.Validator;

import Arquitetura.Exception.CpfInvalidoException;
import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Exception.IdInvalidoException;
import Arquitetura.Exception.UsuarioInvalidoException;
import Arquitetura.Model.Enums.Genero;
import Arquitetura.Model.Enums.TipoUsuario;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.UsuarioService;
import Arquitetura.Utilidades.Ferramentas;

import java.util.Date;

public class UsuarioValidator {

    // -- Métodos de análise -- //
    public boolean isUsuarioExistente(Usuario usuario) {
        return usuario.getId() != 0;
    }

    public boolean isAutoUpdate(long idUsuario, long idAtualizado) {
        return idUsuario == idAtualizado;
    }

    // -------------- Métodos verificadores de regras de negócio -------------- //
    public void verificaRegrasInsercaoUsuario(Usuario inseror, Usuario usuarioInserido) {
        verificaRegrasUsuarioInsersor(inseror);
        verificarRegrasObjeto(usuarioInserido);
        verificarRegrasCpf(usuarioInserido.getCpf());
        verificarRegrasEmail(usuarioInserido.getEmail());
        verificarRegrasNome(usuarioInserido.getNome());
        verificarRegrasTelefone(usuarioInserido.getTelefone());
        verificarRegrasTipoUsuario(usuarioInserido.getTipoUsuario());
        verificarRegrasSexo(usuarioInserido.getSexo());
        verificarRegrasSenha(usuarioInserido.getSenha());
        verificarRegrasDataNascimento(usuarioInserido.getDataNascimento());
        verificaExistenciaUsuario(usuarioInserido);
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

        if(tipoUsuario.getIdTipoUsuario() > 3) {
            throw new DadosInvalidosException("ERRO! TIPO USUÁRIO INVÁLIDO");
        }
    }

    public void verificarRegrasSexo(Genero sexo) {
        if(sexo == null) {
            throw new DadosInvalidosException("ERRO! O SEXO NÃO PODE SER NULO");
        }

        if(sexo.getIdGenero() > 2) {
            throw new DadosInvalidosException("ERRO! GÊNERO INVÁLIDO");
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
                throw new IllegalStateException("ERRO! A SENHA NÃO PODE SER NULA");
            }

            if(senha.contains(" ")) {
                throw new IllegalStateException("ERRO! A SENHA NÃO PODE CONTER ESPAÇOS");
            }

            if(senha.length() < 6) {
                throw new IllegalStateException("ERRO! A SENHA DEVE CONTER MAIS DE 5 CARACTERES");
            }


            // -- VALIDAÇÃO DE MAIUSCULAS E ESPECIAIS -- //
            boolean verificaMaiuscula = false;
            boolean verificaEspecial = false;

            for(String maiuscula : Ferramentas.listaMaiusculos) {
                if (senha.contains(maiuscula)) {
                    verificaMaiuscula = true;
                    break;
                }
            }

            for(String caractereEspecial : Ferramentas.listaEspeciais) {
                if(senha.contains(caractereEspecial)) {
                    verificaEspecial = true;
                    break;
                }
            }

            if(!verificaMaiuscula) {
                throw new IllegalStateException("ERRO! A SENHA DEVE CONTER UMA LETRA MAIÚSCULA");
            }

            if(!verificaEspecial) {
                throw new IllegalStateException("ERRO! A SENHA DEVE CONTER UM CARACTERE ESPECIAL");
            }
        }


    public void verificarRegrasDataNascimento(Date dataNascimento) {
        if(dataNascimento == null) {
            throw new DadosInvalidosException("ERRO! A DATA DE NASCIMENTO NÃO PODE SER NULA");
        }
    }

    public void verificaRegrasUsuarioInsersor(Usuario usuario) {
        if(usuario == null) {
            throw new UsuarioInvalidoException("ERRO! O USUÁRIO INSERSOR NÃO PODE SER NULO");
        }
    }


    // -------------- Métodos verificadores de integridade de dados -------------- //
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
        // -- Verifica se o email é nulo ou se apenas contem espaços
        if (email.isBlank()) {
            throw new DadosInvalidosException("ERRO! O EMAIL NÃO PODE SER VAZIO!");
        }

        // Comando regex para verificar email
        String regex = "(?i)^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$";

        // Verifica se as informações do email condiz com padrão do regex
        if (!email.matches(regex)) {
            throw new DadosInvalidosException("ERRO! O FORMATO DO EMAIL ESTÁ INCORRETO!");
        }
    }

    public static void verificaIntegridadeId(long id) {
        if(id < 0) {
            throw new DadosInvalidosException("ERRO! O ID NÃO PODE SER MENOR QUE 0");
        }
    }
}
