package Arquitetura.View.FuncoesADM;

import Arquitetura.Exception.CpfInvalidoException;
import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Exception.DataInvalidaException;
import Arquitetura.Exception.TipoUsuarioException;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Enums.Departamento;
import Arquitetura.Model.Enums.Especialidade;
import Arquitetura.Model.Enums.Genero;
import Arquitetura.Model.Enums.Plantao;
import Arquitetura.Model.Medico;
import Arquitetura.Model.Paciente;
import Arquitetura.Service.AdministradorService;
import Arquitetura.Service.MedicoService;
import Arquitetura.Service.PacienteService;
import Arquitetura.Service.UsuarioService;
import Arquitetura.Service.Validator.AdministradorValidator;
import Arquitetura.Service.Validator.DataValidator;
import Arquitetura.Service.Validator.FuncionarioValidator;
import Arquitetura.Service.Validator.UsuarioValidator;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.MenuDefault;

import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.InputMismatchException;

public class MenuCadastro
{

    public static void CriarMedico(Administrador adm)
    {
        boolean continuar = true;

        do {

            Ferramentas.limpaTerminal();

            System.out.println("     -----------------------------");
            System.out.println("     ----    Cadastro Médico  ----");
            System.out.println("     -----------------------------");

            // Entrada do nome
            System.out.println("\n\n\nDigite o nome: ");
            String nome = Ferramentas.lString();


            // Entrada do CPF
            System.out.println("Digite o CPF: ");
            String cpf = Ferramentas.lString();


            // Entrada da senha
            System.out.println("Digite a senha: ");
            String senha = Ferramentas.lString();


            // Entrada do sexo
            int opsex = 0;
            boolean verifica;

            do {

                System.out.println("Digite o seu sexo:");
                System.out.println("1-Masculino");
                System.out.println("2-Feminino");

                try {
                    opsex = Ferramentas.lInteiro();
                    verifica = false;

                } catch (InputMismatchException e) {
                    Ferramentas.limpaTerminal();

                    verifica = true;

                    System.err.println("ERRO.  OPÇÂO INVALIDA");
                    Ferramentas.Delay(1500);
                }

            } while (!verifica);

            // Converte a entrada de genero usando switch expression
            Genero genero = switch (opsex) {
                case 1 -> Genero.MASCULINO;
                default -> Genero.FEMININO;
            };


            // Entrada do telefone
            System.out.println("Digite o número de telefone: ");
            String telefone = Ferramentas.lString();


            // Entrada do email
            System.out.println("Digite o email: ");
            String email = Ferramentas.lString();


            // Entrada da data de nascimento
            System.out.println("Data de nascimento");

            int ano = 0;
            System.out.println("Digite o Ano: ");
            try {
                ano = Ferramentas.lInteiro();
            } catch (InputMismatchException e) {
                Ferramentas.limpaTerminal();
                System.err.print(e.getMessage());
                Ferramentas.Delay(1500);
                continuar = false;
            }

            int mes = 0;
            System.out.println("Digite o Mês: ");
            try {
                mes = Ferramentas.lInteiro();
            } catch (InputMismatchException e) {
                Ferramentas.limpaTerminal();
                System.err.print(e.getMessage());
                Ferramentas.Delay(1500);
                continuar = false;
            }


            int dia = 0;
            System.out.println("Digite Dia: ");
            try {
                dia = Ferramentas.lInteiro();
            } catch (InputMismatchException e) {
                System.err.print(e.getMessage());
                Ferramentas.Delay(1500);
                continuar = false;
            }


            int cargaHoraria = 0;

            // Entrada da carga horária semanal
            System.out.println("Digite a carga horária semanal: ");
            try {
                cargaHoraria = Ferramentas.lInteiro();
            }catch (InputMismatchException e) {
                Ferramentas.limpaTerminal();
                System.err.print(e.getMessage());
                Ferramentas.Delay(1500);
            }
            double salario = 0;

            // Entrada do salário
            System.out.println("Digite o salário: ");
            try {
                salario = Ferramentas.lDouble();
            } catch (InputMismatchException e) {
                Ferramentas.limpaTerminal();
                System.err.print(e.getMessage());
                Ferramentas.Delay(1500);
                continuar = false;
            }

            boolean verific = true;

            do {

                // Entrada da especialidade
                System.out.println("Digite a especialidade: ");
                System.out.println("1-CLINICO_GERAL           2-CARDIOLOGISTA    3-RADIOLOGISTA");
                System.out.println("4-OTORRINOLARINGOLOGISTA  5-OFTALMOLOGISTA   6-ENDOCRINOLOGISTA");
                System.out.println("7-HEMATOLOGISTA");

                try {
                    opsex = Ferramentas.lInteiro();

                    verifica = false;
                } catch (InputMismatchException e) {

                    Ferramentas.limpaTerminal();

                    System.err.println("ERRO.  OPÇÂO INVALIDA");
                    Ferramentas.Delay(1500);

                    verific = true;
                }
            } while (!verific);

            // Converte a entrada de Especialidade usando switch expression
            Especialidade especialidade = switch (opsex) {
                case 1 -> Especialidade.CLINICO_GERAL;
                case 2 -> Especialidade.CARDIOLOGISTA;
                case 3 -> Especialidade.RADIOLOGISTA;
                case 4 -> Especialidade.OTORRINOLARINGOLOGISTA;
                case 5 -> Especialidade.OFTALMOLOGISTA;
                case 6 -> Especialidade.ENDOCRINOLOGISTA;
                default -> Especialidade.HEMATOLOGISTA;
            };

            // Entrada da formação acadêmica
            System.out.println("Digite a formação academica: ");
            String formacao = Ferramentas.lString();

            // Entrada da sub especialidade (caso houver)
            System.out.println("Digite a Sub Especialidade: (Caso houver) ");
            String subE = Ferramentas.lString();

            // Entrada do plantão
            boolean verificaOp = true;
            int opPlantao = 0;

            do {
                System.out.println("Qual é o seu plantão? ");
                System.out.println("1 - MATUTINO ");
                System.out.println("2 - VESPERTINO ");
                System.out.println("3 - NORTURNO");
                System.out.print("Opção: ");

                try {

                    opPlantao = Ferramentas.lInteiro();

                    verificaOp = false;

                } catch (InputMismatchException e) {

                    Ferramentas.limpaTerminal();

                    System.err.println("ERRO! OPÇÃO INVÁLIDA \n");
                    Ferramentas.Delay(1500);

                    verificaOp = true;
                }
            } while (!verificaOp);

            Plantao plantao = switch (opPlantao) {
                case 1 -> Plantao.MATUTINO;
                case 2 -> Plantao.VERPERTINO;
                default -> Plantao.NOTURNO;
            };

            LocalDate dataNascimento = LocalDate.of(ano, mes, dia);

            Date sqlDate = Date.valueOf(dataNascimento);

            // Cria médico com subespecialidade
            if (subE.isEmpty()) {

                try {
                    Medico medico = new Medico(nome, cpf, senha, genero, telefone, email, sqlDate, cargaHoraria, salario, plantao, especialidade, formacao);

                    MedicoService medicoService = new MedicoService();

                    medicoService.inserirMedico(adm, medico);

                    System.out.println("Medico criado");

                    Ferramentas.Delay(1500);
                } catch (IllegalArgumentException e) {
                    Ferramentas.limpaTerminal();
                    System.err.print(e.getMessage());
                    Ferramentas.Delay(1500);
                }

            } else {

                try {
                    Medico medico = new Medico(nome, cpf, senha, genero, telefone, email, sqlDate, cargaHoraria, salario, plantao, especialidade, formacao, subE);

                    MedicoService medicoService = new MedicoService();

                    medicoService.inserirMedico(adm, medico);

                    System.out.println("Medico criado");

                    Ferramentas.Delay(1500);

                } catch (IllegalArgumentException e) {
                    Ferramentas.limpaTerminal();
                    System.err.print(e.getMessage());
                    Ferramentas.Delay(1500);
                }
            }

        }while (!continuar);
    }

