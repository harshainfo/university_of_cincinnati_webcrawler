package cin;

public class PaperPresentation {

	int person_id;
	int id;
	String topic;
	String event;
	String year;
	String level;
	String authors;
		
	public PaperPresentation(int personId, String t, String e, String y, String l, String a) {
		person_id = personId;
		topic = t;
		event = e;
		year = y;
		level = l;
		authors = a;
	}
	public PaperPresentation(int personId, Integer j) {
		person_id = personId;
		id = j;
	}
}
