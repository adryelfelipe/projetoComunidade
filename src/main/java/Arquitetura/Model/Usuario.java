package Arquitetura.Model;

public class Usuario {

    // Atributos
    private String nome;
    private String cpf;
    private String senha;
    private String sexo;
    private String telefone;
    private String email;
    private String dataNascimento;
    private String tipoUsuario;
    int id;

    // Construtor
    public Usuario(String nome, String cpf, String senha, String sexo, String telefone, String email, String dataNascimento) {
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    // Setters e Getters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
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
