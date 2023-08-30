package br.unaerp.testesoftware;

public class Funcionario {
	public int idFuncionario;
	private String primeiroNome, ultimoNome;
	private float salario;
	
	public int getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(int id) {
		this.idFuncionario = id;
	}
	public String getPrimeiroNome() {
		return primeiroNome;
	}
	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}
	public String getUltimoNome() {
		return ultimoNome;
	}
	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	
	public void calcularMediaSalarial() {
		
	}
}
