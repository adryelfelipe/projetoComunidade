package Arquitetura.View.FuncoesADM;
import Arquitetura.Exception.*;
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
import Arquitetura.View.FuncoesMedico.MenuSetMedico;
import Arquitetura.View.FuncoesPACIENTE.MenuSetPaciente;
import Arquitetura.View.MenuUsuarios.MenuSetFuncionario;
import Arquitetura.View.MenuUsuarios.MenuSetUsuario;

public class MenuCadastro
{

    public static void CriarMedico(Administrador adm)
    {

        // Validadores de regras de negócio
        MedicoService medicoService = new MedicoService();

        // Variaveis Gerais
        String nome;
        String cpf;
        String senha;
        String telefone;
        String email;
        Genero genero;
        java.sql.Date sqlDate;

        //Medico
        int cargaHoraria;
        double salario;
        String subE;
        String formacao;
        Especialidade especialidade;
        Plantao plantao;

        Ferramentas.limpaTerminal();
        System.out.println("     -----------------------------");
        System.out.println("     ----    Cadastro Médico  ----");
        System.out.println("     -----------------------------");

        // Entrada do nome
        nome = MenuSetUsuario.SetNome();

        System.out.println(); // pula uma linha

        // Entrada do CPF
        cpf = MenuSetUsuario.SetCpf();

        System.out.println(); // pula uma linha

        // Entrada da senha
        senha = MenuSetUsuario.SetSenha();

        System.out.println(); // pula uma linha

        // Entrada do sexo
        genero = MenuSetUsuario.SetSexo();

        System.out.println(); // pula uma linha

        // Entrada do telefone
        telefone = MenuSetUsuario.SetTelefone();

        System.out.println(); // pula uma linha

        // Entrada do email
        email = MenuSetUsuario.SetEmail();

        System.out.println(); // pula uma linha

        // Entrada da data de nascimento
        sqlDate = MenuSetUsuario.SetDataNascimento();

        System.out.println(); // pula uma linha

        // Entrada da carga horária semanal
        cargaHoraria = MenuSetFuncionario.SetCargahoraria();

        System.out.println(); // pula uma linha

        // Entrada do salário
        salario = MenuSetFuncionario.SetSalario();

        System.out.println(); // pula uma linha

        // Entrada da especialidade
        especialidade = MenuSetMedico.SetEspecialidade();

        System.out.println(); // pula uma linha

        // Entrada da formação acadêmica
        formacao = MenuSetMedico.SetFormacao();

        System.out.println(); // pula uma linha

        // Entrada da sub especialidade (caso houver)
        subE = MenuSetMedico.SetSubEspecialidade();

        System.out.println(); // pula uma linha

        // Entrada do plantão
        plantao = MenuSetMedico.SetPlantao();

        Ferramentas.limpaTerminal();
        System.out.println("PROCESSANDO...");
        System.out.println(); // pula uma linha

        // Cria médico com subespecialidade
        try {
            if (subE == null || subE.isBlank()) {
                Medico medico = new Medico(nome, cpf, senha, genero, telefone, email, sqlDate, cargaHoraria, salario, plantao, especialidade, formacao);
                medicoService.inserirMedico(adm, medico);;
            } else {
                Medico medico = new Medico(nome, cpf, senha, genero, telefone, email, sqlDate, cargaHoraria, salario, plantao, especialidade, formacao, subE);
                medicoService.inserirMedico(adm, medico);
            }

            System.out.println("Medico criado");
            Ferramentas.Delay(1500);
        } catch (DadosInvalidosException | TipoUsuarioException | CpfInvalidoException | EmailInvalidoException e) {
            System.err.println("FALHA NO CADASTRO!");
            Ferramentas.Delay(1000);
            Ferramentas.mensagemErro(e.getMessage());
        }
    }

