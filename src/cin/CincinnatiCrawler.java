package cin;
//import java.beans.Statement;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.regex.Matcher;
import java.sql.Statement;
import java.util.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeFilter.FilterResult;


import com.sun.xml.internal.ws.server.sei.EndpointArgumentsBuilder;

import sun.net.idn.Punycode;

//import com.sun.corba.se.pept.transport.Connection;


public class CincinnatiCrawler {

	static Logger logger = Logger.getLogger(CincinnatiCrawler.class);
	static Properties prop_file = PropertyLoader.loadProperties("cin.loader");
	static Connection conn = null;
	static Date startDate = null;
	static String schema = "cincinnati_crawl";

	static String frontUrl = "https://researchdirectory.uc.edu/Search/Search?searchType=ResearchersByName&searchText=";
	static String backUrl = "%25&_=1522472517055";
	static String mainUrl2 ="https://researchdirectory.uc.edu/Search/#c=ResearchersByName&q=%%";
	static String baseUrl = "https://researchdirectory.uc.edu";
	static String specUrl = "https://researchdirectory.uc.edu/p/abdelmza";
	static String specUrl2 = "https://researchdirectory.uc.edu/p/aaronmc";
	static String specUrl3 = "https://researchdirectory.uc.edu/p/doarnc";
	static String specUrl4 = "https://researchdirectory.uc.edu/p/chennn";
	static String specUrl5 = "https://researchdirectory.uc.edu/p/bransord";
	static String specUrl6 = "https://researchdirectory.uc.edu/p/daviskg";
	static String specUrl7 = "https://researchdirectory.uc.edu/p/chennn";
	static String specUrl8 = "https://researchdirectory.uc.edu/p/nashdb";	
	static String specUrl9 = "https://researchdirectory.uc.edu/p/beeryt";
	static String specUrl10 = "https://researchdirectory.uc.edu/p/cottrer";
	static String specUrl11 = "https://researchdirectory.uc.edu/p/winkofed";
	static String specUrl12 = "https://researchdirectory.uc.edu/p/wiglepr";
	static String specUrl13 = "https://researchdirectory.uc.edu/p/yeghialt";
	static String specUrl14 = "https://researchdirectory.uc.edu/p/yenji";
	static String specUrl15 = "https://researchdirectory.uc.edu/p/zhanx5";
	static String specUrl16 = "https://researchdirectory.uc.edu/p/zhangtl";
	static String specUrl17 = "https://researchdirectory.uc.edu/p/aicherts";
	static String specUrl18 = "https://researchdirectory.uc.edu/p/baiksy";
	static String specUrl19 = "https://researchdirectory.uc.edu/p/bankstkd";
	static String specUrl20 = "https://researchdirectory.uc.edu/p/batcheny";
	static String specUrl21 = "https://researchdirectory.uc.edu/p/berrymcl";
	static String specUrl22 = "https://researchdirectory.uc.edu/p/boeriofj";
	static String specUrl23 = "https://researchdirectory.uc.edu/p/bogendh"; // Totally different formats
	static String specUrl24 = "https://researchdirectory.uc.edu/p/bonanskn";
	static String specUrl25 = "https://researchdirectory.uc.edu/p/brintzkn";
	static String specUrl26 = "https://researchdirectory.uc.edu/p/cancelje";
	static String specUrl27 = "https://researchdirectory.uc.edu/p/canfiejs";
	static String specUrl28 = "https://researchdirectory.uc.edu/p/carnahc";
	static String specUrl29 = "https://researchdirectory.uc.edu/p/celayabz";
	static String specUrl30 = "https://researchdirectory.uc.edu/p/combssg";
	static String specUrl31 = "https://researchdirectory.uc.edu/p/cottrer";
	static String specUrl32 = "https://researchdirectory.uc.edu/p/dietzae";
	static String specUrl33 = "https://researchdirectory.uc.edu/p/dunnsc";
	static String specUrl34 = "https://researchdirectory.uc.edu/p/eisnerwr";
	static String specUrl35 = "https://researchdirectory.uc.edu/p/engelrs";
	static String specUrl36 = "https://researchdirectory.uc.edu/p/giannera";
	static String specUrl37 = "https://researchdirectory.uc.edu/p/gormanme";
	static String specUrl38 = "https://researchdirectory.uc.edu/p/govilat";
	static String specUrl39 = "https://researchdirectory.uc.edu/p/graceka";
	static String specUrl40 = "https://researchdirectory.uc.edu/p/grethesm";
	static String specUrl41 = "https://researchdirectory.uc.edu/p/gupta2aa";
	static String specUrl42 = "https://researchdirectory.uc.edu/p/guylerlr";
	static String specUrl43 = "https://researchdirectory.uc.edu/p/hosm";
	static String specUrl44 = "https://researchdirectory.uc.edu/p/jensenwb";
	static String specUrl45 = "https://researchdirectory.uc.edu/p/kanekaa";
	static String specUrl46 = "https://researchdirectory.uc.edu/p/kazmiem";
	static String specUrl47 = "https://researchdirectory.uc.edu/p/keenekn";
	static String specUrl48 = "https://researchdirectory.uc.edu/p/kelceybn";
	static String specUrl49 = "https://researchdirectory.uc.edu/p/khatrip";
	static String specUrl50 = "https://researchdirectory.uc.edu/p/kim3h4"; // Publications from CV
	static String specUrl51 = "https://researchdirectory.uc.edu/p/kirleytl";
	static String specUrl52 = "https://researchdirectory.uc.edu/p/kleinddo";
	static String specUrl53 = "https://researchdirectory.uc.edu/p/kramerkn";
	static String specUrl54 = "https://researchdirectory.uc.edu/p/lee2rc";
	static String specUrl55 = "https://researchdirectory.uc.edu/p/leejo";
	static String specUrl56 = "https://researchdirectory.uc.edu/p/lemenll";
	static String specUrl57 = "https://researchdirectory.uc.edu/p/likv"; //Directs to external link for publication information
	static String specUrl58 = "https://researchdirectory.uc.edu/p/maclenaj";
	static String specUrl59 = "https://researchdirectory.uc.edu/p/mahonemc";
	static String specUrl60 = "https://researchdirectory.uc.edu/p/marcumu";
	static String specUrl61 = "https://researchdirectory.uc.edu/p/mcnamar";
	static String specUrl62 = "https://researchdirectory.uc.edu/p/moore2so";
	static String specUrl63 = "https://researchdirectory.uc.edu/p/moultojs";
	static String specUrl64 = "https://researchdirectory.uc.edu/p/narmond";
	static String specUrl65 = "https://researchdirectory.uc.edu/p/ngsl";
	static String specUrl66 = "https://researchdirectory.uc.edu/p/okaneb";
	static String specUrl67 = "https://researchdirectory.uc.edu/p/paehe";
	static String specUrl68 = "https://researchdirectory.uc.edu/p/parran";
	static String specUrl69 = "https://researchdirectory.uc.edu/p/peteetbj";
	static String specUrl70 = "https://researchdirectory.uc.edu/p/phillicr";
	static String specUrl71 = "https://researchdirectory.uc.edu/p/prabhaab";
	static String specUrl72 = "https://researchdirectory.uc.edu/p/privitmb";
	static String specUrl73 = "https://researchdirectory.uc.edu/p/puffra";
	static String specUrl74 = "https://researchdirectory.uc.edu/p/puhalldm";
	static String specUrl75 = "https://researchdirectory.uc.edu/p/raywt";
	static String specUrl76 = "https://researchdirectory.uc.edu/p/reidts";
	static String specUrl77 = "https://researchdirectory.uc.edu/p/reutmass";
	static String specUrl78 = "https://researchdirectory.uc.edu/p/riordee";
	static String specUrl79 = "https://researchdirectory.uc.edu/p/sampsedi";
	static String specUrl80 = "https://researchdirectory.uc.edu/p/schlagsp";
	static String specUrl81 = "https://researchdirectory.uc.edu/p/shanmun";
	static String specUrl82 = "https://researchdirectory.uc.edu/p/shanovvn";
	static String specUrl83 = "https://researchdirectory.uc.edu/p/shearnj";
	static String specUrl84 = "https://researchdirectory.uc.edu/p/escuece";
	static String specUrl85 = "https://researchdirectory.uc.edu/p/sivagas";
	static String specUrl86 = "https://researchdirectory.uc.edu/p/tanseyem";
	static String specUrl87 = "https://researchdirectory.uc.edu/p/kendalce";
	static String specUrl88 = "https://researchdirectory.uc.edu/p/thompay";
	static String specUrl89 = "https://researchdirectory.uc.edu/p/ustickje";
	static String specUrl90 = "https://researchdirectory.uc.edu/p/vanvoop";
	static String specUrl91 = "https://researchdirectory.uc.edu/p/wallacgn";
	static String specUrl92 = "https://researchdirectory.uc.edu/p/washink2";
	static String specUrl93 = "https://researchdirectory.uc.edu/p/weastja";
	static String specUrl94 = "https://researchdirectory.uc.edu/p/weissg";
	static String specUrl95 = "https://researchdirectory.uc.edu/p/wiglepr";
	static String specUrl96 = "https://researchdirectory.uc.edu/p/winkofed";
	static String specUrl97 = "https://researchdirectory.uc.edu/p/yeghialt";
	static String specUrl98 = "https://researchdirectory.uc.edu/p/yenji";
		
