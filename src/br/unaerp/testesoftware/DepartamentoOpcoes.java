package br.unaerp.testesoftware;

import java.util.Scanner;

public class DepartamentoOpcoes {

    static DepartamentoDAO dao = new DepartamentoDAO();
    static FuncionarioDAO daoF = new FuncionarioDAO();
    static LocalDAO daoL = new LocalDAO();
    
    static Departamento dpto = new Departamento();
    static Funcionario funcionario = new Funcionario();
    static Local local = new Local();

    static Scanner entradaInclude = new Scanner(System.in);
    static Scanner entradaIdUpdate = new Scanner(System.in);
    static Scanner entradaUpdateStrings = new Scanner(System.in);
    static Scanner entradaIdDelete = new Scanner(System.in);

    public static void incluirDepartamento() {
        System.out.printf("%n");
        System.out.println("1 - INCLUIR LOCAL");

        System.out.println("Digite o ENDEREÇO: ");
        String endereco = entradaInclude.nextLine();
        
        System.out.println("Digite o CÓDIGO POSTAL: ");
        String codigoPostal = entradaInclude.nextLine();
        
        System.out.println("Digite a CIDADE: ");
        String cidade = entradaInclude.nextLine();
        
        System.out.println("Digite o ESTADO: ");
        String estadoProvincia = entradaInclude.nextLine();

        daoP.getAll();
        
        System.out.println("Digite o ID do PAÍS: ");
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

    public static void exibirDepartamentos() {
        System.out.printf("%n");
        System.out.println("2 - EXIBIR TODOS OS DEPARTAMENTOS");

        dao.getAll();
    }

    public static void updateDepartamento() {
        System.out.printf("%n");
        System.out.println("3 - ATUALIZAR LOCAL");

        dao.getAll();

        System.out.println("Digite o ID: ");
        int id = entradaIdUpdate.nextInt();

        System.out.println("Digite o NOVO ENDEREÇO: ");
        String novoEndereco = entradaUpdateStrings.nextLine();
        
        System.out.println("Digite o NOVO CÓDIGO POSTAL: ");
        String novoCodigoPostal = entradaUpdateStrings.nextLine();
        
        System.out.println("Digite a NOVA CIDADE: ");
        String novaCidade = entradaUpdateStrings.nextLine();
        
        System.out.println("Digite o NOVO ESTADO: ");
        String novoEstado = entradaUpdateStrings.nextLine();
        
        daoP.getAll();

        System.out.println("Digite o NOVO ID do PAÍS: ");
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

    public static void deleteDepartamento() {
        System.out.printf("%n");
        System.out.println("4 - APAGAR LOCAL");

        dao.getAll();
        
        System.out.println("Digite o ID: ");
        int idDelete = entradaIdDelete.nextInt();
        local.setIdLocal(idDelete);

        dao.delete(local);
    }
}
