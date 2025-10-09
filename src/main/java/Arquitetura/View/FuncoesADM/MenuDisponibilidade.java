package Arquitetura.View.FuncoesADM;

import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Medico;
import Arquitetura.Model.Usuario;
import Arquitetura.Utilidades.Ferramentas;

public class MenuDisponibilidade
{

    static UsuarioDAO usuarioDAO = new UsuarioDAO();

    public static void Disponibilidade(Administrador adm)
    {

        Ferramentas.limpaTerminal();

        System.out.println("     Agenda Médico");
        System.out.println("\n\nDigite o cpf do medico: ");
        String cpf = Ferramentas.lString();

        for (Usuario entrada: usuarioDAO.findAllUsers())
        {
            if(entrada.getCpf().equals(cpf))
            {
                Medico medico = (Medico) entrada;

                Ferramentas.limpaTerminal();

                System.out.println("       CONSULTAS DO MÉDICO");

                //medico.Consultas
            }
        }

        System.out.println("Médico não encontrado!");

        System.out.println("\n\nDigite para continuar");
        String tempo = Ferramentas.lString();

    }
}