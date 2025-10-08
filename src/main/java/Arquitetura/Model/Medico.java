package Arquitetura.Model;

import Arquitetura.Model.Enums.Especialidade;
import Arquitetura.Model.Enums.Genero;
import Arquitetura.Model.Enums.Plantao;
import Arquitetura.Model.Enums.TipoUsuario;

import java.sql.Date;

public class Medico extends Funcionario{

    // -- Atributos -- //
    private Plantao plantao;
    private Especialidade especialidade;
    private String subEspecialidade;
    private String formacao;

    // -- Construtores -- //

    // Não possui subEspecialidade nem id
    public Medico(String nome, String cpf, String senha, Genero sexo, String telefone, String email, Date dataNascimento, int cargaHorariaSemanal, double salario, Plantao plantao, Especialidade especialidade, String formacao ) {
        super(TipoUsuario.MEDICO, nome,cpf,senha,sexo,telefone,email,dataNascimento, salario, cargaHorariaSemanal);
        this.plantao = plantao;
        this.especialidade = especialidade;
        this.formacao = formacao;
        this.subEspecialidade = "N/A";
    }

    // Não possui subEspecialidade e possui ID
    public Medico(long id, String nome, String cpf, String senha,Genero sexo,String telefone,  String email,  Date dataNascimento , int cargaHorariaSemanal,double salario, Plantao plantao, Especialidade especialidade, String formacao) {
        this(nome, cpf, senha,sexo,telefone,email, dataNascimento, cargaHorariaSemanal,   salario,plantao, especialidade, formacao );
        this.subEspecialidade = "N/A";
        this.setId(id);
    }

    // Possui subEspecialidade e possui ID
    public Medico(long id, String nome, String cpf, String senha,Genero sexo,String telefone,  String email,  Date dataNascimento , int cargaHorariaSemanal,double salario, Plantao plantao, Especialidade especialidade, String formacao,String subEspecialidade) {
        this(nome, cpf, senha,sexo,telefone,email, dataNascimento, cargaHorariaSemanal,   salario,plantao, especialidade, formacao );
        this.subEspecialidade = subEspecialidade;
        this.setId(id);
    }

    // Possui subEspecialidade e não possui ID
    public Medico( String nome, String cpf, String senha,Genero sexo,String telefone,  String email,  Date dataNascimento , int cargaHorariaSemanal,double salario, Plantao plantao, Especialidade especialidade, String formacao, String subEspecialidade) {
        this(nome, cpf, senha,sexo,telefone,email, dataNascimento, cargaHorariaSemanal,   salario,plantao, especialidade, formacao);
        this.subEspecialidade = subEspecialidade;
    }


    // -- Getters & Setters -- //
    public void setEspecialidade(Especialidade especialidade) {
        if(especialidade != null) {
            this.especialidade = especialidade;
        }
    }

    public Especialidade getEspecialidade() {
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

    public void setPlantao(Plantao plantao) {
        if (plantao != null) {
            this.plantao = plantao;
        }
    }

    public Plantao getPlantao() {
        return plantao;
    }

    public void setSubEspecialidade(String subEspecialidade) {
        if(!subEspecialidade.isEmpty()) {
            this.subEspecialidade = subEspecialidade;
        }
    }

    public long getIdPlantao() {
        if(plantao == Plantao.MATUTINO) {

            return 1;
        } else if(plantao == Plantao.VERPERTINO) {

            return 2;
        } else {

            return 3;
        }
    }

    public String getSubEspecialidade() {
        return subEspecialidade;
    }

    @Override
    public void dadosPessoais() {
        super.dadosPessoais();
        System.out.println("PLANTÃO: " + plantao.name());
        System.out.println("ESPECIALIDADE: " + especialidade);

        if(!subEspecialidade.isEmpty() && !(subEspecialidade == null)) {
            System.out.println("SUBESPECIALIDADE: " + subEspecialidade);
        }

        System.out.println("FORMAÇÃO: " + formacao);
    }
}
