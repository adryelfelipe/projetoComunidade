
import Arquitetura.Dao.MedicoDAO;
import Arquitetura.Model.Medico;
import Arquitetura.View.MenuInicial;

import java.sql.SQLException;

public class Main
{
    public static void main(String[] args) throws SQLException
    {
        MedicoDAO medicoDAO = new MedicoDAO();
        Medico med = medicoDAO.findByID(17);

        System.out.println("Olá meu nome é "+med.getNome());
    }
}