package Arquitetura.Service;

import Arquitetura.Dao.FuncionarioDAO;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Funcionario;
import Arquitetura.Model.Usuario;

public class FuncionarioService {

    // -- Atributos -- //
    private final UsuarioService usuarioService = new UsuarioService();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    // -- Construtor -- //
    public FuncionarioService() {

    }

    // -- Métodos -- //

    // Verifica os dados gerais de funcionários
    private boolean verificarDadosFunc(Funcionario funcionario) {

        return (funcionario.getCargaHorariaSemanal() >= 40 && funcionario.getSalario() > 0);
    }

    // Insere os atributos gerais de Funcionario na tabela Funcionario do banco de dados
    public boolean inserirFuncionario(Usuario usuario, Funcionario funcionario) {
        if(usuario.getTipoUsuario().getNivelAcesso().temAcessoTotal()) {
            if (verificarDadosFunc(funcionario)) { // Verifica os dados de Funcionario
                if (usuarioService.inserirUsuario(usuario, funcionario)) { // Verifica os dados de Usuario
                    funcionarioDAO.inserirFuncionario(funcionario);

                    return true;
                }
            }
        }

        return false;
    }

    // Cojunto de regras de negócio gerais para deletar qualquer tipo de funcionario
    boolean deletarFuncionario(long id) {

        return usuarioService.deletarUsuario(id);

        // Somente verificações gerais por enquanto
    }
}