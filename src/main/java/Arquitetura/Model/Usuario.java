package Arquitetura.Model;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Model.Enums.Genero;
import Arquitetura.Model.Enums.TipoUsuario;
import Arquitetura.Service.Validator.UsuarioValidator;

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
        UsuarioValidator.verificaIntegridadeNome(nome);

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
        UsuarioValidator.verificaIntegridadeTelefone(telefone);

        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        UsuarioValidator.verificaIntegridadeEmail(email);

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
        UsuarioValidator.verificaIntegridadeId(id);

        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        UsuarioValidator.verificaIntegridadeSenha(senha);

        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        UsuarioValidator.verificaIntegridadeCpf(cpf);

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
