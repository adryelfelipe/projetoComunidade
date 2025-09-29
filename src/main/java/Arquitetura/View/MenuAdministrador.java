package Arquitetura.View;

import Arquitetura.Model.Administrador;
import Arquitetura.Model.Medico;
import Arquitetura.Model.Paciente;
import Arquitetura.Model.Usuario;
import Arquitetura.Utilidades.Ferramentas;

import java.sql.Date;
import java.time.LocalDate;

public class MenuAdministrador
{

    public static Medico cadastroMedico() {
        Ferramentas.limpaTerminal();

        System.out.println("       CADASTRO\n\n\n");
        System.out.println("Digite o nome: ");
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

            return new Medico(nome,cpf,senha,salario,cargaHoraria,plantao,especialidade,formacao,sexo,telefone,email,sqlDate);

        } else {

            return new Medico(nome,cpf,senha,salario,cargaHoraria,plantao,especialidade,formacao,subE,sexo,telefone,email,sqlDate);
        }
    }

    public static Paciente cadastroPaciente() {

        Ferramentas.limpaTerminal();

        System.out.println("       CADASTRO\n\n\n");
        System.out.println("Digite o nome: ");
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

        return new Paciente(nome,cpf,senha,sexo,telefone,email,sqlDate, contatoEmer, contatoEmer);
    }

    public static void Menu()
    {

        boolean continuar = true;

        while (continuar) {
            Ferramentas.limpaTerminal();

            System.out.println("        MENU ");
            System.out.println("\n\nDigite:");
            System.out.println("1-Listar Usuários");
            System.out.println("2-Editar Usuários");
            System.out.println("3-Excluir Usuários");
            System.out.println("4-Gerar Relatórios");
            System.out.println("5-Disponibilidade de Médicos");
            System.out.println("6-Sair");
            int op = Ferramentas.lInteiro();

            switch (op) {
                case 1: {

                    Ferramentas.limpaTerminal();

                    System.out.println("             USUÁRIOS");

                    MenuAdministrador.ListarMedico();

                    MenuAdministrador.ListarPaciente();

                    System.out.println("Digite para continuar");
                    String tempo = Ferramentas.lString();

                    break;
                }
                case 2: {

                    Ferramentas.limpaTerminal();

                    MenuAdministrador.Editar();

                    break;
                }
                case 3: {

                    Ferramentas.limpaTerminal();

                    MenuAdministrador.ExcluirUsuario();

                    break;
                }
                case 4: {

                    Ferramentas.limpaTerminal();

                    MenuAdministrador.GerarRelatorios();

                    break;
                }
                case 5:
                {

                    Ferramentas.limpaTerminal();

                    MenuAdministrador.Disponibilidade();

                    break;
                }
                case 6: {

                    continuar = false;

                    break;
                }
                default: {
                    System.out.println("Valor digitado incorreto!");

                    Ferramentas.Delay(2000);

                    break;
                }
            }
        }
    }

    public static void ListarMedico()
    {

        System.out.println("\n\n  -Médicos-\n\n");

        //for ()
        //{
        //
        //}

    }

    public static void ListarPaciente()
    {

        System.out.println("\n\n  -Pacientes-\n\n");

        //for ()
        //{
        //
        //}

    }

    public static void ExcluirUsuario()
    {

        Ferramentas.limpaTerminal();

        System.out.println("     EXCLUIR");
        System.out.println("\n\nDigite o cpf do usuario: ");
        String cpf = Ferramentas.lString();

        //for(Usuario entrada: USUARIOS)
        //{
        //    if (entrada.getCpf().equals(cpf))
        //    {
        //
        //
        //    }
        //}

        System.out.println("Usuario não encontrado!");

        System.out.println("\n\nDigite para continuar");
        String tempo = Ferramentas.lString();


    }

    public static void GerarRelatorios()
    {

        Ferramentas.limpaTerminal();


    }

    public static void Disponibilidade()
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

        Menu();

    }

    public static void Editar()
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
