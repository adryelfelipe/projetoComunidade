package Arquitetura.View.MenuUsuarios;

import Arquitetura.Utilidades.Ferramentas;

public class MenuMedico
{


    public static void Menu()
    {

        boolean continuar = true;

        while (continuar) {

            Ferramentas.limpaTerminal();

            System.out.println("           ---------------");
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
                    System.out.println("Valor digitado está incorreto!");

                    Ferramentas.Delay(2000);
                }
            }
        }
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
