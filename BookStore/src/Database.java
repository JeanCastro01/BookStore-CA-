import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Database {

	Readers reader = new Readers();
	Books book = new Books();

	ArrayList<Readers> myReaders = new ArrayList<Readers>();
	ArrayList<Books> myBooks = new ArrayList<Books>();

	public Database() {

	}

	public void readingReaders() {

		try {

			File xmlDoc = new File("Reader.xml");

			DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbBuild = dbFact.newDocumentBuilder();
			Document doc = dbBuild.parse(xmlDoc);

			// Read root element
			System.out.println(doc.getDocumentElement().getNodeName());

			// read array of students elements
			// this array is called Nodelist

			NodeList nList = doc.getElementsByTagName("reader");

			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					String id;
					String firstname;
					String lastname;
					String email;
					String phone;

					Element eElement = (Element) nNode;
					id = eElement.getAttribute("id");

					firstname = eElement.getElementsByTagName("firstname").item(0).getTextContent();

					lastname = eElement.getElementsByTagName("lastname").item(0).getTextContent();

					email = eElement.getElementsByTagName("email").item(0).getTextContent();

					phone = eElement.getElementsByTagName("phone").item(0).getTextContent();

					Readers reader = new Readers(id, firstname, lastname, email, phone);
					myReaders.add(reader);

				}
			}

		} catch (Exception e) {

		}

	}

	public void redingbooks() {

		try {

			File xmlDoc = new File("Books.xml");

			DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbBuild = dbFact.newDocumentBuilder();
			Document doc = dbBuild.parse(xmlDoc);

			// Read root element

			System.out.println(doc.getDocumentElement().getNodeName());

			// read array of students elements
			// this array is called Nodelist

			NodeList nList = doc.getElementsByTagName("book");

			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					String id;
					String title;
					String author;
					String genre;
					boolean borrowed;

					Element eElement = (Element) nNode;

					id = eElement.getAttribute("id");

					title = eElement.getElementsByTagName("Title").item(0).getTextContent();

					author = eElement.getElementsByTagName("Author").item(0).getTextContent();

					genre = eElement.getElementsByTagName("Genre").item(0).getTextContent();

					borrowed = Boolean.parseBoolean(eElement.getElementsByTagName("Borrowed").item(0).getTextContent());

					Books book = new Books(id, author, title, genre, borrowed);
					myBooks.add(book);

				}
			}

		} catch (Exception e) {

		}

	}

	public void alphabeticalorder() {

		for (int i = 0; i < myBooks.size(); i++) {

			for (int j = 0; j < myBooks.size() - 1; j++)

				if (String.valueOf(myBooks.get(j).getTitle()).compareTo(myBooks.get(j + 1).getTitle()) > 0) {

					Books temp = myBooks.get(j);
					myBooks.set(j, myBooks.get(j + 1));
					myBooks.set(j + 1, temp);

				}

		}

		for (int i = 0; i < myBooks.size(); i++) {

			System.out.println("\n" + myBooks.get(i).getTitle());
		}
	}

	public Books searchbyAuthor(String Author) {

		Books searchedBook = null;

		// Going one by one the elements in the array
		for (int i = 0; i < myBooks.size(); i++) {

			// When the element is found, stop the loop and return the index
			if (myBooks.get(i).getAuthor().equals(Author) || myBooks.get(i).getTitle().equals(Author)) {
				searchedBook = new Books(myBooks.get(i).getID(), myBooks.get(i).getAuthor(), myBooks.get(i).getTitle(),
						myBooks.get(i).getGenre(), myBooks.get(i).isBorrowed());

				return searchedBook;
			}

		}

		return null;

	}

	public Readers searchbyname(String Name) {

		Readers searchedname = null;

		// Going one by one the elements in the array
		for (int i = 0; i < myReaders.size(); i++) {

			// When the element is found, stop the loop and return the index
			if (myReaders.get(i).getFirstname().equals(Name) || myReaders.get(i).getID().equals(Name)) {
				searchedname = new Readers(myReaders.get(i).getID(), myReaders.get(i).getFirstname(),
						myReaders.get(i).getLastname(), myReaders.get(i).getEmail(), myReaders.get(i).getPhone());
				return searchedname;

			}

		}
		return null;

	}

	public void waitingList(Readers searchbyname, Books searchbyAuthor) {
		
		
		
	}

}
