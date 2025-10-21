package Arquitetura.View.FuncoesADM;

import Arquitetura.Dao.UsuarioDAO;
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
    private static UsuarioDAO usuarioDAO = new UsuarioDAO();

    public static void ExcluirUsuario(Administrador adm)
    {

        Ferramentas.limpaTerminal();

        System.out.println("     EXCLUIR");
        System.out.println("\n\nDigite o cpf do usuario: ");
        System.out.println(); // pula linha

        try{

            String cpf = Ferramentas.lString();

            int i = usuarioService.cpfParaTipoUsuario(cpf);

            if(i == 1) {
                pacienteService.deletarPaciente(adm,cpf);

                System.out.println("Paciente deletado");

                Ferramentas.Delay(1500);
            }
            else if(i == 2) {
                medicoService.deletarMedico(adm,cpf);

                System.out.println("Médico deletado");

                Ferramentas.Delay(1500);
            }else {
                administradorService.deletarAdministrador(adm,cpf);

                System.out.println("Administrador deletado");

                Ferramentas.Delay(1500);
            }
        } catch(TipoUsuarioException | AutoDeleteException | CpfInvalidoException e )
        {
            Ferramentas.mensagemErro(e.getMessage());
        }
        catch (UltimoAdminException e)
        {
            Ferramentas.mensagemErro("NÃO FOI POSSÍVEL DELETAR O ADM");
        }
    }
}
