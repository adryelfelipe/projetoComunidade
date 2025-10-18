package Arquitetura.Model;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Model.Enums.Genero;
import Arquitetura.Model.Enums.TipoUsuario;
import Arquitetura.Service.Validator.FuncionarioValidator;

import java.sql.Date;

public abstract class Funcionario extends Usuario{

    // -- Atributos -- //
    private double salario;
    private int cargaHorariaSemanal;
    FuncionarioValidator funcionarioValidator = new FuncionarioValidator();

    // -- Construtor -- //
    public Funcionario(TipoUsuario tipoUsuario, String nome, String cpf, String senha, Genero sexo, String telefone, String email, Date dataNascimento, double salario, int cargaHorariaSemanal) {
        super(tipoUsuario, nome, cpf, senha, sexo, telefone, email, dataNascimento);
        setSalario(salario);
        setCargaHorariaSemanal(cargaHorariaSemanal);
    }

    // -- Setters e Getters -- //
    public void setCargaHorariaSemanal(int cargaHorariaSemanal) {
        funcionarioValidator.verificaIntegridadeCargaHoraria(cargaHorariaSemanal);

        this.cargaHorariaSemanal = cargaHorariaSemanal;
    }

    public int getCargaHorariaSemanal() {
        return cargaHorariaSemanal;
    }

    public void setSalario(double salario) {
        funcionarioValidator.verificaIntegridadeSalario(salario);

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
