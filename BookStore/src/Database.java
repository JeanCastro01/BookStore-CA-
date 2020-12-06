import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class Database {

	Readers reader;
	Books book;
	Borrowed borrowed;

	ArrayList<Readers> myReaders = new ArrayList<Readers>();
	ArrayList<Books> myBooks = new ArrayList<Books>();
	ArrayList<Borrowed> myBorrowed = new ArrayList<Borrowed>();

	public Database() {

	}

	public void myBorrowedBooks(Books outterBook, Readers outterReader, String daterented, String datereturn)
			throws TransformerException, ParserConfigurationException {

		borrowed = new Borrowed(outterBook, outterReader, daterented, datereturn);
		myBorrowed.add(borrowed);
		System.out.println(outterReader.getEmail());

		// for(int i; i< myBorrowed.size(); i++) {
		// System.out.println(myBorrowed.get(i).getMybook().getID());
		// }

	}

	/**
	 * this method is to creat the xml file and load the data from the borrowed
	 * arraylist
	 * 
	 * @throws TransformerException
	 * @throws ParserConfigurationException
	 */
	public void creatingBorrowedXML() throws TransformerException, ParserConfigurationException {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		Document xmldoc = docBuilder.newDocument();

		// <borroweds>

		// <book id="1">
		// <reader id="">
		// <daterented></daterented>
		// <datereturned></datereturned>
		// </reader>
		// </book>

		// </borroweds>

		Element rootElement = xmldoc.createElement("borroweds");
		xmldoc.appendChild(rootElement);

		for (int i = 0; i < myBorrowed.size(); i++) {

			Element mainElement = xmldoc.createElement("book");
			mainElement.setAttribute("id", myBorrowed.get(i).getMybook().getID());

			Element booktnameText4 = xmldoc.createElement("reader");
			Text readerID = xmldoc.createTextNode(myBorrowed.get(i).getMyreader().getID());
			booktnameText4.appendChild(readerID);

			Element booktnameText1 = xmldoc.createElement("daterented");
			Text bookNameText1 = xmldoc.createTextNode(myBorrowed.get(i).getDaterented());
			booktnameText1.appendChild(bookNameText1);

			Element booktnameText2 = xmldoc.createElement("datereturned");
			Text bookNameText2 = xmldoc.createTextNode(myBorrowed.get(i).getDatetreturn());
			booktnameText2.appendChild(bookNameText2);

			mainElement.appendChild(booktnameText1);
			mainElement.appendChild(booktnameText2);
			mainElement.appendChild(booktnameText4);

			rootElement.appendChild(mainElement);

		}

		DOMSource source = new DOMSource(xmldoc);

		String path2 = "Borrowed.xml";
		File f2 = new File(path2);

		Result result = new StreamResult(f2);

		TransformerFactory transformerfactory = TransformerFactory.newInstance();
		Transformer transformer = transformerfactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(source, result);

		// System.out.println("Write data sucess to file:" + path2);

	}

	/**
	 * this method is to load the data from the Readers.xml file into the myreaders
	 * arraylist
	 */
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

	/**
	 * this method is to load the data from the Books.xml file into the myBooks
	 * arraylist
	 */
	public void readingbooks() {

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

	/**
	 * this method is to sorting the Books in Alphabetical Order
	 */
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

	/**
	 * this method is to sortinf the Books from the Books.xml file by ID order
	 */
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

	/**
	 * this method is to sorting the Readers from the Readers.xml file in
	 * Alphabetical Order
	 */
	public void alphabeticalorderReader() {


		for (int i = 0; i < myReaders.size(); i++) {

			for (int j = 0; j < myReaders.size() - 1; j++)

				if (String.valueOf(myReaders.get(j).getFirstname())
						.compareTo(myReaders.get(j + 1).getFirstname()) > 0) {

					Readers temp = myReaders.get(j);
					myReaders.set(j, myReaders.get(j + 1));
					myReaders.set(j + 1, temp);

				}

		}

		for (int i = 0; i < myReaders.size(); i++) {

			System.out.println("\n" + myReaders.get(i).getFirstname());
		}
	}
/**
 * This methos set the borrowed true if the book is borrowed
 * @param this book
 * @throws ParserConfigurationException
 */
	public void setTrue(Books books) throws ParserConfigurationException {


		for (int i = 0; i < myBooks.size(); i++) {

			// When the element is found, stop the loop and return the index
			if (myBooks.get(i).getAuthor().equalsIgnoreCase(books.getAuthor())
					|| myBooks.get(i).getTitle().equalsIgnoreCase(books.getTitle())) {

				myBooks.get(i).setBorrowed(true);

			}
		}

	}
	/**
	 * this method write the new file borrowed.xml with the borrowed updated
	 * @throws ParserConfigurationException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws TransformerException
	 */
	public void setBorrowed()
			throws ParserConfigurationException, FileNotFoundException, IOException, TransformerException {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		Document xmldoc = docBuilder.newDocument();

		// <books>
		// <book>
		// <Title></Title>
		// <Author></Author>
		// <Genre></Genre>
		// <Borrowed></Borrowed>
		// <book>
		// </books>

		Element rootElement = xmldoc.createElement("books");
		xmldoc.appendChild(rootElement);

		for (int i = 0; i < myBooks.size(); i++) {

			// Element rootElement = xmldoc.createElement("books");

			// Text bookIDAttribute = xmldoc.createTextNode(myBooks.get(i).getID());
			Element mainElement = xmldoc.createElement("book");
			mainElement.setAttribute("id", myBooks.get(i).getID());
			rootElement.appendChild(mainElement);

			Element booktnameText1 = xmldoc.createElement("Title");
			Text bookNameText1 = xmldoc.createTextNode(myBooks.get(i).getTitle());
			booktnameText1.appendChild(bookNameText1);
			mainElement.appendChild(booktnameText1);

			Element booktnameText2 = xmldoc.createElement("Author");
			Text bookNameText2 = xmldoc.createTextNode(myBooks.get(i).getAuthor());
			booktnameText2.appendChild(bookNameText2);
			mainElement.appendChild(booktnameText2);

			Element booktnameText3 = xmldoc.createElement("Genre");
			Text bookNameText3 = xmldoc.createTextNode(myBooks.get(i).getGenre());
			booktnameText3.appendChild(bookNameText3);
			mainElement.appendChild(booktnameText3);

			Element booktnameText4 = xmldoc.createElement("Borrowed");
			Text bookNameText4 = xmldoc.createTextNode(String.valueOf(myBooks.get(i).isBorrowed()));
			booktnameText4.appendChild(bookNameText4);
			mainElement.appendChild(booktnameText4);

		}

		DOMSource source = new DOMSource(xmldoc);

		String path2 = "Books.xml";
		File f2 = new File(path2);

		Result result = new StreamResult(f2);

		TransformerFactory transformerfactory = TransformerFactory.newInstance();
		Transformer transformer = transformerfactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(source, result);

		// System.out.println("Write data sucess to file:" + path2);

		creatingBorrowedXML();

	}
/**
 * this method print the ID readers in order
 */
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
/**
 * this method returns the books searched by the user
 * @param this Author's boook
 * @return
 */
	public Books searchbyAuthor(String Author) {

		Books searchedBook = null;

		// Going one by one the elements in the array
		for (int i = 0; i < myBooks.size(); i++) {

			// When the element is found, stop the loop and

			if (myBooks.get(i).getAuthor().equalsIgnoreCase(Author)
					|| myBooks.get(i).getTitle().equalsIgnoreCase(Author)
					|| myBooks.get(i).getID().equalsIgnoreCase(Author)) {
				searchedBook = new Books(myBooks.get(i).getID(), myBooks.get(i).getAuthor(), myBooks.get(i).getTitle(),
						myBooks.get(i).getGenre(), myBooks.get(i).isBorrowed());

				return searchedBook;
			}

		}

		return null;

	}
/**
 * this method returns the reader searched by the user
 * @param this reader Name
 * @return
 */
	public Readers searchbyname(String Name) {

		Readers searchedname = null;

		// Going one by one the elements in the array
		for (int i = 0; i < myReaders.size(); i++) {

			// When the element is found, stop the loop and return the index
			if (myReaders.get(i).getFirstname().equalsIgnoreCase(Name)
					|| myReaders.get(i).getID().equalsIgnoreCase(Name)) {
				searchedname = new Readers(myReaders.get(i).getID(), myReaders.get(i).getFirstname(),
						myReaders.get(i).getLastname(), myReaders.get(i).getEmail(), myReaders.get(i).getPhone());
				return searchedname;

			}

		}
		return null;

	}

	/**
	 * this method is responsible to reader from the queue.xml and store in the book's queue
	 */
	public void readingqueue() {

		/*
		 * 1 - get bookID and create book object 2 - get readerID and create reader
		 * object 3 - search in your array of books for that book 4 - access the queue
		 * from that book 5 - add the reader object to the queue
		 */

		try {

			File xmlDoc = new File("Queue.xml");

			DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbBuild = dbFact.newDocumentBuilder();
			Document doc = dbBuild.parse(xmlDoc);

			// Read root element

			System.out.println( doc.getDocumentElement().getNodeName());

			// read array of students elements
			// this array is called Nodelist

			NodeList nList = doc.getElementsByTagName("book");
			
			

			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					String bookid;
					String readerid;

					Element eElement = (Element) nNode;

					bookid = eElement.getAttribute("id");

					readerid = eElement.getElementsByTagName("readerid").item(0).getTextContent();

					book = searchbyAuthor(bookid);

					reader = searchbyname(readerid);

					for (Books books : myBooks) {
						if (books.getID().equals(book.getID())) {
							books.getQueue().addLast(reader);
							
						}
					}



				}
			}

		} catch (Exception e) {

		}

	}
