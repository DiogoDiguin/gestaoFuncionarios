package br.unaerp.testesoftware;

import java.util.Scanner;

public class PaisOpcoes {

    static PaisDAO dao = ApplicationContext.getPaisDAO();
    static RegiaoDAO daoR = ApplicationContext.getRegiaoDAO();
    static Pais pais = new Pais();
    static Regiao regiao = new Regiao();

    static Scanner entradaInclude = new Scanner(System.in);
    static Scanner entradaIdUpdate = new Scanner(System.in);
    static Scanner entradaUpdateStrings = new Scanner(System.in);
    static Scanner entradaIdDelete = new Scanner(System.in);

    public static void operacoesPais() {
    	Scanner scannerOperacao = new Scanner(System.in);
    	int opcaoOperacao = 0;
    	
	    while (opcaoOperacao <= 5){
			System.out.println("\nPAÍSES");
			System.out.println("===== MENU =====");
		    System.out.println("1 - INCLUIR PAÍS");
		    System.out.println("2 - EXIBIR TODOS OS PAÍSES");
		    System.out.println("3 - ATUALIZAR PAÍS");
		    System.out.println("4 - APAGAR PAÍS");
		    System.out.println("5 - EXIBIR DEPARTAMENTOS POR PAÍS");
		    System.out.print("Digite uma opção: ");
		    opcaoOperacao = scannerOperacao.nextInt();
			    if(opcaoOperacao == 0) {
		    		break;
		    	}else {
		    		switch (opcaoOperacao) {
				    case 1:	            	
				    	PaisOpcoes.incluirPais();
			            break;
			        case 2:
			        	PaisOpcoes.exibirPaises();
			            break;
			        case 3:
			        	PaisOpcoes.updatePais();
			            break;
			        case 4:
			        	PaisOpcoes.deletePais();
			            break;
			        case 5:
			        	PaisOpcoes.departamentosPais();
			            break;
			        default:
			            System.out.println("Opção inválida. Digite novamente.");
			            break;
		    		}
		    	}
		}
    }
    
    public static void incluirPais() {
        System.out.printf("%n");
        System.out.println("1 - INCLUIR PAÍS");

        System.out.print("Digite o NOME: ");
        String nome = entradaInclude.nextLine();

        daoR.getAll();
        
        System.out.print("Digite o ID da REGIÃO: ");
        int regiaoId = entradaInclude.nextInt();

        pais.setNomePais(nome);
        regiao.setId(regiaoId);

        // Associa a região ao país
        pais.setRegiao(regiao);

        // Chama o método insert() para adicionar o país ao banco de dados
        dao.insert(pais);

        System.out.println("-");
    }

    public static void exibirPaises() {
        System.out.printf("%n");
        System.out.println("2 - EXIBIR TODOS OS PAÍSES");

        dao.getAll();
    }

    public static void updatePais() {
        System.out.printf("%n");
        System.out.println("3 - ATUALIZAR PAÍS");

        dao.getAll();

        System.out.print("Digite o ID: ");
        int id = entradaIdUpdate.nextInt();

        System.out.print("Digite o NOVO NOME: ");
        String novoNome = entradaUpdateStrings.nextLine();
        
        daoR.getAll();

        System.out.print("Digite o NOVO ID da REGIÃO: ");
        int novoIdRegiao = entradaIdUpdate.nextInt();

        pais.setIdPais(id);
        pais.setNomePais(novoNome);
        regiao.setId(novoIdRegiao);

        // Associa a nova região ao país
        pais.setRegiao(regiao);

        // Chama o método update() para atualizar o país no banco de dados
        dao.update(pais);
    }

    public static void deletePais() {
        System.out.printf("%n");
        System.out.println("4 - APAGAR REGIÃO");

        dao.getAll();
        System.out.print("Digite o ID: ");
        int idDelete = entradaIdDelete.nextInt();
        pais.setIdPais(idDelete);

        dao.delete(pais);
    }
    
    public static void departamentosPais() {
        System.out.printf("%n");
        System.out.println("5 - EXIBIR DEPARTAMENTOS POR PAÍS");

        dao.selectDepartamentos();
    }
}
