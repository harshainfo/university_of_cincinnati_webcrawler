package cin;

public class Lecture {

	int person_id;
	int id;
	String topic;
	String event;
	String year;
			
	public Lecture(int personId, String t, String e, String y) {
		person_id = personId;
		topic = t;
		event = e;
		year = y;
	}
	public Lecture(int personId, Integer j) {
		person_id = personId;
		id = j;
	}
}