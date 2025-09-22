package Arquitetura.Model;

public class Administrador extends Usuario {

    // Atributos
    private double salario;
    private int cargaHoraria;

    // Construtor
    public Administrador(String nome, String cpf, String senha, double salario, int cargaHoraria, String sexo, String telefone, String email, String dataNascimento)
    {
        super(nome, cpf, senha, sexo, telefone, email, dataNascimento);
        this.salario = salario;
        this.cargaHoraria = cargaHoraria;
        setTipoUsuario("Administrador");
    }

    // Setters e Getters
    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    // Métodos

}
