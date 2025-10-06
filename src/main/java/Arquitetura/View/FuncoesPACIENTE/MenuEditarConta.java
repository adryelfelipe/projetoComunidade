package Arquitetura.View.FuncoesPACIENTE;

import Arquitetura.Model.Paciente;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.MenuDefault;

public class MenuEditarConta {


    public static void Editar(Paciente paciente)
    {

        boolean continuar = true;

        do {
            Ferramentas.limpaTerminal();

            System.out.println("       -----------------             ------------------------");
            System.out.println("       |EDITAR PACIENTE|             |Nome: " + paciente.getNome());
            System.out.println("       -----------------             |CPF: " + paciente.getCpf());
            System.out.println("                                     |EMAIL: " + paciente.getEmail());
            System.out.println("Digite para alterar:                 |Telefone: " + paciente.getTelefone());
            System.out.println("1-Email                              |Contato Emergência: " + paciente.getContatoEmergencia());
            System.out.println("2-Telefone                           |Senha: " + paciente.getSenha());
            System.out.println("3-Senha                              |Data nascimento: " + paciente.getDataNascimento());
            System.out.println("4-Contato Emergência                 |Numero carteirinha: " + paciente.getNumeroCarterinha());
            System.out.println("5-Numero da carteirinha              |Status: " + paciente.getStatusPaciente());
            System.out.println("6-Sair                               ------------------------");
            int escolha = Ferramentas.lInteiro();

            switch (escolha) {
                case 1: {

                    System.out.println("Digite seu novo Email: ");
                    String email = Ferramentas.lString();

                    paciente.setEmail(email);

                    break;
                }
                case 2: {

                    System.out.println("Digite seu novo Telefone: ");
                    String telefone = Ferramentas.lString();

                    paciente.setTelefone(telefone);

                    break;
                }
                case 3: {

                    System.out.println("Digite sua nova Senha: ");
                    String senha = Ferramentas.lString();

                    paciente.setSenha(senha);
                    break;
                }
                case 4: {

                    System.out.println("Digite seu novo Contato de emergência: ");
                    String contato = Ferramentas.lString();

                    paciente.setContatoEmergencia(contato);

                    break;
                }
                case 5: {

                    System.out.println("Digite seu novo numero da carteirinha: ");
                    String carteirinha = Ferramentas.lString();

                    paciente.setNumeroCarterinha(carteirinha);

                    break;
                }
                case 6: {

                    continuar = false;

                    break;
                }
                default: {

                    MenuDefault.menuDefault();

                    break;
                }
            }
        }while (!continuar);
    }
}
