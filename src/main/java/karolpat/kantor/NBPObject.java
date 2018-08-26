package karolpat.kantor;

public class NBPObject {

	private String table;
	private String effectiveDate;
	private String no;

	public NBPObject() {
	}

	public NBPObject(String table, String effectiveDate, String no) {
		this.table = table;
		this.effectiveDate = effectiveDate;
		this.no=no;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	@Override
	public String toString() {
		return "NBPObject [table=" + table + ", effectiveDate=" + effectiveDate + ", no=" + no + "]";
	}

}
