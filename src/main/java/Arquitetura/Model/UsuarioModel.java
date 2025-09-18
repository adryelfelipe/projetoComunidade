package Arquitetura.Model;

public class UsuarioModel {

    // Atributos
    String nome;

    String cpf;

    String senha;

    String tipoUsuario;

    int id;

    // Construtor
    public UsuarioModel(String nome, String cpf, String senha) {
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
    }

    // Setters e Getters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // Métodos
}
