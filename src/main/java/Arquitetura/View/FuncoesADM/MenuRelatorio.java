package Arquitetura.View.FuncoesADM;

import Arquitetura.Model.Administrador;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.MenuDefault;

public class MenuRelatorio
{

    public static void GerarRelatorios(Administrador adm)
    {

        Ferramentas.limpaTerminal();

        System.out.println("       Relatório");
        System.out.println("\n\nDigite: ");
        System.out.println("1-Médico");
        System.out.println("2-Paciente");
        System.out.println("3-Periodo");
        int escolha = Ferramentas.lInteiro();

        switch (escolha)
        {
            case 1:
            {
                System.out.println("Digite o cpf do médico");
                String cpf = Ferramentas.lString();

                //consultaService.GetByUser

            }
            case 2:
            {
                System.out.println("Digite o cpf do Paciente");
                String cpf = Ferramentas.lString();

            }
            case 3:
            {
                System.out.println("Digite o ano do período: ");
                int ano = Ferramentas.lInteiro();

                System.out.println("Digite o mês do periodo: ");
                int mes = Ferramentas.lInteiro();

                //consultaService.GetByData

            }
            default:
            {
                MenuDefault.menuDefault();
            }
        }
    }
}
