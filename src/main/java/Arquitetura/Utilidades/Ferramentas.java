package Arquitetura.Utilidades;

import Arquitetura.View.MenuDefault;

import java.rmi.server.ExportException;
import java.util.*;

public class Ferramentas {
    // Atributos Estáticos
    public static final List<String> listaMaiusculos = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));

    public static final List<String> listaEspeciais = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
            "!", "@", "#", "$", "%", "&", "*", "(", ")", "-", "_",
            "=", "+", "[", "]", "´", "`", "^", "~", ":", ";", "/",
            "?", "|", "{", "}", "<", ">", ",", ".", ":", "'", "\"",
            "\\", "$", "€", "£", "¥"));

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
        try {
            Thread.sleep(ms);
        } catch(InterruptedException e){

        }
    }

    // ------ FAZ INPUT DE DOUBLE E RETORNA ------ //
    public static double lDouble() {

        try {
            double num = ler.nextDouble();
            ler.nextLine();

            return num;
        }catch (Exception e) {

            ler.nextLine(); // Esvazia o buffer

            throw e;
        }
    }

    // ------ FAZ INPUT DE STRING E RETORNA ------ //
    public static String lString() {

        try {
            String t = ler.nextLine();

            return t;
        }catch (Exception e){
            ler.nextLine();

            throw e;
        }
    }

    // ------ FAZ INPUT DE INTEIRO E RETORNA ------ //
    public static int lInteiro() {
        try{
            int num = ler.nextInt();
            ler.nextLine();

            return num;
        } catch(Exception e) {
            ler.nextLine();

            throw e;
        }
    }

    // ------ PULA MUITAS LINHAS DO TERMINAL ------ //
    public static void limpaTerminal() {
        for(int i = 0; i < 35; i ++) {
            System.out.println();
        }
    }

    // ------- RECEBE MENSAGEM DE ERRO E EXIBE ------- //
    public static void mensagemErro(String mensagem) {
        Ferramentas.limpaTerminal();
        System.err.println(mensagem);
        Ferramentas.Delay(1700);
        Ferramentas.limpaTerminal();
    }

    public static void mensagemDefault() {
        Ferramentas.limpaTerminal();
        System.err.println("-------------------------\n");
        System.err.println("Valor digitado incorreto!");
        System.err.println("-------------------------\n");
        Ferramentas.Delay(1500);
        Ferramentas.limpaTerminal();
    }

    // ------- LÊ, VALIDA E RETORNA OPÇÃO DO SWITCH CASE ------- //
    public static int lerOpcao() {
        int opcao;

        while(true) {
            System.out.print("ESCOLHA: ");

            try {
                opcao = Ferramentas.lInteiro();
                return opcao;
            } catch (InputMismatchException e){
                MenuDefault.menuDefault();
            }
        }
    }
}
