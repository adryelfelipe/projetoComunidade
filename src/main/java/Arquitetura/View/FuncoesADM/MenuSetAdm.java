package Arquitetura.View.FuncoesADM;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Model.Enums.Departamento;
import Arquitetura.Service.UsuarioService;
import Arquitetura.Service.Validator.AdministradorValidator;
import Arquitetura.Service.Validator.FuncionarioValidator;
import Arquitetura.Service.Validator.UsuarioValidator;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.MenuDefault;

import java.util.InputMismatchException;

public class MenuSetAdm {

    public static final AdministradorValidator administradorValidator = new AdministradorValidator();

    public static Departamento SetDepartamento(){

        int opDepartamento;

        while (true) {
            System.out.println("Qual é o seu departamento? ");
            System.out.println("1 - FINANCEIRO ");
            System.out.println("2 - INFRAESTRUTURA ");
            System.out.println("3 - MARKETING");
            System.out.println("4 - RH");
            System.out.print("OPÇÃO: ");

            try {
                opDepartamento = Ferramentas.lInteiro();
                if(opDepartamento < 0 || opDepartamento > 4) {
                    MenuDefault.menuDefault();
                } else {

                    // Converte a entrada de departamento usando switch expression
                    Departamento departamento = switch (opDepartamento) {
                        case 1 -> Departamento.FINANCEIRO;
                        case 2 -> Departamento.INFRAESTRUTURA;
                        case 3 -> Departamento.MARKETING;
                        default -> Departamento.RH;
                    };

                    try{
                        administradorValidator.verificaRegrasDepartamento(departamento);
                        return departamento;
                    } catch (DadosInvalidosException e) {
                        Ferramentas.mensagemErro(e.getMessage());
                    }
                }
            }catch (InputMismatchException e) {
                MenuDefault.menuDefault();
            }
        }
    }
}
