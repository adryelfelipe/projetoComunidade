package Arquitetura.Model;

import Arquitetura.Model.Enums.Genero;
import Arquitetura.Model.Enums.TipoUsuario;

import java.sql.Date;

public abstract class Usuario {

    // -- Atributos -- //
    private String nome;
    private String cpf;
    private String senha;
    private Genero sexo;
    private String telefone;
    private String email;
    private Date dataNascimento;
    private long id;
    private final TipoUsuario tipoUsuario;

    // -- Construtor -- //
    public Usuario(TipoUsuario tipoUsuario, String nome, String cpf, String senha, Genero sexo, String telefone, String email, Date dataNascimento) {
        if(tipoUsuario == null) {
            throw new IllegalArgumentException("ERRO! O TIPO USUÁRIO NÃO PODE SER NULO");
        }

        this.tipoUsuario = tipoUsuario;

        setSenha(senha);

        setNome(nome);

        setCpf(cpf);

        setSexo(sexo);

        setTelefone(telefone);

        setEmail(email);
    }

    // -- Setters e Getters -- //
    public TipoUsuario getTipoUsuario() {
        return this.tipoUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome.isEmpty()) {
            throw new IllegalArgumentException("ERRO! O NOME NÃO PODE SER VAZIO");
        }

        this.nome = nome;
    }

    public Genero getSexo() {
        return sexo;
    }

    public void setSexo(Genero sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if(telefone.isEmpty()) {
            throw new IllegalArgumentException("ERRO! O TELEFONE NÃO PODE SER VAZIO");
        }

        if(telefone.length() != 11) {
            throw new IllegalArgumentException("ERRO! O TELEFONE DEVE TER 11 DÍGITOS");
        }

        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email.isEmpty()) {
            throw new IllegalArgumentException("ERRO! O EMAIL NÃO PODE SER VAZIO");
        }

        if(!email.contains("@")) {
            throw new IllegalArgumentException("ERRO! EMAIL INVÁLIDO");
        }

        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if(id < 0) {
            throw new IllegalArgumentException("ERRO! O ID NÃO PODE SER MENOR QUE 0");
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if(senha.isEmpty()) {
            throw new IllegalArgumentException("ERRO! A SENHA NÃO PODE SER VAZIA");
        }

        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if(cpf.isEmpty()) {
            throw new IllegalArgumentException("ERRO! O CPF NÃO PODE SER VAZIO");
        }

        this.cpf = cpf;
    }

    public void dadosPessoais() {
        System.out.println("ID: " + getId());
        System.out.println("NOME: " + getNome());
        System.out.println("EMAIL: " + getEmail());
        System.out.println("CPF: " + getCpf());
        System.out.println("SEXO: " + getSexo());
        System.out.println("TELEFONE: " + getTelefone());
        System.out.println("DATA DE NASCIMENTO: " + getDataNascimento());
    }
}
