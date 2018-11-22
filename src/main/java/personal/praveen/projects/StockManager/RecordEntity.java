package personal.praveen.projects.StockManager;

public class RecordEntity 
{
	boolean buy;
	int quantity;
	float price;
	float totalTradedValue;
	String shareCode;
	String tradedTime;
	String nameOfSecurity;
	
	public boolean isBuy() {
		return buy;
	}

	public void setBuy(String buy) {
		if(buy.equals("B"))
			this.buy = true;
		else
			this.buy = false;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = Float.parseFloat(price);
	}

	public float getTotalTradedValue() {
		return totalTradedValue;
	}

	public void setTotalTradedValue(String totalTradedValue) {
		this.totalTradedValue = Float.parseFloat(totalTradedValue);
	}

	public String getShareCode() {
		return shareCode;
	}

	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}

	public String getTradedTime() {
		return tradedTime;
	}

	public void setTradedTime(String tradedTime) {
		this.tradedTime = tradedTime;
	}
	
	public void setNameOfSecurity(String nameOfSecurity) {
		this.nameOfSecurity = nameOfSecurity;
	}
	
	public String getNameOfSecurity() {
		return this.nameOfSecurity;
	}
	
	public void setQuantity(String quantity) {
		this.quantity = Integer.parseInt(quantity);
	}
	
	public int getQuantity() {
		return this.quantity;
	}
}
