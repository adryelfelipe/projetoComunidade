package Arquitetura.View;

import Arquitetura.Model.Administrador;
import Arquitetura.Utilidades.Ferramentas;

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

            }
            case 2:
            {

            }
            case 3:
            {

            }
            default:
            {

            }
        }
    }
}
