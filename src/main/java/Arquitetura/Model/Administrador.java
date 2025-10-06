package Arquitetura.Model;

import Arquitetura.Model.Enums.Departamento;
import Arquitetura.Model.Enums.Genero;
import Arquitetura.Model.Enums.TipoUsuario;

import java.sql.Date;

public class Administrador extends Funcionario {

    // -- Atributos -- //
    private Departamento departamento;

    // -- Construtores -- //

    // Construtor sem id
    public Administrador(String nome, String cpf, String senha, Genero sexo, String telefone, String email, Date dataNascimento, double salario, int cargaHorariaSemanal, Departamento departamento)
    {
        super(TipoUsuario.ADMIN, nome, cpf, senha, sexo, telefone, email, dataNascimento, salario, cargaHorariaSemanal);
        this.departamento = departamento;
    }

    // Construtor com id
    public Administrador(String nome, String cpf, String senha, Genero sexo, String telefone,String email,Date dataNascimento,double salario, int cargaHoraria,Departamento departamento, long id) {
        this(nome, cpf, senha, sexo, telefone,email, dataNascimento, salario, cargaHoraria, departamento);
        this.setId(id);
    }

    // --  Setters e Getters -- //
    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        if(departamento != null) {
            this.departamento = departamento;
        }
    }

    public long getIdDepartamento() {

        if(departamento == Departamento.FINANCEIRO) {

            return 1;
        } else if(departamento == Departamento.INFRAESTRUTURA) {

            return 2;
        } else if(departamento == Departamento.MARKETING) {

            return 3;
        } else {

            return 4;
        }
    }

    @Override
    public void dadosPessoais() {
        super.dadosPessoais();
        System.out.println("DEPARTAMENTO: " + departamento.name());
    }
}
