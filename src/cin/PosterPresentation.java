package cin;

public class PosterPresentation {

	int person_id;
	int id;
	String topic;
	String event;
	String year;
	String level;
	String authors;
		
	public PosterPresentation(int personId, String t, String e, String y, String l, String a) {
		person_id = personId;
		topic = t;
		event = e;
		year = y;
		level = l;
		authors = a;
	}
	public PosterPresentation(int personId, Integer j) {
		person_id = personId;
		id = j;
	}
}
