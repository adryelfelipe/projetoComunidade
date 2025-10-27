package Arquitetura.View.FuncoesPACIENTE;
import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Model.Enums.Genero;
import Arquitetura.Model.Enums.StatusPaciente;
import Arquitetura.Service.Validator.PacienteValidator;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.MenuDefault;

public class MenuSetPaciente {

    private static final PacienteValidator pacienteValidator = new PacienteValidator();
    public static String SetContatoEmergencia() {

        String contatoEmer;
        while (true) {
            System.out.print("Digite o número do contato de emergência: ");
            try {
                contatoEmer = Ferramentas.lString();
                PacienteValidator.verificaIntegridadeContatoEmerg(contatoEmer);
                pacienteValidator.verificaRegrasContatoEmergencia(contatoEmer);
                 return contatoEmer;
            } catch (DadosInvalidosException e) {
                Ferramentas.mensagemErro(e.getMessage());
            }
        }
    }

    public static String SetNumeroCadastro(){

        String numeroCar;
        while (true) {
            System.out.print("Digite o número da carteirinha: ");
            try{
                numeroCar = Ferramentas.lString();
                PacienteValidator.verificaIntegridadeNumeroCadastro(numeroCar);
                pacienteValidator.verificaRegrasNumeroCarterinha(numeroCar);
                return numeroCar;
            }catch(DadosInvalidosException e){
                Ferramentas.mensagemErro(e.getMessage());
            }
        }
    }

    public static StatusPaciente SetStatusPaciente() {
        while(true) {
            System.out.println("Digite o Status:");
            System.out.println("1-ATIVO");
            System.out.println("2-INATIVO");
            int op = Ferramentas.lerOpcao();

            if(op < 1 || op > 2) {
                MenuDefault.menuDefault();
            } else {
                // Converte a entrada de genero usando switch expression
                StatusPaciente statusPaciente = switch (op) {
                    case 1 -> StatusPaciente.ATIVO;
                    default -> StatusPaciente.INATIVO;
                };

                try{
                    pacienteValidator.verificaRegrasStatusPaciente(statusPaciente);
                } catch (DadosInvalidosException e) {
                    Ferramentas.mensagemErro(e.getMessage());
                }

                return statusPaciente;
            }
        }
    }
}
