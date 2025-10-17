package Arquitetura.View.FuncoesMedico;

import Arquitetura.Dao.ConsultaDAO;
import Arquitetura.Dao.PacienteDAO;
import Arquitetura.Model.Consulta;
import Arquitetura.Model.Enums.StatusConsulta;
import Arquitetura.Model.Medico;
import Arquitetura.Service.MedicoService;
import Arquitetura.Service.PacienteService;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.MenuDefault;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class MenuMinhaAgenda
{

        static MedicoService medicoService = new MedicoService();
        static PacienteService pacienteService = new PacienteService();
        static PacienteDAO pacienteDAO = new PacienteDAO();
        static ConsultaDAO consultaDAO = new ConsultaDAO();

        public static void MinhaAgenda(Medico medico)
        {

            boolean continuar = true;

            do {

                Ferramentas.limpaTerminal();

                System.out.println("         --------------------");
                System.out.println("         -- MINHA  AGENDA --");
                System.out.println("         --------------------");

                System.out.println("\n\n\nDigite");
                System.out.println("1-Consultas de hoje");
                System.out.println("2-Buscar Disponibilidade");
                System.out.println("3-Sair");

                int op = 0;

                try{
                    op = Ferramentas.lInteiro();
                }catch (InputMismatchException e)
                {
                    System.err.print(e.getMessage());
                    Ferramentas.Delay(1500);
                }

                switch (op){
                    case 1:
                    {
                        ConsultaHoje(medico);
                        break;
                    }
                    case 2:
                    {
                        Disponibilidade(medico);
                        break;
                    }
                    case 3:
                    {
                        continuar = false;
                        break;
                    }
                    default:
                    {
                        Ferramentas.limpaTerminal();
                        MenuDefault.menuDefault();
                        break;
                    }
                }
            }while (!continuar);
        }

        public static void ConsultaHoje(Medico medico)
        {

            Ferramentas.limpaTerminal();

            ArrayList<Consulta> consultas = consultaDAO.findAllConsultasOfMedico(medico);

            LocalDate dataAtual = LocalDate.now();

            for(Consulta entrada: consultas)
            {
                if(entrada.getDataConsulta().equals(dataAtual)) {
                    if (entrada.getStatus().equals(StatusConsulta.AGENDADA)) {


                        System.out.println("\n\n\n");
                        System.out.println(entrada.getIdConsulta());
                        System.out.println(entrada.getPaciente());
                        System.out.println(entrada.getRelatorio());
                        System.out.println(entrada.getExame());
                    }
                }
            }


        }

        public static void Disponibilidade(Medico medico)
        {

            Ferramentas.limpaTerminal();

            int ano = 0;
            System.out.println("Digite o Ano: ");
            try {
                ano = Ferramentas.lInteiro();
            } catch (InputMismatchException e) {
                Ferramentas.limpaTerminal();
                System.err.print(e.getMessage());
                Ferramentas.Delay(1500);

            }

            int mes = 0;
            System.out.println("Digite o Mês: ");
            try {
                mes = Ferramentas.lInteiro();
            } catch (InputMismatchException e) {
                Ferramentas.limpaTerminal();
                System.err.print(e.getMessage());
                Ferramentas.Delay(1500);

            }


            int dia = 0;
            System.out.println("Digite Dia: ");
            try {
                dia = Ferramentas.lInteiro();
            } catch (InputMismatchException e) {
                Ferramentas.limpaTerminal();
                System.err.print(e.getMessage());
                Ferramentas.Delay(1500);

            }

            try {

                LocalDate dataNascimento = LocalDate.of(ano, mes, dia);

                java.sql.Date sqlDate = Date.valueOf(dataNascimento);

                ArrayList<Consulta> consultas = consultaDAO.findAllConsultasByData(sqlDate);

                int cont = 1;

                for (Consulta entrada : consultas) {

                    System.out.println("\n\n CONSULTA " + cont + "\n\n");
                    System.out.println(entrada.getIdConsulta());
                    System.out.println(entrada.getPaciente());
                    System.out.println(entrada.getMedico());
                    System.out.println(entrada.getDataConsulta());
                    System.out.println(entrada.getHorarioConsulta());
                    System.out.println(entrada.getExame());

                    cont++;
                }
            }catch (Exception e)
            {
                Ferramentas.limpaTerminal();
                System.err.println("Dados inválidos!");
                Ferramentas.Delay(1500);
            }
        }
}
