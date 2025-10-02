package Arquitetura.View;

import Arquitetura.Model.Administrador;
import Arquitetura.Model.Medico;
import Arquitetura.Model.Paciente;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.AdministradorService;
import Arquitetura.Service.MedicoService;
import Arquitetura.Service.PacienteService;
import Arquitetura.Utilidades.Ferramentas;

import java.sql.Date;
import java.sql.SQLOutput;
import java.time.LocalDate;

public class MenuAdministrador
{


    public static void Menu(Administrador adm)
    {

        boolean continuar = true;

        while (continuar) {
            Ferramentas.limpaTerminal();
            System.out.println("           ---------------");
            System.out.println("           |     MENU    |" );
            System.out.println("           ---------------\n\n");
            System.out.println("----------------------");
            System.out.println("|  Digite:           |");
            System.out.println("|--------------------|");
            System.out.println("| 1-Listar Usuários  |");
            System.out.println("| 2-Editar Usuários  |");
            System.out.println("| 3-Excluir Usuários |");
            System.out.println("| 4-Gerar Relatórios |");
            System.out.println("| 5-Disponibilidade  |");
            System.out.println("| 6-Cadastro adm     |");
            System.out.println("| 7-Cadastro Medico  |");
            System.out.println("| 8-Cadastro Paciente|");
            System.out.println("| 9-Sair             |");
            System.out.println("----------------------");

            int op = Ferramentas.lInteiro();

            switch (op) {
                case 1: {

                    Ferramentas.limpaTerminal();
                    System.out.println("          --------------");
                    System.out.println("          |  USUÁRIOS  |");
                    System.out.println("          --------------");

                    MenuAdministrador.ListarMedico(adm);

                    MenuAdministrador.ListarPaciente(adm);

                    System.out.println("\n\n\n---------------------");
                    System.out.println("Digite para continuar");
                    System.out.println("---------------------");
                    String tempo = Ferramentas.lString();

                    break;
                }
                case 2: {

                    Ferramentas.limpaTerminal();

                    MenuAdministrador.Editar(adm);

                    break;
                }
                case 3: {

                    Ferramentas.limpaTerminal();

                    MenuAdministrador.ExcluirUsuario(adm);

                    break;
                }
                case 4: {

                    Ferramentas.limpaTerminal();

                    MenuAdministrador.GerarRelatorios(adm);

                    break;
                }
                case 5:
                {

                    Ferramentas.limpaTerminal();

                    MenuAdministrador.Disponibilidade(adm);

                    break;
                }
                case 6:
                {

                    Ferramentas.limpaTerminal();

                    MenuAdministrador.CriarAdm(adm);

                    break;
                }
                case 7:
                {

                    Ferramentas.limpaTerminal();

                    MenuAdministrador.cadastroMedico(adm);

                    break;
                }
                case 8:
                {

                    Ferramentas.limpaTerminal();

                    MenuAdministrador.cadastroPaciente(adm);

                    break;
                }
                case 9: {

                    continuar = false;

                    break;
                }
                default: {

                    System.out.println("\n\n\n\n-------------------------");
                    System.out.println("Valor digitado incorreto!");
                    System.out.println("-------------------------");

                    Ferramentas.Delay(2000);

                    break;
                }
            }
        }

        MenuInicial.Menu();
    }

    public static void CriarAdm(Administrador adm)
    {
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

        LocalDate dataNascimento = LocalDate.of(ano, mes, dia);

        Date sqlDate = Date.valueOf(dataNascimento);
//
//        Administrador administrador = new Administrador(nome,cpf,senha,sexo,telefone,email,sqlDate,salario,cargaHoraria);
//
//        AdministradorService administradorService =  new AdministradorService();
//
//        administradorService.inserirAdmin(administrador);

        System.out.println("Adm criado");

        Ferramentas.Delay(1500);
    }

