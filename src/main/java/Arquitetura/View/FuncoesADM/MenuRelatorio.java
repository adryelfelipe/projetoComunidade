package Arquitetura.View.FuncoesADM;

import Arquitetura.Model.Administrador;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.MenuDefault;

public class MenuRelatorio
{

    public static void GerarRelatorios(Administrador adm)
    {

        int escolha = 0;

        boolean continuar = true;

        do {
            Ferramentas.limpaTerminal();

            System.out.println("       Relatório");
            System.out.println("\n\nDigite: ");
            System.out.println("1-Médico");
            System.out.println("2-Paciente");
            System.out.println("3-Periodo");
            System.out.println("4-Sair");

            try {
                escolha = Ferramentas.lInteiro();
            } catch (IllegalArgumentException e) {
                MenuDefault.menuDefault();
            }
            switch (escolha) {
                case 1: {
                    System.out.println("Digite o cpf do médico");
                    String cpf = Ferramentas.lString();

                    //consultaService.GetByUser

                    break;
                }
                case 2: {
                    System.out.println("Digite o cpf do Paciente");
                    String cpf = Ferramentas.lString();

                    break;
                }
                case 3: {
                    System.out.println("Digite o ano do período: ");

                    try {
                        int ano = Ferramentas.lInteiro();
                    }catch (IllegalArgumentException e)
                    {
                        MenuDefault.menuDefault();
                    }
                    System.out.println("Digite o mês do periodo: ");

                    try {
                        int mes = Ferramentas.lInteiro();
                    }catch (IllegalArgumentException e)
                    {
                        MenuDefault.menuDefault();
                    }
                    //consultaService.GetByData

                    break;
                }
                case 4: {

                    continuar = false;

                    break;
                }
                default: {
                    MenuDefault.menuDefault();
                    break;
                }
            }
        }while (continuar);
    }
}
