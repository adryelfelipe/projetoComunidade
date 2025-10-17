package Arquitetura.View.FuncoesMedico;

import Arquitetura.Model.Consulta;
import Arquitetura.Model.Enums.StatusConsulta;
import Arquitetura.Model.Medico;
import Arquitetura.Service.MedicoService;
import Arquitetura.Utilidades.Ferramentas;

import java.util.ArrayList;

public class MenuMinhaAgenda
{

        static MedicoService medicoService = new MedicoService();

        public static void MinhaAgenda(Medico medico)
        {

             Ferramentas.limpaTerminal();

            ArrayList<Consulta> consultas = medicoService.ConsultasMedico(medico,medico.getCpf());

            System.out.println("         ----------------------");
            System.out.println("         -- MINHAS CONSULTAS --");
            System.out.println("         ----------------------");

            System.out.println("\n\n\n");
            for (Consulta entrad: consultas)
            {
                if (entrad.getStatus().equals(StatusConsulta.AGENDADA)) {
                    System.out.println(entrad.getIdConsulta());
                    System.out.println(entrad.getDataConsulta());
                    System.out.println(entrad.getHorarioConsulta());
                    System.out.println(entrad.getExame());
                    System.out.println(entrad.getPaciente());
                }
            }

            System.out.println("\n\nDigite para continuar");
            String tempo = Ferramentas.lString();

        }
}
