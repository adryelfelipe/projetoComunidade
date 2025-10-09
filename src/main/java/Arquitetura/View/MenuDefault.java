package Arquitetura.View;

import Arquitetura.Utilidades.Ferramentas;

public class MenuDefault {

    // -- MÉTODOS ESTÁTICOS -- //
    public static void menuDefault() {
        Ferramentas.limpaTerminal();
        System.out.println("-------------------------\n");
        System.out.println("Valor digitado incorreto!");
        System.out.println("-------------------------\n");
        Ferramentas.Delay(1500);
        Ferramentas.limpaTerminal();
    }
}
