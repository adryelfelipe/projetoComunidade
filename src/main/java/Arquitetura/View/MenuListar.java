package Arquitetura.View;

import Arquitetura.Model.Administrador;
import Arquitetura.Model.Medico;
import Arquitetura.Model.Paciente;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.UsuarioService;
import Arquitetura.Utilidades.Ferramentas;

public class MenuListar
{

    private static UsuarioService usuarioService = new UsuarioService();

    public static void ListarUsuarios(Administrador adm)
    {

        Ferramentas.limpaTerminal();

        Ferramentas.limpaTerminal();
        System.out.println("          --------------");
        System.out.println("          |  USUÁRIOS  |");
        System.out.println("          --------------");

        MenuListar.ListarPaciente(adm);

        MenuListar.ListarMedico(adm);

        System.out.println("\n\n\n---------------------");
        System.out.println("Digite para continuar");
        System.out.println("---------------------");

        String tempo = Ferramentas.lString();

    }

    public static void ListarPaciente(Administrador adm)
    {

        Ferramentas.limpaTerminal();

        System.out.println("\n\n  -Pacientes-\n\n");

        int cont = 1;

        System.out.println("\n\n  -Médicos-\n\n");

        for (Usuario entrada: usuarioService.findAllUsers(adm))
        {
            if(entrada instanceof Paciente)
            {
                System.out.println("\n\n   Paciente " + cont);
                System.out.println(entrada.getNome());
                System.out.println(entrada.getCpf());
                System.out.println(entrada.getEmail());
                System.out.println(entrada.getId());
                System.out.println(entrada.getDataNascimento());

                cont ++;
            }
        }
    }

    public static void ListarMedico(Administrador adm)
    {

        Ferramentas.limpaTerminal();

        int cont = 1;
        System.out.println("          -------------");
        System.out.println("          |  Médicos  |");
        System.out.println("          -------------");

        for (Usuario entrada: usuarioService.findAllUsers(adm))
        {
            if(entrada instanceof Medico)
            {
                System.out.println("\n\n   Medico " + cont);
                System.out.println(entrada.getNome());
                System.out.println(entrada.getCpf());
                System.out.println(entrada.getEmail());
                System.out.println(entrada.getId());
                System.out.println(entrada.getDataNascimento());
                cont ++;
            }
        }
    }
}