/**
 * this method is responsible to reader from the borrowed.xml and store to the array myBorrowed
 */
	public void readingborrowed() {

		try {

			File xmlDoc = new File("Borrowed.xml");

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

					String bookid;
					String readerid;
					String daterented;
					String datereturned;

					Element eElement = (Element) nNode;

					bookid = eElement.getAttribute("id");
					readerid = eElement.getElementsByTagName("reader").item(0).getTextContent();
					daterented = eElement.getElementsByTagName("daterented").item(0).getTextContent();
					datereturned = eElement.getElementsByTagName("datereturned").item(0).getTextContent();

					Books book = searchbyAuthor(bookid);
					Readers readers = searchbyname(readerid);

					Borrowed borrow = new Borrowed(book, readers, daterented, datereturned);
					myBorrowed.add(borrow);

				}
			}
		} catch (Exception e) {

		}

	}
/**
 * this method is responsible to add the Reader to the queue if the book is already borrowed
 * @param this searchbyname's is the reader
 * @param this searchbyAuthor's is the book
 * @throws TransformerException
 * @throws ParserConfigurationException
 */
	public void waitingList(Readers searchbyname, Books searchbyAuthor)
			throws TransformerException, ParserConfigurationException {

		/*
		 * 1 - find the book in my book array
		 * 2 - get the book 3 - access the book queue and add Reader
		 */

		for (int i = 0; i < myBooks.size(); i++) {

			if (myBooks.get(i).getID().equals(searchbyAuthor.getID())) {

				myBooks.get(i).getQueue().addLast(searchbyname);
			

				writingtoqueue();
			}

		}

	}
