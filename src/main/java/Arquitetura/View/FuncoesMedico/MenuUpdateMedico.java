package Arquitetura.View.FuncoesMedico;

import Arquitetura.Exception.*;
import Arquitetura.Model.Enums.Especialidade;
import Arquitetura.Model.Enums.Plantao;
import Arquitetura.Model.Medico;
import Arquitetura.Service.UsuarioService;
import Arquitetura.Service.Validator.MedicoValidator;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.MenuDefault;
import Arquitetura.View.MenuUsuarios.MenuSetUsuario;

import java.util.InputMismatchException;

public class MenuUpdateMedico {

    private static final MedicoValidator medicoValidator = new MedicoValidator();
    static UsuarioService usuarioService = new UsuarioService();


    public static void EditarMedico(Medico medico) {

        boolean verifica = false;
        int op = 0;

        while (true){
            Ferramentas.limpaTerminal();
            while (!verifica) {
                System.out.println("       -----------------             --------- ATUAL --------");
                System.out.println("       |EDITAR   PERFIL|             |Nome: " + medico.getNome());
                System.out.println("       -----------------             |CPF: " + medico.getCpf());
                System.out.println("                                     |Senha: " + medico.getSenha());
                System.out.println("                                     |Email: " + medico.getEmail());
                System.out.println("Digite para alterar:                 |Telefone: " + medico.getTelefone());
                System.out.println("                                     |Formação: " + medico.getFormacao());
                System.out.println("[1] - Email                          |Especialidade: " + medico.getEspecialidade());
                System.out.println("[2] - Telefone                       |SubEspecialidade: " + medico.getSubEspecialidade());
                System.out.println("[3] - Senha                          |Plantão: "+ medico.getPlantao());
                System.out.println("[4] - Sair                           |Data nascimento: " + medico.getDataNascimento());
                try {
                    op = Ferramentas.lInteiro();
                    verifica = true;
                } catch (InputMismatchException e){
                    MenuDefault.menuDefault();
                }
            }

            // REINICIA A VARIÁVEL DE VERIFICAÇÃO
            verifica = false;

            try {

                switch (op) {
                    case 1: {
                        String email = MenuSetUsuario.SetEmail();
                        usuarioService.updateEmailUsuario(medico, medico.getId(), email);
                        break;
                    }
                    case 2: {

                        String telefone = MenuSetUsuario.SetTelefone();
                        usuarioService.updateTelefoneUsuario(medico, medico.getId(), telefone);
                        break;
                    }
                    case 3: {

                        String senha = MenuSetUsuario.SetSenha();
                        usuarioService.updateSenhaUsuario(medico, medico.getId(), senha);
                        break;
                    }
                    case 4: {

                         return;

                    }
                    default: {

                        MenuDefault.menuDefault();
                        break;
                    }
                }
            }catch(TipoUsuarioException | DadosInvalidosException | IdInvalidoException
                  | EmailInvalidoException | TelefoneInvalidoException e) {
            Ferramentas.mensagemErro(e.getMessage());
            }

        }
    }
}
