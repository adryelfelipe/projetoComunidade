package Arquitetura.View;

import Arquitetura.Exception.CpfInvalidoException;
import Arquitetura.Exception.SenhaInvalidaException;
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

    private static final UsuarioService usuarioService = new UsuarioService();

    public static void Menu() {

        boolean continuar = true;

        String cpf = "1";
        String senha = "1";

        while (continuar) {


            Ferramentas.limpaTerminal();

            System.out.println("                ===============");
            System.out.println("                |    LOGIN    |");
            System.out.println("                ===============");

            System.out.println("-------------------------");
            System.out.print("- Digite seu CPF: " );
            cpf = Ferramentas.lString();
            System.out.println("-------------------------");


            System.out.println("-------------------------");
            System.out.print("- Digite sua senha: ");

            senha = Ferramentas.lString();

            System.out.println("-------------------------");

            try{
                Usuario usuario = usuarioService.loginUsuario(cpf, senha);
            } catch(SenhaInvalidaException | CpfInvalidoException e) {
                System.err.print("ERRO! SENHA OU CPF INVÁLIDOS");
            }

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
