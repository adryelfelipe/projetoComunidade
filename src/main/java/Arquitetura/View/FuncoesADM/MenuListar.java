package Arquitetura.View.FuncoesADM;

import Arquitetura.Exception.UsuarioInvalidoException;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Medico;
import Arquitetura.Model.Paciente;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.UsuarioService;
import Arquitetura.Utilidades.Ferramentas;

import java.util.ArrayList;

public class MenuListar
{

    private static final UsuarioService usuarioService = new UsuarioService();

    public static void ListarUsuarios(Administrador adm) {
        // Inicialização de variáveis
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();

        // Tenta atribuir a lista de usuários em uma ArrayList
        try{
            listaUsuarios = usuarioService.findAllUsers(adm);
        } catch (UsuarioInvalidoException e) {
            Ferramentas.mensagemErro(e.getMessage());
        }

        // Menu de Usuários
        Ferramentas.limpaTerminal();

        System.out.println("          --------------");
        System.out.println("          |  USUÁRIOS  |");
        System.out.println("          --------------");

        for(Usuario usuario : listaUsuarios) {
            System.out.printf("ID: %d%nNome: %s%n", usuario.getId(), usuario.getNome());
            System.out.println(); // pula linha
        }

        System.out.println("----------------------------");
        System.out.println("Aperte enter para continuar");
        System.out.println("----------------------------");

        String tempo = Ferramentas.lString();
    }

    public static void ListarPaciente(Administrador adm)
    {

        Ferramentas.limpaTerminal();

        int cont = 1;
        System.out.println("          -------------");
        System.out.println("          |  Paciente |");
        System.out.println("          -------------");
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
