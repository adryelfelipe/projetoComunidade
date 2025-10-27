package Arquitetura.View.MenuUsuarios;
import Arquitetura.Exception.*;
import Arquitetura.Model.Enums.Genero;
import Arquitetura.Model.Enums.TipoUsuario;
import Arquitetura.Service.UsuarioService;
import Arquitetura.Service.Validator.DataValidator;
import Arquitetura.Service.Validator.FuncionarioValidator;
import Arquitetura.Service.Validator.UsuarioValidator;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.MenuDefault;
import java.sql.Date;
import java.time.LocalDate;
import java.util.InputMismatchException;

public class MenuSetUsuario {

    static UsuarioService usuarioService = new UsuarioService();
    static UsuarioValidator usuarioValidator = new UsuarioValidator();

    public static String SetNome(){

        String nome;
        while(true){
            System.out.print("\n\n\nDigite o nome: ");
            nome = Ferramentas.lString();

            try {
                UsuarioValidator.verificaIntegridadeNome(nome);
                usuarioValidator.verificarRegrasNome(nome);
                return nome;
            } catch (DadosInvalidosException e) {
                Ferramentas.mensagemErro(e.getMessage());
            }
        }
    }

    public static String SetCpf(){

        String cpf;
        while(true) {
            System.out.print("Digite o CPF: ");
            cpf = Ferramentas.lString();

            try{
                UsuarioValidator.verificaIntegridadeCpf(cpf);
                usuarioValidator.verificarRegrasCpf(cpf);
                usuarioService.cpfUtilizadoValidator(cpf);
                return cpf;
            } catch(DadosInvalidosException | CpfInvalidoException e) {
                Ferramentas.mensagemErro(e.getMessage());
            }
        }
    }

    public static String SetSenha(){

        String senha;
        while (true) {
            System.out.print("Digite a senha: ");
            senha = Ferramentas.lString();

            try{
                UsuarioValidator.verificaIntegridadeSenha(senha);
                usuarioValidator.verificarRegrasSenha(senha);
                return senha;
            } catch (DadosInvalidosException e) {
                Ferramentas.mensagemErro(e.getMessage());
            }
        }
    }

    public static Genero SetSexo(){

        while(true) {
            System.out.println("Digite o sexo:");
            System.out.println("1-Masculino");
            System.out.println("2-Feminino");
            int op = Ferramentas.lerOpcao();

            if(op < 1 || op > 2) {
                MenuDefault.menuDefault();
            } else {
                // Converte a entrada de genero usando switch expression
                Genero genero = switch (op) {
                    case 1 -> Genero.MASCULINO;
                    default -> Genero.FEMININO;
                };

                try{
                    usuarioValidator.verificarRegrasSexo(genero);
                } catch (DadosInvalidosException e) {
                    Ferramentas.mensagemErro(e.getMessage());
                }

                return genero;
            }
        }
    }

    public static String SetTelefone(){

        String telefone;
        while(true) {
            System.out.print("Digite o número de telefone: ");
            telefone = Ferramentas.lString();

            try{
                UsuarioValidator.verificaIntegridadeTelefone(telefone);
                usuarioValidator.verificarRegrasTelefone(telefone);
                usuarioService.telefoneUtilizadoValidator(telefone);
                return telefone;
            } catch (DadosInvalidosException | TelefoneInvalidoException e) {
                Ferramentas.mensagemErro(e.getMessage());
            }
        }
    }

    public static String SetEmail(){

        String email;
        while(true) {
            System.out.print("Digite o email: ");
            email = Ferramentas.lString();

            try{
                UsuarioValidator.verificaIntegridadeEmail(email);
                usuarioValidator.verificarRegrasEmail(email);
                usuarioService.emailUtilizadoValidator(email);
                return email;
            } catch (DadosInvalidosException | EmailInvalidoException e) {
                Ferramentas.mensagemErro(e.getMessage());
            }
        }
    }

    public static java.sql.Date SetDataNascimento(){

        boolean verifica = false;

        System.out.println("Data de nascimento");

        // Ano
        int ano = 0;
        while (!verifica) {
            System.out.print("Digite o Ano: ");
            try {
                ano = Ferramentas.lInteiro();
                DataValidator.verificaAno(ano);
                verifica = true;
            } catch (InputMismatchException e) {
                MenuDefault.menuDefault();
            } catch (DataInvalidaException e) {
                Ferramentas.mensagemErro(e.getMessage());
            }
        }

        // RESETA A VERIFICAÇÃO
        verifica = false;

        // MÊS
        int mes = 0;
        while (!verifica) {
            System.out.print("Digite o Mês: ");
            try {
                mes = Ferramentas.lInteiro();
                DataValidator.verificaMes(mes);
                verifica = true;
            } catch (InputMismatchException e) {
                MenuDefault.menuDefault();
            } catch (DataInvalidaException e) {
                Ferramentas.mensagemErro(e.getMessage());
            }
        }

        // RESETA A VERIFICAÇÃO
        verifica = false;

        // DIA
        int dia = 0;
        while (!verifica) {
            System.out.print("Digite Dia: ");
            try {
                dia = Ferramentas.lInteiro();
                DataValidator.verificaDia(ano, mes, dia);
                verifica = true;
            } catch (InputMismatchException e) {
                MenuDefault.menuDefault();
            } catch (DataInvalidaException e) {
                Ferramentas.mensagemErro(e.getMessage());
            }
        }

        // Transforma ano, mês e dia em uma sqlDate
        LocalDate dataNascimento = LocalDate.of(ano, mes, dia);
        Date sqlDate = Date.valueOf(dataNascimento);

        try {
            usuarioValidator.verificarRegrasDataNascimento(sqlDate);
            return sqlDate;
        } catch (DadosInvalidosException e) {
            Ferramentas.mensagemErro(e.getMessage());
        }

        throw new DataInvalidaException("ERRO AO VERIFICAR A DATA");
    }

    public static TipoUsuario SetTipoUsuario() {
        while(true) {
            System.out.println("Digite o seu sexo:");
            System.out.println("1-Administrador");
            System.out.println("2-Médico");
            System.out.println("3-Paciente");
            System.out.print("OPÇÃO: ");
            int op = Ferramentas.lerOpcao();

            if(op < 1 || op > 3) {
                MenuDefault.menuDefault();
            } else {
                // Converte a entrada de genero usando switch expression
                TipoUsuario tipoUsuario = switch (op) {
                    case 1 -> TipoUsuario.ADMIN;
                    case 2 -> TipoUsuario.MEDICO;
                    default -> TipoUsuario.PACIENTE;
                };

                try{
                    usuarioValidator.verificarRegrasTipoUsuario(tipoUsuario);
                } catch (DadosInvalidosException e) {
                    Ferramentas.mensagemErro(e.getMessage());
                }

                return tipoUsuario;
            }
        }
    }
}
