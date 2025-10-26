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
        long idMedico;

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
            System.out.println("       -----------------             --------- ATUAL --------");
            System.out.println("       |EDITAR   Medico|             |Nome: " + medico.getNome());
            System.out.println("       -----------------             |CPF: " + medico.getCpf());
            System.out.println("                                     |Senha: " + medico.getSenha());
            System.out.println(" [1] - Nome                          |Email: " + medico.getEmail());
            System.out.println(" [2] - CPF                           |Telefone: " + medico.getTelefone());
            System.out.println(" [3] - Senha                         |Sexo: " + medico.getSexo().name());
            System.out.println(" [4] - Email                         |TipoUsuario: " + medico.getTipoUsuario().name());
            System.out.println(" [5] - Telefone                      |Data de Nascimento: " + medico.getDataNascimento());
            System.out.println(" [6] - Sexo                          |Formação: " + medico.getFormacao());
            System.out.println(" [7] - TipoUsuario                   |Especialidade: " + medico.getEspecialidade());
            System.out.println(" [8] - Data de Nascimento            |SubEspecialidade: " + medico.getSubEspecialidade());
            System.out.println(" [9] - Formação                      |Plantão: " + medico.getPlantao().name());
            System.out.println("[10] - Especialidade                 ------------------------");
            System.out.println("[11] - SubEspecialidade              ");
            System.out.println("[12] - Plantão                       ");
            System.out.println("[13] - Sair                          ");

            // -- Leitura da opção -- //
            int op = Ferramentas.lerOpcao();

            Ferramentas.limpaTerminal();

            // -- Lógica de alteração -- //

            try {
                switch(op) {
                    case 1 -> {
                        String nome = MenuSetUsuario.SetNome();
                        usuarioService.updateNomeUsuario(administrador, idMedico, nome);
                        medico.setNome(nome);
                    }

                    case 2 -> {
                        String cpf = MenuSetUsuario.SetCpf();
                        usuarioService.updateCpf(administrador, idMedico, cpf);
                        medico.setCpf(cpf);
                    }

                    case 3 -> {
                        String senha = MenuSetUsuario.SetSenha();
                        usuarioService.updateSenhaUsuario(administrador, idMedico, senha);
                        medico.setSenha(senha);
                    }

                    case 4 -> {
                        String email = MenuSetUsuario.SetEmail();
                        usuarioService.updateEmailUsuario(administrador, idMedico, email);
                        medico.setEmail(email);
                    }

                    case 5 -> {
                        String telefone = MenuSetUsuario.SetTelefone();
                        usuarioService.updateTelefoneUsuario(administrador, idMedico, telefone);
                        medico.setTelefone(telefone);
                    }

                    case 6 -> {
                        Genero sexo = MenuSetUsuario.SetSexo();
                        usuarioService.updateSexo(administrador, idMedico, sexo);
                        medico.setSexo(sexo);
                    }

                    case 7 -> {
                        TipoUsuario tipoUsuario = MenuSetUsuario.SetTipoUsuario();
                        usuarioService.updateTipoUsuario(administrador, idMedico, tipoUsuario);
                        medico.setTipoUsuario(tipoUsuario);
                    }

                    case 8 -> {
                        Date dataNascimento = MenuSetUsuario.SetDataNascimento();
                        usuarioService.updateDataNascimento(administrador, idMedico, dataNascimento);
                        medico.setDataNascimento(dataNascimento);
                    }

                    case 9 -> {
                        String formacao = MenuSetMedico.SetFormacao();
                        medicoService.updateFormacaoMedico(administrador, idMedico, formacao);
                        medico.setFormacao(formacao);
                    }

                    case 10 -> {
                        Especialidade especialidade = MenuSetMedico.SetEspecialidade();
                        medicoService.updateEspecialidadeMedico(administrador, idMedico, especialidade);
                        medico.setEspecialidade(especialidade);
                    }

                    case 11 -> {
                        String subEspecialidade = MenuSetMedico.SetSubEspecialidade();
                        medicoService.updateSubEspecialidadeMedico(administrador, idMedico, subEspecialidade);
                        medico.setSubEspecialidade(subEspecialidade);
                    }

                    case 12 -> {
                        Plantao plantao = MenuSetMedico.SetPlantao();
                        medicoService.updatePlantaoMedico(administrador, idMedico, plantao);
                        medico.setPlantao(plantao);
                    }

                    case 13 -> {
                        return;
                    }

                    default -> MenuDefault.menuDefault();
                }
            } catch(TipoUsuarioException | DadosInvalidosException | IdInvalidoException |
                    DataInvalidaException | CpfInvalidoException | EmailInvalidoException | TelefoneInvalidoException e) {
                Ferramentas.mensagemErro(e.getMessage());
            }
        }
    }
}
