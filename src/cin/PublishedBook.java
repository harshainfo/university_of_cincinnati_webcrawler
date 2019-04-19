package cin;

public class PublishedBook {
	int person_id;
	String id;
	String title;
	String authors;
	String year;
	String publisher;
	
	public PublishedBook(int personId, String t, String a, String y, String p) {
		person_id = personId;
		title = t;
		authors = a;
		year = y;
		publisher = p;
	}
}
