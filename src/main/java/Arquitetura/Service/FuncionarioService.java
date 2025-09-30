package Arquitetura.Service;

import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Funcionario;

public class FuncionarioService {

    // -- Atributos -- //
    UsuarioService usuarioService = new UsuarioService();

    // -- Construtor -- //
    public FuncionarioService() {

    }

    // -- Métodos -- //

    // Verifica os dados gerais de funcionários
    public boolean verificarDadosFunc(int cargaHorariaSemanal, double salario) {

        return (cargaHorariaSemanal >= 40 && salario > 0);
    }

    // Insere os atributos gerais de Funcionario na tabela Funcionario do banco de dados
    public boolean inserirFuncionario(Administrador administrador, Funcionario funcionario) {
        if (verificarDadosFunc(funcionario.getCargaHorariaSemanal(), funcionario.getSalario())) {
            if (usuarioService.inserirUsuario(administrador, funcionario)) {
                // adicionar funcionrario chamando funcionarioDAO

                return true;
            }
        }

        return false;
    }
}