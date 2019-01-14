
import java.util.ArrayList;
import java.util.List;

public class vendas {

	private int saleID;
	private String nome;
	
	
	List<item> itens = new ArrayList<item>();
	
	public List<item> getItens() {
		return itens;
	}

	public float calculaTotal() {
		float total = 0;
		
		for(item i : itens) {
			total += i.getQuant() * i.valUnit;
		}
		return total;
	}
	public void novoItem(String idItem, String quant, String valUnit) {
		item i = new item(idItem, quant, valUnit);
		
		itens.add(i);
		
	}

	vendas(int saleID, String nome) {
		this.saleID = saleID;
		this.nome = nome;
	}

	public int getSaleID() {
		return saleID;
	}

	public void setSaleID(int saleID) {
		this.saleID = saleID;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
