package Arquitetura.Model;

public class Medico extends Usuario {

    //Atributos
    private double salario;
    private int cargaHorariaSemanal;
    private String plantao;
    private String especialidade;
    private String subEspecialidade;
    private String formacao;

    //Construtores

    // Não possui subEspecialidade
    public Medico(String nome, String cpf, String senha, double salario, int cargaHorariaSemanal, String plantao, String especialidade, String formacao, String sexo, String telefone, String email, String dataNascimento) {
        super(nome,cpf,senha, sexo, telefone, email, dataNascimento);
        this.cargaHorariaSemanal = cargaHorariaSemanal;
        this.salario = salario;
        this.plantao = plantao;
        this.especialidade = especialidade;
        this.formacao = formacao;
        setTipoUsuario("Médico");
        this.subEspecialidade = "N/A";
    }

    // Possui subEspecialidade
    public Medico(String nome, String cpf, String senha, double salario, int cargaHorariaSemanal, String plantao, String especialidade, String formacao, String subEspecialidade, String sexo, String telefone, String email, String dataNascimento) {
        this(nome, cpf, senha, salario, cargaHorariaSemanal, plantao, especialidade, formacao, sexo, telefone, email, dataNascimento);
        this.subEspecialidade = subEspecialidade;
    }

    //Getters & Setters
    public void setCargaHorariaSemanal(int cargaHorariaSemanal) {
        this.cargaHorariaSemanal = cargaHorariaSemanal;
    }

    public int getCargaHorariaSemanal() {
        return cargaHorariaSemanal;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setPlantao(String plantao) {
        this.plantao = plantao;
    }

    public String getPlantao() {
        return plantao;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSubEspecialidade(String subEspecialidade) {
        this.subEspecialidade = subEspecialidade;
    }

    public String getSubEspecialidade() {
        return subEspecialidade;
    }

    //Métodos

}
