package Arquitetura.Model;

public class UsuarioModel {

    // Atributos
    private String nome;

    private String cpf;

    private String senha;

    private String tipoUsuario;

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

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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
