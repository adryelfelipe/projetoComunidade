package Arquitetura.View.MenusAdm;

import Arquitetura.Model.Administrador;
import Arquitetura.Model.Enums.Departamento;
import Arquitetura.Model.Enums.Plantao;
import Arquitetura.Model.Medico;
import Arquitetura.Model.Paciente;
import Arquitetura.Service.AdministradorService;
import Arquitetura.Service.MedicoService;
import Arquitetura.Service.PacienteService;
import Arquitetura.Utilidades.Ferramentas;

import java.sql.Date;
import java.time.LocalDate;

public class MenuCadastro
{

    public static void CriarMedico(Administrador adm)
    {
        Ferramentas.limpaTerminal();

        System.out.println("     --------------------------");
        System.out.println("     ----    Cadastro Médico  ----");
        System.out.println("     --------------------------");

        System.out.println("\n\n\nDigite o nome: ");
        String nome = Ferramentas.lString();

        System.out.println("Digite o CPF: ");
        String cpf = Ferramentas.lString();

        System.out.println("Digite a senha: ");
        String senha = Ferramentas.lString();

        System.out.println("Digite o sexo:  ( M/F )");
        String sexo = Ferramentas.lString();

        System.out.println("Digite o número de telefone: ");
        String telefone = Ferramentas.lString();

        System.out.println("Digite o email: ");
        String email = Ferramentas.lString();

        System.out.println("Data ");
        System.out.println("Digite o Ano: ");
        int ano = Ferramentas.lInteiro();

        System.out.println("Digite o Mês: ");
        int mes = Ferramentas.lInteiro();

        System.out.println("Digite Dia: ");
        int dia = Ferramentas.lInteiro();

        System.out.println("Digite a carga horária semanal: ");
        int cargaHoraria = Ferramentas.lInteiro();

        System.out.println("Digite o salário: ");
        double salario = Ferramentas.lDouble();

        System.out.println("Digite a especialidade: ");
        String especialidade = Ferramentas.lString();

        System.out.println("Digite a formação academica: ");
        String formacao = Ferramentas.lString();

        System.out.println("Digite a Sub Especialidade: (Caso houver) ");
        String subE = Ferramentas.lString();

        // Atributo plantao
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

        if(subE.isEmpty()) {

            Medico medico = new Medico(nome, cpf, senha, sexo, telefone, email, sqlDate, cargaHoraria, salario, plantao, especialidade, formacao);

            MedicoService medicoService = new MedicoService();

                medicoService.inserirMedico(adm,medico);

                System.out.println("Medico criado");

                Ferramentas.Delay(1500);

            } else {

                Medico medico = new Medico(nome,cpf,senha,sexo,telefone,email,sqlDate,cargaHoraria,salario,plantao,especialidade,formacao,subE);

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


        System.out.println("\n\n\nDigite o nome: ");
        String nome = Ferramentas.lString();

        System.out.println("Digite o CPF: ");
        String cpf = Ferramentas.lString();

        System.out.println("Digite a senha: ");
        String senha = Ferramentas.lString();

        String sexo = "Null";

        boolean continuar = true;

        while (continuar) {

            System.out.println("Digite o sexo:  ( M/F )");
            sexo = Ferramentas.lString();

            if (!(sexo.equals("M") || sexo.equals("F"))) {

            }
            else
            {
                continuar = false;
            }
        }

        System.out.println("Digite o número de telefone: ");
        String telefone = Ferramentas.lString();

        System.out.println("Digite o email: ");
        String email = Ferramentas.lString();

        System.out.println("Data ");
        System.out.println("Digite o Ano: ");
        int ano = Ferramentas.lInteiro();

        System.out.println("Digite o Mês: ");
        int mes = Ferramentas.lInteiro();

        System.out.println("Digite Dia: ");
        int dia = Ferramentas.lInteiro();

        System.out.println("Digite o número do contato de emergência: ");
        String contatoEmer = Ferramentas.lString();

        System.out.println("Digite o número da carteirinha: ");
        String numeroCar = Ferramentas.lString();

        LocalDate dataNascimento = LocalDate.of(ano, mes, dia);

        Date sqlDate = Date.valueOf(dataNascimento);

        Paciente paciente = new Paciente(nome,cpf,senha,sexo,telefone,email,sqlDate, contatoEmer, contatoEmer);

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

        System.out.println("\n\n\nDigite o nome: ");
        String nome = Ferramentas.lString();

        System.out.println("Digite o CPF: ");
        String cpf = Ferramentas.lString();

        System.out.println("Digite a senha: ");
        String senha = Ferramentas.lString();

        System.out.println("Digite o sexo:  ( M/F )");
        String sexo = Ferramentas.lString();

        System.out.println("Digite o número de telefone: ");
        String telefone = Ferramentas.lString();

        System.out.println("Digite o email: ");
        String email = Ferramentas.lString();

        System.out.println("Data ");
        System.out.println("Digite o Ano: ");
        int ano = Ferramentas.lInteiro();

        System.out.println("Digite o Mês: ");
        int mes = Ferramentas.lInteiro();

        System.out.println("Digite Dia: ");
        int dia = Ferramentas.lInteiro();

        System.out.println("Digite a carga horária semanal: ");
        int cargaHoraria = Ferramentas.lInteiro();

        System.out.println("Digite o salário: ");
        double salario = Ferramentas.lDouble();

        System.out.println("Qual é o seu departamento? ");
        System.out.println("1 - FINANCEIRO ");
        System.out.println("2 - INFRAESTRUTURA ");
        System.out.println("3 - MARKETING");
        System.out.println("4 - RH");
        int opDepartamento = Ferramentas.lInteiro();

        Departamento departamento = null;

        switch(opDepartamento) {

            case 1 -> {
                departamento = Departamento.FINANCEIRO;
            }

            case 2 -> {
                departamento = Departamento.INFRAESTRUTURA;
            }

            case 3 -> {
                departamento = Departamento.MARKETING;
            }

            case 4 -> {
                departamento = Departamento.RH;
            }
        }

        LocalDate dataNascimento = LocalDate.of(ano, mes, dia);

        Date sqlDate = Date.valueOf(dataNascimento);

        Administrador administrador = new Administrador(nome,cpf,senha,sexo,telefone,email,sqlDate,salario,cargaHoraria, departamento);

        AdministradorService administradorService =  new AdministradorService();

        administradorService.inserirAdmin(adm,administrador);

        if(administrador.getId() == 0) {
            System.out.println("    Erro, dados invalidos, por favor tente novamente.");
        }
        else {
            System.out.println("Adm criado");
        }

        Ferramentas.Delay(1500);

    }
}
