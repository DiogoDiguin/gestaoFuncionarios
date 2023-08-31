package br.unaerp.testesoftware;

public class ApplicationContext {
	private static DepartamentoDAO departamentoDAO;
    private static FuncionarioDAO funcionarioDAO;
    private static LocalDAO localDAO;
    private static PaisDAO paisDAO;
    private static RegiaoDAO regiaoDAO;
    
    static {
        funcionarioDAO = new FuncionarioDAO();
        departamentoDAO = new DepartamentoDAO();
        regiaoDAO = new RegiaoDAO();
        localDAO = new LocalDAO();
        paisDAO = new PaisDAO();
    }

    public static FuncionarioDAO getFuncionarioDAO() {
        return funcionarioDAO;
    }

    public static DepartamentoDAO getDepartamentoDAO() {
        return departamentoDAO;
    }

	public static RegiaoDAO getRegiaoDAO() {
		return regiaoDAO;
	}

	public static LocalDAO getLocalDAO() {
		return localDAO;
	}
	
	public static PaisDAO getPaisDAO() {
		return paisDAO;
	}
}
