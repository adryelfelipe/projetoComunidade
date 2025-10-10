package Arquitetura.View.MenuUsuarios;

import Arquitetura.Model.Administrador;
import Arquitetura.Service.AdministradorService;
import Arquitetura.Service.MedicoService;
import Arquitetura.Service.PacienteService;
import Arquitetura.Service.UsuarioService;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.*;
import Arquitetura.View.FuncoesADM.*;
import Arquitetura.View.FuncoesPACIENTE.MenuEditarConta;

public class MenuAdministrador
{

    static UsuarioService usuarioService = new UsuarioService();
    static AdministradorService administradorService = new AdministradorService();
    static PacienteService pacienteService = new PacienteService();
    static MedicoService medicoService = new MedicoService();

    public static void Menu(Administrador adm)
    {

        boolean continuar = true;

        while (continuar) {

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
            System.out.println("| 6-Cadastro adm     |");
            System.out.println("| 7-Cadastro Medico  |");
            System.out.println("| 8-Cadastro Paciente|");
            System.out.println("| 9-Sair             |");
            System.out.println("----------------------");

            int op = Ferramentas.lInteiro();

            switch (op) {
                case 1: {

                    MenuListar.ListarUsuarios(adm);

                    break;
                }
                case 2: {

                    //MenuEditarConta.Editar(adm);

                    break;
                }
                case 3: {

                    MenuExcluir.ExcluirUsuario(adm);

                    break;
                }
                case 4: {

                    MenuRelatorio.GerarRelatorios(adm);

                    break;
                }
                case 5:
                {

                    MenuDisponibilidade.Disponibilidade(adm);

                    break;
                }
                case 6:
                {

                    MenuCadastro.CriarADM(adm);

                    break;
                }
                case 7:
                {

                    MenuCadastro.CriarMedico(adm);

                    break;
                }
                case 8:
                {

                    MenuCadastro.CriarPaciente(adm);

                    break;
                }
                case 9: {

                    continuar = false;

                    break;
                }
                default: {

                    MenuDefault.menuDefault();

                    break;
                }
            }
        }

        MenuInicial.Menu();
    }
}
