package Arquitetura.View.FuncoesADM;

import Arquitetura.Model.Administrador;
import Arquitetura.Utilidades.Ferramentas;

public class MenuDisponibilidade
{

    public static void Disponibilidade(Administrador adm)
    {

        Ferramentas.limpaTerminal();

        System.out.println("     Agenda Médico");
        System.out.println("\n\nDigite o cpf do medico: ");
        String cpf = Ferramentas.lString();

        //for(Usuario entrada: USUARIOS)
        //{
        //    if (entrada.getCpf().equals(cpf))
        //    {
        //
        //
        //    }
        //}

        System.out.println("Médico não encontrado!");

        System.out.println("\n\nDigite para continuar");
        String tempo = Ferramentas.lString();

    }
}
