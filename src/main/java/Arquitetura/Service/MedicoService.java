package Arquitetura.Service;

import Arquitetura.Dao.MedicoDAO;
import Arquitetura.Model.Medico;

public class MedicoService {

    // -- Atributos -- //
    MedicoDAO medicoDAO = new MedicoDAO();
    UsuarioService usuarioService = new UsuarioService();

    // Construtor -- //
    public MedicoService() {

    }

    // -- Métodos -- //

    // Verifica a veracidade dos atributos específicos de Medico
    public boolean verificarDadosMed(Medico medico) {
        return(medico.getFormacao() != null && medico.getEspecialidade() != null && medico.getPlantao() != null && medico.getCargaHorariaSemanal() >= 40 && medico.getSalario() > 0);
    }

    // Insere o objeto do tipo Medico no banco de dados
    public void inserirMedico(Medico medico) {
        verificarDadosMed(medico);
        usuarioService.verificarDados(medico);
        medicoDAO.inserirMedico(medico);
    }


}
