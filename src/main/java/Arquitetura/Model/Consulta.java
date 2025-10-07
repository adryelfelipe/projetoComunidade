package Arquitetura.Model;

import Arquitetura.Model.Enums.Exame;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Consulta {


    //Atributos

    private int idConsulta;
    private LocalDate dataConsulta;
    private LocalTime horarioConsulta;
    private String relatorio;
    private Paciente paciente;
    private Medico medico;
    private Exame exame;

    //Construtor

    //Possui relatorio
    public Consulta(int idConsulta, LocalDate dataConsulta, LocalTime horarioConsulta, Paciente paciente, Medico medico,Exame exame,String relatorio)
    {
        this.idConsulta = idConsulta;
        this.dataConsulta  = dataConsulta;
        this.horarioConsulta = horarioConsulta;
        this.paciente = paciente;
        this.medico = medico;
        this.exame = exame;
        this.relatorio = relatorio;
    }

    //Não possui relatorio
    public Consulta(int idConsulta, LocalDate dataConsulta, LocalTime horarioConsulta, Paciente paciente, Medico medico,Exame exame)
    {
        this.idConsulta = idConsulta;
        this.dataConsulta  = dataConsulta;
        this.horarioConsulta = horarioConsulta;
        this.paciente = paciente;
        this.medico = medico;
        this.exame = exame;
        this.relatorio = null;
    }

    //getters e setters

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public LocalTime getHorarioConsulta() {
        return horarioConsulta;
    }

    public void setHorarioConsulta(LocalTime horarioConsulta) {
        this.horarioConsulta = horarioConsulta;
    }

    public String getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(String relatorio) {
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
}
