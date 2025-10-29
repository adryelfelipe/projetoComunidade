package Arquitetura.View.FuncoesPACIENTE;
import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Model.Enums.Genero;
import Arquitetura.Model.Enums.StatusPaciente;
import Arquitetura.Service.Validator.PacienteValidator;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.MenuDefault;

import java.util.InputMismatchException;

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
            System.out.print("Digite o número do cadastro: ");
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
        int op = 0;
        boolean verifica = false;

        while(true) {
            while(!verifica) {
                System.out.println("Digite o Status:");
                System.out.println("1-ATIVO");
                System.out.println("2-INATIVO");
                try {
                    op = Ferramentas.lInteiro();
                    verifica = true;
                } catch (InputMismatchException e){
                    MenuDefault.menuDefault();
                }
            }

            // REINICIA A VARIÁVEL
            verifica = false;

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
