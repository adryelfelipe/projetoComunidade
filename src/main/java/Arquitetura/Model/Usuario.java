package Arquitetura.Model;

import Arquitetura.Model.Enums.TipoUsuario;

import java.sql.Date;

public abstract class Usuario {

    // -- Atributos -- //
    private String nome;
    private String cpf;
    private String senha;
    private String sexo;
    private String telefone;
    private String email;
    private Date dataNascimento;
    private long id;
    private final TipoUsuario tipoUsuario;

    // -- Construtor -- //
    public Usuario(TipoUsuario tipoUsuario, String nome, String cpf, String senha, String sexo, String telefone, String email, Date dataNascimento) {
        this.tipoUsuario = tipoUsuario;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    // -- Setters e Getters -- //
    public TipoUsuario getTipoUsuario() {
        return this.tipoUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(!nome.isEmpty()) {
            this.nome = nome;
        }
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        if(!sexo.isEmpty()) {
            this.sexo = sexo;
        }
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if(!telefone.isEmpty()) {
            this.telefone = telefone;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(!email.isEmpty()) {
            this.email = email;
        }
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
        if(id > 0) {
            this.id = id;
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if(!senha.isEmpty()){
        this.senha = senha;
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if(!cpf.isEmpty()) {
            this.cpf = cpf;
        }
    }

    public long getIdTipoUsuario()
    {
        if(getTipoUsuario() == TipoUsuario.PACIENTE)
        {
            return 1;
        }
        else if (getTipoUsuario() == TipoUsuario.MEDICO)
        {
            return 2;
        }

        return 3;
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
