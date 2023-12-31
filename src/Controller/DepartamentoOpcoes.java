package Controller;

import java.util.Scanner;

import Model.ApplicationContext;
import Model.Departamento;
import Model.DepartamentoDAO;
import Model.Funcionario;
import Model.FuncionarioDAO;
import Model.Local;
import Model.LocalDAO;

public class DepartamentoOpcoes {

    static DepartamentoDAO dao = ApplicationContext.getDepartamentoDAO();
    static FuncionarioDAO daoF = ApplicationContext.getFuncionarioDAO();
    static LocalDAO daoL = ApplicationContext.getLocalDAO();

    static Departamento dpto = new Departamento();
    static Funcionario funcionario = new Funcionario();
    static Local local = new Local();

    static Scanner entradaInclude = new Scanner(System.in);
    static Scanner entradaIdUpdate = new Scanner(System.in);
    static Scanner entradaInt = new Scanner(System.in);
    static Scanner entradaUpdateStrings = new Scanner(System.in);
    static Scanner entradaIdDelete = new Scanner(System.in);

    public static void operacoesDepartamento() {
    	Scanner scannerOperacao = new Scanner(System.in);
    	int opcaoOperacao = 0;
    	
	    while (opcaoOperacao <= 4){
			System.out.println("\nDEPARTAMENTOS");
			System.out.println("===== MENU =====");
		    System.out.println("1 - INCLUIR DEPARTAMENTO");
		    System.out.println("2 - EXIBIR TODOS OS DEPARTAMENTOS");
		    System.out.println("3 - ATUALIZAR DEPARTAMENTO");
		    System.out.println("4 - APAGAR DEPARTAMENTO");
		    System.out.print("Digite uma opção: ");
		    opcaoOperacao = scannerOperacao.nextInt();
		    	if(opcaoOperacao == 0) {
		    		break;
		    	}else {
		    		switch (opcaoOperacao) {
				    case 1:
				    	DepartamentoOpcoes.incluirDepartamento();
			            break;
			        case 2:
			        	DepartamentoOpcoes.exibirDepartamentos();
			            break;
			        case 3:
			        	DepartamentoOpcoes.updateDepartamento();
			            break;
			        case 4:
			        	DepartamentoOpcoes.deleteDepartamento();
			            break;
			        default:
			            System.out.println("Opção inválida. Digite novamente.");
			            break;
		    		}
		    	}
		}
    }
    
    public static void incluirDepartamento() {
        System.out.printf("%n");
        System.out.println("1 - INCLUIR DEPARTAMENTO");

        System.out.print("Digite o NOME: ");
        String nomeDpto = entradaInclude.nextLine();

        daoF.getAll();
        System.out.print("Digite o CÓDIGO DO GERENTE: ");
        int gerenteDpto = entradaInt.nextInt();

        daoL.getAll();
        System.out.print("Digite a CÓDIGO DO LOCAL: ");
        int localDpto = entradaInt.nextInt();

        dpto.setNomeDpto(nomeDpto);
        funcionario.setIdFuncionario(gerenteDpto);
        local.setIdLocal(localDpto);

        // Associa o funcionários e local ao departamento
        dpto.setGerente(funcionario);
        dpto.setLocal(local);

        // Chama o método insert() para adicionar o país ao banco de dados
        dao.insert(dpto);

        System.out.println("-");
    }

    public static void exibirDepartamentos() {
        System.out.printf("%n");
        System.out.println("2 - EXIBIR TODOS OS DEPARTAMENTOS");

        dao.getAll();
    }

    public static void updateDepartamento() {
        System.out.printf("%n");
        System.out.println("3 - ATUALIZAR DEPARTAMENTO");

        dao.getAll();

        System.out.print("Digite o ID: ");
        int id = entradaIdUpdate.nextInt();

        System.out.print("Digite o NOVO NOME: ");
        String novoNome = entradaUpdateStrings.nextLine();

        daoF.getAll();
        System.out.print("Digite o NOVO GERENTE: ");
        int novoGerente = entradaInt.nextInt();

        daoL.getAll();
        System.out.print("Digite a NOVO LOCAL: ");
        int novoLocal = entradaInt.nextInt();

        dpto.setIdDpto(id);
        dpto.setNomeDpto(novoNome);
        local.setIdLocal(novoLocal);
        funcionario.setIdFuncionario(novoGerente);

        // Associa o novo gerente e o novo local ao Departamento
        dpto.setGerente(funcionario);
        dpto.setLocal(local);

        // Chama o método update() para atualizar o Departamento no banco de dados
        dao.update(dpto);
    }

    public static void deleteDepartamento() {
        System.out.printf("%n");
        System.out.println("4 - APAGAR DEPARTAMENTO");

        dao.getAll();

        System.out.print("Digite o ID: ");
        int idDelete = entradaIdDelete.nextInt();
        dpto.setIdDpto(idDelete);

        dao.delete(dpto);
    }
}
