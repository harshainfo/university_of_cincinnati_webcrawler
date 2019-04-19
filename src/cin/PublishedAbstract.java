package cin;

public class PublishedAbstract {
	int person_id;
	int id;
	String title;
	String journal;
	String authors;
	String year;
	String volume;
	String pages;
	
	public PublishedAbstract(int personId, String t, String j, String a, String y, String v, String p) {
		person_id = personId;
		title = t;
		journal = j;
		authors = a;
		year = y;
		volume = v;
		pages = p;
	}
}
