package cin;

public class InvitedPresentation {
	int person_id;
	int id;
	String topic;
	String event;
	String year;
	String level;
		
	public InvitedPresentation(int personId, String t, String e, String y, String l) {
		person_id = personId;
		topic = t;
		event = e;
		year = y;
		level = l;
	}
	public InvitedPresentation(int personId, Integer j) {
		person_id = personId;
		id = j;
	}
}
