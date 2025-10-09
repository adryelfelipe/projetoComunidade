package Arquitetura.View;

import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Enums.Especialidade;
import Arquitetura.Model.Enums.Plantao;
import Arquitetura.Model.Medico;
import Arquitetura.Model.Paciente;
import Arquitetura.Model.Usuario;
import Arquitetura.Utilidades.Ferramentas;

public class MenuEditarConta {


    static UsuarioDAO usuarioDAO = new UsuarioDAO();

    public static void EditarPaciente(Paciente paciente)
    {

        int escolha = 0;

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


            try {
                escolha = Ferramentas.lInteiro();
            } catch (Exception e) {
                MenuDefault.menuDefault();
            }
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

    public static void Editar(Administrador adm)
    {

        Ferramentas.limpaTerminal();

        System.out.println("        EDITAR ");
        System.out.println("\n\nDigite o CPF: ");
        String cpf = Ferramentas.lString();

        for(Usuario entrada: usuarioDAO.findAllUsers())
        {
            if(entrada.getCpf().equals(cpf))
            {
                if(entrada instanceof Medico)
                {
                    Medico entrad = (Medico) entrada;

                    Ferramentas.limpaTerminal();

                    MenuEditarConta.EditarMedico(entrad);

                }
                else if(entrada instanceof Paciente)
                {
                    Paciente entrad = (Paciente) entrada;

                    Ferramentas.limpaTerminal();

                    MenuEditarConta.EditarPaciente(entrad);
                }
                else if(entrada instanceof Administrador)
                {
                    System.out.println("\n\nEste usuário é um adm, não pode ser alterado!\n\n");
                    System.out.println("Digite para continuar");
                    String tempo = Ferramentas.lString();
                }
            }
        }

        System.out.println("\n\nUsuário não encontrado!");

        System.out.println("\n\nDigite para continuar");
        String tempo = Ferramentas.lString();
    }

    public static void EditarMedico(Medico medico) {

        boolean continuar = true;

        do {
            Ferramentas.limpaTerminal();

            System.out.println("       -----------------             ------------------------");
            System.out.println("       |EDITAR   Medico|             |Nome: " + medico.getNome());
            System.out.println("       -----------------             |CPF: " + medico.getCpf());
            System.out.println("                                     |EMAIL: " + medico.getEmail());
            System.out.println("Digite para alterar:                 |Telefone: " + medico.getTelefone());
            System.out.println("1-Email                              |Formação: " + medico.getFormacao());
            System.out.println("2-Telefone                           |Especialidade: " + medico.getEspecialidade());
            System.out.println("3-Senha                              |SubEspecialidade: " + medico.getSubEspecialidade());
            System.out.println("4-Formação                           |Plantão: " + medico.getPlantao());
            System.out.println("5-Especialidade                      ------------------------");
            System.out.println("6-SubEspecialidade                   ");
            System.out.println("7-Plantão                            ");
            System.out.println("8-Sair                               ");
            int escolha = Ferramentas.lInteiro();

            switch (escolha) {
                case 1: {

                    System.out.println("Digite seu novo Email: ");
                    String email = Ferramentas.lString();

                    medico.setEmail(email);

                    break;
                }
                case 2: {

                    System.out.println("Digite seu novo Telefone: ");
                    String telefone = Ferramentas.lString();

                    medico.setTelefone(telefone);

                    break;
                }
                case 3: {

                    System.out.println("Digite sua nova Senha: ");
                    String senha = Ferramentas.lString();

                    medico.setSenha(senha);

                    break;
                }
                case 4: {

                    System.out.println("Digite sua nova Formação: ");
                    String formacao = Ferramentas.lString();

                    medico.setFormacao(formacao);

                    break;
                }
                case 5: {

                    String especialidade = Ferramentas.lString();

                    boolean verifica = true;

                    int opsex;
                    do {

                        // Entrada da especialidade
                        System.out.println("Digite sua nova Especialidade: ");

                        System.out.println("1-CLINICO_GERAL           2-CARDIOLOGISTA    3-RADIOLOGISTA");
                        System.out.println("4-OTORRINOLARINGOLOGISTA  5-OFTALMOLOGISTA   6-ENDOCRINOLOGISTA");
                        System.out.println("7-HEMATOLOGISTA");
                        opsex = Ferramentas.lInteiro();

                        if (opsex != 1 && opsex != 2) {
                            verifica = false;

                            Ferramentas.limpaTerminal();

                            System.out.println("ERRO.  OPÇÂO INVALIDA");
                        } else {
                            verifica = true;
                        }
                    } while (!verifica);

                    // Converte a entrada de Especialidade usando switch expression
                    Especialidade especialidad = switch (opsex) {
                        case 1 -> Especialidade.CLINICO_GERAL;
                        case 2 -> Especialidade.CARDIOLOGISTA;
                        case 3 -> Especialidade.RADIOLOGISTA;
                        case 4 -> Especialidade.OTORRINOLARINGOLOGISTA;
                        case 5 -> Especialidade.OFTALMOLOGISTA;
                        case 6 -> Especialidade.ENDOCRINOLOGISTA;
                        default -> Especialidade.HEMATOLOGISTA;
                    };

                    medico.setEspecialidade(especialidad);

                    break;
                }
                case 6:{

                    System.out.println("Digite sua nova SubEspecialidade");
                    String subespecialidade = Ferramentas.lString();

                    medico.setSubEspecialidade(subespecialidade);

                    break;
                }
                case 7: {

                    boolean verificaOp = true;

                    int opPlantao;
                    do {
                        System.out.println("Digite seu novo Plantão: ");
                        System.out.println("1 - MATUTINO ");
                        System.out.println("2 - VESPERTINO ");
                        System.out.println("3 - NORTURNO");
                        System.out.print("Opção: ");
                        opPlantao = Ferramentas.lInteiro();

                        if (opPlantao != 1 && opPlantao != 2 && opPlantao != 3) {
                            verificaOp = false;

                            Ferramentas.limpaTerminal();

                            System.out.println("ERRO! OPÇÃO INVÁLIDA \n");
                        } else {
                            verificaOp = true;
                        }
                    } while (!verificaOp);

                    Plantao plantao = switch (opPlantao) {
                        case 1 -> Plantao.MATUTINO;
                        case 2 -> Plantao.VERPERTINO;
                        default -> Plantao.NOTURNO;
                    };

                    medico.setPlantao(plantao);

                    break;
                }
                case 8: {

                    continuar = false;

                    break;
                }
                default: {

                    MenuDefault.menuDefault();

                    break;
                }
            }
        } while (!continuar);
    }
}
