package Arquitetura.View.MenuUsuarios;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Service.Validator.FuncionarioValidator;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.MenuDefault;

import java.util.InputMismatchException;

public class MenuSetFuncionario {

    public static int SetCargahoraria(FuncionarioValidator funcionarioValidator){

        int cargaHoraria;
        while (true) {

            System.out.print("Digite a carga horária semanal: ");
            try {
                cargaHoraria = Ferramentas.lInteiro();
                FuncionarioValidator.verificaIntegridadeSalario(cargaHoraria);
                funcionarioValidator.verificaRegrasCargaHoraria(cargaHoraria);
                return cargaHoraria;
            } catch (InputMismatchException e) {
                MenuDefault.menuDefault();
            } catch(DadosInvalidosException e) {
                Ferramentas.mensagemErro(e.getMessage());
            }
        }
    }

    public static double SetSalario(FuncionarioValidator funcionarioValidator){

        double salario;
        while(true) {
            System.out.print("Digite o salário: ");
            try {
                salario = Ferramentas.lDouble();
                FuncionarioValidator.verificaIntegridadeSalario(salario);
                funcionarioValidator.verificaRegrasSalario(salario);
                return salario;
            } catch (InputMismatchException e) {
                MenuDefault.menuDefault();
            } catch (DadosInvalidosException e) {
                Ferramentas.mensagemErro(e.getMessage());
            }
        }
    }
}
