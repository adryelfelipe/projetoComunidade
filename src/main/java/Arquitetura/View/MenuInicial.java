package Arquitetura.View;

import Arquitetura.Utilidades.Ferramentas;

import javax.swing.*;

public class  MenuInicial {

    public static void Menu() {

        int op = 0;

        boolean continuar = true;

        while (continuar) {
            Ferramentas.limpaTerminal();

            System.out.println("               ==================");
            System.out.println("               |    CLINI WEG   |");
            System.out.println("               ==================");

            System.out.println("\n\n\n");
            System.out.println("=============");
            System.out.println("| Digite:   |");
            System.out.println("| 1-Login   |");
            System.out.println("| 2-Sair    |");
            System.out.println("=============");
            System.out.println("\n");

            try {

                op = Ferramentas.lInteiro();

            }catch (Exception e){

                MenuDefault.menuDefault();
            }

            System.out.println("\n\n\n"); // pula 4 linhas

            switch(op) {

                case 1 -> {
                    Ferramentas.limpaTerminal();
                    MenuLogin.Menu();
                }

                case 2 -> {
                    MenuDesligar.menuDesligar();
                    continuar = false;
                }

                default -> {
                    MenuDefault.menuDefault();
                }
            }
        }
    }
}
