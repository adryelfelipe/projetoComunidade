package Arquitetura.View.MenuUsuarios;

import Arquitetura.Model.Medico;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.FuncoesMedico.MenuMinhaAgenda;
import Arquitetura.View.FuncoesMedico.MenuPrescricoesLaudos;
import Arquitetura.View.FuncoesMedico.MenuProntuario;
import Arquitetura.View.FuncoesMedico.MenuUpdateMedico;
import Arquitetura.View.MenuDefault;
import Arquitetura.View.MenuInicial;

import java.util.InputMismatchException;

public class MenuMedico
{


    public static void Menu(Medico medico)
    {
        boolean verifica = false;
        int op = 0;

        while (true) {
            Ferramentas.limpaTerminal();

            while(!verifica) {
                System.out.println("           ---------------                         Usuário: " + medico.getId());
                System.out.println("           |     MENU    |" );
                System.out.println("           ---------------\n\n");
                System.out.println("1 - Minha Agenda");
                System.out.println("2 - Prontuário Clínico");
                System.out.println("3 - Editar conta");
                System.out.println("4 - Prescrições");
                System.out.println("5 - Sair");
                try {
                    op = Ferramentas.lInteiro();
                    verifica = true;
                } catch (InputMismatchException e){
                    MenuDefault.menuDefault();
                }
            }

            // RESETA A VERIFICAÇÃO
            verifica = false;

            switch (op) {
                case 1 ->  MenuMinhaAgenda.MinhaAgenda(medico);
                case 2 -> MenuProntuario.Prontuario(medico);
                case 3 -> MenuUpdateMedico.EditarMedico(medico);
                case 4 -> MenuPrescricoesLaudos.Prescricao(medico);

                case 5 -> {
                    return;
                }

                default -> MenuDefault.menuDefault();
            }
        }
    }
}
