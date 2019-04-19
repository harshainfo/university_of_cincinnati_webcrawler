package cin;

public class Editorial {
	int person_id;
	int id;
	String title;
	String authors;
	String year;
	String volume;
	String pages;
		
	public Editorial(int personId, String t, String a, String y, String v, String p) {
		person_id = personId;
		title = t;
		authors = a;
		year = y;
		volume = v;
		pages = p;
	}
	public Editorial(int personId, Integer j) {
		person_id = personId;
		id = j;
	}
}
