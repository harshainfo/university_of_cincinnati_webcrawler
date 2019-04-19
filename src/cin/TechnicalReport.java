package cin;

public class TechnicalReport {
	int person_id;
	int id;
	String title;
	String authors;
	String year;
	String pages;
		
	public TechnicalReport(int personId, String t, String a, String y, String p) {
		person_id = personId;
		title = t;
		authors = a;
		year = y;
		pages = p;
	}
	public TechnicalReport(int personId, Integer j) {
		person_id = personId;
		id = j;
	}
}
