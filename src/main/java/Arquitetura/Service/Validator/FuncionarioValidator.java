package Arquitetura.Service.Validator;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Model.Funcionario;

public class FuncionarioValidator {

    // -- Atributos -- //
    private UsuarioValidator usuarioValidator = new UsuarioValidator();

    // -- Métodos de verificação -- //
    public void verificarDadosFuncionario(Funcionario funcionario)
    {
        usuarioValidator.verificarDadosUser(funcionario);

        if(funcionario.getCargaHorariaSemanal() < 40)
        {
            throw new DadosInvalidosException("ERRO! A CARGA GHORÁRIA NÃO PODE SER MENOR QUE 40");
        }

        if (funcionario.getSalario() < 1500)
        {
            throw new DadosInvalidosException("ERRO! O SALÁRIO NÃO PODE SER MENOR QUE 1500");
        }
    }
}