package Arquitetura.View.FuncoesMedico;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Service.Validator.MedicoValidator;
import Arquitetura.Utilidades.Ferramentas;

public class MenuUpdate {

    public static void menuUpdateFormacao(MedicoValidator medicoValidator) {
        System.out.println("Digite sua nova Formação: ");
        String formacao = Ferramentas.lString();

        try {
            MedicoValidator.verificaIntegridadeFormacao(formacao);
            medicoValidator.verificaRegrasFormacao(formacao);
        }catch (DadosInvalidosException e){
            Ferramentas.mensagemErro(e.getMessage());
        }
    }
}