    public static void CriarPaciente(Administrador adm)
    {
        boolean continuar = true;

        do {

            Ferramentas.limpaTerminal();

            System.out.println("     -----------------------------");
            System.out.println("     ----  Cadastro Paciente  ----");
            System.out.println("     -----------------------------");

            // Entrada do nome
            System.out.println("\n\n\nDigite o nome: ");
            String nome = Ferramentas.lString();


            // Entrada do CPF
            System.out.println("Digite o CPF: ");
            String cpf = Ferramentas.lString();


            // Entrada da senha
            System.out.println("Digite a senha: ");
            String senha = Ferramentas.lString();


            // Entrada do sexo
            int opsex = 0;
            boolean verifica;

            do {

                System.out.println("Digite o seu sexo:");
                System.out.println("1-Masculino");
                System.out.println("2-Feminino");

                try {
                    opsex = Ferramentas.lInteiro();
                    verifica = false;

                } catch (InputMismatchException e) {
                    Ferramentas.limpaTerminal();

                    verifica = true;

                    System.err.println("ERRO.  OPÇÂO INVALIDA");
                    Ferramentas.Delay(1500);
                }

            } while (!verifica);

            // Converte a entrada de genero usando switch expression
            Genero genero = switch (opsex) {
                case 1 -> Genero.MASCULINO;
                default -> Genero.FEMININO;
            };


            // Entrada do telefone
            System.out.println("Digite o número de telefone: ");
            String telefone = Ferramentas.lString();


            // Entrada do email
            System.out.println("Digite o email: ");
            String email = Ferramentas.lString();


            // Entrada da data de nascimento
            System.out.println("Data de nascimento");

            int ano = 0;
            System.out.println("Digite o Ano: ");
            try {
                ano = Ferramentas.lInteiro();
            } catch (InputMismatchException e) {
                Ferramentas.limpaTerminal();
                System.err.print(e.getMessage());
                Ferramentas.Delay(1500);
                continuar = false;
            }

            int mes = 0;
            System.out.println("Digite o Mês: ");
            try {
                mes = Ferramentas.lInteiro();
            } catch (InputMismatchException e) {
                Ferramentas.limpaTerminal();
                System.err.print(e.getMessage());
                Ferramentas.Delay(1500);
                continuar = false;
            }


            int dia = 0;
            System.out.println("Digite Dia: ");
            try {
                dia = Ferramentas.lInteiro();
            } catch (InputMismatchException e) {
                Ferramentas.limpaTerminal();
                System.err.print(e.getMessage());
                Ferramentas.Delay(1500);
                continuar = false;
            }

            // Entrada do contato de emergência
            System.out.println("Digite o número do contato de emergência: ");
            String contatoEmer = Ferramentas.lString();

            // Entrada do número da carteirinha
            System.out.println("Digite o número da carteirinha: ");
            String numeroCar = Ferramentas.lString();

            LocalDate dataNascimento = LocalDate.of(ano, mes, dia);

            Date sqlDate = Date.valueOf(dataNascimento);

            try {
                Paciente paciente = new Paciente(nome, cpf, senha, genero, telefone, email, sqlDate, contatoEmer, contatoEmer);

                PacienteService pacienteService = new PacienteService();

                pacienteService.inserirPaciente(adm, paciente);

                System.out.println("Paciente criado");

                Ferramentas.Delay(1500);
            } catch (IllegalArgumentException e) {
                Ferramentas.limpaTerminal();
                System.err.print(e.getMessage());
                Ferramentas.Delay(1500);
            }

        }while (!continuar);
    }

