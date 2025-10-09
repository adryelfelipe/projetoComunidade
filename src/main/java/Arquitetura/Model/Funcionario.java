package Arquitetura.Model;

import Arquitetura.Model.Enums.Genero;
import Arquitetura.Model.Enums.TipoUsuario;

import java.sql.Date;

public abstract class Funcionario extends Usuario{

    // -- Atributos -- //
    private double salario;
    private int cargaHorariaSemanal;

    // -- Construtor -- //
    public Funcionario(TipoUsuario tipoUsuario, String nome, String cpf, String senha, Genero sexo, String telefone, String email, Date dataNascimento, double salario, int cargaHorariaSemanal) {
        super(tipoUsuario, nome, cpf, senha, sexo, telefone, email, dataNascimento);
        setSalario(salario);
        setCargaHorariaSemanal(cargaHorariaSemanal);
    }

    // -- Setters e Getters -- //
    public void setCargaHorariaSemanal(int cargaHorariaSemanal) {
        if(cargaHorariaSemanal < 0) {
            throw new IllegalArgumentException("ERRO! A CARGA HORÁRIA SEMANAL NÃO PODE SER MENOR QUE 0");
        }

        this.cargaHorariaSemanal = cargaHorariaSemanal;
    }

    public int getCargaHorariaSemanal() {
        return cargaHorariaSemanal;
    }

    public void setSalario(double salario) {
        if(salario < 0) {
            throw new IllegalArgumentException("ERRO! O SALÁRIO NÃO PODE SER MENOR QUE 0");
        }

        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    @Override
    public void dadosPessoais() {
        super.dadosPessoais();
        System.out.println("SALÁRIO: " + salario);
        System.out.println("CARGA HORÁRIA: " + cargaHorariaSemanal);
    }
}