	static Connection con;


	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException{
		PropertyConfigurator.configure("log4j.info");
		conn = getConnection();
		startDate = new Date();

		truncateCommands();

		CincinnatiCrawler theCrawler = new CincinnatiCrawler();
		theCrawler.crawlPeople();
		//theCrawler.crawlTrials();

		con = DbCon.getConnection();
		//////System.out.println("Cincinnati crawl started at "+ new SimpleDateFormat("MM-dd-yyyy HH:mm:ss.S").format(new Date()));
		//XMLTraverse2(specUrl98);
		//////System.out.println("Cincinnati crawl ended at "+ new SimpleDateFormat("MM-dd-yyyy HH:mm:ss.S").format(new Date()));

	}
	static void truncateCommands() {
		
		try {
			executeCommand("truncate "+ schema +".person");
		
		executeCommand("truncate "+ schema +".conference_publication");
		executeCommand("truncate "+ schema +".journal_publication");
		executeCommand("truncate "+ schema +".book_publication");
		executeCommand("truncate "+ schema +".presentation_publication");
		executeCommand("truncate "+ schema +".academic_appointment");
		executeCommand("truncate "+ schema +".administrative_appointment");
		executeCommand("truncate "+ schema +".clinical_focus");
		executeCommand("truncate "+ schema +".clinical_trial_role");
		executeCommand("truncate "+ schema +".clinical_trial");
		executeCommand("truncate "+ schema +".clinical_trial_intervention");
		executeCommand("truncate "+ schema +".clinical_trial_investigator");
		executeCommand("truncate "+ schema +".published_abstract");
		executeCommand("truncate "+ schema +".published_book");
		executeCommand("truncate "+ schema +".book_chapter");
		executeCommand("truncate "+ schema +".other_publication");
		executeCommand("truncate "+ schema +".technical_report");
		executeCommand("truncate "+ schema +".invited_publication");
		executeCommand("truncate "+ schema +".invited_presentation");
		executeCommand("truncate "+ schema +".poster_presentation");
		executeCommand("truncate "+ schema +".paper_presentation");
		executeCommand("truncate "+ schema +".lecture");
		executeCommand("truncate "+ schema +".grantTable");
		executeCommand("truncate "+ schema +".editorial");

		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	static Connection getConnection() throws ClassNotFoundException, SQLException {
		String use_ssl = prop_file.getProperty("nihdb.use.ssl", "false");
		logger.debug("Database SSL: " + use_ssl);

		String databaseHost = prop_file.getProperty("db.host", "localhost");
		logger.debug("Database Host: " + databaseHost);

		String databaseName = prop_file.getProperty("db.name", "db");
		logger.debug("Database Name: " + databaseName);

		String db_url = prop_file.getProperty("nihdb.url", "jdbc:postgresql://" + databaseHost + "/" + databaseName);
		logger.debug("Database URL: " + db_url);

		Class.forName("org.postgresql.Driver");
		Properties props = new Properties();
		props.setProperty("user", "un");
		props.setProperty("password", "pw");
		if (use_ssl.equals("true")) {
			props.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
			props.setProperty("ssl", "true");
		}	
		Connection conn = DriverManager.getConnection(db_url, props);
		//		conn.setAutoCommit(false);

		return conn;

	}

	static void executeCommand(String command) throws SQLException {
		logger.info(command+"...");
		conn.prepareStatement(command).execute();
	}

	String fullName = null;
	String title = null;
	String email = null;
	String phone = null;
	String researchOverview = null;

	int seqnum = 0;

	void crawlPeople() throws IOException {
		for (char letter = 'a'; letter <= 'z'; letter++) {
			logger.info("letter: " + letter);
			Document doc = Jsoup.connect(frontUrl+letter+backUrl).timeout(0).get();
			//Elements elements = doc.getElementsByClass("container");
			Element theElement = doc.getElementsByTag("ul").first();
			logger.trace("element: " + theElement);
			for (Element element : theElement.getElementsByTag("li")) {
				// Link for each person is in a href value in h3 element Ex: <h3><a href="/p/johndoe">John Doe</a></h3>
				////System.out.println(element.html());
				Element h3Element = (Element) element.getElementsByTag("h3").get(0);	
				Element aElement = (Element) h3Element.getElementsByTag("a").get(0);

				String linkHref = aElement.attr("href");
				String linkText = aElement.text();

				logger.trace("\thref: " + linkHref);
				try {
					XMLTraverse2(baseUrl + linkHref);
				} catch (Exception e) {
					logger.error("error processing person " + e);
					for (StackTraceElement trace : e.getStackTrace())
						logger.error("\t" + trace);
				}
				reset();
			}
		}
	}

	void reset() {
		fullName = null;
		title = null;
		email = null;
		phone = null;
		researchOverview = null;
	}



	void extractPerson(String url) throws IOException, SQLException {
		XMLTraverse2(url);

	}
	public static void XMLTraverse2(String url) {

		Document doc;
		try {

			int person_id = 0;
			String person_uri = "";
			String person_name ="";
			String person_title = "";
			String person_email = "";
			String person_phone = "";
			String person_overview = "";

			org.jsoup.Connection jsoupCon1 = Jsoup.connect(url);
			jsoupCon1.timeout(9000000);
			doc = jsoupCon1.get();

			person_uri = url;
			//////System.out.println("person_url " +person_uri);

			if(doc.select("h1.profile-name").size() > 0)
				person_name = doc.select("h1.profile-name").first().html();			
			//////System.out.println("person_name " +person_name);

			if(doc.select("h2.profile-title").size() > 0)
				person_title = doc.select("h2.profile-title").first().html();	
			//////System.out.println("person_title " +person_title);	

			Elements personal_data = doc.select("div.contact-links");
			//////System.out.println(personal_data.first().select("a").first().attr("href"));
			for(Element aHref : personal_data.select("a")) {
				if(aHref.attr("href").contains("mailto:") && aHref.attr("href").length() > 7) {
					person_email = aHref.attr("href").substring(aHref.attr("href").indexOf(":")+1, aHref.attr("href").length());
					//////System.out.println("person_email "+person_email);
				}
				if(aHref.attr("href").contains("tel:") && aHref.attr("href").length() > 4) {
					person_phone = aHref.attr("href").substring(aHref.attr("href").indexOf(":")+1, aHref.attr("href").length());
					//////System.out.println("person_phone "+person_phone);
				}

			}

			Element professional_data = doc.getElementsByTag("div").get(0).getElementsByTag("div").get(0).getElementsByTag("div").get(0).getElementsByTag("div").get(0).getElementById("professional-data");
			Elements articlesInProfData = (Elements) professional_data.getElementsByTag("article");

			Elements articlesInProfessionalSummary = professional_data.select("article:has(header#summary)");

			String professionalSummary = "";

			if(articlesInProfessionalSummary.size() > 0) {
				professionalSummary = "Professional Summary: ";
				Elements pInProfSumm = articlesInProfessionalSummary.get(0).select("p");
				if(pInProfSumm.size() > 0) {
					for(int i=0; i<pInProfSumm.size(); i++) {
						if(i==0) {
							professionalSummary += pInProfSumm.get(i).text().trim();
						}else {
							professionalSummary += " "+pInProfSumm.get(i).text().trim();
						}
					}
				}
				Elements liInProfSumm = articlesInProfessionalSummary.get(0).select("li");
				if(liInProfSumm.size() > 0) {
					for(int i=0; i<liInProfSumm.size(); i++) {
						if(i==0) {
							professionalSummary += liInProfSumm.get(i).text().trim();
						}else {
							professionalSummary += ";"+liInProfSumm.get(i).text().trim();
						}
					}
				}
			}

			Elements artiResInterests = professional_data.select("article:has(header#rsearchInterests)");

			String researchInterests = "";
			if(artiResInterests.size() > 0) {
				researchInterests = "Research Interests: ";

				Elements pInResIntr = artiResInterests.get(0).select("p");
				if(pInResIntr.size() > 0) {
					for(int i=0; i<pInResIntr.size(); i++) {
						if(i==0) {
							researchInterests = researchInterests + pInResIntr.get(i).text().trim();
						}else {
							researchInterests += "\n"+pInResIntr.get(i).text().trim();
						}
					}
				}
				Elements liInResIntr = artiResInterests.get(0).select("li");
				if(liInResIntr.size() > 0) {
					for(int i=0; i<liInResIntr.size(); i++) {
						if(i==0) {
							researchInterests += liInResIntr.get(i).text().trim();
						}else {
							researchInterests += ";"+liInResIntr.get(i).text().trim();
						}
					}

				}
			}

			if(professionalSummary.trim().equalsIgnoreCase("") && researchInterests.trim().equalsIgnoreCase("")) {
				person_overview = "";
			}else if(professionalSummary.trim().equalsIgnoreCase("")){
				person_overview = researchInterests.trim();
			}else if(researchInterests.trim().equalsIgnoreCase("")) {
				person_overview = professionalSummary.trim();
			}else {
				person_overview = professionalSummary + "\n"+researchInterests;
			}

			//////System.out.println("person_overview "+person_overview);
			logger.info("fullName: " + person_name);
			int personMaxId = writeToPerson(person_uri, person_name, person_title, person_email, person_phone, person_overview);
			//Elements publicationsAll = professional_data.select("article:has(h3:contains(Peer Reviewed Publications))");
			//System.out.println("personMaxId="+personMaxId);
			Elements publicationsAll = professional_data.select("article:has(h2:contains(Publications))");
			//////System.out.println("Size="+publicationsAll.size());
			if(publicationsAll.size() > 0){
				crawlPublications(publicationsAll, personMaxId);
			}
			
			Elements presentationsAll = professional_data.select("article:has(h2:contains(Presentations))");
			//////System.out.println("Size="+publicationsAll.size());
			if(presentationsAll.size() > 0){
				crawlPresentations(presentationsAll, personMaxId);
			}
			
			Elements grantsAll = professional_data.select("article:has(h2:contains(Research Support))");
			//////System.out.println("Size="+publicationsAll.size());
			if(presentationsAll.size() > 0){
				crawlGrants(grantsAll, personMaxId);
			}

		}catch(Exception e) {
			//e.printStackTrace();
			logger.error("error processing person " + e);
			for (StackTraceElement trace : e.getStackTrace())
				logger.error("\t" + trace);
		}
	}
	
	public static void crawlPublications(Elements publicationsAll, int personMaxId) {
		for(Element articlePublications: publicationsAll) {
			//Element articlePublications = publicationsAll.get(0);
			////System.out.println("Publications\n"+articlePublications);

			Elements publications = articlePublications.children();
			Element currP;
			int lastJournalId = 0, lastAbstractId = 0, lastBookId = 0, lastEditorialId = 0,
					lastBookChapterId = 0, lastOtherPublicationId=0, lastInvitedPublicationId = 0,
					lastId = 0, lastTechnicalReportId = 0;
			boolean inPublishedAbstracts = false, 
					inPeerReviewedPublications = false, 
					inOtherPublications = false, 
					inPublishedBooks = false, 
					inBookChapters = false,
					inInvitedPublications = false,
					inEditorial = false,
					inTechnicalReports = false;
			for(Element ele : publications) {

				if(ele.is("h3")) {
					if(ele.text().contains("Published Abstracts")) {
						inPublishedAbstracts = true;
						inPeerReviewedPublications = false; 
						inOtherPublications = false;
						inPublishedBooks = false;
						inBookChapters = false;
						inInvitedPublications = false;
						inTechnicalReports = false;
						inEditorial = false;
					}else if(ele.text().contains("Peer Reviewed Publications")){
						inPeerReviewedPublications = true;
						inPublishedAbstracts = false; 
						inOtherPublications = false;
						inPublishedBooks = false;
						inBookChapters = false;
						inInvitedPublications = false;
						inTechnicalReports = false;
						inEditorial = false;
					}else if(ele.text().contains("Other Publications")){
						inOtherPublications = true;
						inPublishedAbstracts = false;
						inPeerReviewedPublications = false; 
						inPublishedBooks = false;
						inBookChapters = false;
						inInvitedPublications = false;
						inTechnicalReports = false;
						inEditorial = false;
					}else if(ele.text().contains("Published Books")){
						inPublishedBooks = true;
						inPublishedAbstracts = false;
						inPeerReviewedPublications = false; 
						inOtherPublications = false;
						inBookChapters = false;
						inInvitedPublications = false;
						inTechnicalReports = false;
						inEditorial = false;
					}else if(ele.text().contains("Book Chapter")){
						inBookChapters = true;
						inPublishedAbstracts = false; 
						inPeerReviewedPublications = false;
						inOtherPublications = false;
						inPublishedBooks = false;
						inInvitedPublications = false;
						inTechnicalReports = false;
						inEditorial = false;
					}else if(ele.text().contains("Invited Publications")){
						inInvitedPublications = true;
						inPublishedAbstracts = false;
						inPeerReviewedPublications = false; 
						inOtherPublications = false;
						inPublishedBooks = false;
						inBookChapters = false;
						inTechnicalReports = false;
						inEditorial = false;
					}else if(ele.text().contains("Editorial")){
						inEditorial = true;
						inInvitedPublications = false;
						inPublishedAbstracts = false;
						inPeerReviewedPublications = false; 
						inOtherPublications = false;
						inPublishedBooks = false;
						inBookChapters = false;
						inTechnicalReports = false;
					}else if(ele.text().contains("Technical Reports")){
						inTechnicalReports = true;
						inEditorial = false;
						inInvitedPublications = false;
						inPublishedAbstracts = false;
						inPeerReviewedPublications = false; 
						inOtherPublications = false;
						inPublishedBooks = false;
						inBookChapters = false;
					}
				}
				if(inPublishedAbstracts) {
					if(ele.is("p")) {
						PublishedAbstract pa = extractPublishedAbstract(ele, personMaxId);
						lastAbstractId = writeToPublishedAbstractTable(pa);
					}
				}else if(inPeerReviewedPublications) {
					if(ele.is("p")) {
						PeerReviewedPublication prp = extractPeerReviewedPublication(ele, personMaxId);
						////System.out.println("Prp.personId="+prp.person_id);
						lastJournalId = writeToPublication(prp);
					}else if(ele.is("div")){
						PeerReviewedPublication prp = extractPeerReviewedPublicationDoi(ele, personMaxId, lastJournalId);
						////System.out.println("Prp.personId="+prp.person_id);
						lastJournalId = updatePublication(prp);
					}
				}else if(inPublishedBooks) {
					if(ele.is("p")) {
						PublishedBook pb = extractPublishedBook(ele, personMaxId);
						lastBookId = writeToPublishedBookTable(pb);
					}
				}else if(inBookChapters) {
					if(ele.is("p")) {
						BookChapter bc = extractBookChapter(ele, personMaxId);
						lastBookChapterId = writeToBookChapterTable(bc);
					}
				}else if(inOtherPublications) {
					if(ele.is("p")) {
						OtherPublication op = extractOtherPublication(ele, personMaxId);
						lastOtherPublicationId = writeToOtherPublicationTable(op);
					}
				}else if(inInvitedPublications) {
					if(ele.is("p")) {
						InvitedPublication ip = extractInvitedPublication(ele, personMaxId);
						lastInvitedPublicationId = writeToInvitedPublicationTable(ip);
					}
				}else if(inEditorial) {
					if(ele.is("p")) {
						Editorial ed = extractEditorial(ele, personMaxId);
						lastEditorialId = writeToEditorialTable(ed);
					}
				}else if(inTechnicalReports) {
					if(ele.is("p")) {
						
						TechnicalReport tr = extractTechnicalReport(ele, personMaxId);
						lastTechnicalReportId = writeToTechnicalReportTable(tr);
					}
				}

			}
		}
	}
	
	public static void crawlPresentations(Elements presentationsAll, int personMaxId) {
		
		
		for(Element articlePresentations: presentationsAll) {
			//Element articlePublications = publicationsAll.get(0);
			////System.out.println("Publications\n"+articlePublications);
			////System.out.println("articlePresentations.size()"+articlePresentations.text());

			Elements presentations = articlePresentations.children();
			//System.out.println("articlePresentations.children()"+articlePresentations.children().size());
			Element currP;
			int lastJournalId = 0, lastAbstractId = 0, lastBookId = 0, 
					lastBookChapterId = 0, lastPosterPresentationId=0, lastInvitedPresentationId = 0,
					lastEditorialId = 0, lastTechnicalReportId = 0;
			boolean 
					inLecture = false,
					inPosterPresentations = false,
					inPaperPresentations = false,
					inInvitedPresentations = false;

			int count = 0;
			for(Element ele : presentations) {
				//System.out.println(count);
				//System.out.println("ele="+ele.text());
				if(ele.is("h3")) {
					if(ele.text().contains("Invited Presentations")) {
						inInvitedPresentations = true;
						inPosterPresentations = false;
						inPaperPresentations = false;
						inLecture = false;
					}else if(ele.text().contains("Poster Presentations")) {
						inPosterPresentations = true;
						inInvitedPresentations = false;
						inPaperPresentations = false;
						inLecture = false;
					}else if(ele.text().contains("Paper Presentations")) {
						inPaperPresentations = true;
						inPosterPresentations = false;
						inInvitedPresentations = false;
						inLecture = false;
					}else if(ele.text().contains("Lecture")) {
						inLecture = true;
						inPaperPresentations = false;
						inPosterPresentations = false;
						inInvitedPresentations = false;
					}
				}
				if(inInvitedPresentations) {
					if(ele.is("p")) {
						InvitedPresentation ip = extractInvitedPresentation(ele, personMaxId);
						lastInvitedPresentationId = writeToInvitedPresentationTable(ip);
					}
				}else if(inPosterPresentations) {
					if(ele.is("p")) {
						PosterPresentation pp = extractPosterPresentation(ele, personMaxId);
						lastPosterPresentationId = writeToPosterPresentationTable(pp);
					}
				}else if(inPaperPresentations) {
					if(ele.is("p")) {
						PaperPresentation pp = extractPaperPresentation(ele, personMaxId);
						lastPosterPresentationId = writeToPaperPresentationTable(pp);
					}
				}else if(inLecture) {
					if(ele.is("p")) {
						Lecture pp = extractLecture(ele, personMaxId);
						lastPosterPresentationId = writeToLectureTable(pp);
					}
				}
				count++;
			}
		}
	}
	
	
	public static void crawlGrants(Elements grantsAll, int personMaxId) {
		for(Element articleGrants: grantsAll) {
			//Element articlePublications = publicationsAll.get(0);
			////System.out.println("Grant1\n"+grantsAll.size());
			////System.out.println("articleGrants"+articleGrants.html());

			Elements grants = articleGrants.children();
			////System.out.println("Grant=\n"+articleGrants.text());
			Element currP;
			int lastGrantId = 0;
			boolean inGrants = false;
			int count = 0;
			for(Element ele : grants) {
				////System.out.println(count);
				////System.out.println("ele="+ele.html());
				if(ele.is("header")) {
					if(ele.text().contains("Research Support")) {
						inGrants = true;
					}
				}
				////System.out.println("inGrants="+inGrants);
				if(inGrants) {
					////System.out.println("In Grants="+inGrants);
					if(ele.is("p")) {
						////System.out.println("In P");
						Grant g = extractGrant(ele, personMaxId);
						lastGrantId = writeToGrantTable(g);
					}
				}
				count++;
			}
		}
	}
	public static Grant extractGrant(Element ele, int personId) {
		Element currP;
		logger.info("grant: "+ele.text());
		
		String grantNo="", grantString = "";
		String investigators="", invString="";
		String from="", dateString = "";
		String to="";
		String agency="",agencyString="";
		String amount="", amountString="";
		String status="", statusString="";
		String type="",typeString="";
		String level="", levelString="";
		String role="", roleString="";
		
		
		
		String eleText = ele.text();
		
		String eleWOGrant=eleText, eleWOGrantInv="", eleWOGrantInvDate = "", 
				eleWOGrantInvDateAgency = "", eleWOGrantInvDateAgencyRole="", eleWOGrantInvDateAgencyRoleAmount="",
						eleWOGrantInvDateAgencyRoleAmountStatus="", eleWOGrantInvDateAgencyRoleAmountStatusType="", 
						eleWOGrantInvDateAgencyRoleAmountStatusTypeLevel="";
		
		////System.out.println("ele=\n"+eleText);
		Matcher grantMatcherWithInves = Pattern.compile("Grant:\\s#.*?Inves").matcher(eleText); //||[0-9]{2}\\-{1}[0-9]{2}]
		Matcher grantMatcherWithDate = Pattern.compile("Grant:\\s#.*?[0-9]{2}\\-{1}[0-9]{2}\\-{1}[0-9]{4}").matcher(eleText); //||[0-9]{2}\\-{1}[0-9]{2}]
		Matcher grantMatcherWithBadDate = Pattern.compile("Grant:\\s#.*?BAD\\sDATE").matcher(eleText); //||[0-9]{2}\\-{1}[0-9]{2}]
		////System.out.println("grantMatcherWithInves.find()"+grantMatcherWithInves.find());
		if(grantMatcherWithInves.find() || grantMatcherWithDate.find() || grantMatcherWithBadDate.find()) {
			grantMatcherWithInves.reset();
			grantMatcherWithDate.reset();
			grantMatcherWithBadDate.reset();
			if(grantMatcherWithInves.find()) {
				////System.out.println("In if");
				grantString = grantMatcherWithInves.group(0);
				////System.out.println("grantString"+grantString);
				grantNo = grantString.replaceAll("\\sInves$", "");
				grantNo = grantNo.replaceAll("^Grant:\\s#", "");
				
				eleWOGrant = eleText.substring(eleText.indexOf(grantString)+grantString.length()-5, eleText.length()).trim();
			}else if(grantMatcherWithDate.find()){
				grantString = grantMatcherWithDate.group(0);
				
				grantString = grantString.replaceAll("\\s[0-9]{2}\\-{1}[0-9]{2}\\-{1}[0-9]{4}$", "");
				grantNo = grantString.replaceAll("^Grant:\\s#", "");
				////System.out.println("grantString"+grantString);
				// String xString = grantString.substring(0, grantString.length()-10);
				////System.out.println(grantString.indexOf(xString)+"xString"+xString);
				eleWOGrant = eleText.substring(eleText.indexOf(grantString)+grantString.length(), eleText.length()).trim();
			}else if(grantMatcherWithBadDate.find()) {
				grantString = grantMatcherWithBadDate.group(0);
				
				grantString = grantString.replaceAll("\\sBAD DATE$", "");
				grantNo = grantString.replaceAll("^Grant:\\s#", "");
				////System.out.println("grantString"+grantString);
				// String xString = grantString.substring(0, grantString.length()-10);
				////System.out.println(grantString.indexOf(xString)+"xString"+xString);
				eleWOGrant = eleText.substring(eleText.indexOf(grantString)+grantString.length(), eleText.length()).trim();
			}
		}else {
			eleWOGrant = eleText;
		}
		////System.out.println("GrantNo="+grantNo);
		
		////System.out.println("eleWOGrant"+eleWOGrant);
		
		Matcher invMatcherWithDate = Pattern.compile("Investigators:.*?\\d").matcher(eleText);
		Matcher invMatcherWithBadDate = Pattern.compile("Investigators:.*?\\bBAD\\b\\s\\bDATE\\b").matcher(eleText);
		
		if(invMatcherWithDate.find() || invMatcherWithBadDate.find()) {
			invMatcherWithDate.reset();
			invMatcherWithBadDate.reset();
			if(invMatcherWithDate.find()) {
				////System.out.println("In if");
				invString = invMatcherWithDate.group(0);
				////System.out.println("invString"+invString);
				investigators = invString.replaceAll("^Investigators:", "");
				investigators = investigators.replaceAll("\\d$", "");
				
				eleWOGrantInv = eleWOGrant.substring(eleWOGrant.indexOf(invString)+invString.length()-1, eleWOGrant.length()).trim();
			}else if(invMatcherWithBadDate.find()) {
				////System.out.println("In if");
				invString = invMatcherWithBadDate.group(0);
				////System.out.println("invString"+invString);
				investigators = invString.replaceAll("^Investigators:", "");
				investigators = investigators.replaceAll("\\bBAD\\b\\s\\bDATE\\b$", "");
				
				eleWOGrantInv = eleWOGrant.substring(eleWOGrant.indexOf(invString)+invString.length()-8, eleWOGrant.length()).trim();
			}
		}else {
			eleWOGrantInv = eleWOGrant;
		}
		////System.out.println("Investigators="+investigators);
		
		////System.out.println("eleWOGrantInv="+eleWOGrantInv);
		Matcher dateMatcher = Pattern.compile("^[0-9]{2}\\-{1}[0-9]{2}\\-{1}[0-9]{4}\\s{1}\\-{1}[0-9]{2}\\-{1}[0-9]{2}\\-{1}[0-9]{4}").matcher(eleWOGrantInv);
		Matcher dateMatcherWithBadDateFront = Pattern.compile("^\\bBAD\\b\\s{0,1}\\bDATE\\b\\s{1}\\-{1}[0-9]{2}\\-{1}[0-9]{2}\\-{1}[0-9]{4}").matcher(eleWOGrantInv);
		Matcher dateMatcherWithBadDateBack = Pattern.compile("^[0-9]{2}\\-{1}[0-9]{2}\\-{1}[0-9]{4}\\s{1}\\-{1}\\bBAD\\b\\s\\bDATE\\b").matcher(eleWOGrantInv);
		Matcher dateMatcherWithBadDates = Pattern.compile("^\\bBAD\\b\\s\\bDATE\\b\\s\\bBAD\\b\\s\\bDATE\\b").matcher(eleWOGrantInv);
		if(dateMatcher.find() || dateMatcherWithBadDateFront.find() || dateMatcherWithBadDateBack.find() || dateMatcherWithBadDates.find()) {
			dateMatcher.reset();
			dateMatcherWithBadDateFront.reset();
			dateMatcherWithBadDateBack.reset();
			dateMatcherWithBadDates.reset();
			if(dateMatcher.find()) {
				dateString = dateMatcher.group(0);
				////System.out.println("dateString"+dateString);
				////System.out.println("dateMatcher"+dateString);
				from = dateString.split("[0-9]{2}\\-{1}[0-9]{2}\\-{1}[0-9]{4}$")[0];
				to = dateString.replaceAll(from, "");
				from =  from.replaceAll("\\-$", "");
				from =  from.replaceAll("\\s$", "");
				eleWOGrantInvDate = eleWOGrantInv.substring(eleWOGrantInv.indexOf(dateString)+dateString.length(), eleWOGrantInv.length()).trim();
				
			}else if(dateMatcherWithBadDates.find()) {
				dateString = dateMatcherWithBadDates.group(0);
				////System.out.println("dateMatcherWithBadDates"+dateString);
				////System.out.println("dateString"+dateString);
				from = "BAD DATE";
				to = "BAD DATE";
								
				eleWOGrantInvDate = eleWOGrantInv.substring(eleWOGrantInv.indexOf(dateString)+dateString.length(), eleWOGrantInv.length()).trim();
			}else if(dateMatcherWithBadDateFront.find()) {
				dateString = dateMatcherWithBadDateFront.group(0);
				////System.out.println("dateMatcherWithBadDateFront"+dateString);
				////System.out.println("dateString"+dateString);
				from = dateString.split("[0-9]{2}\\-{1}[0-9]{2}\\-{1}[0-9]{4}$")[0];
				to = dateString.replaceAll(from, "");
				from =  from.replaceAll("\\-$", "");
				from =  from.replaceAll("\\s$", "");
				
				eleWOGrantInvDate = eleWOGrantInv.substring(eleWOGrantInv.indexOf(dateString)+dateString.length(), eleWOGrantInv.length()).trim();
			}else if(dateMatcherWithBadDateBack.find()) {
				dateString = dateMatcherWithBadDateBack.group(0);
				////System.out.println("dateMatcherWithBadDateBack"+dateString);
				////System.out.println("dateString"+dateString);
				from = dateString.split("[0-9]{2}\\-{1}[0-9]{2}\\-{1}[0-9]{4}$")[0];
				to = dateString.replaceAll(from, "");
				from =  from.replaceAll("\\-$", "");
				from =  from.replaceAll("\\s$", "");
				
				eleWOGrantInvDate = eleWOGrantInv.substring(eleWOGrantInv.indexOf(dateString)+dateString.length(), eleWOGrantInv.length()).trim();
			}
		}
		////System.out.println("from="+from);
		////System.out.println("to="+to);
		
		////System.out.println("eleWOGrantInvDate="+eleWOGrantInvDate);
		
		Matcher agencyMatcherWithRole = Pattern.compile("^.*?Role:").matcher(eleWOGrantInvDate);
		Matcher agencyMatcherWithDollar = Pattern.compile("^.*?[$]").matcher(eleWOGrantInvDate);
		
		if(agencyMatcherWithRole.find() || agencyMatcherWithDollar.find()) {
			agencyMatcherWithRole.reset();
			agencyMatcherWithDollar.reset();			
			if(agencyMatcherWithRole.find()) {
				////System.out.println("In if");
				agencyString = agencyMatcherWithRole.group(0);
				////System.out.println("yearString"+yearString);
				agency = agencyString.replaceAll("Role:", "");
				//investigators = investigators.replaceAll("\\d$", "");
				
				eleWOGrantInvDateAgency = eleWOGrantInvDate.substring(eleWOGrantInvDate.indexOf(agencyString)+agencyString.length()-5, eleWOGrantInvDate.length()).trim();
			}else if(agencyMatcherWithDollar.find()) {
				////System.out.println("In if");
				agencyString = agencyMatcherWithDollar.group(0);
				agency = agencyString.replaceAll("[$]", "");
				
				eleWOGrantInvDateAgency = eleWOGrantInvDate.substring(eleWOGrantInvDate.indexOf(agencyString)+agencyString.length()-1, eleWOGrantInvDate.length()).trim();
			}
		}else{
				eleWOGrantInvDateAgency = eleWOGrantInvDate;
		}
		
		////System.out.println("eleWOGrantInvDateAgency="+eleWOGrantInvDateAgency);
		
		Matcher roleMatcherWithDollar = Pattern.compile("Role:.*?[\\s]").matcher(eleWOGrantInvDateAgency);
		if(roleMatcherWithDollar.find()) {
			roleMatcherWithDollar.reset();
			if(roleMatcherWithDollar.find()) {
				roleString = roleMatcherWithDollar.group(0);
				role = roleString.replaceAll("Role:", "");
				eleWOGrantInvDateAgencyRole = eleWOGrantInvDateAgency.substring(eleWOGrantInvDateAgency.indexOf(roleString)+roleString.length(), eleWOGrantInvDateAgency.length()).trim();
			}
		}else{
			eleWOGrantInvDateAgencyRole = eleWOGrantInvDateAgency;
		}
		
		Matcher amountMatcherWithDollar = Pattern.compile("[$][0-9, /,/.]*?\\s").matcher(eleWOGrantInvDateAgencyRole);
		if(amountMatcherWithDollar.find()) {
			amountMatcherWithDollar.reset();
			if(amountMatcherWithDollar.find()) {
				amountString = amountMatcherWithDollar.group(0);
				amount = amountString.replaceAll("\\s", "");
				eleWOGrantInvDateAgencyRoleAmount = eleWOGrantInvDateAgencyRole.substring(eleWOGrantInvDateAgencyRole.indexOf(amountString)+amountString.length(), eleWOGrantInvDateAgencyRole.length()).trim();
			}
		}else{
			eleWOGrantInvDateAgencyRoleAmount = eleWOGrantInvDateAgencyRole;
		}
		////System.out.println("Amount="+amount);
		////System.out.println("eleWOGrantInvDateAgencyRoleAmount="+eleWOGrantInvDateAgencyRoleAmount);
		
		Matcher statusMatcherWithSpace = Pattern.compile("^.*?\\s").matcher(eleWOGrantInvDateAgencyRoleAmount);
		if(statusMatcherWithSpace.find()) {
			statusMatcherWithSpace.reset();
			if(statusMatcherWithSpace.find()) {
				////System.out.println("In IF");
				statusString = statusMatcherWithSpace.group(0);
				status = statusString.replaceAll("\\s", "");
				eleWOGrantInvDateAgencyRoleAmountStatus = eleWOGrantInvDateAgencyRoleAmount.substring(eleWOGrantInvDateAgencyRoleAmount.indexOf(statusString)+statusString.length(), eleWOGrantInvDateAgencyRoleAmount.length()).trim();
			}
		}else{
			eleWOGrantInvDateAgencyRoleAmountStatus = eleWOGrantInvDateAgencyRoleAmount;
		}
		////System.out.println("Status="+status);
		////System.out.println("eleWOGrantInvDateAgencyRoleAmountStatus="+eleWOGrantInvDateAgencyRoleAmountStatus);
		
		Matcher typeMatcher = Pattern.compile("Type:.*[\\s]{0,1}").matcher(eleWOGrantInvDateAgencyRoleAmountStatus);
		if(typeMatcher.find()) {
			typeMatcher.reset();
			if(typeMatcher.find()) {
				////System.out.println("In IF");
				typeString = typeMatcher.group(0);
				type = typeString.replaceAll("Type:", "");
				eleWOGrantInvDateAgencyRoleAmountStatusType = eleWOGrantInvDateAgencyRoleAmountStatus.substring(eleWOGrantInvDateAgencyRoleAmountStatus.indexOf(typeString)+typeString.length(), eleWOGrantInvDateAgencyRoleAmountStatus.length()).trim();
			}
		}else{
			eleWOGrantInvDateAgencyRoleAmountStatusType = eleWOGrantInvDateAgencyRoleAmountStatus;
		}
		////System.out.println("Type="+type);
		////System.out.println("eleWOGrantInvDateAgencyRoleAmountStatusType="+eleWOGrantInvDateAgencyRoleAmountStatusType);
		
		Matcher levelMatcher = Pattern.compile("Level:.*[\\s]{0,1}").matcher(eleWOGrantInvDateAgencyRoleAmountStatusType);
		if(levelMatcher.find()) {
			levelMatcher.reset();
			if(levelMatcher.find()) {
				////System.out.println("In IF");
				levelString = levelMatcher.group(0);
				level = levelString.replaceAll("Level:", "");
				eleWOGrantInvDateAgencyRoleAmountStatusTypeLevel = eleWOGrantInvDateAgencyRoleAmountStatusType.substring(eleWOGrantInvDateAgencyRoleAmountStatusType.indexOf(levelString)+levelString.length(), eleWOGrantInvDateAgencyRoleAmountStatusType.length()).trim();
			}
		}else{
			eleWOGrantInvDateAgencyRoleAmountStatusTypeLevel = eleWOGrantInvDateAgencyRoleAmountStatusType;
		}
		////System.out.println("Level="+level);
		////System.out.println("eleWOGrantInvDateAgencyRoleAmountStatusTypeLevel="+eleWOGrantInvDateAgencyRoleAmountStatusTypeLevel);
		
		return new Grant(personId, grantNo, investigators, from, to, agency, amount, status, type, role, level);
	}
	
	public static int writeToOtherPublicationTable(OtherPublication op) {
		con = DbCon.getConnection();
		int max_id = 0;
		try {
			ResultSet rs = con.prepareStatement("SELECT MAX(to_number(id, '99999999999999')) FROM "+schema+".other_publication WHERE person_id="+op.person_id).executeQuery();
			while(rs.next()) {
				if(rs.getString(1) != null) {
					max_id = Integer.parseInt(rs.getString(1));
				}else {
					max_id = 0;
				}
			}

			max_id++;
			if(max_id==10) {

			}

			String sql = "INSERT INTO "+schema+".other_publication(person_id,id,title,journal,authors,year,volume,pages) VALUES (?,?,?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, op.person_id);
			ps.setString(2, ""+max_id);
			ps.setString(3, op.title);
			ps.setString(4, op.journal);
			ps.setString(5, op.authors);
			ps.setString(6, op.year);
			ps.setString(7, op.volume);
			ps.setString(8, op.pages);
			////System.out.println(ps.toString());
			int resultUpdate = ps.executeUpdate();

			con.close();
			if(resultUpdate > 0) {
				return max_id;
			}else {
				return -1;

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return 1;
	}

	public static InvitedPresentation extractInvitedPresentation(Element ele, int personId) {
		Element currP;
		logger.info("presentation: "+ele.text());

		currP = ele;

		String topic = "";
		String year = "", yearString="";
		String level = "", levelString = "";
		String event = "";

		String eleText = ele.text();

		////System.out.println("eleText=\n"+eleText);

		if(ele.select("i").size() > 0) {
			topic = ele.select("i").get(0).text();
		}

		Matcher yearMatcher = Pattern.compile("\\(?[0-9]{0,2}\\-?[0-9]{0,2}\\-?[0-9]{0,4}(\\.?\\s*)\\)?").matcher(eleText);
		//Matcher yearMatcherBadDate = Pattern.compile("[(]BAD\\sDATE\\.\\s[)]").matcher(eleText);
		if(yearMatcher.find()) {
			////System.out.println("In if");
			yearString = yearMatcher.group(0);
			////System.out.println("yearString"+yearString);
			year = yearString.replaceAll("[^0-9|\\-]", "");

		}
		/*else if(yearMatcherBadDate.find()) {
			//System.out.println("In if");
			yearString = yearMatcherBadDate.group(0);
			////System.out.println("yearString"+yearString);
			year = yearString.replaceAll("[^A-Z]", "");
		}*/
		Matcher levelMatcher = Pattern.compile("[Ll]evel:{1}.+(\\.?|\\s?)").matcher(eleText);
		if(levelMatcher.find()) {
			////System.out.println("In if");
			levelString = levelMatcher.group(0);
			////System.out.println("Level String="+levelString);
			level = levelString.replaceAll("[Ll]evel:", "");

		}
		
		if(yearString.indexOf("(") >= 0) {
			event = eleText.substring(yearString.length(), eleText.length());
		}else {
			event = eleText.replaceAll(yearString, "");
		}
			
		event = event.replaceAll(levelString, "");
		
		try {
			event = event.replaceAll(topic, "");
		}catch (PatternSyntaxException e) {
			Matcher doubleBrackMatcher = Pattern.compile("\\)\\)").matcher(event);
			Matcher specialCharMatcher = Pattern.compile("^[^a-zA-Z0-9]*?[a-zA-Z0-9]{1}").matcher(event);
			Matcher specialCharMatcherEnd = Pattern.compile("[a-zA-Z0-9]{1}[^a-zA-Z0-9]{1,}}$").matcher(event);
			String specialCharString = "";
			if(doubleBrackMatcher.find()) {
				event = event.replaceAll("\\)\\)", "\\)");
			}else if(specialCharMatcher.find()){
				specialCharString = specialCharMatcher.group(0);
				event = event.substring(specialCharString.length(), event.length());
			}else if(specialCharMatcherEnd.find()) {
				specialCharString = specialCharMatcherEnd.group(0);
				event = event.substring(0, event.length()-specialCharString.length()+1);
			}else {
				event = event.replaceAll(topic.replaceAll("\\(", "\\\\("), "");
			}
		}
		event = event.replaceAll("^\\(", "");
		event = event.replaceAll("^\\)", "");
		event = event.replaceAll("^\\s", "");
		event = event.replaceAll("^\\.", "");
		event = event.replaceAll("[^0-9|a-z|A-Z|\\s]$", "");
		////System.out.println("event="+event);
		
		
//		
		
		
		return new InvitedPresentation(personId, topic, event, year, level);

	}
	
	public static Lecture extractLecture(Element ele, int personId) {
		Element currP;
		logger.info("presentation: "+ele.text());

		currP = ele;

		String topic = "";
		String year = "", yearString="";
		String level = "", levelString = "";
		String event = "";

		String eleText = ele.text();

		////System.out.println("eleText=\n"+eleText);

		if(ele.select("i").size() > 0) {
			topic = ele.select("i").get(0).text();
		}

		Matcher yearMatcher = Pattern.compile("\\(?[0-9]{0,2}\\-?[0-9]{0,2}\\-?[0-9]{0,4}(\\.?\\s*)\\)?").matcher(eleText);
		if(yearMatcher.find()) {
			////System.out.println("In if");
			yearString = yearMatcher.group(0);
			////System.out.println("yearString"+yearString);
			year = yearString.replaceAll("[^0-9|\\-]", "");

		}
//		Matcher levelMatcher = Pattern.compile("[Ll]evel:?.+(\\.?|\\s?)").matcher(eleText);
//		if(levelMatcher.find()) {
//			////System.out.println("In if");
//			levelString = levelMatcher.group(0);
//			////System.out.println("Level String="+levelString);
//			level = levelString.replaceAll("[Ll]evel:", "");
//
//		}
		////System.out.println("eleText="+eleText);
		////System.out.println("year="+year);
		
		////System.out.println("topic="+topic);
		////System.out.println("level="+level);
		
		
		try {
			event = eleText.replaceAll(yearString, "");
			////System.out.println("event="+event);
		}catch (PatternSyntaxException e) {
			////System.out.println("topic="+topic);
			yearString = yearString.replaceAll("\\(", "\\\\(");
			yearString = yearString.replaceAll("\\)", "\\\\)");
			yearString = yearString.replaceAll("\\[", "\\\\[");
			yearString = yearString.replaceAll("\\]", "\\\\]");
			////System.out.println("topic="+topic);
			event = event.replaceAll(yearString, "");
		}
		//event = event.replaceAll(levelString, "");
		
		try {
			event = event.replaceAll(topic, "");
			////System.out.println("event="+event);
		}catch (PatternSyntaxException e) {
			////System.out.println("topic="+topic);
			topic = topic.replaceAll("\\(", "\\\\(");
			topic = topic.replaceAll("\\)", "\\\\)");
			topic = topic.replaceAll("\\[", "\\\\[");
			topic = topic.replaceAll("\\]", "\\\\]");
			////System.out.println("topic="+topic);
			event = event.replaceAll(topic, "");
		}
		event = event.replaceAll("^\\(", "");
		event = event.replaceAll("^\\)", "");
		event = event.replaceAll("^\\s", "");
		event = event.replaceAll("^\\.", "");
		event = event.replaceAll("[^0-9|a-z|A-Z|\\s]$", "");
		////System.out.println("event="+event);
		
		
//		
		
		
		return new Lecture(personId, topic, event, year);

	}
	
	public static PosterPresentation extractPosterPresentation(Element ele, int personId) {
		Element currP;
		logger.info("presentation: "+ele.text());

		currP = ele;

		String topic = "";
		String year = "", yearString="";
		String level = "", levelString = "";
		String event = "";
		String authors = "", authorsString="";

		String eleText = ele.text();

		////System.out.println("eleText=\n"+eleText);
		
		
		
		if(ele.select("i").size() > 0) {
			topic = ele.select("i").get(0).text();
		}

		Matcher yearMatcher = Pattern.compile("\\({1}[0-9]{0,2}\\-{0,1}[0-9]{0,4}\\.{1}\\s*\\){1}").matcher(eleText);
		if(yearMatcher.find()) {
			////System.out.println("In if");
			yearString = yearMatcher.group(0);
			//System.out.println("yearString"+yearString);
			year = yearString.replaceAll("[^0-9|\\-]", "");
		}
		Matcher authorsMatcher = Pattern.compile("^.*?\\(").matcher(eleText);
		if(authorsMatcher.find()) {
			////System.out.println("In if");
			authorsString = authorsMatcher.group(0);
			//System.out.println("authorsString"+authorsString);
			authors = authorsString.replaceAll(yearString, "");
			authors = authorsString.substring(0, authorsString.length()-1);
		}
		//System.out.println("authors="+authors);
		Matcher levelMatcher = Pattern.compile("[Ll]evel:{1}.+(\\.?|\\s?)").matcher(eleText);
		if(levelMatcher.find()) {
			////System.out.println("In if");
			levelString = levelMatcher.group(0);
			////System.out.println("Level String="+levelString);
			level = levelString.replaceAll("[Ll]evel:", "");

		}
		////System.out.println("eleText="+eleText);
		////System.out.println("year="+year);
		
//		//System.out.println("topic="+topic);
//		//System.out.println("level="+level);
//		//System.out.println("year="+year);
		if(eleText.indexOf(levelString) > 0 && eleText.indexOf(topic) > 0) {
			event = eleText.substring(eleText.indexOf(topic)+topic.length(), eleText.indexOf(levelString));
		}else if(eleText.indexOf(topic) > 0){
			event = eleText.substring(eleText.indexOf(topic)+topic.length(), eleText.length());
		}
		//System.out.println("event"+event);
		//event = event.replaceAll(levelString, "");
		//event = event.replaceAll(authors, "");
//		try {
//			event = event.replaceAll(topic, "");
//		}catch (PatternSyntaxException e) {
//			event = event.replaceAll(topic.replaceAll("\\(", "\\\\("), "");
//		}
//		//System.out.println("authors"+authors);
//		
//		event = event.replaceAll("^\\(", "");
//		event = event.replaceAll("^\\.", "");
//		event = event.replaceAll("^\\s", "");
//		event = event.replaceAll("^\\)", "");
		event = event.replaceAll("^\\s", "");
		event = event.replaceAll("^\\.", "");
//		
//		
		event = event.replaceAll("[^0-9|a-z|A-Z|\\s]$", "");
		////System.out.println("event="+event);
		//System.out.println("event"+event);
		
		return new PosterPresentation(personId, topic, event, year, level, authors);

	}
	
	public static PaperPresentation extractPaperPresentation(Element ele, int personId) {
		Element currP;
		logger.info("presentation: "+ele.text());

		currP = ele;

		String topic = "";
		String year = "", yearString="";
		String level = "", levelString = "";
		String event = "";
		String authors = "", authorsString="";

		String eleText = ele.text();

		////System.out.println("eleText=\n"+eleText);
		
		
		
		if(ele.select("i").size() > 0) {
			topic = ele.select("i").get(0).text();
		}

		Matcher yearMatcher = Pattern.compile("\\({1}[0-9]{0,2}\\-{0,1}[0-9]{0,4}\\.{1}\\s*\\){1}").matcher(eleText);
		if(yearMatcher.find()) {
			////System.out.println("In if");
			yearString = yearMatcher.group(0);
			//System.out.println("yearString"+yearString);
			year = yearString.replaceAll("[^0-9|\\-]", "");
		}
		Matcher authorsMatcher = Pattern.compile("^.*?\\(").matcher(eleText);
		if(authorsMatcher.find()) {
			////System.out.println("In if");
			authorsString = authorsMatcher.group(0);
			//System.out.println("authorsString"+authorsString);
			authors = authorsString.replaceAll(yearString, "");
			authors = authorsString.substring(0, authorsString.length()-1);
		}
		//System.out.println("authors="+authors);
		Matcher levelMatcher = Pattern.compile("[Ll]evel:{1}.+(\\.?|\\s?)").matcher(eleText);
		if(levelMatcher.find()) {
			////System.out.println("In if");
			levelString = levelMatcher.group(0);
			////System.out.println("Level String="+levelString);
			level = levelString.replaceAll("[Ll]evel:", "");

		}
		////System.out.println("eleText="+eleText);
		////System.out.println("year="+year);
		
//		//System.out.println("topic="+topic);
//		//System.out.println("level="+level);
//		//System.out.println("year="+year);
		if(eleText.indexOf(levelString) > 0 && eleText.indexOf(topic) > 0) {
			event = eleText.substring(eleText.indexOf(topic)+topic.length(), eleText.indexOf(levelString));
		}else if(eleText.indexOf(topic) > 0){
			event = eleText.substring(eleText.indexOf(topic)+topic.length(), eleText.length());
		}
		//System.out.println("event"+event);
		//event = event.replaceAll(levelString, "");
		//event = event.replaceAll(authors, "");
//		try {
//			event = event.replaceAll(topic, "");
//		}catch (PatternSyntaxException e) {
//			event = event.replaceAll(topic.replaceAll("\\(", "\\\\("), "");
//		}
//		//System.out.println("authors"+authors);
//		
//		event = event.replaceAll("^\\(", "");
//		event = event.replaceAll("^\\.", "");
//		event = event.replaceAll("^\\s", "");
//		event = event.replaceAll("^\\)", "");
		event = event.replaceAll("^\\s", "");
		event = event.replaceAll("^\\.", "");
//		
//		
		event = event.replaceAll("[^0-9|a-z|A-Z|\\s]$", "");
		////System.out.println("event="+event);
		//System.out.println("event"+event);
		
		return new PaperPresentation(personId, topic, event, year, level, authors);

	}
	
	
	public static PeerReviewedPublication extractPeerReviewedPublicationDoi(Element ele, int personId, int journalId) {
		logger.info("publication doi: "+ele.val());
		Element currP = ele;
		String doi = ele.select("input").attr("value");

		return new PeerReviewedPublication(personId, journalId, doi);
	}

	public static PeerReviewedPublication extractPeerReviewedPublication(Element ele, int personId) {
		Element currP;
		logger.info("publication: "+ele.text());

		currP = ele;

		String authors = "";
		String year = "";
		String pages = "";
		String volume = "";
		String journal = "";
		String title = "";

		int indexOfLeftBr = 0;
		if(currP.html().indexOf("(") > 0) {
			indexOfLeftBr = currP.html().indexOf("(");
		}

		int indexOfI = 0;
		if(currP.html().indexOf("<i>") > 0) {
			indexOfI = currP.html().indexOf("<i>");
		}

		if(indexOfI > 0 && indexOfLeftBr > 0) {
			if(indexOfLeftBr < indexOfI) {

				String titleItalic = "";
				if(currP.select("i").size() > 1) {
					String italicFirst = currP.select("i").first().text();

					String italicLast = currP.select("i").last().text();

					int index1 = currP.text().indexOf(italicFirst, currP.text().indexOf("("));

					int index2 = currP.text().indexOf(italicLast);

					titleItalic = currP.text().substring(index1, index2);

				}else {
					titleItalic = currP.select("i").first().text();
				}

				String upToJournal = currP.text().substring(0, currP.text().indexOf(titleItalic));
				authors = upToJournal.substring(0, upToJournal.indexOf("("));

				year = upToJournal.substring(upToJournal.indexOf("("), upToJournal.indexOf(")")).replaceAll("[^0-9]", "");
			}else {
				String titleItalic = currP.select("i").first().text();
				String upToJournal = currP.text().substring(0, currP.text().indexOf(titleItalic));
				authors = upToJournal;
			}

		}else if(indexOfI > 0){
			String titleItalic = currP.select("i").first().text();
			String upToJournal = currP.text().substring(0, currP.text().indexOf(titleItalic));
			authors = upToJournal;

		}else if(indexOfLeftBr > 0) {
			authors = currP.html().substring(0, indexOfLeftBr);
			year = currP.html().substring(indexOfLeftBr, currP.html().indexOf(")")).replaceAll("[^0-9]", "");
		}

		if(year.length() > 4) {
			year = year.substring(year.length()-4, year.length());
		}

		String abstractText = "";
		if(currP.select("i").first() != null) {

			Elements italicEles = currP.select("i");
			if(italicEles.size() > 0) {
				if(italicEles.size() > 1) {
					journal = italicEles.get(1).text().substring(0, italicEles.get(1).text().length()-1);
					title = currP.select("i").get(0).text();

				}else {
					logger.info(currP.text());
					journal = italicEles.get(0).text().substring(0, italicEles.get(0).text().length()-1);

					//Matcher titleMatcher = Pattern.compile("\\).*")
					
					title = italicEles.get(0).text();
				}

			}

			int indexOfLastComma = currP.text().lastIndexOf(",");

			if(indexOfLastComma < 0) {
				indexOfLastComma = 0;
			}
			pages = currP.text().substring(indexOfLastComma+1, currP.text().length());


			String uptoVolume = currP.text().substring(0, indexOfLastComma);

			int indexOfSecondLastComma = uptoVolume.lastIndexOf(",");
			if(indexOfSecondLastComma > 0) {
				volume = uptoVolume.substring(indexOfSecondLastComma+1, uptoVolume.length());
			}

		}

		return new PeerReviewedPublication(personId, title, journal, authors, year, volume, pages, abstractText);

	}

	public static InvitedPublication extractInvitedPublication(Element ele, int personId) {
		Element currP;
		logger.info("publication: "+ele.text());

		currP = ele;

		String authors = "";
		String year = "";
		String pages = "";
		String volume = "";
		String journal = "";
		String title = "";
		String abstractText = "";
		

		int indexOfLeftBr = 0;
		//try {
			if(currP.html().indexOf("(") > 0) {
				indexOfLeftBr = currP.html().indexOf("(");
				////System.out.println(indexOfLeftBr);
			}
	
			int indexOfI = 0;
			if(currP.html().indexOf("<i>") > 0) {
				indexOfI = currP.html().indexOf("<i>");
				////System.out.println(indexOfLeftBr);
			}
	
			if(indexOfI > 0 && indexOfLeftBr > 0) {
				if(indexOfLeftBr < indexOfI) {
					String titleItalic = "";
					if(currP.select("i").size() > 1) {
						String italicFirst = currP.select("i").first().text();
	
						String italicLast = currP.select("i").last().text();
	
						int index1 = currP.text().indexOf(italicFirst, currP.text().indexOf("("));
	
						int index2 = currP.text().indexOf(italicLast);
	
						titleItalic = currP.text().substring(index1, index2);
	
					}else {
						titleItalic = currP.select("i").first().text();
					}
	
					String upToJournal = currP.text().substring(0, currP.text().indexOf(titleItalic));
					authors = upToJournal.substring(0, upToJournal.indexOf("("));
	
					year = upToJournal.substring(upToJournal.indexOf("("), upToJournal.indexOf(")")).replaceAll("[^0-9]", "");
				}else {
					String titleItalic = currP.select("i").first().text();
					String upToJournal = currP.text().substring(0, currP.text().indexOf(titleItalic));
					if(upToJournal.indexOf(":") > 0) {
						authors = upToJournal.substring(0, upToJournal.indexOf(":"));
						title = upToJournal.substring(upToJournal.indexOf(":")+1, upToJournal.length());
					}else {
						authors = upToJournal;
					}
				}
	
			}else if(indexOfI > 0){
				String titleItalic = currP.select("i").first().text();
				String upToJournal = currP.text().substring(0, currP.text().indexOf(titleItalic));
				authors = upToJournal;
	
			}else if(indexOfLeftBr > 0) {
				authors = currP.html().substring(0, indexOfLeftBr);
				year = currP.html().substring(indexOfLeftBr, currP.html().indexOf(")")).replaceAll("[^0-9]", "");
			}
	
			if(year.length() > 4) {
				year = year.substring(year.length()-4, year.length());
			}
	
			if(currP.select("i").first() != null) {
	
				Elements italicEles = currP.select("i");
				if(italicEles.size() > 0) {
					if(italicEles.size() > 1) {
						journal = italicEles.get(1).text().substring(0, italicEles.get(1).text().length()-1);
						title = currP.select("i").get(0).text();
	
					}else {
	
						journal = italicEles.get(0).text().substring(0, italicEles.get(0).text().length()-1);
						title = currP.text().split(journal)[0];
						
						if(title.equals("")) {
							title = currP.text().substring(currP.text().indexOf(")"), currP.text().indexOf(journal));
						}
					}
	
				}
	
				int indexOfLastComma = currP.text().lastIndexOf(",");
	
				if(indexOfLastComma < 0) {
					indexOfLastComma = 0;
				}
				pages = currP.text().substring(indexOfLastComma+1, currP.text().length());
	
	
				String uptoVolume = currP.text().substring(0, indexOfLastComma);
	
				int indexOfSecondLastComma = uptoVolume.lastIndexOf(",");
				if(indexOfSecondLastComma > 0) {
					volume = uptoVolume.substring(indexOfSecondLastComma+1, uptoVolume.length());
				}
	
			}
//		}catch(ArrayIndexOutOfBoundsException ex) {
//			logger.info(ex.getLocalizedMessage());
//			title = currP.text();
//		}

		return new InvitedPublication(personId, title, journal, authors, year, volume, pages, abstractText);

	}

	public static Editorial extractEditorial(Element ele, int personId) {
		Element currP;
		logger.info("publication: "+ele.text());

		currP = ele;

		String authors = "";
		String year = "", yearString="";
		String pages = "", pagesString = "";
		String volume = "";
		String title = "";

		String eleText = ele.text();

		////System.out.println(eleText);

		if(ele.select("i").size() > 0) {
			title = ele.select("i").get(0).text();
		}

		Matcher yearMatcher = Pattern.compile("\\(+.*\\.*\\s\\)+").matcher(eleText);
		if(yearMatcher.find()) {
			////System.out.println("In if");
			yearString = yearMatcher.group(0);
			year = yearString.replaceAll("[^0-9|\\-]", "");

		}
		if(eleText.indexOf(".") > 0 && eleText.indexOf("(") > 0) {
			if(eleText.indexOf(".") < eleText.indexOf("(")) {
				authors = eleText.substring(0, eleText.indexOf("."));			
			}else {
				authors = eleText.substring(0, eleText.indexOf("("));
			}
		}else if(eleText.indexOf(".") > 0 ){
			authors = eleText.substring(0, eleText.indexOf("."));		
		}else if(eleText.indexOf("(") > 0) {
			authors = eleText.substring(0, eleText.indexOf("("));
		}

		if(authors.indexOf(yearString) > 0) {
			authors = authors.substring(0, authors.indexOf(yearString));
		}
		////System.out.println("authors.indexOf(title)"+authors.indexOf(title));
		if(authors.indexOf(title) > 0) {
			authors = authors.substring(0, authors.indexOf(title));
		}


		Matcher pagesMatcher = Pattern.compile("[p]{2}.*").matcher(eleText);
		if(pagesMatcher.find()) {
			////System.out.println("In if");
			pagesString = pagesMatcher.group(0);
			pages = pagesString.replaceAll("[^0-9|\\-|\\(|\\)|:]", "");
			if(pages.indexOf(":") > 0) {
				volume = pages.substring(0, pages.indexOf(":"));
				pages = pages.substring(pages.indexOf(":")+1, pages.length());
				pages = pages.replaceAll("[^0-9|\\-]", "");
			}else {
				pages = pages.replaceAll("[^0-9|\\-]", "");
			}
			
		}
		/*
		//System.out.println("authors="+authors);
		//System.out.println("year="+year);
		//System.out.println("title="+title);
		//System.out.println("volume="+volume);
		//System.out.println("pages="+pages);
		*/
		return new Editorial(personId, title, authors, year, volume, pages);

	}

	public static PublishedAbstract extractPublishedAbstract(Element ele, int personId) {

		String authors = "";
		String year = "";
		String pages = "";
		String volume = "";
		String journal = "";
		String title = ele.text();

		String eleText = ele.text();

		if(eleText.contains("(")) {
			authors = eleText.substring(0, eleText.indexOf("("));
		}
		if(eleText.contains("(") && eleText.contains(". )")) {
			year = eleText.substring(eleText.indexOf("(")+1, eleText.indexOf(". )"));
		}
		if(eleText.contains(". )") && eleText.contains(".[Abstracy]")) {
			title = eleText.substring(eleText.indexOf(". )")+3, eleText.indexOf(".[Abstracy]"));
		}
		if(ele.children().size() > 0) {
			if(ele.children().get(0).is("i")) {
				journal = ele.select("i").get(0).text();
			}
		}
		if(ele.children().size() > 0) {
			if(ele.children().get(0).is("i")) {
				////System.out.println("eleText.lastIndexOf(\",\")"+eleText.lastIndexOf(","));
				////System.out.println("eleText.indexOf(\"</i>\")"+eleText.indexOf(journal));
				////System.out.println("journal.length()"+journal.length());
				if(eleText.lastIndexOf(",") > eleText.indexOf(journal)) {
					int lastCommaIndex = ( ( eleText.lastIndexOf(",") - (eleText.indexOf(journal) + journal.length()) > 0 ? eleText.lastIndexOf(",") : eleText.length())); 
					volume = eleText.substring(eleText.indexOf(journal)+journal.length(), lastCommaIndex);
					pages = eleText.substring(eleText.lastIndexOf(",")+1, eleText.length());
				}
			}
		}
		////System.out.println("title="+title);
		return new PublishedAbstract(personId, title, journal, authors,  year, volume, pages);
	}

	public static PublishedBook extractPublishedBook(Element ele, int personId) {

		String authors = "";
		String year = "";
		String publisher = "";
		String title = ele.text();

		String eleText = ele.text();

		if(eleText.contains("(")) {
			authors = eleText.substring(0, eleText.indexOf("("));
		}
		if(eleText.contains("(") && eleText.contains(". )")) {
			year = eleText.substring(eleText.indexOf("(")+1, eleText.indexOf(". )"));
		}

		if(ele.children().size() > 0) {
			if(ele.children().get(0).is("i")) {
				title = ele.select("i").get(0).text();
			}
		}
		if(ele.children().size() > 0) {
			if(ele.children().get(0).is("i")) {
				////System.out.println("eleText.lastIndexOf(\",\")"+eleText.lastIndexOf(","));
				////System.out.println("eleText.indexOf(\"</i>\")"+eleText.indexOf(title));
				////System.out.println("title.length()"+title.length());
				//if(eleText.lastIndexOf(",") > eleText.indexOf(title)) {
				//int lastCommaIndex = ( ( eleText.lastIndexOf(",") - (eleText.indexOf(journal) + journal.length()) > 0 ? eleText.lastIndexOf(",") : eleText.length())); 
				publisher = eleText.substring(eleText.indexOf(title)+title.length());
				//pages = eleText.substring(eleText.lastIndexOf(",")+1, eleText.length());
				//}
			}
		}
		////System.out.println("title="+title);
		return new PublishedBook(personId, title, authors,  year, publisher);
	}

	public static BookChapter extractBookChapter(Element ele, int personId) {

		String authors = "";
		String year = "";
		String publisher = "";
		String chapter = ele.text();
		String title = "";

		String eleText = ele.text();

		if(eleText.contains("(")) {
			authors = eleText.substring(0, eleText.indexOf("("));
		}
		if(eleText.contains("(") && eleText.contains(")")) {
			year = eleText.substring(eleText.indexOf("(")+1, eleText.indexOf(")"));
		}

		if(ele.children().size() > 0) {
			if(ele.children().get(0).is("i")) {
				chapter = ele.select("i").get(0).text();
			}
		}
		if(ele.children().size() > 0) {
			if(ele.children().get(0).is("i")) {
				String titleAndPublisher = eleText.substring(eleText.indexOf(chapter)+chapter.length(), eleText.length());
				if(titleAndPublisher.contains(".")) {
					////System.out.println("titleAndPublisher="+titleAndPublisher.indexOf("."));
					//					for(String a:titleAndPublisher.split(".")) {
					//						//System.out.println(a);
					//					}
					//String arr[] = titleAndPublisher.split(".");
					////System.out.println("arr="+arr.length);
					title = titleAndPublisher.substring(0, titleAndPublisher.indexOf(".") );
					publisher = titleAndPublisher.substring(titleAndPublisher.indexOf(".")+1, titleAndPublisher.length() );
				}
			}
		}
		////System.out.println("title="+title);
		return new BookChapter(personId, chapter, authors,  year, title, publisher);
	}
	public static OtherPublication extractOtherPublication(Element ele, int personId) {

		String authors = "";
		String year = "";
		String pages = "";
		String volume = "";
		String journal = "";
		String title = ele.text();

		String eleText = ele.text();

		if(eleText.contains("(")) {
			authors = eleText.substring(0, eleText.indexOf("("));
		}
		if(eleText.contains("(") && eleText.contains(". )")) {
			year = eleText.substring(eleText.indexOf("(")+1, eleText.indexOf(". )"));
		}
		if(ele.children().size() > 0) {
			if(ele.children().get(0).is("i")) {
				journal = ele.select("i").get(0).text();
			}
		}
		Matcher titleMatcher = Pattern.compile("\\.\\s\\).*\\.").matcher(eleText);
		Matcher titleMatcherWODot = Pattern.compile("\\.\\s\\).*").matcher(eleText);
		logger.info(eleText);
		if(titleMatcher.find()) {
			title = eleText.substring(eleText.indexOf(". )")+3, eleText.indexOf(".", eleText.indexOf(". )")+3));
		}else if(titleMatcherWODot.find()){
			title = titleMatcherWODot.group(0).replaceAll("\\.\\s\\)", "");
		}
		
		if(ele.children().size() > 0) {
			if(ele.children().get(0).is("i")) {
				 
				volume = eleText.substring(eleText.indexOf(journal)+journal.length(), eleText.length());
				
			}
		}
		////System.out.println("title="+title);
		return new OtherPublication(personId, title, journal, authors,  year, volume);
	}

	/**
	 * @param ele
	 * @param personId
	 * @return
	 */
	public static TechnicalReport extractTechnicalReport(Element ele, int personId) {
		Element currP;
		logger.info("publication: "+ele.text());

		currP = ele;

		String authors = "";
		String year = "", yearString="";
		String pages = "", pagesString = "";
		String title = "";

		String eleText = ele.text();

		////System.out.println(eleText);

		if(ele.select("i").size() > 0) {
			title = ele.select("i").get(0).text();
		}

		Matcher yearMatcher = Pattern.compile("\\(+.*\\.*\\s\\)+").matcher(eleText);
		if(yearMatcher.find()) {
			////System.out.println("In if");
			yearString = yearMatcher.group(0);
			year = yearString.replaceAll("[^0-9|\\-]", "");

		}
		if(eleText.indexOf(".") > 0 && eleText.indexOf("(") > 0) {
			if(eleText.indexOf(".") < eleText.indexOf("(")) {
				authors = eleText.substring(0, eleText.indexOf("."));			
			}else {
				authors = eleText.substring(0, eleText.indexOf("("));
			}
		}else if(eleText.indexOf(".") > 0 ){
			authors = eleText.substring(0, eleText.indexOf("."));		
		}else if(eleText.indexOf("(") > 0) {
			authors = eleText.substring(0, eleText.indexOf("("));
		}

		if(authors.indexOf(yearString) > 0) {
			authors = authors.substring(0, authors.indexOf(yearString));
		}
		////System.out.println("authors.indexOf(title)"+authors.indexOf(title));
		if(authors.indexOf(title) > 0) {
			authors = authors.substring(0, authors.indexOf(title));
		}

		//pp.*(\\.{0,1})|[Pp]ages.*(\\.{0,1})|
		Matcher pagesMatcher = Pattern.compile("[0-9]{1,}:[0-9]{1,}[\\-]{0,}[0-9]{1,}|[Pp]{2}[\\.]{0,}[\\s]{0,}[0-9]{1,}[\\-]{0,1}[0-9]{1,}[\\s\\.]{0,}|[Pp]{1}age[s]{0,1}[\\s]{0,}[0-9]{1,}[\\-]{0,1}[0-9]{1,}[\\s\\.]{0,}|\\.[0-9]{1,}[\\-]{0,1}[0-9]{1,}$").matcher(eleText);//[p]{2}|[Pages]{1}|
		if(pagesMatcher.find()) {
			////System.out.println("In if"+pagesMatcher.start()+" "+pagesMatcher.end());
			if(pagesMatcher.start() != pagesMatcher.end()) {
				pagesString = eleText.substring(pagesMatcher.start(), pagesMatcher.end());
				pages = pagesString.replaceAll("[^0-9|\\-|\\(|\\)|:]", "");

			}
			
		}
		/*
		//System.out.println("eleText="+eleText);
		//System.out.println("authors="+authors);
		//System.out.println("year="+year);
		//System.out.println("title="+title);
		//System.out.println("pages="+pages);
		*/
		return new TechnicalReport(personId, title, authors, year, pages);

	}

	
	public static void XMLTraverse(String frontUrl, String backUrl) {


		Document doc,doc1;
		try {
			char ascii = 96;
			for(int i=0; i<26; i++) {
				ascii++;
				String character = Character.toString(ascii);
				org.jsoup.Connection jsoupCon = Jsoup.connect(frontUrl+character+backUrl);
				jsoupCon.timeout(90000000);
				doc1 = jsoupCon.get();
				Elements liElements = (Elements) doc1.getElementsByTag("li");

				//Path file = Paths.get("cincinnati.txt");

				//BufferedWriter writer = Files.newBufferedWriter(file);
				int count = 1;
				// Traversing through li elements of all people
				outer : for (Element liElement : liElements) {

					////////System.out.println("Harvesting "+count+"/"+liElements.size()+" at "+ new SimpleDateFormat("MM-dd-yyyy HH:mm:ss.S").format(new Date()));
					int person_id = 0;
					String person_uri = "";
					String person_name ="";
					String person_title = "";
					String person_email = "";
					String person_phone = "";
					String person_overview = "";

					// Link for each person is in a href value in h3 element Ex: <h3><a href="/p/johndoe">John Doe</a></h3>

					Element h3Element = (Element) liElement.getElementsByTag("h3").get(0);	
					Element aElement = (Element) h3Element.getElementsByTag("a").get(0);

					String linkHref = aElement.attr("href");
					String linkText = aElement.text();

					////////System.out.println("person_id "+person_id);
					// Gives the individual person's web page
					org.jsoup.Connection jsoupCon1 = Jsoup.connect(baseUrl + linkHref);
					jsoupCon1.timeout(9000000);
					doc = jsoupCon1.get();

					person_uri = baseUrl + linkHref;
					////////System.out.println("person_url " +person_uri);

					XMLTraverse2(person_uri);

					count++;

				}
				//writer.close();
			}

		} catch (IOException e) {

			e.printStackTrace();
		}


	}

	public static int writeToPublication(PeerReviewedPublication prp) {
		con = DbCon.getConnection();
		int max_id = 0;
		try {
			ResultSet rs = con.prepareStatement("SELECT MAX(to_number(id, '99999999999999')) FROM "+schema+".journal_publication WHERE person_id="+prp.person_id).executeQuery();
			while(rs.next()) {
				if(rs.getString(1) != null) {
					max_id = Integer.parseInt(rs.getString(1));
				}else {
					max_id = 0;
				}
			}

			max_id++;
			if(max_id==10) {

			}
			String sql = "INSERT INTO "+schema+".journal_publication(person_id,id,title,journal,authors,year,volume,pages,abstract) VALUES (?,?,?,?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, prp.person_id);
			ps.setString(2, ""+max_id);
			ps.setString(3, prp.title);
			ps.setString(4, prp.journal);
			ps.setString(5, prp.authors);
			ps.setString(6, prp.year);
			ps.setString(7, prp.volume);
			ps.setString(8, prp.pages);
			ps.setString(9, prp.abstractText);

			int resultUpdate = ps.executeUpdate();
			//ps.close();
			//con.commit();
			con.close();
			if(resultUpdate > 0) {
				return max_id;
			}else {
				return -1;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 1;
	}

	public static int writeToInvitedPublicationTable(InvitedPublication ip) {
		con = DbCon.getConnection();
		int max_id = 0;
		try {
			ResultSet rs = con.prepareStatement("SELECT MAX(to_number(id, '99999999999999')) FROM "+schema+".invited_publication WHERE person_id="+ip.person_id).executeQuery();
			while(rs.next()) {
				if(rs.getString(1) != null) {
					max_id = Integer.parseInt(rs.getString(1));
				}else {
					max_id = 0;
				}
			}

			max_id++;
			if(max_id==10) {

			}
			String sql = "INSERT INTO "+schema+".invited_publication(person_id,id,title,journal,authors,year,volume,pages,abstract) VALUES (?,?,?,?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, ip.person_id);
			ps.setString(2, ""+max_id);
			ps.setString(3, ip.title);
			ps.setString(4, ip.journal);
			ps.setString(5, ip.authors);
			ps.setString(6, ip.year);
			ps.setString(7, ip.volume);
			ps.setString(8, ip.pages);
			ps.setString(9, ip.abstractText);

			int resultUpdate = ps.executeUpdate();

			con.close();
			if(resultUpdate > 0) {
				return max_id;
			}else {
				return -1;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 1;
	}

	public static int writeToInvitedPresentationTable(InvitedPresentation ip) {
		con = DbCon.getConnection();
		int max_id = 0;
		try {
			ResultSet rs = con.prepareStatement("SELECT MAX(to_number(id, '99999999999999')) FROM "+schema+".invited_presentation WHERE person_id="+ip.person_id).executeQuery();
			while(rs.next()) {
				if(rs.getString(1) != null) {
					max_id = Integer.parseInt(rs.getString(1));
				}else {
					max_id = 0;
				}
			}

			max_id++;
			if(max_id==10) {

			}
			String sql = "INSERT INTO "+schema+".invited_presentation(person_id,id,topic,event,year,level) VALUES (?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, ip.person_id);
			ps.setString(2, ""+max_id);
			ps.setString(3, ip.topic);
			ps.setString(4, ip.event);
			ps.setString(5, ip.year);
			ps.setString(6, ip.level);
		
			int resultUpdate = ps.executeUpdate();

			con.close();
			if(resultUpdate > 0) {
				return max_id;
			}else {
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	public static int writeToLectureTable(Lecture l) {
		con = DbCon.getConnection();
		int max_id = 0;
		try {
			ResultSet rs = con.prepareStatement("SELECT MAX(to_number(id, '99999999999999')) FROM "+schema+".lecture WHERE person_id="+l.person_id).executeQuery();
			while(rs.next()) {
				if(rs.getString(1) != null) {
					max_id = Integer.parseInt(rs.getString(1));
				}else {
					max_id = 0;
				}
			}

			max_id++;
			if(max_id==10) {

			}
			String sql = "INSERT INTO "+schema+".lecture(person_id,id,topic,event,year) VALUES (?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, l.person_id);
			ps.setString(2, ""+max_id);
			ps.setString(3, l.topic);
			ps.setString(4, l.event);
			ps.setString(5, l.year);
//			ps.setString(6, ip.level);
		
			int resultUpdate = ps.executeUpdate();

			con.close();
			if(resultUpdate > 0) {
				return max_id;
			}else {
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	public static int writeToPosterPresentationTable(PosterPresentation pp) {
		con = DbCon.getConnection();
		int max_id = 0;
		try {
			ResultSet rs = con.prepareStatement("SELECT MAX(to_number(id, '99999999999999')) FROM "+schema+".poster_presentation WHERE person_id="+pp.person_id).executeQuery();
			while(rs.next()) {
				if(rs.getString(1) != null) {
					max_id = Integer.parseInt(rs.getString(1));
				}else {
					max_id = 0;
				}
			}

			max_id++;
			if(max_id==10) {

			}
			String sql = "INSERT INTO "+schema+".poster_presentation(person_id,id,topic,event,year,level,authors) VALUES (?,?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, pp.person_id);
			ps.setString(2, ""+max_id);
			ps.setString(3, pp.topic);
			ps.setString(4, pp.event);
			ps.setString(5, pp.year);
			ps.setString(6, pp.level);
			ps.setString(7, pp.authors);
		
			int resultUpdate = ps.executeUpdate();

			con.close();
			if(resultUpdate > 0) {
				return max_id;
			}else {
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	public static int writeToPaperPresentationTable(PaperPresentation pp) {
		con = DbCon.getConnection();
		int max_id = 0;
		try {
			ResultSet rs = con.prepareStatement("SELECT MAX(to_number(id, '99999999999999')) FROM "+schema+".paper_presentation WHERE person_id="+pp.person_id).executeQuery();
			while(rs.next()) {
				if(rs.getString(1) != null) {
					max_id = Integer.parseInt(rs.getString(1));
				}else {
					max_id = 0;
				}
			}

			max_id++;
			if(max_id==10) {

			}
			String sql = "INSERT INTO "+schema+".paper_presentation(person_id,id,topic,event,year,level,authors) VALUES (?,?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, pp.person_id);
			ps.setString(2, ""+max_id);
			ps.setString(3, pp.topic);
			ps.setString(4, pp.event);
			ps.setString(5, pp.year);
			ps.setString(6, pp.level);
			ps.setString(7, pp.authors);
		
			int resultUpdate = ps.executeUpdate();

			con.close();
			if(resultUpdate > 0) {
				return max_id;
			}else {
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
	
	public static int writeToPublishedAbstractTable(PublishedAbstract pa) {
		con = DbCon.getConnection();
		int max_id = 0;
		try {
			ResultSet rs = con.prepareStatement("SELECT MAX(to_number(id, '99999999999999')) FROM "+schema+".published_abstract WHERE person_id="+pa.person_id).executeQuery();
			while(rs.next()) {
				if(rs.getString(1) != null) {
					max_id = Integer.parseInt(rs.getString(1));
				}else {
					max_id = 0;
				}
			}

			max_id++;
			if(max_id==10) {

			}

			String sql = "INSERT INTO "+schema+".published_abstract(person_id,id,title,journal,authors,year,volume,pages) VALUES (?,?,?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, pa.person_id);
			ps.setString(2, ""+max_id);
			ps.setString(3, pa.title);
			ps.setString(4, pa.journal);
			ps.setString(5, pa.authors);
			ps.setString(6, pa.year);
			ps.setString(7, pa.volume);
			ps.setString(8, pa.pages);
			////System.out.println(ps.toString());
			int resultUpdate = ps.executeUpdate();

			con.close();
			if(resultUpdate > 0) {
				return max_id;
			}else {
				return -1;

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return 1;
	}

	public static int writeToPublishedBookTable(PublishedBook pb) {
		con = DbCon.getConnection();
		int max_id = 0;
		try {
			ResultSet rs = con.prepareStatement("SELECT MAX(to_number(id, '99999999999999')) FROM "+schema+".published_book WHERE person_id="+pb.person_id).executeQuery();
			while(rs.next()) {
				if(rs.getString(1) != null) {
					max_id = Integer.parseInt(rs.getString(1));
				}else {
					max_id = 0;
				}
			}

			max_id++;
			if(max_id==10) {

			}

			String sql = "INSERT INTO "+schema+".published_book(person_id,id,title,authors,year,publisher) VALUES (?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, pb.person_id);
			ps.setString(2, ""+max_id);
			ps.setString(3, pb.title);
			ps.setString(4, pb.authors);
			ps.setString(5, pb.year);
			ps.setString(6, pb.publisher);
			////System.out.println(ps.toString());
			int resultUpdate = ps.executeUpdate();

			con.close();
			if(resultUpdate > 0) {
				return max_id;
			}else {
				return -1;

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return 1;
	}

	public static int writeToBookChapterTable(BookChapter bc) {
		con = DbCon.getConnection();
		int max_id = 0;
		try {
			ResultSet rs = con.prepareStatement("SELECT MAX(to_number(id, '99999999999999')) FROM "+schema+".book_chapter WHERE person_id="+bc.person_id).executeQuery();
			while(rs.next()) {
				if(rs.getString(1) != null) {
					max_id = Integer.parseInt(rs.getString(1));
				}else {
					max_id = 0;
				}
			}

			max_id++;
			if(max_id==10) {

			}

			String sql = "INSERT INTO "+schema+".book_chapter(person_id,id,chapter,authors,year,title,publisher) VALUES (?,?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, bc.person_id);
			ps.setString(2, ""+max_id);
			ps.setString(3, bc.chapter);
			ps.setString(4, bc.authors);
			ps.setString(5, bc.year);
			ps.setString(6, bc.title);
			ps.setString(7, bc.publisher);
			////System.out.println(ps.toString());
			int resultUpdate = ps.executeUpdate();

			con.close();
			if(resultUpdate > 0) {
				return max_id;
			}else {
				return -1;

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return 1;
	}

	public static int writeToGrantTable(Grant g) {
		con = DbCon.getConnection();
		int max_id = 0;
		try {
			ResultSet rs = con.prepareStatement("SELECT MAX(to_number(id, '99999999999999')) FROM "+schema+".grantTable WHERE person_id="+g.person_id).executeQuery();
			while(rs.next()) {
				if(rs.getString(1) != null) {
					max_id = Integer.parseInt(rs.getString(1));
				}else {
					max_id = 0;
				}
			}

			max_id++;
			if(max_id==10) {

			}

			String sql = "INSERT INTO "+schema+".grantTable(person_id,id,grant_no, investigators, dateFrom, dateTo, agency, amount, status, type, role, level) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, g.person_id);
			ps.setString(2, ""+max_id);
			ps.setString(3, g.grantNo);
			ps.setString(4, g.investigators);
			ps.setString(5, g.from);
			ps.setString(6, g.to);
			ps.setString(7, g.agency);
			ps.setString(8, g.amount);
			ps.setString(9, g.status);
			ps.setString(10, g.type);
			ps.setString(11, g.role);
			ps.setString(12, g.level);
			////System.out.println(ps.toString());
			int resultUpdate = ps.executeUpdate();

			con.close();
			if(resultUpdate > 0) {
				return max_id;
			}else {
				return -1;

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return 1;
	}

	public static int updatePublication(PeerReviewedPublication prp) {
		con = DbCon.getConnection();
		int max_id = 0;
		try {

			String sql = "UPDATE "+schema+".journal_publication SET doi = ? WHERE person_id=? AND id=?";

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, prp.doi);
			ps.setInt(2, prp.person_id);

			ps.setString(3, ""+prp.id);

			int resultUpdate = ps.executeUpdate();

			con.close();
			if(resultUpdate > 0) {
				return prp.id;
			}else {
				return -1;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 1;
	}

	public static int writeToPerson(String uri, String name, String title, String email, String phone, String overview) {
		con = DbCon.getConnection();
		int max_id = 0;
		try {
			ResultSet rs = con.prepareStatement("SELECT MAX(id) FROM "+schema+".person").executeQuery();
			while(rs.next()) {
				if(rs.getString(1) != null) {
					max_id = Integer.parseInt(rs.getString(1));
				}else {
					max_id = 0;
				}
			}

			max_id++;
			String sql = "INSERT INTO "+schema+".person(id,uri,name,title,email,phone,overview) VALUES (?,?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, max_id);
			ps.setString(2, uri);
			ps.setString(3, name);
			ps.setString(4, title);
			ps.setString(5, email);
			ps.setString(6, phone);
			ps.setString(7, overview);
			int resultUpdate = ps.executeUpdate();

			con.close();
			if(resultUpdate > 0) {
				return max_id;
			}else {
				return -1;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 1;

	}

	public static int writeToEditorialTable(Editorial ed) {
		con = DbCon.getConnection();
		int max_id = 0;
		try {
			ResultSet rs = con.prepareStatement("SELECT MAX(to_number(id, '99999999999999')) FROM "+schema+".editorial WHERE person_id="+ed.person_id).executeQuery();
			while(rs.next()) {
				if(rs.getString(1) != null) {
					max_id = Integer.parseInt(rs.getString(1));
				}else {
					max_id = 0;
				}
			}

			max_id++;
			if(max_id==10) {

			}
			String sql = "INSERT INTO "+schema+".editorial(person_id,id,title,authors,year,volume,pages) VALUES (?,?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, ed.person_id);
			ps.setString(2, ""+max_id);
			ps.setString(3, ed.title);
			ps.setString(4, ed.authors);
			ps.setString(5, ed.year);
			ps.setString(6, ed.volume);
			ps.setString(7, ed.pages);
			
			int resultUpdate = ps.executeUpdate();
			//ps.close();
			//con.commit();
			con.close();
			if(resultUpdate > 0) {
				return max_id;
			}else {
				return -1;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 1;
	}

	public static int writeToTechnicalReportTable(TechnicalReport tr) {
		con = DbCon.getConnection();
		int max_id = 0;
		try {
			ResultSet rs = con.prepareStatement("SELECT MAX(to_number(id, '99999999999999')) FROM "+schema+".technical_report WHERE person_id="+tr.person_id).executeQuery();
			while(rs.next()) {
				if(rs.getString(1) != null) {
					max_id = Integer.parseInt(rs.getString(1));
				}else {
					max_id = 0;
				}
			}

			max_id++;
			if(max_id==10) {

			}
			String sql = "INSERT INTO "+schema+".technical_report(person_id,id,title,authors,year,pages) VALUES (?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, tr.person_id);
			ps.setString(2, ""+max_id);
			ps.setString(3, tr.title);
			ps.setString(4, tr.authors);
			ps.setString(5, tr.year);
			ps.setString(6, tr.pages);
			
			int resultUpdate = ps.executeUpdate();
			//ps.close();
			//con.commit();
			con.close();
			if(resultUpdate > 0) {
				return max_id;
			}else {
				return -1;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 1;
	}

	



}
