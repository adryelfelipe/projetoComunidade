package Arquitetura.View.FuncoesADM;

import Arquitetura.Exception.*;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Enums.Especialidade;
import Arquitetura.Model.Enums.Genero;
import Arquitetura.Model.Enums.Plantao;
import Arquitetura.Model.Enums.TipoUsuario;
import Arquitetura.Model.Medico;
import Arquitetura.Service.MedicoService;
import Arquitetura.Service.UsuarioService;
import Arquitetura.Service.Validator.UsuarioValidator;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.FuncoesMedico.MenuSetMedico;
import Arquitetura.View.MenuDefault;
import Arquitetura.View.MenuUsuarios.MenuEscolhaId;
import Arquitetura.View.MenuUsuarios.MenuSetUsuario;

import java.sql.Date;
import java.util.InputMismatchException;

public class MenuUpdateADM {

    // -- Atributos -- -- //
    private static final UsuarioValidator usuarioValidator = new UsuarioValidator();
    private static final UsuarioService usuarioService = new UsuarioService();
    private static final MedicoService medicoService = new MedicoService();

    public static void menuUpdateInicial(Administrador administrador) {
        // Variáveis
        boolean verifica = false;
        // Menu

        while(true) {
            System.out.println("     -----------------------------");
            System.out.println("     ----      MENU UPDATE    ----");
            System.out.println("     -----------------------------");

            System.out.print("\n\n"); // pula linhas

            System.out.println("1 - Alterar a si mesmo");
            System.out.println("2 - Alterar um paciente");
            System.out.println("3 - Alterar um médico");
            System.out.println("4 - SAIR DO MENU");
            int opUpdate = Ferramentas.lerOpcao();

            switch(opUpdate) {
                case 1 -> menuAutoUpdate(administrador);

                case 2 -> menuUpdatePaciente(administrador);

                case 3 -> menuUpdateMedico(administrador);

                case 4 -> {
                    return;
                }

                default -> Ferramentas.mensagemDefault();
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
        // -- Garantia de inicialização -- //
        long idMedico = 0;
        int op = 0;

        // Menu de escolha de ID
        Ferramentas.limpaTerminal();

        try {
            idMedico = MenuEscolhaId.escolhaIdUpdate();
        } catch (IdInvalidoException e) {
            Ferramentas.mensagemErro(e.getMessage());
            return;
        }

        try {
            medicoService.idMedicoValidator(idMedico);
        } catch(IdInvalidoException e) {
            Ferramentas.mensagemErro(e.getMessage());
            return;
        }

        Medico medico = ((Medico) usuarioService.findById(administrador, idMedico));

        // -- Menu de escolha da mudança -- //
        while(true) {
            System.out.println("       -----------------             ------- ATUAL ------");
            System.out.println("       |EDITAR   Medico|             |Nome: " + medico.getNome());
            System.out.println("       -----------------             |CPF: " + medico.getCpf());
            System.out.println("                                     |EMAIL: " + medico.getEmail());
            System.out.println("                                     |Telefone: " + medico.getTelefone());
            System.out.println(" [1] - Email                         |Formação: " + medico.getFormacao());
            System.out.println(" [2] - Telefone                      |Especialidade: " + medico.getEspecialidade());
            System.out.println(" [3] - Senha                         |SubEspecialidade: " + medico.getSubEspecialidade());
            System.out.println(" [4] - Formação                      |Plantão: " + medico.getPlantao());
            System.out.println(" [5] - Especialidade                 |TipoUsuario " + medico.getTipoUsuario().name());
            System.out.println(" [6] - SubEspecialidade              |Data de Nascimento: " + medico.getDataNascimento());
            System.out.println(" [7] - Plantão                       |Sexo " + medico.getSexo().name());
            System.out.println(" [8] - CPF                           ------------------------");
            System.out.println(" [9] - Data de Nascimento                ");
            System.out.println("[10] - Tipo de Usuário                   ");
            System.out.println("[11] - Sexo                              ");
            System.out.println("[12] - Nome                              ");
            System.out.println("[13] - Sair                              ");

            // -- Leitura da opção -- //
            try {
                op = Ferramentas.lInteiro();
            } catch(InputMismatchException e) {
                MenuDefault.menuDefault();
            }

            Ferramentas.limpaTerminal();

            // -- Lógica de alteração -- //
            switch(op) {
                case 1 -> {
                    String email = MenuSetUsuario.SetEmail();

                    try {
                        usuarioService.updateEmailUsuario(administrador, idMedico, email);
                        medico.setEmail(email);
                    } catch (TipoUsuarioException | DadosInvalidosException |
                             IdInvalidoException | EmailInvalidoException e) {
                        Ferramentas.mensagemErro(e.getMessage());
                    }

                }

                case 2 -> {
                    String telefone = MenuSetUsuario.SetTelefone();

                    try {
                        usuarioService.updateTelefoneUsuario(administrador, idMedico, telefone);
                        medico.setTelefone(telefone);
                    } catch (TipoUsuarioException | DadosInvalidosException | IdInvalidoException | TelefoneInvalidoException e) {
                        Ferramentas.mensagemErro(e.getMessage());
                    }
                }

                case 3 -> {
                    String senha = MenuSetUsuario.SetSenha();

                    try {
                        usuarioService.updateSenhaUsuario(administrador, idMedico, senha);
                        medico.setSenha(senha);
                    } catch (TipoUsuarioException | DadosInvalidosException | IdInvalidoException e) {
                        Ferramentas.mensagemErro(e.getMessage());
                    }
                }

                case 4 -> {
                    String formacao = MenuSetMedico.SetFormacao();

                    try {
                        medicoService.updateFormacaoMedico(administrador, idMedico, formacao);
                        medico.setFormacao(formacao);
                    } catch (TipoUsuarioException | DadosInvalidosException | IdInvalidoException e) {
                        Ferramentas.mensagemErro(e.getMessage());
                    }
                }

                case 5 -> {
                    Especialidade especialidade = MenuSetMedico.SetEspecialidade();

                    try {
                        medicoService.updateEspecialidadeMedico(administrador, idMedico, especialidade);
                        medico.setEspecialidade(especialidade);
                    } catch (TipoUsuarioException | DadosInvalidosException | IdInvalidoException e) {
                        Ferramentas.mensagemErro(e.getMessage());
                    }
                }

                case 6 -> {
                    String subEspecialidade = MenuSetMedico.SetSubEspecialidade();

                    try {
                        medicoService.updateSubEspecialidadeMedico(administrador, idMedico, subEspecialidade);
                        medico.setSubEspecialidade(subEspecialidade);
                    } catch (TipoUsuarioException | DadosInvalidosException | IdInvalidoException e) {
                        Ferramentas.mensagemErro(e.getMessage());
                    }
                }

                case 7 -> {
                    Plantao plantao = MenuSetMedico.SetPlantao();

                    try {
                        medicoService.updatePlantaoMedico(administrador, idMedico, plantao);
                        medico.setPlantao(plantao);
                    } catch (TipoUsuarioException | DadosInvalidosException | IdInvalidoException e) {
                        Ferramentas.mensagemErro(e.getMessage());
                    }
                }

                case 8 -> {
                    String cpf = MenuSetUsuario.SetCpf();

                    try {
                        usuarioService.updateCpf(administrador, idMedico, cpf);
                        medico.setCpf(cpf);
                    } catch (TipoUsuarioException | DadosInvalidosException | IdInvalidoException | CpfInvalidoException e) {
                        Ferramentas.mensagemErro(e.getMessage());
                    }
                }

                case 9 -> {
                    Date dataNascimento = MenuSetUsuario.SetDataNascimento();

                    try {
                        usuarioService.updateDataNascimento(administrador, idMedico, dataNascimento);
                        medico.setDataNascimento(dataNascimento);
                    } catch (TipoUsuarioException | DadosInvalidosException | IdInvalidoException | DataInvalidaException e) {
                        Ferramentas.mensagemErro(e.getMessage());
                    }
                }

                case 10 -> {
                    TipoUsuario tipoUsuario = MenuSetUsuario.SetTipoUsuario();

                    try {
                        usuarioService.updateTipoUsuario(administrador, idMedico, tipoUsuario);
                        medico.setTipoUsuario(tipoUsuario);
                    }  catch(TipoUsuarioException | DadosInvalidosException | IdInvalidoException e) {
                        Ferramentas.mensagemErro(e.getMessage());
                    }
                }

                case 11 -> {
                    Genero sexo = MenuSetUsuario.SetSexo();

                    try {
                        usuarioService.updateSexo(administrador, idMedico, sexo);
                        medico.setSexo(sexo);
                    } catch (TipoUsuarioException | DataInvalidaException | IdInvalidoException e) {
                        Ferramentas.mensagemErro(e.getMessage());
                    }
                }

                case 12 -> {
                    String nome = MenuSetUsuario.SetNome();

                    try{
                        usuarioService.updateNomeUsuario(administrador, idMedico, nome);
                        medico.setNome(nome);
                    } catch (TipoUsuarioException | DataInvalidaException | IdInvalidoException e) {
                        Ferramentas.mensagemErro(e.getMessage());
                    }
                }

                case 13 -> {
                    return;
                }

                default -> MenuDefault.menuDefault();
            }
        }
    }
}
