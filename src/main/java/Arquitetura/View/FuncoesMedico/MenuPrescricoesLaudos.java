package Arquitetura.View.FuncoesMedico;

import Arquitetura.Exception.AutoDeleteException;
import Arquitetura.Exception.CpfInvalidoException;
import Arquitetura.Exception.TipoUsuarioException;
import Arquitetura.Exception.UltimoAdminException;
import Arquitetura.Model.Medico;
import Arquitetura.Service.UsuarioService;
import Arquitetura.Utilidades.Ferramentas;

import java.util.InputMismatchException;

public class MenuPrescricoesLaudos {

    static UsuarioService usuarioService = new UsuarioService();

    public static void Prescricao(Medico medico) {

        Ferramentas.limpaTerminal();

        System.out.println("         -----------------");
        System.out.println("         --  Relatório  --");
        System.out.println("         -----------------");

        System.out.println("\n\n\nDigite o cpf do usuario: ");

        try {

            String cpf = Ferramentas.lString();

            int i = usuarioService.cpfParaTipoUsuario(cpf);

            if (i == 1) {

                Ferramentas.limpaTerminal();

                System.out.println();

            }
            else{

                System.err.println("\n\n\nErro. Este usuário não é um paciente! ");
                Ferramentas.Delay(1500);
            }
        } catch(InputMismatchException e) {

            System.err.println(e.getMessage());
            Ferramentas.Delay(1500);
        }
    }
}