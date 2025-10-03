package Arquitetura.View;

import Arquitetura.Model.Administrador;
import Arquitetura.Model.Medico;
import Arquitetura.Model.Paciente;
import Arquitetura.Model.Usuario;
import Arquitetura.Utilidades.Ferramentas;

import static Arquitetura.View.MenuUsuarios.MenuAdministrador.*;

public class MenuExcluir
{


    public static void ExcluirUsuario(Administrador adm)
    {

        Ferramentas.limpaTerminal();

        System.out.println("     EXCLUIR");
        System.out.println("\n\nDigite o cpf do usuario: ");
        String cpf = Ferramentas.lString();

        for(Usuario entrada: usuarioService.findAllUsers(adm))
        {
            if (entrada.getCpf().equals(cpf))
            {
                if(entrada instanceof Administrador)
                {
                    Administrador admdel = (Administrador) entrada;

                    administradorService.deletarAdministrador(adm,admdel);
                }
                else if(entrada instanceof Medico)
                {
                    Medico medicodel = (Medico) entrada;

                    medicoService.deletarMedico(adm,medicodel);
                }
                else if(entrada instanceof Paciente)
                {
                    Paciente pacientedel = (Paciente) entrada;

                    pacienteService.deletarPaciente(adm,pacientedel);
                }

                System.out.println("Usuario excluido");


                System.out.println("\n\nDigite para continuar");
                String tempo = Ferramentas.lString();

                return;
            }
        }

        System.out.println("Usuario não encontrado!");

        System.out.println("\n\nDigite para continuar");
        String tempo = Ferramentas.lString();

    }

}
