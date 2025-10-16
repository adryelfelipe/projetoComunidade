package Arquitetura.View.FuncoesADM;

import Arquitetura.Exception.*;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Medico;
import Arquitetura.Model.Paciente;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.AdministradorService;
import Arquitetura.Service.MedicoService;
import Arquitetura.Service.PacienteService;
import Arquitetura.Service.UsuarioService;
import Arquitetura.Utilidades.Ferramentas;

public class MenuExcluir
{

    private static UsuarioService usuarioService = new UsuarioService();
    private static AdministradorService administradorService = new AdministradorService();
    private static MedicoService medicoService = new MedicoService();
    private static PacienteService pacienteService = new PacienteService();

    public static void ExcluirUsuario(Administrador adm)
    {


        Ferramentas.limpaTerminal();

        System.out.println("     EXCLUIR");
        System.out.println("\n\nDigite o cpf do usuario: ");
        String cpf = Ferramentas.lString();


        try{



        } catch(TipoUsuarioException | AutoDeleteException | CpfInvalidoException e )
        {
            e.getMessage();
        }
        catch (UltimoAdminException e)
        {
            System.out.printf("Não foi possivel deletar adm");
        }


        System.out.println("Usuario não encontrado!");

        System.out.println("\n\nDigite para continuar");

        String tempo = Ferramentas.lString();
    }
}
