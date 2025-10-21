package Arquitetura.View.FuncoesMedico.Updates;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Service.Validator.MedicoValidator;
import Arquitetura.Service.Validator.UsuarioValidator;
import Arquitetura.Utilidades.Ferramentas;

public class MenuUpdateFormacao {

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
