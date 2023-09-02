package br.unaerp.testesoftware;

import java.util.Scanner;

public class FuncionarioOpcoes {

    App aplicacao = new App();

    static FuncionarioDAO dao = ApplicationContext.getFuncionarioDAO();
    static DepartamentoDAO daoD = ApplicationContext.getDepartamentoDAO();
    static RegiaoDAO daoR = ApplicationContext.getRegiaoDAO();

    static Funcionario func1 = new Funcionario();
    static Departamento dpto = new Departamento();

    static Scanner entradaInclude = new Scanner(System.in);
    static Scanner entradaIncludeFloat = new Scanner(System.in);
    static Scanner entradaIdUpdate = new Scanner(System.in);
    static Scanner entradaUpdateStrings = new Scanner(System.in);
    static Scanner entradaUpdateFloat = new Scanner(System.in);
    static Scanner entradaIdDelete = new Scanner(System.in);
    static Scanner entradaInts = new Scanner(System.in);

    public static void operacoesFuncionario() {
    	Scanner scannerOperacao = new Scanner(System.in);
    	int opcaoOperacao = 0;
    	
    	while (opcaoOperacao <= 6){
    		System.out.println("\nFUNCIONÁRIOS");
    		System.out.println("===== MENU =====");
		    System.out.println("1 - INCLUIR FUNCIONÁRIO");
		    System.out.println("2 - EXIBIR TODOS OS FUNCIONÁRIOS");
		    System.out.println("3 - EXIBIR INFORMAÇÕES DE UM FUNCIONÁRIO");
		    System.out.println("4 - ATUALIZAR FUNCIONÁRIO");
		    System.out.println("5 - APAGAR FUNCIONÁRIO");
		    System.out.println("6 - REDEFINIR SALÁRIO");
		    System.out.print("Digite uma opção: ");
		    opcaoOperacao = scannerOperacao.nextInt();
		    	if(opcaoOperacao == 0) {
		    		break;
		    	}else {
			        switch (opcaoOperacao) {
			            case 1:
			            	incluirFuncionario();
			                break;
			            case 2:
			            	exibirFuncionario();
			                break;
			            case 3:
			            	exibirFuncionarioEspecfico();
			                break;
			            case 4:
			            	updateFuncionario();
			                break;
			            case 5:
			            	deleteFuncionario();
			                break;
			            case 6:
			            	recalcSalario();
			                break;
			            default:
			                System.out.println("Opção inválida. Digite novamente.");
			                break;
			        }
		    	}
    	}
    	//scannerOpcao.close();
    }
    
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

        daoD.getAll();
        System.out.print("Digite o DEPARTAMENTO: ");
        int dpto = entradaInts.nextInt();

        func1.setPrimeiroNome(Pnome);
        func1.setUltimoNome(Unome);
        func1.setSalario(salario);
        func1.setRegiao(regiao);
        func1.setDpto(dpto);

        dao.insert(func1);
        System.out.println("-");
    }
    
    public static void exibirFuncionario() {
    	System.out.printf("%n");
        System.out.println("2 - EXIBIR TODOS OS FUNCIONÁRIOS");

        dao.getAll();
    }
    
    public static void exibirFuncionarioEspecfico() {
    	System.out.printf("%n");
        System.out.println("3 - EXIBIR INFORMAÇÕES DE UM FUNCIONÁRIO");

        dao.getAll();
        
        System.out.print("Digite o ID: ");
        int id = entradaInts.nextInt();
        func1.setIdFuncionario(id);
        
        dao.getSpecify(func1);
        System.out.println("-");
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

        daoR.getAll();
        System.out.print("Digite a REGIÃO: ");
        int NovaRegiao = entradaInts.nextInt();
        func1.setRegiao(NovaRegiao);

        daoD.getAll();
        System.out.print("Digite o DEPARTAMENTO: ");
        int NovoDpto = entradaInts.nextInt();
        func1.setDpto(NovoDpto);

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
    
    public static void recalcSalario() {
    	System.out.printf("%n");
        System.out.println("5 - REDEFINIR SALÁRIO");
        dao.getAll();
        System.out.print("Digite o ID: ");
        int idFunc = entradaInts.nextInt();
        func1.setIdFuncionario(idFunc);

        System.out.print("Digite o percentual: ");
        int perct = entradaInts.nextInt();

        Scanner scannerCalSalario = new Scanner(System.in);
        System.out.print("1 - Aumentar, 0 - Diminuir: ");
        int calcSalario = scannerCalSalario.nextInt();

        switch (calcSalario) {
            case 1:
                dao.recalcSalario(calcSalario, perct, idFunc);
                break;
            case 0:
                dao.recalcSalario(calcSalario, perct, idFunc);
                break;
            default:
                System.out.println("Opção INVÁLIDA");
                break;
        }
    }
}
