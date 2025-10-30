package Arquitetura.View.FuncoesADM;

import Arquitetura.Exception.*;
import Arquitetura.Model.Administrador;
import Arquitetura.Service.AdministradorService;
import Arquitetura.Service.MedicoService;
import Arquitetura.Service.PacienteService;
import Arquitetura.Service.UsuarioService;
import Arquitetura.Service.Validator.UsuarioValidator;
import Arquitetura.Utilidades.Ferramentas;

public class MenuExcluir
{

    private static UsuarioService usuarioService = new UsuarioService();
    private static AdministradorService administradorService = new AdministradorService();
    private static MedicoService medicoService = new MedicoService();
    private static PacienteService pacienteService = new PacienteService();
    private static UsuarioValidator usuarioValidator = new UsuarioValidator();

    public static void ExcluirUsuario(Administrador adm)
    {
        boolean verifica = false;
        String cpf = "1";
        Ferramentas.limpaTerminal();

        System.out.println("[EXCLUIR]");

        while(!verifica) {
            System.out.print("- Digite o CPF do usuário: " );
            try{
                cpf = Ferramentas.lString();
                UsuarioValidator.verificaIntegridadeCpf(cpf);
                usuarioValidator.verificarRegrasCpf(cpf);
                verifica = true;
            }catch(DadosInvalidosException e){
                Ferramentas.mensagemErro(e.getMessage());
            }
        }

        try {
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
        } catch(TipoUsuarioException | AutoDeleteException | CpfInvalidoException e ) {
            Ferramentas.mensagemErro(e.getMessage());
        } catch (UltimoAdminException e) {
            Ferramentas.mensagemErro("NÃO FOI POSSÍVEL DELETAR O ADM");
        }
    }
}
