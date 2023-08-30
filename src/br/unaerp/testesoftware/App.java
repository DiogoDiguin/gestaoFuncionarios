package br.unaerp.testesoftware;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import br.unaerp.testesoftware.ResultSetPrinter;

public class App {

	public static void main(String[] args) throws SQLException {
		
		/*ResultSetPrinter rsp = new ResultSetPrinter();
		
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario func1 = new Funcionario();
		
		RegiaoDAO daoR = new RegiaoDAO();
		Regiao reg1 = new Regiao();*/
		
		Scanner scannerOpcao = new Scanner(System.in);
		Scanner scannerOperacao = new Scanner(System.in);
		Scanner entradaInclude = new Scanner(System.in);
		Scanner entradaIncludeFloat = new Scanner(System.in);
		
		Scanner entradaIdUpdate = new Scanner(System.in);
		Scanner entradaUpdateStrings = new Scanner(System.in);
		Scanner entradaUpdateFloat = new Scanner(System.in);
		
		Scanner entradaIdDelete = new Scanner(System.in);
		
			int opcao = 0;
			int opcaoOperacao = 0;

			while (opcao <= 5) {
				System.out.println("===== MENU =====");
				System.out.println("1 - REGIÕES");
				System.out.println("2 - PAÍSES");
			    System.out.println("3 - LOCAIS");
			    System.out.println("4 - DEPARTAMENTOS");
			    System.out.println("5 - FUNCIONÁRIOS");
			    
			    System.out.print("Digite uma opção: ");
			    opcao = scannerOpcao.nextInt();
			    
			    switch (opcao) {
			    
			    case 1:
			    		while (opcaoOperacao <= 4){
			    		System.out.println("\nREGIÕES");
			    		System.out.println("===== MENU =====");
					    System.out.println("1 - INCLUIR REGIÃO");
					    System.out.println("2 - EXIBIR TODAS AS REGIÕES");
					    System.out.println("3 - ATUALIZAR REGIÃO");
					    System.out.println("4 - APAGAR REGIÃO");
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
						        default:
						            System.out.println("Opção inválida. Digite novamente.");
						            break;
					    		}
					    	}
			    		}
			    	break;//Fim REGIÃO
			    	
			    case 2:
			    	while (opcaoOperacao <= 4){
			    		System.out.println("\nPAÍSES");
			    		System.out.println("===== MENU =====");
					    System.out.println("1 - INCLUIR PAÍS");
					    System.out.println("2 - EXIBIR TODOS OS PAÍSES");
					    System.out.println("3 - ATUALIZAR PAÍS");
					    System.out.println("4 - APAGAR PAÍS");
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
						        default:
						            System.out.println("Opção inválida. Digite novamente.");
						            break;
					    		}
					    	}
			    		}
			    	break;//Fim PAÍSES
			    	
			    case 3:
			    	while (opcaoOperacao <= 4){
			    		System.out.println("\nLOCAIS");
			    		System.out.println("===== MENU =====");
					    System.out.println("1 - INCLUIR LOCAL");
					    System.out.println("2 - EXIBIR TODOS OS LOCAIS");
					    System.out.println("3 - ATUALIZAR LOCAL");
					    System.out.println("4 - APAGAR LOCAL");
					    System.out.print("Digite uma opção: ");
					    opcaoOperacao = scannerOperacao.nextInt();
					    	if(opcaoOperacao == 0) {
					    		break;
					    	}else {
					    		switch (opcaoOperacao) {
							    case 1:
							    	LocalOpcoes.incluirLocal();
						            break;
						        case 2:
						        	LocalOpcoes.exibirLocais();
						            break;
						        case 3:
						        	LocalOpcoes.updateLocal();
						            break;
						        case 4:
						        	LocalOpcoes.deleteLocal();
						            break;
						        default:
						            System.out.println("Opção inválida. Digite novamente.");
						            break;
					    		}
					    	}
			    		}
			    	break;//Fim LOCAIS
			    	
			    case 4:
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
			    	break;//Fim DEPARTAMENTOS
			    	
			    case 5:	
			    		while (opcaoOperacao <= 4){
			    		System.out.println("\nFUNCIONÁRIOS");
			    		System.out.println("===== MENU =====");
					    System.out.println("1 - INCLUIR FUNCIONÁRIO");
					    System.out.println("2 - EXIBIR TODOS OS FUNCIONÁRIOS");
					    System.out.println("3 - ATUALIZAR FUNCIONÁRIO");
					    System.out.println("4 - APAGAR FUNCIONÁRIO");
					    System.out.print("Digite uma opção: ");
					    opcaoOperacao = scannerOperacao.nextInt();
					    	if(opcaoOperacao == 0) {
					    		break;
					    	}else {
					    		switch (opcaoOperacao) {
							    case 1:
							    	FuncionarioOpcoes.incluirFuncionario();
						            break;
						        case 2:
						        	FuncionarioOpcoes.exibirFuncionarios();
						            break;
						        case 3:
						        	FuncionarioOpcoes.updateFuncionario();
						            break;
						        case 4:
						        	FuncionarioOpcoes.deleteFuncionario();
						            break;
						        default:
						            System.out.println("Opção inválida. Digite novamente.");
						            break;
					    		}
					    	}
			    		}
			    	break;//Fim FUNCIONÁRIOS
			    				    
			    }//Final SWITCH das opções INTERNAS DO MENU
			}//Final SWITCH das opções do MENU
		
	      scannerOpcao.close();
	      entradaIdUpdate.close();
	      entradaInclude.close();
	      entradaUpdateStrings.close();
	      entradaIdDelete.close();
	      entradaIncludeFloat.close();
	      entradaUpdateFloat.close();
	      scannerOperacao.close();
	      
//		Contato con1 = new Contato();
//		con1.setNome("Diogo");
//		con1.setEmail("diogo@gmail.com");
//		con1.setEndereco("Rua ABC, 123");
//		con1.setDataNascimento(Calendar.getInstance());
//		
//		dao.insert(con1);
		
	}

}
