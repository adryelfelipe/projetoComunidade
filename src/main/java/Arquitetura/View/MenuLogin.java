package Arquitetura.View;

import Arquitetura.Utilidades.Ferramentas;

public class MenuLogin {

    public static void Menu() {

        boolean continuar = true;

        while (continuar) {


            Ferramentas.limpaTerminal();

            System.out.println("                ===============");
            System.out.println("                |    LOGIN    |");
            System.out.println("                ===============");

            System.out.println("-------------------------");
            System.out.print("- Digite seu CPF: " );
            String cpf = Ferramentas.lString();
            System.out.println("-------------------------");



            System.out.println("-------------------------");
            System.out.println("- Digite sua senha: ");
            String senha = Ferramentas.lString();
            System.out.println("-------------------------");



        }
    }

}
