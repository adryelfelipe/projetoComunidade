package Arquitetura.Model;

import java.sql.Date;

public class Administrador extends Funcionario {

    // -- Atributos -- //
    private String departamento;
    private static final String tipoUsuario = "Administrador";

    // -- Construtores -- //

    // Construtor sem id
    public Administrador(String nome, String cpf, String senha, String sexo, String telefone, String email,Date dataNascimento, double salario, int cargaHorariaSemanal, String departamento)
    {
        super(nome, cpf, senha, sexo, telefone, email, dataNascimento, salario, cargaHorariaSemanal);
    }

    // Construtor com id
    public Administrador(String nome, String cpf, String senha, String sexo, String telefone,String email,Date dataNascimento,double salario, int cargaHoraria,String departamento, long id) {
        this(nome, cpf, senha, sexo, telefone,email, dataNascimento, salario, cargaHoraria, departamento);
        this.setId(id);
    }

    // --  Setters e Getters -- //
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String getTipoUsuario() {
        return Administrador.tipoUsuario;
    }
}
