public class clientes {
	
	String CNPJ;
	String nome;
	String business_area;
	
	clientes(String CNPJ, String nome, String business_area){
		this.CNPJ = CNPJ;
		this.nome = nome;
		this.business_area = business_area;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getBusiness_area() {
		return business_area;
	}

	public void setBusiness_area(String business_area) {
		this.business_area = business_area;
	}
	
}
