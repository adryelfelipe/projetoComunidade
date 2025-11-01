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
import Arquitetura.View.MenuUsuarios.MenuSetUsuario;

public class MenuLogin {

    private static final UsuarioService usuarioService = new UsuarioService();
    static UsuarioValidator usuarioValidator = new UsuarioValidator();

    public static void Menu() {
        String cpf = "1";
        String senha;
        Usuario usuario;
        boolean verifica = false;

            Ferramentas.limpaTerminal();

            System.out.println("                ===============");
            System.out.println("                |    LOGIN    |");
            System.out.println("                ===============");


            while(!verifica) {
              System.out.print("Digite o CPF: ");
              cpf = Ferramentas.lString();

              try{
                  UsuarioValidator.verificaIntegridadeCpf(cpf);
                  usuarioValidator.verificarRegrasCpf(cpf);
                  verifica = true;
              } catch(DadosInvalidosException | CpfInvalidoException e) {
                 Ferramentas.mensagemErro(e.getMessage());
              }
            }

            verifica = false;
            senha = MenuSetUsuario.SetSenha();

            try{
                usuario = usuarioService.loginUsuario(cpf, senha);
            } catch(SenhaInvalidaException | CpfInvalidoException e) {
                Ferramentas.mensagemErro("ERRO! SENHA OU CPF INVÁLIDOS");
                return;
            }

            if(usuario instanceof Administrador) {
                Administrador adm = (Administrador) usuario;
                MenuAdministrador.Menu(adm);
            }
            else if(usuario instanceof Medico) {
                Medico medico = (Medico) usuario;
                MenuMedico.Menu(medico);
            }
            else {
            Paciente paciente = (Paciente) usuario;
            MenuPaciente.Menu(paciente);
            }
    }
}
