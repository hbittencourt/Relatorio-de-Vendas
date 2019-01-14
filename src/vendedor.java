
public class vendedor {

	String CPF;
	String nome;
	double salary;

	vendedor(String CPF, String nome, double salary) {
		this.CPF = CPF;
		this.nome = nome;
		this.salary = salary;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}
