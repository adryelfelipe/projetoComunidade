package Arquitetura.View;

import Arquitetura.Model.Administrador;
import Arquitetura.Model.Medico;
import Arquitetura.Model.Paciente;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.AdministradorService;
import Arquitetura.Service.UsuarioService;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.MenuUsuarios.MenuAdministrador;
import Arquitetura.View.MenuUsuarios.MenuMedico;
import Arquitetura.View.MenuUsuarios.MenuPaciente;

public class MenuLogin {

    private static UsuarioService usuarioService = new UsuarioService();

    public static void Menu() {

        boolean continuar = true;

        while (continuar) {


            Ferramentas.limpaTerminal();

            System.out.println("                ===============");
            System.out.println("                |    LOGIN    |");
            System.out.println("                ===============");

            System.out.println("-------------------------");
            System.out.print("- Digite seu CPF: " );
            String cpf = Ferramentas.lString();
            System.out.println("-------------------------");

            boolean cpfexiste = usuarioService.isCpfExistente(cpf);

            if(!cpfexiste) {

                System.out.println("\n\nCPF incorreto/invalido");

                Ferramentas.Delay(1500);

                continuar = false;

            }
            else {

                System.out.println("-------------------------");
                System.out.print("- Digite sua senha: ");
                String senha = Ferramentas.lString();
                System.out.println("-------------------------");

                Usuario usuario = usuarioService.loginUsuario(cpf, senha);

                if (usuario == null) {

                    System.out.println("Senha incorreta!");

                    Ferramentas.Delay(1500);

                    continuar = false;

                }
                else {

                    if(usuario instanceof Administrador)
                    {

                        Administrador adm = (Administrador) usuario;

                        MenuAdministrador.Menu(adm);

                    }
                    else if(usuario instanceof Medico)
                    {

                        Medico medico = (Medico) usuario;

                        MenuMedico.Menu(medico);
                    }
                    else
                    {

                        Paciente paciente = (Paciente) usuario;

                        MenuPaciente.Menu(paciente);
                    }
                }
            }
        }
    }
}
