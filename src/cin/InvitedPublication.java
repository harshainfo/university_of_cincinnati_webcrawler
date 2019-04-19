package cin;

public class InvitedPublication {
	int person_id;
	int id;
	String pmid;
	String wos;
	String doi;
	String title;
	String journal;
	String authors;
	String year;
	String volume;
	String pages;
	String abstractText;
	
	public InvitedPublication(int personId, String t, String j, String a, String y, String v, String p, String ab) {
		person_id = personId;
		title = t;
		journal = j;
		authors = a;
		year = y;
		volume = v;
		pages = p;
		abstractText = ab;
	}
	public InvitedPublication(int personId, Integer j, String d) {
		person_id = personId;
		id = j;
		doi = d;
	}
}
