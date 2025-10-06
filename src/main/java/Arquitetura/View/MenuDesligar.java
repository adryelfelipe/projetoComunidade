package Arquitetura.View;

import Arquitetura.Utilidades.Ferramentas;

public class MenuDesligar {

    // -- MÉTODOS -- //
    public static void menuDesligar() {
        System.out.println("-------------------------\n");
        System.out.println("Desligando . . .");
        System.out.println("-------------------------\n");

        Ferramentas.Delay(1500);

        System.exit(0);
    }
}
