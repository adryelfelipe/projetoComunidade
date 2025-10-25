package Arquitetura.View.FuncoesADM;

import Arquitetura.Exception.CpfInvalidoException;
import Arquitetura.Exception.IdInvalidoException;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Enums.Especialidade;
import Arquitetura.Model.Enums.Plantao;
import Arquitetura.Model.Medico;
import Arquitetura.Service.UsuarioService;
import Arquitetura.Service.Validator.UsuarioValidator;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.MenuDefault;

import java.util.InputMismatchException;

public class MenuUpdateADM {

    // -- Atributos -- -- //
    private static final UsuarioValidator usuarioValidator = new UsuarioValidator();
    private static final UsuarioService usuarioService = new UsuarioService();

    public static void menuUpdateInicial(Administrador administrador) {
        // Variáveis
        boolean verifica = false;
        int opUpdate = 0;

        // Menu

        while(!verifica) {
            System.out.println("     -----------------------------");
            System.out.println("     ----      MENU UPDATE    ----");
            System.out.println("     -----------------------------");

            System.out.print("\n\n"); // pula linhas

            System.out.println("1 - Alterar a si mesmo");
            System.out.println("2 - Alterar um paciente");
            System.out.println("3 - Alterar um médico");
            System.out.println("4 - SAIR DO MENU");
            System.out.print("ESCOLHA: ");

            try {
                opUpdate = Ferramentas.lInteiro();
                verifica = true;
            } catch (InputMismatchException e){
                MenuDefault.menuDefault();
            }
        }

        switch(opUpdate) {
            case 1 -> menuAutoUpdate(administrador);

            case 2 -> menuUpdatePaciente(administrador);

            case 3 -> menuUpdateMedico(administrador);

            case 4 -> {
                return;
            }
        }
    }

    private static void menuAutoUpdate(Administrador administrador) {
        Ferramentas.limpaTerminal();

        System.out.println("     -----------------------------");
        System.out.println("     ---    MENU UPDATE ADM   ----");
        System.out.println("     -----------------------------");


    }

    private static void menuUpdateMedico(Administrador administrador) {
        // -- Atributos -- //
        long idMedico = 0;
        boolean verifica = false;

        // Menu
        Ferramentas.limpaTerminal();

        while(!verifica) {
            System.out.print("DIGITE O ID DO MÉDICO: ");

            try {
                idMedico = Ferramentas.lInteiro();
                verifica = true;
                usuarioService.idExistenteValidator(idMedico);
            } catch (InputMismatchException e) {
                MenuDefault.menuDefault();
            } catch (IdInvalidoException e) {
                Ferramentas.mensagemErro(e.getMessage());
                return;
            }
        }

        Ferramentas.limpaTerminal();

        Medico medico = ((Medico) usuarioService.findById(administrador, idMedico));

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


        public static void EditarMedico(Medico medico) {

            boolean continuar = true;

            do {
                int escolha = Ferramentas.lInteiro();

                switch (escolha) {
                    case 1: {

                        System.out.println("Digite seu novo Email: ");
                        String email = Ferramentas.lString();

                        try {
                            medico.setEmail(email);
                        }catch (IllegalArgumentException e)
                        {
                            System.err.print(e.getMessage());
                            Ferramentas.Delay(1500);
                        }
                        break;
                    }
                    case 2: {

                        System.out.println("Digite seu novo Telefone: ");
                        String telefone = Ferramentas.lString();

                        try {
                            medico.setTelefone(telefone);
                        }catch (IllegalArgumentException e)
                        {
                            System.err.print(e.getMessage());
                            Ferramentas.Delay(1500);
                        }
                        break;
                    }
                    case 3: {

                        System.out.println("Digite sua nova Senha: ");
                        String senha = Ferramentas.lString();

                        try {
                            medico.setSenha(senha);
                        }catch (IllegalArgumentException e)
                        {
                            System.err.print(e.getMessage());
                            Ferramentas.Delay(1500);
                        }
                        break;
                    }
                    case 4: {

                        System.out.println("Digite sua nova Formação: ");
                        String formacao = Ferramentas.lString();

                        try {
                            medico.setFormacao(formacao);
                        }catch (IllegalArgumentException e)
                        {
                            System.err.print(e.getMessage());
                            Ferramentas.Delay(1500);
                        }
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

                        try {
                            medico.setSubEspecialidade(subespecialidade);
                        }catch (IllegalArgumentException e) {
                            System.err.print(e.getMessage());
                            Ferramentas.Delay(1500);
                        }
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
}
