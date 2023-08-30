package br.unaerp.testesoftware;

import java.util.Scanner;

public class FuncionarioOpcoes {
	
	App aplicacao = new App();
	
	static FuncionarioDAO dao = new FuncionarioDAO();
	static Funcionario func1 = new Funcionario();
	static RegiaoDAO daoR = new RegiaoDAO();
	
	static Scanner entradaInclude = new Scanner(System.in);
	static Scanner entradaIncludeFloat = new Scanner(System.in);
	static Scanner entradaIdUpdate = new Scanner(System.in);
	static Scanner entradaUpdateStrings = new Scanner(System.in);
	static Scanner entradaUpdateFloat = new Scanner(System.in);
	static Scanner entradaIdDelete = new Scanner(System.in);
	static Scanner entradaInts = new Scanner(System.in);
	
	public static void incluirFuncionario() {
		System.out.printf("%n");
    	System.out.println("1 - INCLUIR FUNCIONÁRIO");
    	
    	System.out.print("Digite o 1º NOME: ");
    	String Pnome = entradaInclude.nextLine();
    	
    	
        System.out.print("Digite o 2º NOME: ");
        String Unome = entradaInclude.nextLine();
                
        System.out.print("Digite o SALÁRIO: ");
        float salario = entradaIncludeFloat.nextFloat();
        
        daoR.getAll();
        System.out.print("Digite a REGIÃO: ");
        int regiao = entradaInts.nextInt();
        
        func1.setPrimeiroNome(Pnome);
        func1.setUltimoNome(Unome);
        func1.setSalario(salario);
        func1.setRegiao(regiao);

        dao.insert(func1);
        System.out.println("-");
    }
    
    public static void exibirFuncionarios() {
    	System.out.printf("%n");
    	System.out.println("2 - EXIBIR TODOS OS FUNCIONÁRIOS");
    	
    	dao.getAll();
    }
    
    public static void updateFuncionario() {
    	System.out.printf("%n");
        System.out.println("3 - ATUALIZAR FUNCIONÁRIO");
        dao.getAll();
        
        System.out.print("Digite o ID: ");
    	int id = entradaIdUpdate.nextInt();
    	func1.setIdFuncionario(id);
    	
    	System.out.print("Digite o 1º NOME: ");
    	String NovoPnome = entradaUpdateStrings.nextLine();
    	func1.setPrimeiroNome(NovoPnome);
    	
        System.out.print("Digite o 2º NOME: ");
        String NovoUnome = entradaUpdateStrings.nextLine();
        func1.setUltimoNome(NovoUnome);
        
        System.out.print("Digite o SALÁRIO: ");
        Float NovoSalario = entradaUpdateFloat.nextFloat();
        func1.setSalario(NovoSalario);
        
    	dao.update(func1);
    }
    
    public static void deleteFuncionario() {
    	System.out.printf("%n");
    	System.out.println("4 - APAGAR FUNCIONÁRIO");
    	dao.getAll();
    	System.out.print("Digite o ID: ");
    	int idDelete = entradaIdDelete.nextInt();
    	func1.setIdFuncionario(idDelete);
    	
    	dao.delete(func1);
    }
    
    public static void calcularMediaSalarial() {
    	System.out.printf("%n");
    	System.out.println("5 - MÉDIA SALARIAL POR REGIÃO");
    	
    	dao.mediaSalarial();
    }
}
