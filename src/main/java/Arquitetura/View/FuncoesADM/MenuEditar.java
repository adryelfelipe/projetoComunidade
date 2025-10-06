package Arquitetura.View.FuncoesADM;

import Arquitetura.Model.Administrador;
import Arquitetura.Utilidades.Ferramentas;

public class MenuEditar
{

    public static void Editar(Administrador adm)
    {

        Ferramentas.limpaTerminal();

        System.out.println("        EDITAR ");
        System.out.println("\n\nDigite o CPF: ");
        String cpf = Ferramentas.lString();

        //for(Usuario entrada: USUARIOS)
        //{
        //    if(entrada.getCpf().equals(cpf))
        //    {
        //        if(entrada instanceof Medico)
        //        {
        //            entrada = (Medico) entrada;
        //
        //        }
        //        else if(entrada instanceof Paciente)
        //        {
        //            entrada = (Paciente) entrada;
        //
        //
        //        }
        //        else if(entrada instanceof Administrador)
        //        {
        //            System.out.println("\n\nEste usuário é um adm, não pode ser alterado!");
//
        //            System.out.println("Digite para continuar");
        //            String tempo = Ferramentas.lString();
        //
        //
        //        }
        //
        //    }
        //}

        System.out.println("\n\nUsuário não encontrado!");

        System.out.println("\n\nDigite para continuar");
        String tempo = Ferramentas.lString();
    }

}
