package Arquitetura.View.MenuUsuarios;

import Arquitetura.Model.Paciente;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.FuncoesPACIENTE.*;
import Arquitetura.View.MenuDefault;
import Arquitetura.View.MenuInicial;

import java.util.InputMismatchException;

public class MenuPaciente
{


    public static void Menu(Paciente paciente)
    {
        boolean continuar = true;
        boolean checkOp = false;
        int op = 0;

        while (continuar) {
            Ferramentas.limpaTerminal();

            while(!checkOp) {
                System.out.println("           ---------------                         Usuário: " + paciente.getId());
                System.out.println("           |     MENU    |" );
                System.out.println("           ---------------\n\n");
                System.out.println("\n\n1-Ficha técnica");
                System.out.println("2-Laudos de exames");
                System.out.println("3-Editar conta");
                System.out.println("4-Agedar consilta");
                System.out.println("5-Minha agenda");
                System.out.println("6-Sair");
                try {
                    op = Ferramentas.lInteiro();
                    checkOp = true;
                } catch (InputMismatchException e){
                    MenuDefault.menuDefault();
                }
            }

            // RESETA A VERIFICAÇÃO
            checkOp = false;

            switch (op) {
                case 1: {

                    MenuFicha.Ficha(paciente);

                    break;
                }
                case 2: {

                    MenuLaudo.Laudo(paciente);

                    break;
                }
                case 3: {

                    MenuUpdatePaciente.EditarPaciente(paciente);

                    break;
                }
                case 4: {

                    MenuAgendar.Agendar(paciente);

                    break;
                }
                case 5:
                {

                    MenuAgenda.Agenda(paciente);

                    break;
                }
                case 6: {
                    continuar = false;
                    break;
                }
                default: {

                    MenuDefault.menuDefault();
                }
            }
        }
        MenuInicial.Menu();
    }
}
