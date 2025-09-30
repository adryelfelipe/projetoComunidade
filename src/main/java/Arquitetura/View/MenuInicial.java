package Arquitetura.View;

import Arquitetura.Utilidades.Ferramentas;

public class  MenuInicial {

    public static void Menu() {
        boolean continuar = true;

        while (continuar) {
            Ferramentas.limpaTerminal();

            System.out.println("               ==================");
            System.out.println("               |    CLINI WEG   |");
            System.out.println("               ==================");

            System.out.println("\n\n\n");
            System.out.println("============");
            System.out.println("| Digite:  |");
            System.out.println("| 1-Login  |");
            System.out.println("| 2-Sair   |");
            System.out.println("============");
            System.out.println("\n");
            int op = Ferramentas.lInteiro();

            if (op == 1) {

                Ferramentas.limpaTerminal();

                MenuLogin.Menu();

            } else if (op == 2) {
                System.out.println("\n\n\n");
                System.out.println("-------------------------\n");
                System.out.println("Desligando . . .");
                System.out.println("-------------------------\n");
                Ferramentas.Delay(1500);
                continuar = false;
            } else {
                System.out.println("\n\n\n");
                System.out.println("-------------------------\n");
                System.out.println("Valor digitado incorreto!");
                System.out.println("-------------------------\n");
                Ferramentas.Delay(1500);
            }
        }
    }
}