/**
 * this method is responsible to write in the queue.xml with the book's queue updated
 */
	public void writingtoqueue() {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document xmldoc = docBuilder.newDocument();

			Element rootElement = xmldoc.createElement("Queues");

			Element mainElement = null;
			Element booktnameText1 = null;

			for (int i = 0; i < myBooks.size(); i++) {

				mainElement = xmldoc.createElement("book");
				mainElement.setAttribute("id", myBooks.get(i).getID());

				for (int j = 0; j < myBooks.get(i).getQueue().size(); j++) {

					booktnameText1 = xmldoc.createElement("readerid");
					Text bookNameText1 = xmldoc
							.createTextNode(myBooks.get(i).getQueue().findElementByPosition(j).getElement().getID());
					booktnameText1.appendChild(bookNameText1);
					mainElement.appendChild(booktnameText1);
					rootElement.appendChild(mainElement);

				}
			}

			xmldoc.appendChild(rootElement);

			DOMSource source = new DOMSource(xmldoc);

			String path2 = "Queue.xml";
			File f2 = new File(path2);

			Result result = new StreamResult(f2);

			TransformerFactory transformerfactory = TransformerFactory.newInstance();
			Transformer transformer = transformerfactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);

			System.out.println("The" + path2 + "Was updated with the new Reader in the Queue");
		} catch (Exception e) {

		}

	}
/**
 * this method is responsible to update the borrowed.xml with the datereturned and to check which is the next reader in the queue for the book
 * @param this myReturnedBook is the book returned
 * @param this ReturnedDate is the returned date
 * @throws FileNotFoundException
 * @throws ParserConfigurationException
 * @throws IOException
 * @throws TransformerException
 */
	public void returnbook(String myReturnedBook, String ReturnedDate)
			throws FileNotFoundException, ParserConfigurationException, IOException, TransformerException {

		// get book id
		// get returndate
		// search book id
		//
		// search book in the array mybooks
		// change information from book isBorrowed to false
		// get into myBooksQueue
		// search for reader in position 0
		// if there is somebody get element.ID and print message on the screen
		// call method that saves myBooks to file
		//
		//
		// search book in the array myBorroweds
		// insert dateReturned
		// call method that saves myBorroweds to file

		for (int i = 0; i < myBooks.size(); i++) {

			if (myBooks.get(i).getID().equals(myReturnedBook)) {

				myBooks.get(i).setBorrowed(false);

				// myBoo ks.get(i).getQueue().findElementByPosition(0).getElement().getID();
				try {
					System.out.println("The next person in the queue is: "
							+ myBooks.get(i).getQueue().findElementByPosition(0).getElement().getFirstname());
				} catch (Exception e) {

					System.out.println("There is nobody in the queue");
					System.out.println(" ");

				}
				setBorrowed();

				System.out.println("The book Was returned to the system");
				System.out.println("___________");
				System.out.println(" ");

			}

			for (int j = 0; j < myBorrowed.size(); j++) {

				if (myBorrowed.get(j).getMybook().getID().equals(myReturnedBook)) {
					myBorrowed.get(j).setDatetreturn(ReturnedDate);

					creatingBorrowedXML();
				}
				
				

			}

		}
	}


public void searchrborrowedbook(Books borrowedbook) {
	
	     System.out.println(borrowedbook.getTitle());

	     for (int i = 0; i < myBorrowed.size(); i++) {

		//if (myBorrowed.get(i).getMybook().equals(borrowedbook)) {

			System.out.println("This book was borrowed to: " + myBorrowed.get(i).getMyreader().getFirstname());
	
		}
	}
}
//}

