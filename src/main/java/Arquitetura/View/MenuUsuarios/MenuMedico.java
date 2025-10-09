package Arquitetura.View.MenuUsuarios;

import Arquitetura.Model.Medico;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.FuncoesMedico.MenuMinhaAgenda;
import Arquitetura.View.FuncoesMedico.MenuPrescricoesLaudos;
import Arquitetura.View.FuncoesMedico.MenuProntuario;
import Arquitetura.View.MenuDefault;
import Arquitetura.View.MenuInicial;

public class MenuMedico
{


    public static void Menu(Medico medico)
    {

        int op = 0;
        boolean continuar = true;

        while (continuar) {

            Ferramentas.limpaTerminal();

            System.out.println("           ---------------                         Usuário: " + medico.getId());
            System.out.println("           |     MENU    |" );
            System.out.println("           ---------------\n\n");
            System.out.println("\n\n1-Minha Agenda");
            System.out.println("2-Prontuário Clínico");
            System.out.println("3-Prescrições e Laudos");
            System.out.println("4-Sair");

            try {
                op = Ferramentas.lInteiro();
            }catch (IllegalArgumentException e){
                MenuDefault.menuDefault();
            }
            switch (op) {
                case 1: {

                    MenuMinhaAgenda.MinhaAgenda(medico);

                    break;
                }
                case 2: {

                    MenuProntuario.Prontuario(medico);

                    break;
                }
                case 3: {

                    MenuPrescricoesLaudos.Prescricao(medico);

                    break;
                }
                case 4: {

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
