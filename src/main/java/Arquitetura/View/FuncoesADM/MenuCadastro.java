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

public class MenuCadastro
{

    public static void CriarMedico(Administrador adm)
    {
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
        int opsex;
        boolean verifica;

        do{

            System.out.println("Digite o seu sexo:");
            System.out.println("1-Masculino");
            System.out.println("2-Feminino");
            opsex =  Ferramentas.lInteiro();

            if(opsex != 1 && opsex != 2)
            {
                verifica = false;

                Ferramentas.limpaTerminal();

                System.out.println("ERRO.  OPÇÂO INVALIDA");
            }
            else
            {
                verifica = true;
            }
        }while (!verifica);

        // Converte a entrada de genero usando switch expression
        Genero genero = switch (opsex){
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
        System.out.println("Digite o Ano: ");
        int ano = Ferramentas.lInteiro();

        System.out.println("Digite o Mês: ");
        int mes = Ferramentas.lInteiro();

        System.out.println("Digite Dia: ");
        int dia = Ferramentas.lInteiro();

        // Entrada da carga horária semanal
        System.out.println("Digite a carga horária semanal: ");
        int cargaHoraria = Ferramentas.lInteiro();

        // Entrada do salário
        System.out.println("Digite o salário: ");
        double salario = Ferramentas.lDouble();

        do{

            // Entrada da especialidade
            System.out.println("Digite a especialidade: ");
            System.out.println("1-CLINICO_GERAL           2-CARDIOLOGISTA    3-RADIOLOGISTA");
            System.out.println("4-OTORRINOLARINGOLOGISTA  5-OFTALMOLOGISTA   6-ENDOCRINOLOGISTA");
            System.out.println("7-HEMATOLOGISTA");
            opsex =  Ferramentas.lInteiro();

            if(opsex != 1 && opsex != 2)
            {
                verifica = false;

                Ferramentas.limpaTerminal();

                System.out.println("ERRO.  OPÇÂO INVALIDA");
            }
            else
            {
                verifica = true;
            }
        }while (!verifica);

        // Converte a entrada de genero usando switch expression
        Especialidade especialidade = switch (opsex){
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
        boolean verificaOp;
        int opPlantao;

        do {
            System.out.println("Qual é o seu plantão? ");
            System.out.println("1 - MATUTINO ");
            System.out.println("2 - VESPERTINO ");
            System.out.println("3 - NORTURNO");
            System.out.print("Opção: ");
            opPlantao = Ferramentas.lInteiro();

            if(opPlantao != 1 && opPlantao != 2 && opPlantao != 3) {
                verificaOp = false;

                Ferramentas.limpaTerminal();

                System.out.println("ERRO! OPÇÃO INVÁLIDA \n");
            } else {
                verificaOp = true;
            }
        } while(!verificaOp);

        Plantao plantao = switch(opPlantao) {
            case 1 -> Plantao.MATUTINO;
            case 2 -> Plantao.VERPERTINO;
            default -> Plantao.NOTURNO;
        };

        LocalDate dataNascimento = LocalDate.of(ano, mes, dia);

        Date sqlDate = Date.valueOf(dataNascimento);

        // Cria médico com subespecialidade
        if(subE.isEmpty()) {

            Medico medico = new Medico(nome, cpf, senha, genero, telefone, email, sqlDate, cargaHoraria, salario, plantao, especialidade, formacao);

            MedicoService medicoService = new MedicoService();

            medicoService.inserirMedico(adm,medico);

            System.out.println("Medico criado");

            Ferramentas.Delay(1500);

            // Cria médico sem subespecialidade
            } else {

            Medico medico = new Medico(nome,cpf,senha,genero,telefone,email,sqlDate,cargaHoraria,salario,plantao,especialidade,formacao,subE);

            MedicoService medicoService = new MedicoService();

            medicoService.inserirMedico(adm,medico);

            System.out.println("Medico criado");

            Ferramentas.Delay(1500);
        }
    }

    public static void CriarPaciente(Administrador adm)
    {
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
        int opsex;
        boolean verifica;

        do{

            System.out.println("Digite o seu sexo:");
            System.out.println("1-Masculino");
            System.out.println("2-Feminino");
            opsex =  Ferramentas.lInteiro();

            if(opsex != 1 && opsex != 2)
            {
                verifica = false;

                Ferramentas.limpaTerminal();

                System.out.println("ERRO!  OPÇÂO INVALIDA");
            }
            else
            {
                verifica = true;
            }
        }while (!verifica);

        // Converte a entrada de genero usando switch expression
        Genero genero = switch (opsex){
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
        System.out.println("Digite o Ano: ");
        int ano = Ferramentas.lInteiro();

        System.out.println("Digite o Mês: ");
        int mes = Ferramentas.lInteiro();

        System.out.println("Digite Dia: ");
        int dia = Ferramentas.lInteiro();

        // Entrada do contato de emergência
        System.out.println("Digite o número do contato de emergência: ");
        String contatoEmer = Ferramentas.lString();

        // Entrada do número da carteirinha
        System.out.println("Digite o número da carteirinha: ");
        String numeroCar = Ferramentas.lString();

        LocalDate dataNascimento = LocalDate.of(ano, mes, dia);

        Date sqlDate = Date.valueOf(dataNascimento);

        Paciente paciente = new Paciente(nome,cpf,senha,genero,telefone,email,sqlDate, contatoEmer, contatoEmer);

        PacienteService pacienteService = new PacienteService();

        pacienteService.inserirPaciente(adm,paciente);

        System.out.println("Paciente criado");

        Ferramentas.Delay(1500);
    }

    public static void CriarADM(Administrador adm)
    {
        Ferramentas.limpaTerminal();

        System.out.println("     --------------------------");
        System.out.println("     ----    Cadastro ADM  ----");
        System.out.println("     --------------------------");

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
        int opsex;
        boolean verifica;

        do{

            System.out.println("Digite o seu sexo:");
            System.out.println("1-Masculino");
            System.out.println("2-Feminino");
            opsex =  Ferramentas.lInteiro();

            if(opsex != 1 && opsex != 2)
            {
                verifica = false;

                Ferramentas.limpaTerminal();

                System.out.println("ERRO.  OPÇÂO INVALIDA");
            }
            else
            {
                verifica = true;
            }
        }while (!verifica);

        // Converte a entrada de genero usando switch expression
        Genero genero = switch (opsex){
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
        System.out.println("Digite o Ano: ");
        int ano = Ferramentas.lInteiro();

        System.out.println("Digite o Mês: ");
        int mes = Ferramentas.lInteiro();

        System.out.println("Digite Dia: ");
        int dia = Ferramentas.lInteiro();

        // Entrada da carga horária semanal
        System.out.println("Digite a carga horária semanal: ");
        int cargaHoraria = Ferramentas.lInteiro();

        System.out.println("Digite o salário: ");
        double salario = Ferramentas.lDouble();

        // Entrada do departamento
        boolean verificaOp;
        int opDepartamento;

        do {
            System.out.println("Qual é o seu departamento? ");
            System.out.println("1 - FINANCEIRO ");
            System.out.println("2 - INFRAESTRUTURA ");
            System.out.println("3 - MARKETING");
            System.out.println("4 - RH");
            opDepartamento = Ferramentas.lInteiro();

            if(opDepartamento != 1 && opDepartamento != 2 && opDepartamento != 3 && opDepartamento != 4) {
                verificaOp = false;

                MenuDefault.menuDefault();

            } else {
                verificaOp = true;
            }
        } while(!verificaOp);

        // Converte a entrada de departamento usando switch expression
        Departamento departamento = switch(opDepartamento) {
            case 1 -> Departamento.FINANCEIRO;
            case 2 -> Departamento.INFRAESTRUTURA;
            case 3 -> Departamento.MARKETING;
            default -> Departamento.RH;
        };

        LocalDate dataNascimento = LocalDate.of(ano, mes, dia);

        Date sqlDate = Date.valueOf(dataNascimento);

        Administrador administrador = new Administrador(nome,cpf,senha,genero,telefone,email,sqlDate,salario,cargaHoraria, departamento);

        AdministradorService administradorService =  new AdministradorService();

        administradorService.inserirAdmin(adm,administrador);

        // Cadastro falhou
        if(administrador.getId() == 0) {
            System.out.println("    Erro, dados invalidos, por favor tente novamente.");
        }

        // Cadastro funcinou
        else {
            System.out.println("Adm criado");
        }

        Ferramentas.Delay(1500);
    }
}
