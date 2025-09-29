package Arquitetura.Model;

import java.sql.Date;

public class Medico extends Usuario {

    //Atributos
    private double salario;
    private int cargaHorariaSemanal;
    private String plantao;
    private String especialidade;
    private String subEspecialidade;
    private String formacao;

    //Construtores


    // Não possui subEspecialidade nem id
    public Medico(String nome, String cpf, String senha, String sexo, String telefone, String email,Date dataNascimento,int cargaHorariaSemanal,double salario,  String plantao, String especialidade, String formacao ) {
        super(nome,cpf,senha,sexo,telefone,email,dataNascimento);
        this.cargaHorariaSemanal = cargaHorariaSemanal;
        this.salario = salario;
        this.plantao = plantao;
        this.especialidade = especialidade;
        this.formacao = formacao;
        setTipoUsuario("Médico");
        this.subEspecialidade = "N/A";
    }

    // Não possui subEspecialidade e possui ID
    public Medico(long id, String nome, String cpf, String senha,String sexo,String telefone,  String email,  Date dataNascimento , int cargaHorariaSemanal,double salario, String plantao, String especialidade, String formacao) {
        this(nome, cpf, senha,sexo,telefone,email, dataNascimento, cargaHorariaSemanal,   salario,plantao, especialidade, formacao );
        this.subEspecialidade = "N/A";
        this.setId(id);
    }

    // Possui subEspecialidade e possui ID
    public Medico(long id, String nome, String cpf, String senha,String sexo,String telefone,  String email,  Date dataNascimento , int cargaHorariaSemanal,double salario, String plantao, String especialidade, String formacao,String subEspecialidade) {
        this(nome, cpf, senha,sexo,telefone,email, dataNascimento, cargaHorariaSemanal,   salario,plantao, especialidade, formacao );
        this.subEspecialidade = subEspecialidade;
        this.setId(id);
    }

    // Possui subEspecialidade e não possui ID
    public Medico( String nome, String cpf, String senha,String sexo,String telefone,  String email,  Date dataNascimento , int cargaHorariaSemanal,double salario, String plantao, String especialidade, String formacao, String subEspecialidade) {
        this(nome, cpf, senha,sexo,telefone,email, dataNascimento, cargaHorariaSemanal,   salario,plantao, especialidade, formacao );
        this.subEspecialidade = subEspecialidade;
    }


    //Getters & Setters
    public void setCargaHorariaSemanal(int cargaHorariaSemanal) {
        if(cargaHorariaSemanal > 0) {
            this.cargaHorariaSemanal = cargaHorariaSemanal;
        }
    }

    public int getCargaHorariaSemanal() {
        return cargaHorariaSemanal;
    }

    public void setEspecialidade(String especialidade) {
        if(!especialidade.isEmpty()) {
            this.especialidade = especialidade;
        }
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setFormacao(String formacao) {
        if(!formacao.isEmpty()) {
            this.formacao = formacao;
        }
    }

    public String getFormacao() {
        return formacao;
    }

    public void setPlantao(String plantao) {
        if(!plantao.isEmpty()) {
            this.plantao = plantao;
        }
    }

    public String getPlantao() {
        return plantao;
    }

    public void setSalario(double salario) {
        if(salario > 0.0) {
            this.salario = salario;
        }
    }

    public double getSalario() {
        return salario;
    }

    public void setSubEspecialidade(String subEspecialidade) {
        if(!subEspecialidade.isEmpty()) {
            this.subEspecialidade = subEspecialidade;
        }
    }

    public String getSubEspecialidade() {
        return subEspecialidade;
    }

    //Métodos

}
