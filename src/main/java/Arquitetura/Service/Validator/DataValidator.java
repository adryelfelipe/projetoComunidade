package Arquitetura.Service.Validator;

import Arquitetura.Exception.DataInvalidaException;

public class DataValidator {
    public static String verificaMes(int mes) {
        if(mes <= 0 || mes > 12) {
            throw new DataInvalidaException("ERRO! O MÊS DEVE ESTAR ENTRE 1 E 12");
        }

        return switch(mes) {
            case 1 -> "JANEIRO";
            case 2 -> "FEVEREIRO";
            case 3 -> "MARÇO";
            case 4 -> "ABRIL";
            case 5 -> "MAIO";
            case 6 -> "JUNHO";
            case 7 -> "JULHO";
            case 8 -> "AGOSTO";
            case 9 -> "SETEMBRO";
            case 10 -> "OUTUBRO";
            case 11 -> "NOVEMBRO";
            default -> "DEZEMBRO";
        };
    }

    public static void verificaDia(int ano, int mes, int dia) {
        String mesNome = verificaMes(mes);

        if(mes == 2) {
            boolean bissexto = ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0);
            int maxDias = bissexto ? 29 : 28;

            if (dia < 1 || dia > maxDias) {
                throw new DataInvalidaException("ERRO! " + mesNome + " POSSUI ENTRE 1 E " + maxDias + " DIAS");
            }
        }

        if((mes == 4 || mes == 6 || mes == 9 || mes == 11) && (dia < 1 || dia > 30)) {
            throw new DataInvalidaException("ERRO! " + mesNome + " POSSUI ENTRE 1 A 30 DIAS");
        }

        if(dia < 1 || dia > 31) {
            throw new DataInvalidaException("ERRO! " + mesNome + " POSSUI ENTRE 1 A 31 DIAS");
        }
    }
}
