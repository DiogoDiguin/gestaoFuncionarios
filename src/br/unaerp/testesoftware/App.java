package br.unaerp.testesoftware;

import java.sql.SQLException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws SQLException {
        exibirMenu();
    }
	
	public static void exibirMenu() throws SQLException {
        Scanner scannerOpcao = new Scanner(System.in);
        
        System.out.println("\n===== MENU =====");
        System.out.println("1 - REGIÕES");
        System.out.println("2 - PAÍSES");
        System.out.println("3 - LOCAIS");
        System.out.println("4 - DEPARTAMENTOS");
        System.out.println("5 - FUNCIONÁRIOS");
        System.out.println("0 - SAIR");
        System.out.print("Digite uma opção: ");

        int opcao = scannerOpcao.nextInt();
	        switch (opcao) {
	            case 1:
	                RegiaoOpcoes.operacoesRegiao();
	                exibirMenu();
	                break;
	            case 2:
	                PaisOpcoes.operacoesPais();
	                exibirMenu();
	                break;
	            case 3:
	            	LocalOpcoes.operacoesLocal();
	            	exibirMenu();
	                break;
	            case 4:
	                DepartamentoOpcoes.operacoesDepartamento();
	                exibirMenu();
	                break;
	            case 5:
	                FuncionarioOpcoes.operacoesFuncionario();
	                exibirMenu();
	                break;
	            case 0:
	                System.out.println("Saindo do programa.");
	                break;
	            default:
	                System.out.println("Opção inválida. Digite novamente.");
	                exibirMenu();
	                break;
	        }

        scannerOpcao.close();
    	}
	}