    public static void CriarPaciente(Administrador adm)
    {
        // Validadores de regras de negócio
        PacienteService pacienteService = new PacienteService();

        // Variaveis Gerais
        String nome;
        String cpf;
        String senha;
        String telefone;
        String email;
        Genero genero;
        java.sql.Date sqlDate;

        // Paciente
        String contatoEmer;
        String numeroCar;


        Ferramentas.limpaTerminal();
        System.out.println("     -----------------------------");
        System.out.println("     ----  Cadastro Paciente  ----");
        System.out.println("     -----------------------------");

        // Entrada do nome
        nome = MenuSetUsuario.SetNome();

        System.out.println(); // pula uma linha

        // Entrada do CPF
        cpf = MenuSetUsuario.SetCpf();

        System.out.println(); // pula uma linha

        // Entrada da senha
        senha = MenuSetUsuario.SetSenha();

        System.out.println(); // pula uma linha

        // Entrada do sexo
        genero = MenuSetUsuario.SetSexo();

        System.out.println(); // pula uma linha

        // Entrada do telefone
        telefone = MenuSetUsuario.SetTelefone();

        System.out.println(); // pula uma linha

        // Entrada do email
        email = MenuSetUsuario.SetEmail();

        System.out.println(); // pula uma linha

        // Entrada da data de nascimento
        sqlDate = MenuSetUsuario.SetDataNascimento();

        System.out.println(); // pula uma linha

        //Contato de emergência
        contatoEmer = MenuSetPaciente.SetContatoEmergencia();

        System.out.println(); // pula uma linha

        //Numero carteirinha
        numeroCar = MenuSetPaciente.SetNumeroCadastro();

        System.out.println(); // pula uma linha

        Ferramentas.limpaTerminal();
        System.out.println("PROCESSANDO...");
        System.out.println(); // pula uma linha

        try {
            Paciente paciente = new Paciente(nome, cpf, senha, genero, telefone, email, sqlDate, contatoEmer, numeroCar);
            pacienteService.inserirPaciente(adm, paciente);
            System.out.println("Paciente criado");
            Ferramentas.Delay(1500);
        } catch (DadosInvalidosException | NumCadastroInvalidoException | TipoUsuarioException | CpfInvalidoException | EmailInvalidoException e) {
            System.err.println("FALHA NO CADASTRO!");
            Ferramentas.Delay(1000);
            Ferramentas.mensagemErro(e.getMessage());
        }
        
    }

    public static void CriarADM(Administrador adm) {
        Ferramentas.limpaTerminal();

        // Validadores de regras de negócio
        AdministradorService administradorService = new AdministradorService();

        // Variaveis Gerais
        String nome;
        String cpf;
        String senha;
        String telefone;
        String email;
        Genero genero;
        java.sql.Date sqlDate;

        //ADM
        Departamento departamento;
        int cargaHoraria;
        double salario;

        // Menu
        System.out.println("     -----------------------");
        System.out.println("     ----    Criar ADM  ----");
        System.out.println("     -----------------------");

        // Entrada do nome
        nome = MenuSetUsuario.SetNome();

        System.out.println(); // pula uma linha

        // Entrada do CPF
        cpf = MenuSetUsuario.SetCpf();

        System.out.println(); // pula uma linha

        // Entrada da senha
        senha = MenuSetUsuario.SetSenha();

        System.out.println(); // pula uma linha

        // Entrada do sexo
        genero = MenuSetUsuario.SetSexo();

        System.out.println(); // pula uma linha

        // Entrada do telefone
        telefone = MenuSetUsuario.SetTelefone();

        System.out.println(); // pula uma linha

        // Entrada do email
        email = MenuSetUsuario.SetEmail();

        System.out.println(); // pula uma linha

        // Entrada da data de nascimento
        sqlDate = MenuSetUsuario.SetDataNascimento();

        System.out.println(); // pula uma linha

        // Entrada da carga horária semanal
        cargaHoraria = MenuSetFuncionario.SetCargahoraria();

        System.out.println(); // pula uma linha

        // Entrada do salário
        salario = MenuSetFuncionario.SetSalario();

        System.out.println(); // pula uma linha

        // Entrada do departamento
        departamento = MenuSetAdm.SetDepartamento();

        Ferramentas.limpaTerminal();
        System.out.println("PROCESSANDO...");
        System.out.println(); // pula uma linha

        // CRIAÇÃO DO OBJETO
        try {
            Administrador administrador = new Administrador(nome, cpf, senha, genero, telefone, email, sqlDate, salario, cargaHoraria, departamento);
            administradorService.inserirAdmin(adm, administrador);
            System.out.println("ADMINISTRADOR CADASTRADO COM SUCESSO!");
            Ferramentas.Delay(1000);
        } catch (DadosInvalidosException | TipoUsuarioException | CpfInvalidoException | EmailInvalidoException e) {
            System.err.println("FALHA NO CADASTRO!");
            Ferramentas.Delay(1000);
            Ferramentas.mensagemErro(e.getMessage());
        }
    }
}
