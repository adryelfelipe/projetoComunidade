package Arquitetura.View.FuncoesADM;

import Arquitetura.Dao.ConsultaDAO;
import Arquitetura.Dao.MedicoDAO;
import Arquitetura.Dao.PacienteDAO;
import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Model.*;
import Arquitetura.Service.PacienteService;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.MenuDefault;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class MenuRelatorio
{

    static ConsultaDAO consultaDAO = new ConsultaDAO();
    static MedicoDAO medicoDAO = new MedicoDAO();
    static PacienteDAO pacienteDAO = new PacienteDAO();
    static PacienteService pacienteService = new PacienteService();
    static UsuarioDAO usuarioDAO = new UsuarioDAO();

    public static void GerarRelatorios(Administrador adm)
    {
        boolean continuar = true;
        boolean verifica = false;
        int op = 0;

        do {
            Ferramentas.limpaTerminal();

            while(!verifica) {
                System.out.println("       Relatório");
                System.out.println("\n\nDigite: ");
                System.out.println("1-Médico");
                System.out.println("2-Paciente");
                System.out.println("3-Periodo");
                System.out.println("4-Sair");
                try {
                    op = Ferramentas.lInteiro();
                    verifica = true;
                } catch (InputMismatchException e){
                    MenuDefault.menuDefault();
                }
            }

            // REINICIA A VARIÁVEL
            verifica = false;

            /*
            switch (op) {
                case 1: {
                    System.out.println("Digite o cpf do médico");
                    String cpf = Ferramentas.lString();

                    Medico medico = medicoDAO.findByCpf(cpf);
                    ArrayList<Consulta> consultas = consultaDAO.findAllConsultasOfMedico(medico);

                    int cont = 1;

                    for(Consulta entrada: consultas)
                    {

                        System.out.println("\n\n CONSULTA " + cont + "\n\n");
                        System.out.println(entrada.getIdConsulta());
                        System.out.println(entrada.getPaciente());
                        System.out.println(entrada.getMedico());
                        System.out.println(entrada.getDataConsulta());
                        System.out.println(entrada.getHorarioConsulta());
                        System.out.println(entrada.getExame());

                        cont ++;
                    }


                    break;
                }
                case 2: {
                    System.out.println("Digite o cpf do Paciente");
                    String cpf = Ferramentas.lString();

                    Usuario paciente = usuarioDAO.findByCpf(cpf);
                    Paciente paciente1 = (Paciente) paciente;

                    ArrayList<Consulta> consultas = consultaDAO.findAllConsultasOfPaciente(paciente1);

                    int cont = 1;

                    for(Consulta entrada: consultas)
                    {

                        System.out.println("\n\n CONSULTA " + cont + "\n\n");
                        System.out.println(entrada.getIdConsulta());
                        System.out.println(entrada.getPaciente());
                        System.out.println(entrada.getMedico());
                        System.out.println(entrada.getDataConsulta());
                        System.out.println(entrada.getHorarioConsulta());
                        System.out.println(entrada.getExame());

                        cont ++;
                    }

                    break;
                }
                case 3: {

                    int ano = 1;
                    int mes = 1;
                    int dia = 1;

                    System.out.println("Digite o ano do período: ");

                    try {
                        ano = Ferramentas.lInteiro();
                    }catch (IllegalArgumentException e)
                    {
                        MenuDefault.menuDefault();
                    }
                    System.out.println("Digite o mês do periodo: ");

                    try {
                        mes = Ferramentas.lInteiro();
                    }catch (IllegalArgumentException e)
                    {
                        MenuDefault.menuDefault();
                    }

                    LocalDate dataNascimento = LocalDate.of(ano, mes, dia);

                    java.sql.Date sqlDate = Date.valueOf(dataNascimento);

                    ArrayList<Consulta> consultas = consultaDAO.findAllConsultasByData(sqlDate);

                    int cont = 1;

                    for(Consulta entrada: consultas)
                    {

                        System.out.println("\n\n CONSULTA " + cont + "\n\n");
                        System.out.println(entrada.getIdConsulta());
                        System.out.println(entrada.getPaciente());
                        System.out.println(entrada.getMedico());
                        System.out.println(entrada.getDataConsulta());
                        System.out.println(entrada.getHorarioConsulta());
                        System.out.println(entrada.getExame());

                        cont ++;
                    }

                    break;
                }
                case 4: {

                    continuar = false;

                    break;
                }
                default: {
                    MenuDefault.menuDefault();
                    break;
                }
            }

             */
        }while (continuar);
    }

}
