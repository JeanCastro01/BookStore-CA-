import java.io.File;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class Database {

	public Database() {
		
		
		 Readers reader = new Readers();
		
		 ArrayList <Readers> myList = new ArrayList<Readers>();
		 
		
        
	}
		public void readingReaders() {
			
			
			
			try {
				
				File xmlDoc = new File("Reader.xml");
				
				DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
				DocumentBuilder dbBuild = dbFact.newDocumentBuilder();
				Document doc = dbBuild.parse(xmlDoc);
				
				// Read root element
				System.out.println("Root Element: " + doc.getDocumentElement().getNodeName());
				
				// read array of students elements
				//this array is called Nodelist
				
				NodeList nList = doc.getElementsByTagName("reader");
				
				for(int i=0; i < nList.getLength(); i ++ )
				{
					Node nNode = nList.item(i);
					
					if (nNode.getNodeType() == Node.ELEMENT_NODE ) {
						
						String id;
						String firstname;
						String lastname;
						String email;
						String phone;
						
						Element eElement = (Element) nNode;
						id = eElement.getAttribute("id");
				
						firstname = eElement.getElementsByTagName("firstname").item(0).getTextContent();
					
						lastname =	eElement.getElementsByTagName("lastname").item(0).getTextContent();
					
						email =		eElement.getElementsByTagName("email").item(0).getTextContent();
			
						phone =		eElement.getElementsByTagName("phone").item(0).getTextContent();
						System.out.println("___________________");
						
						Readers reader = new Readers(id,firstname,lastname,email,phone);
						System.out.println(reader.getFirstname());
					}
				}
				
				
				
			} catch (Exception e) {
				
				
			}
			
			
			
			
		}
		
		
	}


