package Arquitetura.View.FuncoesPACIENTE;
import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Service.Validator.PacienteValidator;
import Arquitetura.Utilidades.Ferramentas;

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
}
