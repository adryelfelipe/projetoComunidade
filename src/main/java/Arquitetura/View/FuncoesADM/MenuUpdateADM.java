package Arquitetura.View.FuncoesADM;

import Arquitetura.Exception.*;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Enums.*;
import Arquitetura.Model.Medico;
import Arquitetura.Model.Paciente;
import Arquitetura.Service.AdministradorService;
import Arquitetura.Service.MedicoService;
import Arquitetura.Service.PacienteService;
import Arquitetura.Service.UsuarioService;
import Arquitetura.Service.Validator.UsuarioValidator;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.FuncoesMedico.MenuSetMedico;
import Arquitetura.View.FuncoesPACIENTE.MenuSetPaciente;
import Arquitetura.View.MenuDefault;
import Arquitetura.View.MenuUsuarios.MenuEscolhaId;
import Arquitetura.View.MenuUsuarios.MenuSetUsuario;

import java.sql.Date;
import java.util.InputMismatchException;

public class MenuUpdateADM {

    // -- Atributos -- -- //
    private static final UsuarioService usuarioService = new UsuarioService();
    private static final MedicoService medicoService = new MedicoService();
    private static final PacienteService pacienteService = new PacienteService();
    private static final AdministradorService administradorService = new AdministradorService();

    public static void menuUpdateInicial(Administrador administrador) {
        // Menu
        boolean verifica = false;
        int opUpdate = 0;

        while(true) {

            while(!verifica) {
                System.out.println("     -----------------------------");
                System.out.println("     ----      MENU UPDATE    ----");
                System.out.println("     -----------------------------");

                System.out.print("\n\n"); // pula linhas

                System.out.println("1 - Alterar a si mesmo");
                System.out.println("2 - Alterar um paciente");
                System.out.println("3 - Alterar um médico");
                System.out.println("4 - SAIR DO MENU");
                try {
                    opUpdate = Ferramentas.lInteiro();
                    verifica = true;
                } catch (InputMismatchException e){
                    MenuDefault.menuDefault();
                }
            }

            // Reinicia a veriável de verificação
            verifica = false;

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

    public static void menuUpdatePaciente(Administrador administrador) {
        // -- Garantia de inicialização -- //
        long idPaciente;

        // Menu de escolha de ID
        Ferramentas.limpaTerminal();

        try {
            idPaciente = MenuEscolhaId.escolhaIdUpdate();
        } catch (IdInvalidoException e) {
            Ferramentas.mensagemErro(e.getMessage());
            return;
        }

        try{
            pacienteService.idPacienteValidator(idPaciente);
        } catch (IdInvalidoException e) {
            Ferramentas.mensagemErro(e.getMessage());
            return;
        }

        Paciente paciente = ((Paciente) usuarioService.findById(administrador, idPaciente));

        // -- Menu de escolha da mudança -- //
        int op = 0;
        boolean verifica = false;

        while(true) {
            while(!verifica) {
                System.out.println("       -------------------           --------- ATUAL --------");
                System.out.println("       |EDITAR   PACIENTE|           |Nome: " + paciente.getNome());
                System.out.println("       -------------------           |CPF: " + paciente.getCpf());
                System.out.println("                                     |Senha: " + paciente.getSenha());
                System.out.println(" [1] - Nome                          |Email: " + paciente.getEmail());
                System.out.println(" [2] - CPF                           |Telefone: " + paciente.getTelefone());
                System.out.println(" [3] - Senha                         |Sexo: " + paciente.getSexo().name());
                System.out.println(" [4] - Email                         |TipoUsuario: " + paciente.getTipoUsuario().name());
                System.out.println(" [5] - Telefone                      |Data de Nascimento: " + paciente.getDataNascimento());
                System.out.println(" [6] - Sexo                          |Cadastro: " + paciente.getNumeroCadastro());
                System.out.println(" [7] - TipoUsuario                   |ContatoEmergência: " + paciente.getContatoEmergencia());
                System.out.println(" [8] - Data de Nascimento            |Status: " + paciente.getStatusPaciente().name());
                System.out.println(" [9] - Número de cadastro            ------------------------");
                System.out.println("[10] - Contato de Emergência          ");
                System.out.println("[11] - Status do Paciente             ");
                System.out.println("[12] - Sair                           ");

                // -- Leitura da opção -- //
                try {
                    op = Ferramentas.lInteiro();
                    verifica = true;
                } catch (InputMismatchException e){
                    MenuDefault.menuDefault();
                }
            }

            // Reinicia a veriável de verificação
            verifica = false;

            Ferramentas.limpaTerminal();

            // -- Lógica de alteração -- //

            try {
                switch(op) {
                    case 1 -> {
                        String nome = MenuSetUsuario.SetNome();
                        usuarioService.updateNomeUsuario(administrador, idPaciente, nome);
                        paciente.setNome(nome);
                    }

                    case 2 -> {
                        String cpf = MenuSetUsuario.SetCpf();
                        usuarioService.updateCpf(administrador, idPaciente, cpf);
                        paciente.setCpf(cpf);
                    }

                    case 3 -> {
                        String senha = MenuSetUsuario.SetSenha();
                        usuarioService.updateSenhaUsuario(administrador, idPaciente, senha);
                        paciente.setSenha(senha);
                    }

                    case 4 -> {
                        String email = MenuSetUsuario.SetEmail();
                        usuarioService.updateEmailUsuario(administrador, idPaciente, email);
                        paciente.setEmail(email);
                    }

                    case 5 -> {
                        String telefone = MenuSetUsuario.SetTelefone();
                        usuarioService.updateTelefoneUsuario(administrador, idPaciente, telefone);
                        paciente.setTelefone(telefone);
                    }

                    case 6 -> {
                        Genero sexo = MenuSetUsuario.SetSexo();
                        usuarioService.updateSexo(administrador, idPaciente, sexo);
                        paciente.setSexo(sexo);
                    }

                    case 7 -> {
                        TipoUsuario tipoUsuario = MenuSetUsuario.SetTipoUsuario();
                        usuarioService.updateTipoUsuario(administrador, idPaciente, tipoUsuario);
                        paciente.setTipoUsuario(tipoUsuario);
                    }

                    case 8 -> {
                        Date dataNascimento = MenuSetUsuario.SetDataNascimento();
                        usuarioService.updateDataNascimento(administrador, idPaciente, dataNascimento);
                        paciente.setDataNascimento(dataNascimento);
                    }

                    case 9 -> {
                        String numeroCadastro = MenuSetPaciente.SetNumeroCadastro();
                        pacienteService.updateNumeroCadastro(administrador, idPaciente, numeroCadastro);
                        paciente.setNumeroCadastro(numeroCadastro);
                    }

                    case 10 -> {
                        String contatoEmergencia = MenuSetPaciente.SetContatoEmergencia();
                        pacienteService.updateContatoEmergencia(administrador, idPaciente, contatoEmergencia);
                        paciente.setContatoEmergencia(contatoEmergencia);
                    }

                    case 11 -> {
                        StatusPaciente statusPaciente = MenuSetPaciente.SetStatusPaciente();
                        pacienteService.updateStatusPaciente(administrador, idPaciente, statusPaciente);
                        paciente.setStatusPaciente(statusPaciente);
                    }

                    case 12 -> {
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

    public static void menuAutoUpdate(Administrador administrador) {
        // Garantia de inicialização
        boolean verifica = false;
        int op = 0;

        Ferramentas.limpaTerminal();

        while(true) {
            while(!verifica) {
                System.out.println("       --------------------             --------- ATUAL --------");
                System.out.println("       |EDITAR  A SI MESMO|             |Nome: " + administrador.getNome());
                System.out.println("       --------------------             |CPF: " + administrador.getCpf());
                System.out.println("                                        |Senha: " + administrador.getSenha());
                System.out.println(" [1] - Nome                             |Email: " + administrador.getEmail());
                System.out.println(" [2] - CPF                              |Telefone: " + administrador.getTelefone());
                System.out.println(" [3] - Senha                            |Sexo: " + administrador.getSexo().name());
                System.out.println(" [4] - Email                            |TipoUsuario: " + administrador.getTipoUsuario().name());
                System.out.println(" [5] - Telefone                         |Data de Nascimento: " + administrador.getDataNascimento());
                System.out.println(" [6] - Sexo                             |Departamento: " + administrador.getDepartamento().name());
                System.out.println(" [7] - TipoUsuario                      ");
                System.out.println(" [8] - Data de Nascimento               ");
                System.out.println(" [9] - Departamento                     ");
                System.out.println("[10] - Sair                             ");

                // -- Leitura da opção -- //
                try {
                    op = Ferramentas.lInteiro();
                    verifica = true;
                } catch (InputMismatchException e){
                    MenuDefault.menuDefault();
                }
            }

            // Reinicia a variável de verificação
            verifica = false;

            Ferramentas.limpaTerminal();

            // -- Lógica de alteração -- //
            try {
                switch(op) {
                    case 1 -> {
                        String nome = MenuSetUsuario.SetNome();
                        usuarioService.updateNomeUsuario(administrador, administrador.getId(), nome);
                        administrador.setNome(nome);
                    }

                    case 2 -> {
                        String cpf = MenuSetUsuario.SetCpf();
                        usuarioService.updateCpf(administrador, administrador.getId(), cpf);
                        administrador.setCpf(cpf);
                    }

                    case 3 -> {
                        String senha = MenuSetUsuario.SetSenha();
                        usuarioService.updateSenhaUsuario(administrador, administrador.getId(), senha);
                        administrador.setSenha(senha);
                    }

                    case 4 -> {
                        String email = MenuSetUsuario.SetEmail();
                        usuarioService.updateEmailUsuario(administrador, administrador.getId(), email);
                        administrador.setEmail(email);
                    }

                    case 5 -> {
                        String telefone = MenuSetUsuario.SetTelefone();
                        usuarioService.updateTelefoneUsuario(administrador, administrador.getId(), telefone);
                        administrador.setTelefone(telefone);
                    }

                    case 6 -> {
                        Genero sexo = MenuSetUsuario.SetSexo();
                        usuarioService.updateSexo(administrador, administrador.getId(), sexo);
                        administrador.setSexo(sexo);
                    }

                    case 7 -> {
                        TipoUsuario tipoUsuario = MenuSetUsuario.SetTipoUsuario();
                        usuarioService.updateTipoUsuario(administrador, administrador.getId(), tipoUsuario);
                        administrador.setTipoUsuario(tipoUsuario);
                    }

                    case 8 -> {
                        Date dataNascimento = MenuSetUsuario.SetDataNascimento();
                        usuarioService.updateDataNascimento(administrador, administrador.getId(), dataNascimento);
                        administrador.setDataNascimento(dataNascimento);
                    }

                    case 9 -> {
                        Departamento departamento = MenuSetAdm.SetDepartamento();
                        administradorService.updateDepartamento(administrador, administrador.getId(), departamento);
                        administrador.setDepartamento(departamento);
                    }

                    case 10 -> {
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

    public static void menuUpdateMedico(Administrador administrador) {
        // -- Garantia de inicialização -- //
        long idMedico;
        int op = 0;
        boolean verifica = false;

        // Menu de escolha de ID
        Ferramentas.limpaTerminal();

        try {
            idMedico = MenuEscolhaId.escolhaIdUpdate();
        } catch (IdInvalidoException e) {
            Ferramentas.mensagemErro(e.getMessage());
            return;
        }

        try{
            medicoService.idMedicoValidator(idMedico);
        } catch (IdInvalidoException e) {
            Ferramentas.mensagemErro(e.getMessage());
            return;
        }

        Medico medico = ((Medico) usuarioService.findById(administrador, idMedico));

        // -- Menu de escolha da mudança -- //
        while(true) {

            while(!verifica) {
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
                try {
                    op = Ferramentas.lInteiro();
                    verifica = true;
                } catch (InputMismatchException e){
                    MenuDefault.menuDefault();
                }
            }

            // Reinicia a variável de verificação
            verifica = false;

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
