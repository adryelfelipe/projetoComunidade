package Arquitetura.View.FuncoesMedico;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Model.Enums.Especialidade;
import Arquitetura.Model.Enums.Plantao;
import Arquitetura.Service.Validator.MedicoValidator;
import Arquitetura.Utilidades.Ferramentas;
import Arquitetura.View.MenuDefault;

import java.util.InputMismatchException;

public class MenuSetMedico {

    private static final MedicoValidator medicoValidator = new MedicoValidator();

    public static Especialidade SetEspecialidade(){

        Especialidade especialidade;
        int opsex;
        while (true){
            System.out.println("Digite a especialidade: ");
            System.out.println("1-CLINICO_GERAL           2-CARDIOLOGISTA    3-RADIOLOGISTA");
            System.out.println("4-OTORRINOLARINGOLOGISTA  5-OFTALMOLOGISTA   6-ENDOCRINOLOGISTA");
            System.out.println("7-HEMATOLOGISTA");
            try {
                opsex = Ferramentas.lInteiro();
                if(opsex < 1|| opsex > 7){
                    MenuDefault.menuDefault();
                }
                else {
                    // Converte a entrada de Especialidade usando switch expression
                     especialidade = switch (opsex) {
                        case 1 -> Especialidade.CLINICO_GERAL;
                        case 2 -> Especialidade.CARDIOLOGISTA;
                        case 3 -> Especialidade.RADIOLOGISTA;
                        case 4 -> Especialidade.OTORRINOLARINGOLOGISTA;
                        case 5 -> Especialidade.OFTALMOLOGISTA;
                        case 6 -> Especialidade.ENDOCRINOLOGISTA;
                        default -> Especialidade.HEMATOLOGISTA;
                    };

                     return especialidade;
                }
            } catch (DadosInvalidosException e) {
                Ferramentas.mensagemErro(e.getMessage());
            } catch(InputMismatchException e) {
                MenuDefault.menuDefault();
            }
        }
    }

    public static String SetFormacao(){

        String formacao;
        while (true){

            System.out.print("Digite a formação academica: ");

            try {
                formacao = Ferramentas.lString();
                MedicoValidator.verificaIntegridadeFormacao(formacao);
                medicoValidator.verificaRegrasFormacao(formacao);
                return formacao;
            }catch (DadosInvalidosException e){
                Ferramentas.mensagemErro(e.getMessage());
            }
        }
    }

    public static String SetSubEspecialidade(){

        String subE;
        while (true){
            System.out.println("Possui Sub Especialidade? ");
            System.out.println("1 - SIM ");
            System.out.println("2 - NÃO ");
            System.out.print("ESCOLHA UMA OPÇÃO: ");

            System.out.println(); // pula uma linha

            try {
                int opSubEsp = Ferramentas.lInteiro();

                if(opSubEsp != 1 && opSubEsp != 2) {
                    MenuDefault.menuDefault();
                }

                if(opSubEsp == 1) {
                    System.out.println("Digite o nome da sua subEspecialidade: ");
                    subE = Ferramentas.lString();
                    MedicoValidator.verificaIntegridadeSubespecialidade(subE);
                    medicoValidator.verificaRegrasSubEspecialidade(subE);
                    return subE;
                } else if(opSubEsp == 2){
                    return null;
                }
            }catch (DadosInvalidosException e){
                Ferramentas.mensagemErro(e.getMessage());
            } catch (InputMismatchException e) {
                MenuDefault.menuDefault();
            }
        }
    }

    public static Plantao SetPlantao(){

        int opPlantao;

        while (true){
            System.out.println("Qual é o seu plantão? ");
            System.out.println("1 - MATUTINO ");
            System.out.println("2 - VESPERTINO ");
            System.out.println("3 - NORTURNO");
            System.out.print("Opção: ");
            try {
                opPlantao = Ferramentas.lInteiro();

                if(opPlantao < 1 || opPlantao > 3)
                {
                    MenuDefault.menuDefault();
                }
                else {
                    Plantao plantao = switch (opPlantao) {
                        case 1 -> Plantao.MATUTINO;
                        case 2 -> Plantao.VERPERTINO;
                        default -> Plantao.NOTURNO;
                    };

                    try{
                        medicoValidator.verificaRegrasPlantao(plantao);
                    } catch (DadosInvalidosException e) {
                        Ferramentas.mensagemErro(e.getMessage());
                    }
                    return plantao;
                }
            } catch (DadosInvalidosException e) {
                Ferramentas.mensagemErro(e.getMessage());
            } catch (InputMismatchException e) {
                MenuDefault.menuDefault();
            }
        }
    }
}
