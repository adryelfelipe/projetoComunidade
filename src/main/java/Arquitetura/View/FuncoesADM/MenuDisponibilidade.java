package Arquitetura.View.FuncoesADM;

import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Consulta;
import Arquitetura.Model.Enums.StatusConsulta;
import Arquitetura.Model.Medico;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.MedicoService;
import Arquitetura.Utilidades.Ferramentas;

import java.util.ArrayList;

public class MenuDisponibilidade
{

    static UsuarioDAO usuarioDAO = new UsuarioDAO();
    static MedicoService medicoService = new MedicoService();

    public static void Disponibilidade(Administrador adm)
    {

        Ferramentas.limpaTerminal();

        System.out.println("     Agenda Médico");
        System.out.println("\n\nDigite o cpf do medico: ");
        String cpf = Ferramentas.lString();

        Usuario usuario = usuarioDAO.findByCpf(cpf);

        if(usuario instanceof Medico)
        {
            Medico medico = (Medico) usuario;

            Ferramentas.limpaTerminal();

            System.out.println("       CONSULTAS DO MÉDICO");

            ArrayList<Consulta> consultas = medicoService.ConsultasMedico(adm,cpf);

            for (Consulta entrad: consultas)
            {
                if (entrad.getStatus().equals(StatusConsulta.AGENDADA)) System.out.println(entrad.getIdConsulta());
                System.out.println(entrad.getDataConsulta());
                System.out.println(entrad.getHorarioConsulta());
                System.out.println(entrad.getExame());
                System.out.println(entrad.getPaciente());
            }
        }
        else
        {
            Ferramentas.limpaTerminal();
            System.out.println("Esse usuário não é um médico!");
        }

        System.out.println("Médico não encontrado!");

        System.out.println("\n\nDigite para continuar");
        String tempo = Ferramentas.lString();

    }
}