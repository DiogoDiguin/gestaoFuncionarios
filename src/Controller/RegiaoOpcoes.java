package Controller;

import java.util.Scanner;

import Model.ApplicationContext;
import Model.Regiao;
import Model.RegiaoDAO;

public class RegiaoOpcoes {
	
	App aplicacao = new App();
	
	static RegiaoDAO dao = ApplicationContext.getRegiaoDAO();
	static Regiao reg = new Regiao();
	
	static Scanner entradaInclude = new Scanner(System.in);
	static Scanner entradaIdUpdate = new Scanner(System.in);
	static Scanner entradaUpdateStrings = new Scanner(System.in);
	static Scanner entradaIdDelete = new Scanner(System.in);
	
	public static void operacoesRegiao() {
		Scanner scannerOperacao = new Scanner(System.in);
    	int opcaoOperacao = 0;
    	
		while (opcaoOperacao <= 5){
			System.out.println("\nREGIÕES");
			System.out.println("===== MENU =====");
		    System.out.println("1 - INCLUIR REGIÃO");
		    System.out.println("2 - EXIBIR TODAS AS REGIÕES");
		    System.out.println("3 - ATUALIZAR REGIÃO");
		    System.out.println("4 - APAGAR REGIÃO");
		    System.out.println("5 - MÉDIA SALARIAL POR REGIÃO");
		    System.out.print("Digite uma opção: ");
		    opcaoOperacao = scannerOperacao.nextInt();
			    if(opcaoOperacao == 0) {
		    		break;
		    	}else {
		    		switch (opcaoOperacao) {
				    case 1:	            	
				    	RegiaoOpcoes.incluirRegiao();
			            break;
			        case 2:
			        	RegiaoOpcoes.exibirRegioes();
			            break;
			        case 3:
			        	RegiaoOpcoes.updateRegiao();
			            break;
			        case 4:
			        	RegiaoOpcoes.deleteRegiao();
			            break;
			        case 5:
			        	RegiaoOpcoes.calcularMediaSalarial();
			            break;
			        default:
			            System.out.println("Opção inválida. Digite novamente.");
			            break;
		    		}
		    	}
			}
	}
	
	public static void incluirRegiao() {
		System.out.printf("%n");
    	System.out.println("1 - INCLUIR REGIÃO");
    	
    	System.out.print("Digite o NOME: ");
    	String nome = entradaInclude.nextLine();
        
    	reg.setNomeRegiao(nome);

    	dao.insert(reg);
        System.out.println("-");
    }
    
    public static void exibirRegioes() {
    	System.out.printf("%n");
    	System.out.println("2 - EXIBIR TODAS AS REGIÕES");
    	
    	dao.getAll();
    }
    
    public static void updateRegiao() {
    	System.out.printf("%n");
        System.out.println("3 - ATUALIZAR REGIÃO");
        dao.getAll();
        
        System.out.print("Digite o ID: ");
    	int id = entradaIdUpdate.nextInt();
    	reg.setId(id);
    	
    	System.out.print("Digite o NOME: ");
    	String NovoNome = entradaUpdateStrings.nextLine();
    	reg.setNomeRegiao(NovoNome);
    	
    	dao.update(reg);
    }
    
    public static void deleteRegiao() {
    	System.out.printf("%n");
    	System.out.println("4 - APAGAR REGIÃO");
    	dao.getAll();
    	System.out.print("Digite o ID: ");
    	int idDelete = entradaIdDelete.nextInt();
    	reg.setId(idDelete);
    	
    	dao.delete(reg);
    }
    
    public static void calcularMediaSalarial() {
    	System.out.printf("%n");
    	System.out.println("5 - MÉDIA SALARIAL POR REGIÃO");
    	
    	dao.mediaSalarial();
    }
}
