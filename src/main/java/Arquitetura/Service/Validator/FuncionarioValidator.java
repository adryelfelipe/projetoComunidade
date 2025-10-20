package Arquitetura.Service.Validator;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Model.Funcionario;

public class FuncionarioValidator {

    // -- Construtor -- //
    public FuncionarioValidator(UsuarioValidator usuarioValidator) {
        this.usuarioValidator = usuarioValidator;
    }

    // -- Atributos -- //
    private UsuarioValidator usuarioValidator;

    // -- Métodos verificadores de regras de negócio -- //
    public void verificaRegrasInsercaoFuncionario(Funcionario funcionario) {
        usuarioValidator.verificaRegrasInsercaoUsuario(funcionario);
        verificaRegrasSalario(funcionario.getSalario());
        verificaRegrasCargaHoraria(funcionario.getCargaHorariaSemanal());
    }

    public void verificaRegrasCargaHoraria(int cargaHorariaSemanal) {
        if(cargaHorariaSemanal < 40) {
            throw new DadosInvalidosException("ERRO! A CARGA HORÁRIA NÃO PODE SER MENOR QUE 40");
        }
    }

    public void verificaRegrasSalario(double salario) {
        if (salario < 1500) {
            throw new DadosInvalidosException("ERRO! O SALÁRIO NÃO PODE SER MENOR QUE 1500");
        }
    }

    // -- Métodos verificadores de integridade de dados -- //
    public static void verificaIntegridadeSalario(double salario) {
        if(salario < 0) {
            throw new DadosInvalidosException("ERRO! O SALÁRIO NÃO PODE SER MENOR QUE 0");
        }
    }

    public static void verificaIntegridadeCargaHoraria(int cargaHorariaSemanal) {
        if(cargaHorariaSemanal < 0) {
            throw new DadosInvalidosException("ERRO! A CARGA HORÁRIA SEMANAL NÃO PODE SER MENOR QUE 0");
        }
    }
}