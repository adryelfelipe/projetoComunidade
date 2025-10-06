package Arquitetura.View.MenuUsuarios;

import Arquitetura.Model.Medico;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.MenuDefault;
import Arquitetura.View.MenuInicial;

public class MenuMedico
{


    public static void Menu(Medico medico)
    {

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
            int op = Ferramentas.lInteiro();

            switch (op) {
                case 1: {

                    Ferramentas.limpaTerminal();

                    MinhaAgenda();

                    break;
                }
                case 2: {

                    Ferramentas.limpaTerminal();

                    Prontuario();

                    break;
                }
                case 3: {

                    Ferramentas.limpaTerminal();

                    Prescricoes();

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

    public static void MinhaAgenda()
    {

    }

    public static void Prontuario()
    {

    }

    public static void Prescricoes()
    {

    }




}