    public static void cadastroMedico(Administrador adm) {
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

            Medico medico = new Medico(nome,cpf,senha,sexo,telefone,email,sqlDate,cargaHoraria,salario,plantao,especialidade,formacao);

            MedicoService medicoService = new MedicoService();
//
//            medicoService.inserirMedico(medico);
//
//            System.out.println("Medico criado");
//
//            Ferramentas.Delay(1500);
//
//        } else {
//
//            Medico medico = new Medico(nome,cpf,senha,sexo,telefone,email,sqlDate,cargaHoraria,salario,plantao,especialidade,formacao,subE);
//
//            MedicoService medicoService = new MedicoService();
//
//            medicoService.inserirMedico(medico);

            System.out.println("Medico criado");

            Ferramentas.Delay(1500);

        }
    }

    public static void cadastroPaciente(Administrador adm) {

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

//        pacienteService.inserirPaciente(paciente);

        System.out.println("Paciente criado");

        Ferramentas.Delay(1500);
    }

    public static void ListarMedico(Administrador adm)
    {

        int cont = 1;
        System.out.println("          -------------");
        System.out.println("          |  Médicos  |");
        System.out.println("          -------------");

        //for (Usuario entrada: USUARIOS)
        //{
        //    if(entrada instanceof Medico)
        //    {
        //        System.out.println("\n\n   Medico " + cont);
        //        System.out.println(entrada.getNome());
        //        System.out.println(entrada.getCpf());
        //        System.out.println(entrada.getEmail());
        //        System.out.println(entrada.getId());
        //        System.out.println(entrada.getDataNascimento());
//
        //        cont ++;
        //    }
        //}

    }

    public static void ListarPaciente(Administrador adm)
    {

        System.out.println("\n\n  -Pacientes-\n\n");

        int cont = 1;

        System.out.println("\n\n  -Médicos-\n\n");

        //for (Usuario entrada: USUARIOS)
        //{
        //    if(entrada instanceof Paciente)
        //    {
        //        System.out.println("\n\n   Paciente " + cont);
        //        System.out.println(entrada.getNome());
        //        System.out.println(entrada.getCpf());
        //        System.out.println(entrada.getEmail());
        //        System.out.println(entrada.getId());
        //        System.out.println(entrada.getDataNascimento());
//
        //        cont ++;
        //    }
        //}

    }

    public static void ExcluirUsuario(Administrador adm)
    {

        Ferramentas.limpaTerminal();

        System.out.println("     EXCLUIR");
        System.out.println("\n\nDigite o cpf do usuario: ");
        String cpf = Ferramentas.lString();

       //for(Usuario entrada: USUARIOS)
       //{
       //    if (entrada.getCpf().equals(cpf))
       //    {
       //        if(entrada instanceof Administrador)
       //        {
       //            System.out.println("Administradores não podem ser excluidos!");
       //        }

       //        EXCLUIRUSUARO;

       //        System.out.println("Usuario excluido");


       //        System.out.println("\n\nDigite para continuar");
       //        String tempo = Ferramentas.lString();

       //        return;
       //    }
       //}

        System.out.println("Usuario não encontrado!");

        System.out.println("\n\nDigite para continuar");
        String tempo = Ferramentas.lString();

    }

    public static void GerarRelatorios(Administrador adm)
    {

        Ferramentas.limpaTerminal();

        System.out.println("       Relatório");
        System.out.println("\n\nDigite: ");
        System.out.println("1-Médico");
        System.out.println("2-Paciente");
        System.out.println("3-Periodo");
        int escolha = Ferramentas.lInteiro();

        switch (escolha)
        {
            case 1:
            {
                System.out.println("Digite o cpf do médico");

            }
            case 2:
            {

            }
            case 3:
            {

            }
            default:
            {

            }
        }

    }

    public static void Disponibilidade(Administrador adm)
    {

        Ferramentas.limpaTerminal();

        System.out.println("     Agenda Médico");
        System.out.println("\n\nDigite o cpf do medico: ");
        String cpf = Ferramentas.lString();

        //for(Usuario entrada: USUARIOS)
        //{
        //    if (entrada.getCpf().equals(cpf))
        //    {
        //
        //
        //    }
        //}

        System.out.println("Médico não encontrado!");

        System.out.println("\n\nDigite para continuar");
        String tempo = Ferramentas.lString();


    }

    public static void Editar(Administrador adm)
    {
        System.out.println("        EDITAR ");
        System.out.println("\n\nDigite o CPF: ");
        String cpf = Ferramentas.lString();

        //for(Usuario entrada: USUARIOS)
        //{
        //    if(entrada.getCpf().equals(cpf))
        //    {
        //        if(entrada instanceof Medico)
        //        {
        //            entrada = (Medico) entrada;
        //
        //        }
        //        else if(entrada instanceof Paciente)
        //        {
        //            entrada = (Paciente) entrada;
        //
        //
        //        }
        //        else if(entrada instanceof Administrador)
        //        {
        //            System.out.println("\n\nEste usuário é um adm, não pode ser alterado!");
//
        //            System.out.println("Digite para continuar");
        //            String tempo = Ferramentas.lString();
        //
        //
        //        }
        //
        //    }
        //}

        System.out.println("\n\nUsuário não encontrado!");

        System.out.println("\n\nDigite para continuar");
        String tempo = Ferramentas.lString();
    }

}
