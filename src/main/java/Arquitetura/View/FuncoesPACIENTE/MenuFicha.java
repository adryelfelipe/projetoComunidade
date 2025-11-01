package Arquitetura.View.FuncoesPACIENTE;

import Arquitetura.Model.Paciente;
import Arquitetura.Utilidades.Ferramentas;

public class MenuFicha {



    public static void Ficha(Paciente paciente)
    {

        Ferramentas.limpaTerminal();

        System.out.println("   FICHA TÉCNICA\n\n");
        paciente.dadosPessoais();

    }
}
