package Arquitetura.View;

import java.util.zip.DataFormatException;

import Arquitetura.Exception.CpfInvalidoException;
import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Exception.SenhaInvalidaException;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Medico;
import Arquitetura.Model.Paciente;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.AdministradorService;
import Arquitetura.Service.UsuarioService;
import Arquitetura.Service.Validator.UsuarioValidator;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.MenuUsuarios.MenuAdministrador;
import Arquitetura.View.MenuUsuarios.MenuMedico;
import Arquitetura.View.MenuUsuarios.MenuPaciente;

public class MenuLogin {

    private static final UsuarioService usuarioService = new UsuarioService();
    static UsuarioValidator usuarioValidator = new UsuarioValidator();

    public static void Menu() {

        boolean continuar = false;

        String cpf = "1";
        String senha = "1";
        Usuario usuario = null;
        boolean verifica = false;


            Ferramentas.limpaTerminal();

            System.out.println("                ===============");
            System.out.println("                |    LOGIN    |");
            System.out.println("                ===============");

            System.out.println("-------------------------");

            do{

                System.out.print("- Digite seu CPF: " );
                try{
                    cpf = Ferramentas.lString();
                    UsuarioValidator.verificaIntegridadeCpf(cpf);
                    usuarioValidator.verificarRegrasCpf(cpf);
                    verifica = true;
                }catch(DadosInvalidosException e){
                    Ferramentas.mensagemErro(e.getMessage());
                }

            }while(!verifica);

            System.out.println("-------------------------");

            System.out.println("\n-------------------------");
            System.out.print("- Digite sua senha: ");

            senha = Ferramentas.lString();

            System.out.println("-------------------------");

            try{
                usuario = usuarioService.loginUsuario(cpf, senha);
            } catch(SenhaInvalidaException | CpfInvalidoException e) {
                Ferramentas.limpaTerminal();
                System.err.print("ERRO! SENHA OU CPF INVÁLIDOS");
                Ferramentas.Delay(500);
                return;
            }

            if(usuario instanceof Administrador)
            {

                Administrador adm = (Administrador) usuario;

                MenuAdministrador.Menu(adm);

            }
            else if(usuario instanceof Medico)
            {

                Medico medico = (Medico) usuario;

               // MenuMedico.Menu(medico);
            }
            else
            {

                Paciente paciente = (Paciente) usuario;

                MenuPaciente.Menu(paciente);
            }

    }
}
