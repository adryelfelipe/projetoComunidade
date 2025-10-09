package Arquitetura.Utilidades;

import java.util.Scanner;

public class Ferramentas {

    // Atributos Estáticos

    // Cores padrão
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    // Cores brilhantes
    public static final String BRIGHT_BLACK = "\u001B[90m";
    public static final String BRIGHT_RED = "\u001B[91m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_BLUE = "\u001B[94m";
    public static final String BRIGHT_PURPLE = "\u001B[95m";
    public static final String BRIGHT_CYAN = "\u001B[96m";
    public static final String BRIGHT_WHITE = "\u001B[97m";

    // Fundos (backgrounds)
    public static final String BG_BLACK = "\u001B[40m";
    public static final String BG_RED = "\u001B[41m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_PURPLE = "\u001B[45m";
    public static final String BG_CYAN = "\u001B[46m";
    public static final String BG_WHITE = "\u001B[47m";

    // Fundos brilhantes
    public static final String BG_BRIGHT_BLACK = "\u001B[100m";
    public static final String BG_BRIGHT_RED = "\u001B[101m";
    public static final String BG_BRIGHT_GREEN = "\u001B[102m";
    public static final String BG_BRIGHT_YELLOW = "\u001B[103m";
    public static final String BG_BRIGHT_BLUE = "\u001B[104m";
    public static final String BG_BRIGHT_PURPLE = "\u001B[105m";
    public static final String BG_BRIGHT_CYAN = "\u001B[106m";
    public static final String BG_BRIGHT_WHITE = "\u001B[107m";

    // Estilos
    public static final String BOLD = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";
    public static final String REVERSED = "\u001B[7m";

    private static Scanner ler = new Scanner(System.in);

    // Construtor privado
    private Ferramentas() {

    }

    // Métodos

    // ------ APLICA DELAY EM MILISEGUNDOS ------ //
    public static void Delay(int ms) {
        try{Thread.sleep(ms);}catch(InterruptedException e){}
    }

    // ------ FAZ INPUT DE DOUBLE E RETORNA ------ //
    public static double lDouble() {
        double num = ler.nextDouble();

        ler.nextLine(); // Esvazia o buffer

        return num;
    }

    // ------ FAZ INPUT DE STRING E RETORNA ------ //
    public static String lString() {
        return ler.nextLine();
    }

    // ------ FAZ INPUT DE INTEIRO E RETORNA ------ //
    public static int lInteiro() {
        int num = ler.nextInt();

        ler.nextLine(); // Esvazia o buffer

        return num;
    }

    // ------ PULA MUITAS LINHAS DO TERMINAL ------ //
    public static void limpaTerminal() {
        for(int i = 0; i < 50; i ++) {
            System.out.println();
        }
    }
}
