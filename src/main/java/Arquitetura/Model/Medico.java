package Arquitetura.Model;

import java.sql.Date;

public class Medico extends Funcionario{

    // -- Atributos -- //
    private String plantao;
    private String especialidade;
    private String subEspecialidade;
    private String formacao;

    // -- Construtores -- //

    // Não possui subEspecialidade nem id
    public Medico(String nome, String cpf, String senha, String sexo, String telefone, String email,Date dataNascimento,int cargaHorariaSemanal,double salario,  String plantao, String especialidade, String formacao ) {
        super(nome,cpf,senha,sexo,telefone,email,dataNascimento, salario, cargaHorariaSemanal);
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


    // -- Getters & Setters -- //
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

    public void setSubEspecialidade(String subEspecialidade) {
        if(!subEspecialidade.isEmpty()) {
            this.subEspecialidade = subEspecialidade;
        }
    }

    public String getSubEspecialidade() {
        return subEspecialidade;
    }
}
