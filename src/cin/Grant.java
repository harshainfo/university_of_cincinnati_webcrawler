package cin;

public class Grant {
	int person_id;
	int id;
	String grantNo;
	String investigators;
	String from;
	String to;
	String agency;
	String amount;
	String status;
	String type;
	String role;
	String level;
	
	public Grant(int person_id, String grantNo, String investigators, String from, String to, String agency,
			String amount, String status, String type, String role, String level) {
		super();
		this.person_id = person_id;
		this.grantNo = grantNo;
		this.investigators = investigators;
		this.from = from;
		this.to = to;
		this.agency = agency;
		this.amount = amount;
		this.status = status;
		this.type = type;
		this.role = role;
		this.level = level;
	}
}
