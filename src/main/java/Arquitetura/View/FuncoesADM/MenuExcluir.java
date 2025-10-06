package Arquitetura.View.FuncoesADM;

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

        boolean excluir = false;

        Ferramentas.limpaTerminal();

        System.out.println("     EXCLUIR");
        System.out.println("\n\nDigite o cpf do usuario: ");
        String cpf = Ferramentas.lString();

        for(Usuario entrada: usuarioService.findAllUsers(adm))
        {
            if (entrada.getCpf().equals(cpf))
            {
                if(entrada instanceof Administrador)
                {
                    Administrador admdel = (Administrador) entrada;

                    excluir = administradorService.deletarAdministrador(adm,admdel);
                }
                else if(entrada instanceof Medico)
                {
                    Medico medicodel = (Medico) entrada;

                    excluir = medicoService.deletarMedico(adm,medicodel);
                }
                else if(entrada instanceof Paciente)
                {
                    Paciente pacientedel = (Paciente) entrada;

                    excluir = pacienteService.deletarPaciente(adm,pacientedel);
                }

                if(excluir == true) {
                    System.out.println("Usuario excluido");
                }
                else {
                    System.out.println("Erro ao excluir usuario");
                }
                System.out.println("\n\nDigite para continuar");
                String tempo = Ferramentas.lString();

                return;
            }
        }

        System.out.println("Usuario não encontrado!");

        System.out.println("\n\nDigite para continuar");
        String tempo = Ferramentas.lString();

    }

}
