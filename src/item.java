
public class item {
	String idItem;
	float quant;
	float valUnit;
	
	
	item(String idItem, String quant, String valUnit){
		this.idItem = idItem;
		this.quant = Float.parseFloat(quant);
		this.valUnit = Float.parseFloat(valUnit);
		
	}


	public String getIdItem() {
		return idItem;
	}


	public void setIdItem(String idItem) {
		this.idItem = idItem;
	}


	public double getQuant() {
		return quant;
	}


	public void setQuant(double quant) {
		this.quant = (float) quant;
	}


	public double getValUnit() {
		return valUnit;
	}


	public void setValUnit(double valUnit) {
		this.valUnit = (float) valUnit;
	}
}
