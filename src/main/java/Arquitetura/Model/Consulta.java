package Arquitetura.Model;

import Arquitetura.Dao.MedicoDAO;
import Arquitetura.Dao.PacienteDAO;
import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Model.Enums.Exame;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
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
    private long idPaciente;
    private long idMedico;

    //Construtor

    //Não possui idConsulta
    public Consulta(Date dataConsulta, Time horarioConsulta, long idPaciente, long idMedico,Exame exame,String relatorio)
    {
        this.dataConsulta  = dataConsulta;
        this.horarioConsulta = horarioConsulta;
        this.exame = exame;
        this.relatorio = relatorio;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
    }

    //Possui idConsulta
    public Consulta(Date dataConsulta, Time horarioConsulta, long idPaciente, long idMedico, Exame exame, String relatorio, long idConsulta)
    {
        this(dataConsulta, horarioConsulta, idPaciente, idMedico, exame, relatorio);
        this.idConsulta = idConsulta;
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
