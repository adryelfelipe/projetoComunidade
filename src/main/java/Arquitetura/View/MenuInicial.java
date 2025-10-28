package Arquitetura.View;

import Arquitetura.Utilidades.Ferramentas;

import javax.swing.*;
import java.util.InputMismatchException;

public class  MenuInicial {

    public static void Menu() {
        int op = 0;
        boolean checkOpcao = false;
        boolean continuar = true;

        while (continuar) {

            Ferramentas.limpaTerminal();

            System.out.println("               ==================");
            System.out.println("               |    CLINI WEG   |");
            System.out.println("               ==================");

            while(!checkOpcao) {
                System.out.println("\n\n\n");
                System.out.println("=============");
                System.out.println("| Digite:   |");
                System.out.println("| 1-Login   |");
                System.out.println("| 2-Sair    |");
                System.out.println("=============");
                System.out.println();
                System.out.print("ESCOLHA: ");

                try {
                    op = Ferramentas.lInteiro();
                    checkOpcao = true;
                } catch (InputMismatchException e){
                    MenuDefault.menuDefault();
                }
            }

            // Reinicia a variável
            checkOpcao = false;

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

                default -> MenuDefault.menuDefault();
            }
        }
    }
}
