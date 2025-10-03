package Arquitetura.View.MenuUsuarios;

import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.MenuDefault;
import Arquitetura.View.MenuInicial;

public class MenuPaciente
{


    public static void Menu()
    {

        boolean continuar = true;

        while (continuar) {

            Ferramentas.limpaTerminal();

            System.out.println("           ---------------");
            System.out.println("           |     MENU    |" );
            System.out.println("           ---------------\n\n");
            System.out.println("\n\n1-Ficha técnica");
            System.out.println("2-Laudos de exames");
            System.out.println("3-Editar conta");
            System.out.println("4-Agedar consilta");
            System.out.println("5-Minha agenda");
            System.out.println("6-Sair");
            int op = Ferramentas.lInteiro();

            switch (op) {
                case 1: {

                    break;
                }
                case 2: {

                    break;
                }
                case 3: {

                    break;
                }
                case 4: {

                    break;
                }
                case 5:
                {

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
