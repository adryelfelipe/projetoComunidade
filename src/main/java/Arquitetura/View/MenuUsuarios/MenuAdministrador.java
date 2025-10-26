package Arquitetura.View.MenuUsuarios;

import Arquitetura.Model.Administrador;
import Arquitetura.Service.AdministradorService;
import Arquitetura.Service.MedicoService;
import Arquitetura.Service.PacienteService;
import Arquitetura.Service.UsuarioService;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.*;
import Arquitetura.View.FuncoesADM.*;

import java.util.InputMismatchException;

public class MenuAdministrador
{

    static UsuarioService usuarioService = new UsuarioService();
    static AdministradorService administradorService = new AdministradorService();
    static PacienteService pacienteService = new PacienteService();
    static MedicoService medicoService = new MedicoService();

    public static void Menu(Administrador adm)
    {
        while (true) {

            Ferramentas.limpaTerminal();
            System.out.println("           ---------------                         Usuário: " + adm.getId());
            System.out.println("           |     MENU    |                                      " );
            System.out.println("           ---------------                                      \n\n");
            System.out.println("----------------------");
            System.out.println("|  Digite:           |");
            System.out.println("|--------------------|");
            System.out.println("| 1-Listar Usuários  |");
            System.out.println("| 2-Editar Usuários  |");
            System.out.println("| 3-Excluir Usuários |");
            System.out.println("| 4-Gerar Relatórios |");
            System.out.println("| 5-Disponibilidade  |");
            System.out.println("| 6-Cadastro ADM     |");
            System.out.println("| 7-Cadastro Medico  |");
            System.out.println("| 8-Cadastro Paciente|");
            System.out.println("| 9-Sair             |");
            System.out.println("----------------------");
            int op = Ferramentas.lerOpcao();

            switch (op) {
                case 1 -> MenuListar.ListarUsuarios(adm);
                case 2 -> MenuUpdateADM.menuUpdateInicial(adm);
                case 3 -> MenuExcluir.ExcluirUsuario(adm);
                case 4 -> MenuRelatorio.GerarRelatorios(adm);
                case 5 -> MenuDisponibilidade.Disponibilidade(adm);
                case 6 -> MenuCadastro.CriarADM(adm);
                case 7 -> MenuCadastro.CriarMedico(adm);
                case 8 -> MenuCadastro.CriarPaciente(adm);
                case 9 -> {
                    return;
                }
                default -> MenuDefault.menuDefault();
            }
        }
    }
}