    public static void CriarADM(Administrador adm) {
        Ferramentas.limpaTerminal();

        // Validadores de regras de negócio
        UsuarioService usuarioService = new UsuarioService();
        AdministradorService administradorService = new AdministradorService();
        UsuarioValidator usuarioValidator = new UsuarioValidator(usuarioService);
        FuncionarioValidator funcionarioValidator = new FuncionarioValidator(usuarioValidator);
        AdministradorValidator administradorValidator = new AdministradorValidator(funcionarioValidator);
        boolean verifica = false;

        // Garantia de inicialização
        String nome = null;
        String cpf = null;
        String senha = null;
        String telefone = null;
        String email = null;
        int cargaHoraria = 0;
        double salario = 0;

        // Menu
        System.out.println("     -----------------------");
        System.out.println("     ----    Criar ADM  ----");
        System.out.println("     -----------------------");

        // Entrada do nome
        while(!verifica) {
            System.out.print("\n\n\nDigite o nome: ");
            nome = Ferramentas.lString();

            try{
                UsuarioValidator.verificaIntegridadeNome(nome);
                verifica = true;
            } catch(DadosInvalidosException e) {
                Ferramentas.mensagemErro(e.getMessage());
            }
        }

        System.out.println(); // pula uma linha

        // RESETA A VERIFICAÇÃO
        verifica = false;

        // Entrada do CPF
        while(!verifica) {
            System.out.print("Digite o CPF: ");
            cpf = Ferramentas.lString();

            try{
                UsuarioValidator.verificaIntegridadeCpf(cpf);
                usuarioValidator.verificarRegrasCpf(cpf);
                usuarioService.cpfUtilizadoValidator(cpf);
                verifica = true;
            } catch(DadosInvalidosException | CpfInvalidoException e) {
                Ferramentas.mensagemErro(e.getMessage());
            }
        }

        System.out.println(); // pula uma linha

        // RESETA A VERIFICAÇÃO
        verifica = false;

        // Entrada da senha
        while (!verifica) {
            System.out.print("Digite a senha: ");
            senha = Ferramentas.lString();

            try{
                UsuarioValidator.verificaIntegridadeSenha(senha);
                usuarioValidator.verificarRegrasSenha(senha);
                verifica = true;
            } catch (DadosInvalidosException e) {
                Ferramentas.mensagemErro(e.getMessage());
            }
        }

        System.out.println(); // pula uma linha

        // RESETA A VERIFICAÇÃO
        verifica = false;

        // Entrada do sexo
        int opsex = 0;

        while(!verifica) {
            System.out.println("Digite o seu sexo:");
            System.out.println("1-Masculino");
            System.out.println("2-Feminino");
            System.out.print("OPÇÃO: ");

            try {
                opsex = Ferramentas.lInteiro();
                if(opsex < 1 || opsex > 2) {
                    MenuDefault.menuDefault();
                } else {
                    verifica = true;
                }
            } catch (InputMismatchException e) {
                MenuDefault.menuDefault();
            }
        }

        // Converte a entrada de genero usando switch expression
        Genero genero = switch (opsex) {
            case 1 -> Genero.MASCULINO;
            default -> Genero.FEMININO;
        };

        try{
            usuarioValidator.verificarRegrasSexo(genero);
        } catch (DadosInvalidosException e) {
            Ferramentas.mensagemErro(e.getMessage());
        }

        System.out.println(); // pula uma linha

        // RESETA A VERIFICAÇÃO
        verifica = false;

        // Entrada do telefone
        while(!verifica) {
            System.out.print("Digite o número de telefone: ");
            telefone = Ferramentas.lString();

            try{
                UsuarioValidator.verificaIntegridadeTelefone(telefone);
                usuarioValidator.verificarRegrasTelefone(telefone);
                verifica = true;
            } catch (DadosInvalidosException e) {
                Ferramentas.mensagemErro(e.getMessage());
            }
        }

        System.out.println(); // pula uma linha

        // RESETA A VERIFICAÇÃO
        verifica = false;

        // Entrada do email
        while(!verifica) {
            System.out.print("Digite o email: ");
            email = Ferramentas.lString();

            try{
                UsuarioValidator.verificaIntegridadeEmail(email);
                usuarioValidator.verificarRegrasEmail(email);
                verifica = true;
            } catch (DadosInvalidosException e) {
                Ferramentas.mensagemErro(e.getMessage());
            }
        }

        System.out.println(); // pula uma linha

        // RESETA A VERIFICAÇÃO
        verifica = false;

        // Entrada da data de nascimento
        System.out.println("Data de nascimento");

        // Ano
        int ano = 0;
        while(!verifica) {
            System.out.print("Digite o Ano: ");
            try {
                ano = Ferramentas.lInteiro();
                verifica = true;
            } catch (InputMismatchException e) {
                MenuDefault.menuDefault();
            }
        }

        // RESETA A VERIFICAÇÃO
        verifica = false;

        // MÊS
        int mes = 0;
        while(!verifica) {
            System.out.print("Digite o Mês: ");
            try {
                mes = Ferramentas.lInteiro();
                DataValidator.verificaMes(mes);
                verifica = true;
            } catch (InputMismatchException e) {
                MenuDefault.menuDefault();
            } catch(DataInvalidaException e) {
                Ferramentas.mensagemErro(e.getMessage());
            }
        }

        // RESETA A VERIFICAÇÃO
        verifica = false;

        // DIA
        int dia = 0;
        while(!verifica) {
            System.out.print("Digite Dia: ");
            try {
                dia = Ferramentas.lInteiro();
                DataValidator.verificaDia(ano,mes,dia);
                verifica = true;
            } catch (InputMismatchException e) {
                MenuDefault.menuDefault();
            } catch(DataInvalidaException e) {
                Ferramentas.mensagemErro(e.getMessage());
            }
        }

        // Transforma ano, mês e dia em uma sqlDate
        LocalDate dataNascimento = LocalDate.of(ano, mes, dia);
        Date sqlDate = Date.valueOf(dataNascimento);

        try{
            usuarioValidator.verificarRegrasDataNascimento(sqlDate);
        } catch (DadosInvalidosException e) {
            Ferramentas.mensagemErro(e.getMessage());
        }

        System.out.println(); // pula uma linha

        // RESETA A VERIFICAÇÃO
        verifica = false;

        // Entrada da carga horária semanal
        while (!verifica) {

            System.out.print("Digite a carga horária semanal: ");
            try {
                cargaHoraria = Ferramentas.lInteiro();
                FuncionarioValidator.verificaIntegridadeSalario(cargaHoraria);
                funcionarioValidator.verificaRegrasCargaHoraria(cargaHoraria);
                verifica = true;
            } catch (InputMismatchException e) {
                MenuDefault.menuDefault();
            } catch(DadosInvalidosException e) {
                Ferramentas.mensagemErro(e.getMessage());
            }
        }

        System.out.println(); // pula uma linha

        // RESETA A VERIFICAÇÃO
        verifica = false;

        // Entrada do salário
        while(!verifica) {
            System.out.print("Digite o salário: ");
            try {
                salario = Ferramentas.lDouble();
                FuncionarioValidator.verificaIntegridadeSalario(salario);
                funcionarioValidator.verificaRegrasSalario(salario);
                verifica = true;
            } catch (InputMismatchException e) {
                MenuDefault.menuDefault();
            } catch (DadosInvalidosException e) {
                Ferramentas.mensagemErro(e.getMessage());
            }
        }

        System.out.println(); // pula uma linha

        // RESETA A VERIFICAÇÃO
        verifica = false;

        // Entrada do departamento
        int opDepartamento = 0;
        do{
            System.out.println("Qual é o seu departamento? ");
            System.out.println("1 - FINANCEIRO ");
            System.out.println("2 - INFRAESTRUTURA ");
            System.out.println("3 - MARKETING");
            System.out.println("4 - RH");
            System.out.print("OPÇÃO: ");

            try {
                opDepartamento = Ferramentas.lInteiro();
                if(opDepartamento < 0 || opDepartamento > 4) {
                    MenuDefault.menuDefault();
                } else {
                    verifica = true;
                }
            }catch (InputMismatchException e) {
                MenuDefault.menuDefault();
            }
        } while (!verifica);

        // Converte a entrada de departamento usando switch expression
        Departamento departamento = switch (opDepartamento) {
            case 1 -> Departamento.FINANCEIRO;
            case 2 -> Departamento.INFRAESTRUTURA;
            case 3 -> Departamento.MARKETING;
            default -> Departamento.RH;
        };

        try{
            administradorValidator.verificaRegrasDepartamento(departamento);
        } catch (DadosInvalidosException e) {
            Ferramentas.mensagemErro(e.getMessage());
        }

        Ferramentas.limpaTerminal();
        System.out.println("PROCESSANDO...");
        System.out.println(); // pula uma linha

        // CRIAÇÃO DO OBJETO
        try {
            Administrador administrador = new Administrador(nome, cpf, senha, genero, telefone, email, sqlDate, salario, cargaHoraria, departamento);
            administradorService.inserirAdmin(adm, administrador);
            System.out.println("ADMINISTRADOR CADASTRADO COM SUCESSO!");
            Ferramentas.Delay(1000);
        } catch (DadosInvalidosException | TipoUsuarioException e) {
            System.err.println("FALHA NO CADASTRO!");
            Ferramentas.Delay(1000);
            Ferramentas.mensagemErro(e.getMessage());
        }
    }
}
