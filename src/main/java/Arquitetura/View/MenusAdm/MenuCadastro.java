package Arquitetura.View.MenusAdm;

import Arquitetura.Model.Administrador;
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

        System.out.println("Digite o horário de plantão:    ( Matutino | Vespertino | Noturno )");
        String plantao = Ferramentas.lString();

        System.out.println("Digite a especialidade: ");
        String especialidade = Ferramentas.lString();

        System.out.println("Digite a formação academica: ");
        String formacao = Ferramentas.lString();

        System.out.println("Digite a Sub Especialidade: (Caso houver) ");
        String subE = Ferramentas.lString();

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

        System.out.println("Digite qual o departamento: ");
        String departamento = Ferramentas.lString();

        LocalDate dataNascimento = LocalDate.of(ano, mes, dia);

        Date sqlDate = Date.valueOf(dataNascimento);

        Administrador administrador = new Administrador(nome,cpf,senha,sexo,telefone,email,sqlDate,salario,cargaHoraria,departamento);

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
