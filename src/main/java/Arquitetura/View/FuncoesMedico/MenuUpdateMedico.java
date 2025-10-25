package Arquitetura.View.FuncoesMedico;

import Arquitetura.Exception.DadosInvalidosException;
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

        boolean continuar = true;
        int escolha = 0;


        do {
            Ferramentas.limpaTerminal();

            System.out.println("       -----------------             ------------------------");
            System.out.println("       |EDITAR   Medico|             |Nome: " + medico.getNome());
            System.out.println("       -----------------             |CPF: " + medico.getCpf());
            System.out.println("                                     |EMAIL: " + medico.getEmail());
            System.out.println("Digite para alterar:                 |Telefone: " + medico.getTelefone());
            System.out.println("1-Email                              |Formação: " + medico.getFormacao());
            System.out.println("2-Telefone                           |Especialidade: " + medico.getEspecialidade());
            System.out.println("3-Senha                              |SubEspecialidade: " + medico.getSubEspecialidade());
            System.out.println("4-Sair                                    |Plantão: " + medico.getPlantao());
            System.out.println("                                     ------------------------");

            try {
                escolha = Ferramentas.lInteiro();
            }catch (InputMismatchException e){
                MenuDefault.menuDefault();
            }
            switch (escolha) {
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
                    usuarioService.updateSenhaUsuario(medico, medico.getId(),senha);
                    break;
                }
                case 4:{
                    continuar = false;
                }
                default: {

                    MenuDefault.menuDefault();
                    break;
                }
            }
        } while (!continuar);
    }
}
