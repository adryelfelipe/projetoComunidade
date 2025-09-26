package Arquitetura.View;

import Arquitetura.Model.Medico;
import Arquitetura.Model.Paciente;
import Arquitetura.Utilidades.Ferramentas;

import java.sql.Date;
import java.time.LocalDate;

public class MenuAdministrador
{

    public static Medico cadastroMedico()
    {
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

    public static Paciente cadastroPaciente()
    {

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

        System.out.println("Digite o número do contato de emergência: ");
        String contatoEmer = Ferramentas.lString();

        System.out.println("Digite o número da carteirinha: ");
        String numeroCar = Ferramentas.lString();

        LocalDate dataNascimento = LocalDate.of(ano, mes, dia);

        Date sqlDate = Date.valueOf(dataNascimento);

        return new Paciente(nome,cpf,senha,sexo,telefone,email,sqlDate, contatoEmer, contatoEmer);
    }







}
