package br.unaerp.testesoftware;

import java.sql.SQLException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws SQLException {

        Scanner scannerOpcao = new Scanner(System.in);
        Scanner scannerOperacao = new Scanner(System.in);
        Scanner entradaInclude = new Scanner(System.in);
        Scanner entradaIncludeFloat = new Scanner(System.in);

        Scanner entradaIdUpdate = new Scanner(System.in);
        Scanner entradaUpdateStrings = new Scanner(System.in);
        Scanner entradaUpdateFloat = new Scanner(System.in);

        Scanner entradaIdDelete = new Scanner(System.in);

        int opcao = 0;

        while (opcao <= 5) {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - REGIÕES");
            System.out.println("2 - PAÍSES");
            System.out.println("3 - LOCAIS");
            System.out.println("4 - DEPARTAMENTOS");
            System.out.println("5 - FUNCIONÁRIOS");

            System.out.print("Digite uma opção: ");
            
            if (scannerOpcao.hasNextInt()) {
                opcao = scannerOpcao.nextInt();

                switch (opcao) {
                
                    case 1:
                        RegiaoOpcoes.operacoesRegiao();
                        break;//Fim REGIÃO

                    case 2:
                        PaisOpcoes.operacoesPais();
                        break;//Fim PAÍSES

                    case 3:
                        LocalOpcoes.operacoesLocal();
                        break;//Fim LOCAIS

                    case 4:
                        DepartamentoOpcoes.operacoesDepartamento();
                        break;//Fim DEPARTAMENTOS

                    case 5:
                        FuncionarioOpcoes.operacoesFuncionario();
                        break;//Fim FUNCIONÁRIOS
                        
                }//FIM SWITCH
            }
        }

        scannerOpcao.close();
        entradaIdUpdate.close();
        entradaInclude.close();
        entradaUpdateStrings.close();
        entradaIdDelete.close();
        entradaIncludeFloat.close();
        entradaUpdateFloat.close();
        scannerOperacao.close();
    }
}
