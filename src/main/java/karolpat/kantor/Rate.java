package karolpat.kantor;

public class Rate {

	private double mid;
	private String currency;
	private String code;
	
	public Rate() {}
	
	public Rate(String currency, String code, double mid) {
		this.currency=currency;
		this.code=code;
		this.mid=mid;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getMid() {
		return mid;
	}

	public void setMid(double mid) {
		this.mid = mid;
	}

	@Override
	public String toString() {
		return "Rate [currency= " + currency + ", code= " + code + ", mid= " + mid +"]";
	}

}
