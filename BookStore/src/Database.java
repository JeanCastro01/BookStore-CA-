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

	Readers reader = new Readers();
	Books book = new Books();
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

	//	for(int i; i< myBorrowed.size(); i++) {
	 //  System.out.println(myBorrowed.get(i).getMybook().getID());
	//	}
		

	}

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
			

			
			
			Element mainElement2 = xmldoc.createElement("reader");
			mainElement2.setAttribute("id", myBorrowed.get(i).getMyreader().getID());
			
		
			Element booktnameText1 = xmldoc.createElement("daterented");
			Text bookNameText1 = xmldoc.createTextNode(myBorrowed.get(i).getDaterented());
			booktnameText1.appendChild(bookNameText1);
			
			Element booktnameText2 = xmldoc.createElement("datereturned");
			Text bookNameText2 = xmldoc.createTextNode(myBorrowed.get(i).getDatetreturn());
			booktnameText2.appendChild(bookNameText2);
			
			mainElement2.appendChild(booktnameText1);
			mainElement2.appendChild(booktnameText2);
			
			mainElement.appendChild(mainElement2);
			
			rootElement.appendChild(mainElement);
			

			DOMSource source = new DOMSource(xmldoc);

			String path2 = "Borrowed.xml";
			File f2 = new File(path2);

			Result result = new StreamResult(f2);

			TransformerFactory transformerfactory = TransformerFactory.newInstance();
			Transformer transformer = transformerfactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);

			System.out.println("Write data sucess to file:" + path2);

		
		
		}

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

	public void setTrue(Books books) throws ParserConfigurationException {

		for (int i = 0; i < myBooks.size(); i++) {

			// When the element is found, stop the loop and return the index
			if (myBooks.get(i).getAuthor().equalsIgnoreCase(books.getAuthor())
					|| myBooks.get(i).getTitle().equalsIgnoreCase(books.getTitle())) {

				myBooks.get(i).setBorrowed(true);

			}
		}

	}

	public void setBorrowed() throws ParserConfigurationException, FileNotFoundException, IOException, TransformerException {

		
	
		
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
			
	
			
			//Element rootElement = xmldoc.createElement("books");

			//Text  bookIDAttribute = xmldoc.createTextNode(myBooks.get(i).getID());
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
			
		

			DOMSource source = new DOMSource(xmldoc);

			String path2 = "Books.xml";
			File f2 = new File(path2);

			Result result = new StreamResult(f2);

			TransformerFactory transformerfactory = TransformerFactory.newInstance();
			Transformer transformer = transformerfactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);

			System.out.println("Write data sucess to file:" + path2);

		}
		
		creatingBorrowedXML();

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

			if (myBooks.get(i).getAuthor().equalsIgnoreCase(Author)
					|| myBooks.get(i).getTitle().equalsIgnoreCase(Author) || myBooks.get(i).getID().equalsIgnoreCase(Author)){
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
			if (myReaders.get(i).getFirstname().equalsIgnoreCase(Name) || myReaders.get(i).getID().equalsIgnoreCase(Name)) {
				searchedname = new Readers(myReaders.get(i).getID(), myReaders.get(i).getFirstname(),
						myReaders.get(i).getLastname(), myReaders.get(i).getEmail(), myReaders.get(i).getPhone());
				return searchedname;

			}

		}
		return null;

	}
	
	public void readingqueue() {
		
		/* 1 - get bookID and create book object
		 * 2 - get readerID and create reader object
		 * 3 - search in your array of books for that book
		 * 4 - access the queue from that book
		 * 5 - add the reader object to the queue*/

		try {

			File xmlDoc = new File("Queue.xml");

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
				

					Element eElement = (Element) nNode;

					bookid = eElement.getAttribute("id");

					readerid = eElement.getElementsByTagName("readerid").item(0).getTextContent();

					
					
					
					Books book = searchbyAuthor(bookid);
					
					
					Readers readers = searchbyname(readerid);
				
					
					
					
					
					for (int j = 0; j < myBooks.size(); j++) {
						if (myBooks.get(j).getID().equals(book.getID())); 
					//array books / get a book / get id from that specific book || book
						
						myBooks.get(j).getQueue().addLast(readers);
					
					

				
					
			}

				}}} catch (Exception e) {
			
		}

	}


	public void waitingList(Readers searchbyname, Books searchbyAuthor) throws TransformerException, ParserConfigurationException {
		
	
		
		/* 1 - find the book in your book array
		 * 
		 * 
		 * 
		 * 2 - get the book
		 * 3 - access the book queue and add Reader*/
		
		for (int i = 0; i < myBooks.size(); i++) {
			
			if (myBooks.get(i).getID().equals(searchbyAuthor.getID())) {
				
				
				myBooks.get(i).getQueue().addLast(searchbyname);	
				
				writingtoqueue();
			}
					
			
		}
		
		
	}
		
	public void writingtoqueue () throws TransformerException, ParserConfigurationException {
		
		
		
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		Document xmldoc = docBuilder.newDocument();

		  // <Queues>
		   
		  //  <book1 id="1">
		  // <readerid></readerid>
		  //</book1>
		   
		   
		   // <book id="2">
		   // <readerid></readerid>
		   // </book>
		   
		   // <book id="3">
		   // <readerid></readerid>
		  //  </book>
		   
		   // <book id="4">
		   // <readerid></readerid>
		   // </book>
		   

		   //</Queues>
		
		
		
		/* 1 -  loop on the myBooks
		 * 2 - access book
		 * 3 - acess queue
		 * 4 - get the reader
		 * 5 - get the information from the reader that you need*/

		Element rootElement = xmldoc.createElement("Queues");
		
		Element mainElement =  null;
		Element booktnameText1 = null;
		
		try {
			
		
		for (int i = 0; i < myBooks.size(); i++) {

			mainElement= xmldoc.createElement("book");
			mainElement.setAttribute("id",myBooks.get(i).getID());

			//loop through queue to get reader id
			for (int j = 0; j < myBooks.get(i).getQueue().size(); j++) {
				
			booktnameText1= xmldoc.createElement("readerid");
			Text bookNameText1 = xmldoc.createTextNode(myBooks.get(i).getQueue().findElementByPosition(j).getElement().getID());
			
				
			booktnameText1.appendChild(bookNameText1);
			mainElement.appendChild(booktnameText1);
			rootElement.appendChild(mainElement);
		
			}}

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

		System.out.println("Write data sucess to file:" + path2);
		
		
			} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Something went terribly wrong. you are screwed!");
		 
		}

	}
	
	public void returnbook(String myReturnedBook, String ReturnedDate) {
		
		
		
		// get book id
		// get returndate
		// search book id
		//
		//search book in the array mybooks
		//change information from book isBorrowed to false
		//get into myBooksQueue
		//search for reader in position 0
		//if there is somebody get element.ID and print message on the screen
		//call method that saves myBooks to file
		//
		//
		//search book in the array myBorroweds
		//insert dateReturned
		//call method that saves myBorroweds to file
		
		
		for (int i =0; i < myBooks.size(); i ++) {
			
			myBooks.get(i).getID().equals(myReturnedBook);
			
			myBooks.get(i).setBorrowed(false);
			
			if (myBooks.get(i).getQueue().findElementByPosition(i).getElement().getFirstname()== null); {
				System.out.println("There is nobody in the queue");
			}
			
			myBooks.get(i).getQueue().findElementByPosition(i).getElement().getFirstname();
				System.out.println("There is nobody in the queue");
			}
			
		}
		
		
		//System.out.println(myReturnedBook);
		//System.out.println(ReturnedDate);
		
		
		
		
		
	
}


		
	
