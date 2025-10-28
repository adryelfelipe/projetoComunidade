package Arquitetura.View.FuncoesPACIENTE;

import Arquitetura.Model.Paciente;
import Arquitetura.Service.PacienteService;
import Arquitetura.Service.UsuarioService;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.MenuDefault;
import Arquitetura.View.MenuUsuarios.MenuSetUsuario;

import java.util.InputMismatchException;

public class MenuUpdatePaciente {

    static UsuarioService usuarioService = new UsuarioService();
    static PacienteService pacienteService = new PacienteService();

    public static void EditarPaciente(Paciente paciente)
    {
        boolean continuar = true;
        boolean verifica = false;
        int op = 0;

        do {
            Ferramentas.limpaTerminal();

            while (!verifica) {
                System.out.println("       -----------------             ------------------------");
                System.out.println("       |EDITAR PACIENTE|             |Nome: " + paciente.getNome());
                System.out.println("       -----------------             |CPF: " + paciente.getCpf());
                System.out.println("                                     |EMAIL: " + paciente.getEmail());
                System.out.println("Digite para alterar:                 |Telefone: " + paciente.getTelefone());
                System.out.println("1-Email                              |Contato Emergência: " + paciente.getContatoEmergencia());
                System.out.println("2-Telefone                           |Senha: " + paciente.getSenha());
                System.out.println("3-Senha                              |Data nascimento: " + paciente.getDataNascimento());
                System.out.println("4-Contato Emergência                 |Numero carteirinha: " + paciente.getNumeroCadastro());
                System.out.println("5-Sair                               |Status: " + paciente.getStatusPaciente());
                System.out.println("                                     ------------------------");
                try {
                    op = Ferramentas.lInteiro();
                    verifica = true;
                } catch (InputMismatchException e){
                    MenuDefault.menuDefault();
                }
            }

            // REINICIA A VARIÁVEL
            verifica = false;

            switch (op) {
                case 1: {

                    String email = MenuSetUsuario.SetEmail();
                    usuarioService.updateEmailUsuario(paciente,paciente.getId(),email);

                    break;
                }
                case 2: {

                    String telefone = MenuSetUsuario.SetTelefone();
                    usuarioService.updateTelefoneUsuario(paciente, paciente.getId(),telefone);
                    break;
                }
                case 3: {

                    String senha = MenuSetUsuario.SetSenha();
                    usuarioService.updateSenhaUsuario(paciente, paciente.getId(),senha);
                    break;
                }
                case 4: {

                    String contato = MenuSetPaciente.SetContatoEmergencia();
                    pacienteService.updateContatoEmergencia(paciente, paciente.getId(), contato);
                    break;
                }
                case 5: {

                    continuar = false;

                    break;
                }
                default: {

                    MenuDefault.menuDefault();

                    break;
                }
            }
        }while (!continuar);
    }
}
