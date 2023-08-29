package br.unaerp.testesoftware;

import java.util.Scanner;

public class RegiaoOpcoes {
	
	App aplicacao = new App();
	
	static RegiaoDAO dao = new RegiaoDAO();
	static Regiao reg = new Regiao();
	
	static Scanner entradaInclude = new Scanner(System.in);
	static Scanner entradaIncludeFloat = new Scanner(System.in);
	static Scanner entradaIdUpdate = new Scanner(System.in);
	static Scanner entradaUpdateStrings = new Scanner(System.in);
	static Scanner entradaUpdateFloat = new Scanner(System.in);
	static Scanner entradaIdDelete = new Scanner(System.in);
	
	public static void incluirRegiao() {
		System.out.printf("%n");
    	System.out.println("1 - INCLUIR REGIÃO");
    	
    	System.out.println("Digite o NOME: ");
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
        
        System.out.println("Digite o ID: ");
    	int id = entradaIdUpdate.nextInt();
    	reg.setId(id);
    	
    	System.out.println("Digite o NOME: ");
    	String NovoNome = entradaUpdateStrings.nextLine();
    	reg.setNomeRegiao(NovoNome);
    	
    	dao.update(reg);
    }
    
    public static void deleteRegiao() {
    	System.out.printf("%n");
    	System.out.println("4 - APAGAR REGIÃO");
    	dao.getAll();
    	System.out.println("Digite o ID: ");
    	int idDelete = entradaIdDelete.nextInt();
    	reg.setId(idDelete);
    	
    	dao.delete(reg);
    }
}
