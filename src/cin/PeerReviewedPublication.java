package cin;

public class PeerReviewedPublication {
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
	
	public PeerReviewedPublication(int personId, String t, String j, String a, String y, String v, String p, String ab) {
		person_id = personId;
		title = t;
		journal = j;
		authors = a;
		year = y;
		volume = v;
		pages = p;
		abstractText = ab;
	}
	public PeerReviewedPublication(int personId, Integer j, String d) {
		person_id = personId;
		id = j;
		doi = d;
	}
	
	public int getPerson_id() {
		return person_id;
	}
	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPmid() {
		return pmid;
	}
	public void setPmid(String pmid) {
		this.pmid = pmid;
	}
	public String getWos() {
		return wos;
	}
	public void setWos(String wos) {
		this.wos = wos;
	}
	public String getDoi() {
		return doi;
	}
	public void setDoi(String doi) {
		this.doi = doi;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getJournal() {
		return journal;
	}
	public void setJournal(String journal) {
		this.journal = journal;
	}
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	public String getAbstractText() {
		return abstractText;
	}
	public void setAbstractText(String abstractText) {
		this.abstractText = abstractText;
	}
}
