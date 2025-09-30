package Arquitetura.Service;

public class FuncionarioService {

    // -- Construtor -- //
    public FuncionarioService() {

    }

    // -- Métodos -- //

    // Verifica os dados gerais de funcionários
    public boolean verificarDadosFunc(int cargaHorariaSemanal, double salario) {

        return (cargaHorariaSemanal >= 40 && salario > 0);
    }
}
