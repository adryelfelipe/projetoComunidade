package Arquitetura.Service.Validator;

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
            throw new IllegalArgumentException("ERRO! A CARGA GHORÁRIA NÃO PODE SER MENOR QUE 40");
        }

        if (funcionario.getSalario() < 1500)
        {
            throw new IllegalArgumentException("ERRO! O SALÁRIO NÃO PODE SER MENOR QUE 1500");
        }
    }
}