package cin;

public class OtherPublication {
	int person_id;
	int id;
	String title;
	String journal;
	String authors;
	String year;
	String volume;
	String pages;
	
	public OtherPublication(int personId, String t, String j, String a, String y, String v) {
		person_id = personId;
		title = t;
		journal = j;
		authors = a;
		year = y;
		volume = v;
		//pages = p;
	}
}
