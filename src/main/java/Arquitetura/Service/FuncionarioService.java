package Arquitetura.Service;

import Arquitetura.Dao.FuncionarioDAO;
import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Funcionario;

public class FuncionarioService {

    // -- Atributos -- //
    UsuarioService usuarioService = new UsuarioService();
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    UsuarioDAO usuarioDAO = new UsuarioDAO();

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
        if (verificarDadosFunc(funcionario.getCargaHorariaSemanal(), funcionario.getSalario())) { // Verifica os dados de Funcionario
            if (usuarioService.inserirUsuario(administrador, funcionario)) { // Verifica os dados de Usuario
                funcionarioDAO.inserirFuncionario(funcionario);

                return true;
            }
        }

        return false;
    }

    // Cojunto de regras de negócio gerais para deletar qualquer tipo de funcionario
    public boolean deletarFuncionario(long id) {

        return usuarioService.deletarUsuario(id);

        // Somente verificações gerais por enquanto
    }
}