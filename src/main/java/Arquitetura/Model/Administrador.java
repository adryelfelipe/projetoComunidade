package Arquitetura.Model;

import java.sql.Date;

public class Administrador extends Usuario {

    // Atributos
    private double salario;
    private int cargaHoraria;

    // Construtor sem id
    public Administrador(String nome, String cpf, String senha, double salario, int cargaHoraria, String sexo, String telefone, String email, Date dataNascimento)
    {
        super(nome, cpf, senha, sexo, telefone, email, dataNascimento);
        this.salario = salario;
        this.cargaHoraria = cargaHoraria;
        setTipoUsuario("Administrador");
    }

    // Construtor com id
    public Administrador(int id, String nome, String cpf, String senha, double salario, int cargaHoraria, String sexo, String telefone, String email, Date dataNascimento) {
        this(nome, cpf, senha, salario, cargaHoraria, sexo, telefone, email, dataNascimento);
        this.setId(id);
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
