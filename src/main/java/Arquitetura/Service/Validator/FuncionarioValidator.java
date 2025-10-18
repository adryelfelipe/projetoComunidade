package Arquitetura.Service.Validator;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Model.Funcionario;

public class FuncionarioValidator {

    // -- Atributos -- //
    private UsuarioValidator usuarioValidator = new UsuarioValidator();

    // -- Métodos de verificação -- //
    public void verificarDadosFuncionario(Funcionario funcionario)
    {
        usuarioValidator.verificaRegrasInsercaoUsuario(funcionario);

        if(funcionario.getCargaHorariaSemanal() < 40)
        {
            throw new DadosInvalidosException("ERRO! A CARGA HORÁRIA NÃO PODE SER MENOR QUE 40");
        }

        if (funcionario.getSalario() < 1500)
        {
            throw new DadosInvalidosException("ERRO! O SALÁRIO NÃO PODE SER MENOR QUE 1500");
        }
    }

    // - Métodos verificadores de integridade de dados - //
    public void verificaIntegridadeSalario(double salario) {
        if(salario < 0) {
            throw new DadosInvalidosException("ERRO! O SALÁRIO NÃO PODE SER MENOR QUE 0");
        }
    }

    public void verificaIntegridadeCargaHoraria(int cargaHorariaSemanal) {
        if(cargaHorariaSemanal < 0) {
            throw new DadosInvalidosException("ERRO! A CARGA HORÁRIA SEMANAL NÃO PODE SER MENOR QUE 0");
        }
    }
}