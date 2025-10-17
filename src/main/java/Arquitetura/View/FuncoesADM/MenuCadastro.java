package Arquitetura.View.FuncoesADM;

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
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.MenuDefault;

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

                    System.out.println("ERRO.  OPÇÂO INVALIDA");
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
                e.getMessage();
                continuar = false;
            }

            int mes = 0;
            System.out.println("Digite o Mês: ");
            try {
                mes = Ferramentas.lInteiro();
            } catch (InputMismatchException e) {
                e.getMessage();
                continuar = false;
            }


            int dia = 0;
            System.out.println("Digite Dia: ");
            try {
                dia = Ferramentas.lInteiro();
            } catch (InputMismatchException e) {
                e.getMessage();
                continuar = false;
            }


            int cargaHoraria = 0;

            // Entrada da carga horária semanal
            System.out.println("Digite a carga horária semanal: ");
            try {
                cargaHoraria = Ferramentas.lInteiro();
            }catch (InputMismatchException e)
            {
                e.getMessage();
            }
            double salario = 0;

            // Entrada do salário
            System.out.println("Digite o salário: ");
            try {
                salario = Ferramentas.lDouble();
            } catch (InputMismatchException e) {
                e.getMessage();
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

                    System.out.println("ERRO.  OPÇÂO INVALIDA");

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

                    System.out.println("ERRO! OPÇÃO INVÁLIDA \n");

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
                    e.getMessage();
                }

            } else {

                try {
                    Medico medico = new Medico(nome, cpf, senha, genero, telefone, email, sqlDate, cargaHoraria, salario, plantao, especialidade, formacao, subE);

                    MedicoService medicoService = new MedicoService();

                    medicoService.inserirMedico(adm, medico);

                    System.out.println("Medico criado");

                    Ferramentas.Delay(1500);

                } catch (IllegalArgumentException e) {
                    e.getMessage();
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

                    System.out.println("ERRO.  OPÇÂO INVALIDA");
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
                e.getMessage();
                continuar = false;
            }

            int mes = 0;
            System.out.println("Digite o Mês: ");
            try {
                mes = Ferramentas.lInteiro();
            } catch (InputMismatchException e) {
                e.getMessage();
                continuar = false;
            }


            int dia = 0;
            System.out.println("Digite Dia: ");
            try {
                dia = Ferramentas.lInteiro();
            } catch (InputMismatchException e) {
                e.getMessage();
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
                e.getMessage();
            }

        }while (!continuar);
    }

    public static void CriarADM(Administrador adm) {
        boolean continuar = true;

        do {

            Ferramentas.limpaTerminal();

            System.out.println("     -----------------------");
            System.out.println("     ----    Criar ADM  ----");
            System.out.println("     -----------------------");

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

                    System.out.println("ERRO.  OPÇÂO INVALIDA");
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
                e.getMessage();
                continuar = false;
            }

            int mes = 0;
            System.out.println("Digite o Mês: ");
            try {
                mes = Ferramentas.lInteiro();
            } catch (InputMismatchException e) {
                e.getMessage();
                continuar = false;
            }


            int dia = 0;
            System.out.println("Digite Dia: ");
            try {
                dia = Ferramentas.lInteiro();
            } catch (InputMismatchException e) {
                e.getMessage();
                continuar = false;
            }

            int cargaHoraria = 0;

            // Entrada da carga horária semanal
            System.out.println("Digite a carga horária semanal: ");
            try {
                cargaHoraria = Ferramentas.lInteiro();
            }catch (InputMismatchException e)
            {
                e.getMessage();
            }
            double salario = 0;

            // Entrada do salário
            System.out.println("Digite o salário: ");
            try {
                salario = Ferramentas.lDouble();
            } catch (InputMismatchException e) {
                e.getMessage();
                continuar = false;
            }


            // Entrada do departamento
            boolean verificOp = true;
            int opDepartamento = 0;

            do{
                System.out.println("Qual é o seu departamento? ");
                System.out.println("1 - FINANCEIRO ");
                System.out.println("2 - INFRAESTRUTURA ");
                System.out.println("3 - MARKETING");
                System.out.println("4 - RH");

                try {
                    opDepartamento = Ferramentas.lInteiro();

                    verificOp = false;

                    MenuDefault.menuDefault();

                }catch (InputMismatchException e) {
                    verificOp = true;

                    System.out.println("ERRO.  OPÇÂO INVALIDA");
                }

            } while (!verificOp);

            // Converte a entrada de departamento usando switch expression
            Departamento departamento = switch (opDepartamento) {
                case 1 -> Departamento.FINANCEIRO;
                case 2 -> Departamento.INFRAESTRUTURA;
                case 3 -> Departamento.MARKETING;
                default -> Departamento.RH;
            };

            try {

                LocalDate dataNascimento = LocalDate.of(ano, mes, dia);

                Date sqlDate = Date.valueOf(dataNascimento);

                Administrador administrador = new Administrador(nome, cpf, senha, genero, telefone, email, sqlDate, salario, cargaHoraria, departamento);

                AdministradorService administradorService = new AdministradorService();

                administradorService.inserirAdmin(adm, administrador);

                Ferramentas.Delay(1500);
            } catch (IllegalArgumentException e) {
                e.getMessage();
            }
        }while (!continuar);
    }
}
