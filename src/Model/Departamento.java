package Model;

public class Departamento {
	public int idDpto;
	private String nomeDpto;
	private Funcionario gerente;
	private Local local;
	
	public int getIdDpto() {
		return idDpto;
	}
	public void setIdDpto(int idDpto) {
		this.idDpto = idDpto;
	}
	public String getNomeDpto() {
		return nomeDpto;
	}
	public void setNomeDpto(String nomeDpto) {
		this.nomeDpto = nomeDpto;
	}
	public Funcionario getGerente() {
		return gerente;
	}
	public void setGerente(Funcionario gerente) {
		this.gerente = gerente;
	}
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
}
