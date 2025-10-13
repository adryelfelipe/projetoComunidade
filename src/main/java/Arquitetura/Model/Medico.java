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

    // Possui subEspecialidade e possui ID
    public Medico(long id, String nome, String cpf, String senha,Genero sexo,String telefone,  String email,  Date dataNascimento , int cargaHorariaSemanal,double salario, Plantao plantao, Especialidade especialidade, String formacao,String subEspecialidade) {
        super(TipoUsuario.MEDICO, nome, cpf, senha, sexo, telefone, email, dataNascimento, salario, cargaHorariaSemanal);
        this.plantao = plantao;
        this.especialidade = especialidade;
        setFormacao(formacao);
        setSubEspecialidade(subEspecialidade);
        this.setId(id);
    }

    // Não possui subEspecialidade nem id
    public Medico(String nome, String cpf, String senha, Genero sexo, String telefone, String email, Date dataNascimento, int cargaHorariaSemanal, double salario, Plantao plantao, Especialidade especialidade, String formacao ) {
        this(0, nome, cpf, senha ,sexo, telefone, email, dataNascimento, cargaHorariaSemanal, salario, plantao, especialidade, formacao, "N/A" );
    }

    // Não possui subEspecialidade e possui ID
    public Medico(long id, String nome, String cpf, String senha,Genero sexo,String telefone,  String email,  Date dataNascimento , int cargaHorariaSemanal,double salario, Plantao plantao, Especialidade especialidade, String formacao) {
        this(id, nome, cpf, senha,sexo,telefone,email, dataNascimento, cargaHorariaSemanal, salario,plantao, especialidade, formacao, "N/A" );
    }

    // Possui subEspecialidade e não possui ID
    public Medico( String nome, String cpf, String senha,Genero sexo,String telefone,  String email,  Date dataNascimento , int cargaHorariaSemanal,double salario, Plantao plantao, Especialidade especialidade, String formacao, String subEspecialidade) {
        this(0, nome, cpf, senha,sexo,telefone,email, dataNascimento, cargaHorariaSemanal, salario,plantao, especialidade, formacao, subEspecialidade);
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
        if(formacao.isEmpty()) {
            throw new IllegalArgumentException("ERRO! FORMAÇÃO NÃO PODE SER VAZIA");
        }

        this.formacao = formacao;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setPlantao(Plantao plantao) {

    }

    public Plantao getPlantao() {
        return plantao;
    }

    public void setSubEspecialidade(String subEspecialidade) {
        if(subEspecialidade.isEmpty()) {
            throw new IllegalArgumentException("ERRO! SUBESPECIALIDADE NÃO PODE SER VAZIA");
        }

        this.subEspecialidade = subEspecialidade;
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
