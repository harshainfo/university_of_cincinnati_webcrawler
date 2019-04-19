package cin;

public class BookChapter {
	int person_id;
	int id;
	String chapter;
	String authors;
	String year;
	String title;
	String publisher;
	
	public BookChapter(int personId, String c, String a, String y, String t, String p) {
		person_id = personId;
		chapter = c;
		authors = a;
		year = y;
		title = t;
		publisher = p;		
		
	}
	
}
