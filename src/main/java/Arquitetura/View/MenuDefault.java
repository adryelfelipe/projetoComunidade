package Arquitetura.View;

import Arquitetura.Utilidades.Ferramentas;

public class MenuDefault {

    // -- MÉTODOS ESTÁTICOS -- //
    public static void menuDefault() {
        Ferramentas.limpaTerminal();
        System.err.println("-------------------------\n");
        System.err.println("Valor digitado incorreto!");
        System.err.println("-------------------------\n");
        Ferramentas.Delay(1500);
        Ferramentas.limpaTerminal();
    }
}
