package Controller;

import java.util.Scanner;

import Model.ApplicationContext;
import Model.Local;
import Model.LocalDAO;
import Model.Pais;
import Model.PaisDAO;

public class LocalOpcoes {

    static LocalDAO dao = ApplicationContext.getLocalDAO();
    static PaisDAO daoP = ApplicationContext.getPaisDAO();
    static Pais pais = new Pais();
    static Local local = new Local();

    static Scanner entradaInclude = new Scanner(System.in);
    static Scanner entradaInts = new Scanner(System.in);
    static Scanner entradaIdUpdate = new Scanner(System.in);
    static Scanner entradaUpdateStrings = new Scanner(System.in);
    static Scanner entradaIdDelete = new Scanner(System.in);
    
    public static void operacoesLocal() {
    	Scanner scannerOperacao = new Scanner(System.in);
    	int opcaoOperacao = 0;
    		
	    while (opcaoOperacao <= 5){
			System.out.println("\nLOCAIS");
			System.out.println("===== MENU =====");
		    System.out.println("1 - INCLUIR LOCAL");
		    System.out.println("2 - EXIBIR TODOS OS LOCAIS");
		    System.out.println("3 - ATUALIZAR LOCAL");
		    System.out.println("4 - APAGAR LOCAL");
		    System.out.println("5 - LISTAR FUNCIONÁRIOS DOS DEPARTAMENTOS");
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
			        case 5:
			        	LocalOpcoes.listarFuncionarios();
			            break;
			        default:
			            System.out.println("Opção inválida. Digite novamente.");
			            break;
		    		}
		    	}
		}
	  //scannerOperacao.close();
    }
    
    public static void incluirLocal() {
        System.out.printf("%n");
        System.out.println("1 - INCLUIR LOCAL");

        System.out.print("Digite o ENDEREÇO: ");
        String endereco = entradaInclude.nextLine();

        System.out.print("Digite o CÓDIGO POSTAL: ");
        String codigoPostal = entradaInclude.nextLine();

        System.out.print("Digite a CIDADE: ");
        String cidade = entradaInclude.nextLine();

        System.out.print("Digite o ESTADO: ");
        String estadoProvincia = entradaInclude.nextLine();

        daoP.getAll();

        System.out.print("Digite o ID do PAÍS: ");
        int paisId = entradaInclude.nextInt();

        local.setEnderecoRua(endereco);
        local.setCodigoPostal(codigoPostal);
        local.setCidade(cidade);
        local.setEstado(estadoProvincia);
        pais.setIdPais(paisId);

        // Associa o país ao local
        local.setPais(pais);

        // Chama o método insert() para adicionar o país ao banco de dados
        dao.insert(local);

        System.out.println("-");
    }

    public static void exibirLocais() {
        System.out.printf("%n");
        System.out.println("2 - EXIBIR TODOS OS LOCAIS");

        dao.getAll();
    }

    public static void updateLocal() {
        System.out.printf("%n");
        System.out.println("3 - ATUALIZAR LOCAL");

        dao.getAll();

        System.out.print("Digite o ID: ");
        int id = entradaIdUpdate.nextInt();

        System.out.print("Digite o NOVO ENDEREÇO: ");
        String novoEndereco = entradaUpdateStrings.nextLine();

        System.out.print("Digite o NOVO CÓDIGO POSTAL: ");
        String novoCodigoPostal = entradaUpdateStrings.nextLine();

        System.out.print("Digite a NOVA CIDADE: ");
        String novaCidade = entradaUpdateStrings.nextLine();

        System.out.print("Digite o NOVO ESTADO: ");
        String novoEstado = entradaUpdateStrings.nextLine();

        daoP.getAll();

        System.out.print("Digite o NOVO ID do PAÍS: ");
        int novoIdPais = entradaIdUpdate.nextInt();

        local.setIdLocal(id);
        local.setEnderecoRua(novoEndereco);
        local.setCodigoPostal(novoCodigoPostal);
        local.setCidade(novaCidade);
        local.setEstado(novoEstado);

        pais.setIdPais(novoIdPais);

        // Associa o novo país ao local
        local.setPais(pais);

        // Chama o método update() para atualizar o local no banco de dados
        dao.update(local);
    }

    public static void deleteLocal() {
        System.out.printf("%n");
        System.out.println("4 - APAGAR LOCAL");

        dao.getAll();

        System.out.print("Digite o ID: ");
        int idDelete = entradaIdDelete.nextInt();
        local.setIdLocal(idDelete);

        dao.delete(local);
    }

    public static void listarFuncionarios() {
        System.out.printf("%n");
        System.out.println("5 - LISTAR FUNCIONÁRIOS DOS DEPARTAMENTOS");

        dao.getAll();

        System.out.print("Digite o ID: ");
        int idLocal = entradaInts.nextInt();
        local.setIdLocal(idLocal);

        dao.listarFuncionarios(local);
    }
}
