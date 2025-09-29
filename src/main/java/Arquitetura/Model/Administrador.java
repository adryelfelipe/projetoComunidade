package Arquitetura.Model;

import java.sql.Date;

public class Administrador extends Usuario {

    // Atributos
    private double salario;
    private int cargaHoraria;

    // Construtor sem id
    public Administrador(String nome, String cpf, String senha, String sexo, String telefone, String email,Date dataNascimento, double salario, int cargaHoraria )
    {
        super(nome, cpf, senha, sexo, telefone, email, dataNascimento);
        this.salario = salario;
        this.cargaHoraria = cargaHoraria;
        setTipoUsuario("Administrador");
    }

    // Construtor com id
    public Administrador( String nome, String cpf, String senha, String sexo, String telefone,String email,Date dataNascimento,double salario, int cargaHoraria,long id) {
        this(nome, cpf, senha, sexo, telefone,email, dataNascimento, salario, cargaHoraria );
        this.setId(id);
    }

    // Setters e Getters
    public void setSalario(double salario) {
        if(salario > 0.0) {
            this.salario = salario;
        }
    }

    public double getSalario() {
        return salario;
    }

    public void setCargaHoraria(int cargaHoraria) {
        if(cargaHoraria > 0) {
            this.cargaHoraria = cargaHoraria;
        }
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    // Métodos

}
