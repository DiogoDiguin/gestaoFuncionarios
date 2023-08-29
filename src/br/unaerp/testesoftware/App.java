package br.unaerp.testesoftware;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Scanner;

import br.unaerp.testesoftware.ResultSetPrinter;

public class App {

	public static void main(String[] args) throws SQLException {
		
		ResultSetPrinter rsp = new ResultSetPrinter();
		
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario func1 = new Funcionario();
		
		Scanner scannerOpcao = new Scanner(System.in);
		Scanner entradaInclude = new Scanner(System.in);
		Scanner entradaIncludeFloat = new Scanner(System.in);
		
		Scanner entradaIdUpdate = new Scanner(System.in);
		Scanner entradaUpdateStrings = new Scanner(System.in);
		Scanner entradaUpdateFloat = new Scanner(System.in);
		
		Scanner entradaIdDelete = new Scanner(System.in);
		
			int opcao = 0;

			while (opcao <= 4){
			     System.out.println("===== MENU =====");
			     System.out.println("1 - INCLUIR FUNCIONÁRIO");
			     System.out.println("2 - EXIBIR TODOS OS FUNCIONÁRIOS");
			     System.out.println("3 - ATUALIZAR FUNCIONÁRIO");
			     System.out.println("4 - APAGAR FUNCIONÁRIO");
			     System.out.print("Digite uma opção: ");
			     opcao = scannerOpcao.nextInt();

			     switch (opcao) {
			        case 1:	            	
			        	System.out.printf("%n");
			        	System.out.println("1 - INCLUIR FUNCIONÁRIO");
			        	
			        	System.out.println("Digite o NOME: ");
			        	String Pnome = entradaInclude.nextLine();
			        	
			        	
			            System.out.println("Digite o E-MAIL: ");
			            String Unome = entradaInclude.nextLine();
			            
			            
			            System.out.println("Digite o ENDERECO: ");
			            float salario = entradaIncludeFloat.nextFloat();
			            
			            func1.setPrimeiroNome(Pnome);
			            func1.setUltimoNome(Unome);
			            func1.setSalario(salario);

			            dao.insert(func1);
			            System.out.println("-");
			            break;
			        case 2:
			        	System.out.printf("%n");
			        	System.out.println("2 - EXIBIR TODOS OS FUNCIONÁRIOS");
			        	
			        	dao.getAll();
			            break;
			        case 3:
			        	System.out.printf("%n");
			            System.out.println("3 - ATUALIZAR FUNCIONÁRIO");
			            dao.getAll();
			            
			            System.out.println("Digite o ID: ");
			        	Long id = entradaIdUpdate.nextLong();
			        	func1.setIdFuncionario(id);
			        	
			        	System.out.println("Digite o 1º NOME: ");
			        	String NovoPnome = entradaUpdateStrings.nextLine();
			        	func1.setPrimeiroNome(NovoPnome);
			        	
			            System.out.println("Digite o 2º NOME: ");
			            String NovoUnome = entradaUpdateStrings.nextLine();
			            func1.setUltimoNome(NovoUnome);
			            
			            System.out.println("Digite o SALÁRIO: ");
			            Float NovoSalario = entradaUpdateFloat.nextFloat();
			            func1.setSalario(NovoSalario);
			            
			        	dao.update(func1);
			            break;
			        case 4:
			        	System.out.printf("%n");
			        	System.out.println("4 - APAGAR FUNCIONÁRIO");
			        	dao.getAll();
			        	System.out.println("Digite o ID: ");
			        	Long idDelete = entradaIdDelete.nextLong();
			        	func1.setIdFuncionario(idDelete);
			        	
			        	dao.delete(func1);
			            break;
			        default:
			            System.out.println("Opção inválida. Digite novamente.");
			            break;
			     }
			  }
		
		
	      scannerOpcao.close();
	      entradaIdUpdate.close();
	      entradaInclude.close();
	      entradaUpdateStrings.close();
	      entradaIdDelete.close();
	      entradaIncludeFloat.close();
	      entradaUpdateFloat.close();
		
//		Contato con1 = new Contato();
//		con1.setNome("Diogo");
//		con1.setEmail("diogo@gmail.com");
//		con1.setEndereco("Rua ABC, 123");
//		con1.setDataNascimento(Calendar.getInstance());
//		
//		dao.insert(con1);
		
	}

}
