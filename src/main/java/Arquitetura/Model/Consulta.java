package Arquitetura.Model;

import Arquitetura.Model.Enums.Exame;
import Arquitetura.Model.Enums.StatusConsulta;

import java.sql.Time;
import java.util.Date;

public class Consulta {


    //Atributos

    private long idConsulta;
    private Date dataConsulta;
    private Time horarioConsulta;
    private String relatorio;
    private Paciente paciente;
    private Medico medico;
    private Exame exame;
    private StatusConsulta status;
    private long idPaciente;
    private long idMedico;

    //Construtor

    //Não possui idConsulta
    public Consulta(Date dataConsulta, Time horarioConsulta, long idPaciente, long idMedico,Exame exame,String relatorio)
    {
        this.dataConsulta  = dataConsulta;
        this.horarioConsulta = horarioConsulta;
        this.exame = exame;
        setRelatorio(relatorio);
        setIdMedico(idMedico);
        setIdPaciente(idPaciente);
        this.status = StatusConsulta.AGENDADA;
    }

    //Possui idConsulta
    public Consulta(Date dataConsulta, Time horarioConsulta, long idPaciente, long idMedico, Exame exame, String relatorio, StatusConsulta status, long idConsulta)
    {
        this(dataConsulta, horarioConsulta, idPaciente, idMedico, exame, relatorio);
        this.idConsulta = idConsulta;
        this.status = status;
    }


    //getters e setters

    public long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(long idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Time getHorarioConsulta() {
        return horarioConsulta;
    }

    public void setHorarioConsulta(Time horarioConsulta) {
        this.horarioConsulta = horarioConsulta;
    }

    public String getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(String relatorio) {
        if(relatorio.isEmpty()) {
            throw new IllegalArgumentException("ERRO! O RELATÓRIO NÃO PODE SER VAZIO");
        }

        this.relatorio = relatorio;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    public StatusConsulta getStatus()
    {
        return status;
    }

    public void setIdPaciente(long idPaciente) {
        if(idPaciente < 0) {
            throw new IllegalArgumentException("ERRO! O ID DO PACIENTE NÃO PODE SER MENOR QUE 0");
        }

        this.idPaciente = idPaciente;
    }

    public void setIdMedico(long idMedico) {
        if(idMedico < 0) {
            throw new IllegalArgumentException("ERRO! O ID DE MÉDICO NÃO PODE SER MENOR QUE 0");
        }

        this.idMedico = idMedico;
    }

    public void setStatus(StatusConsulta status)
    {
        this.status = status;
    }
}
