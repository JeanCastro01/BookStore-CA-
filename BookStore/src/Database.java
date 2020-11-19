import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Database {

	Readers reader = new Readers();
	Books book = new Books();
	Borrowed borrowed;

	ArrayList<Readers> myReaders = new ArrayList<Readers>();
	ArrayList<Books> myBooks = new ArrayList<Books>();
	ArrayList<Borrowed> myBorrowed= new ArrayList<Borrowed>();

	public Database() {

	}
	
	public void myBorrowedBooks(Books outterBook, Readers outterReader, String daterented, String datereturn) {
		
		
		
		borrowed = new Borrowed(outterBook, outterReader, daterented, datereturn );
		myBorrowed.add(borrowed); 
		
		//TODO CALL METHOD TO WRITE IN THE BORROWED.XML FILE
		

		
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

	public void alphabeticalorderBooks() {

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
	

	
	public void IDorderBooks() {

		for (int i = 0; i < myBooks.size(); i++) {

			for (int j = 0; j < myBooks.size() - 1; j++)

				if (String.valueOf(myBooks.get(j).getID()).compareTo(myBooks.get(j + 1).getID()) > 0) {

					Books temp = myBooks.get(j);
					myBooks.set(j, myBooks.get(j + 1));
					myBooks.set(j + 1, temp);

				}

		}

		for (int i = 0; i < myBooks.size(); i++) {

			System.out.println("\n" + myBooks.get(i).getID());
		}
	}
	
	public void alphabeticalorderReader() {

		for (int i = 0; i < myReaders.size(); i++) {

			for (int j = 0; j < myReaders.size() - 1; j++)

				if (String.valueOf(myReaders.get(j).getFirstname()).compareTo(myReaders.get(j + 1).getFirstname()) > 0) {

					Readers temp = myReaders.get(j);
					myReaders.set(j, myReaders.get(j + 1));
					myReaders.set(j + 1, temp);

				}

		}

		for (int i = 0; i < myReaders.size(); i++) {

			System.out.println("\n" + myReaders.get(i).getFirstname());
		}
	}
	
	
	public void setTrue(Books books) throws ParserConfigurationException {
		
		for (int i = 0; i < myBooks.size(); i++) {

			// When the element is found, stop the loop and return the index
			if (myBooks.get(i).getAuthor().equalsIgnoreCase(books.getAuthor()) || myBooks.get(i).getTitle().equalsIgnoreCase(books.getTitle()))
			{
				 
              myBooks.get(i).setBorrowed(true);
              
              
             
				setBorrowed();
		
			
	
			}
		}
	
	}
	
	public void setBorrowed()  {
		
		
	}
		

	public void IDorderReaders() {

		for (int i = 0; i < myReaders.size(); i++) {

			for (int j = 0; j < myReaders.size() - 1; j++)

				if (String.valueOf(myReaders.get(j).getID()).compareTo(myReaders.get(j + 1).getID()) > 0) {

					Readers temp = myReaders.get(j);
					myReaders.set(j, myReaders.get(j + 1));
					myReaders.set(j + 1, temp);

				}

		}

		for (int i = 0; i < myReaders.size(); i++) {

			System.out.println("\n" + myReaders.get(i).getID());
		}
	}


	public Books searchbyAuthor(String Author) {

		Books searchedBook = null;

		// Going one by one the elements in the array
		for (int i = 0; i < myBooks.size(); i++) {

			// When the element is found, stop the loop and
			
			if (myBooks.get(i).getAuthor().equalsIgnoreCase(Author) || myBooks.get(i).getTitle().equalsIgnoreCase(Author)) {
				searchedBook = new Books(myBooks.get(i).getID(), myBooks.get(i).getAuthor(), myBooks.get(i).getTitle(),
						myBooks.get(i).getGenre(), myBooks.get(i).isBorrowed());

				return searchedBook;
			}

		}

		return null;

	}

	public Readers searchbyname(String Name ) {

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
