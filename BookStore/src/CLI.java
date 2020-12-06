
import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * 
 * this is the command line class, responsible to interact with the user
 * 
 * @author jeancastro
 *
 */
public class CLI {

	Database db = new Database();

	/**
	 * readingReaders(); this extended from Database method is to load the Readers
	 * in the arraylist readingbooks(); this extended from Database method is to
	 * load the Books in the arraylist. readingqueue(); this extended from Database
	 * method is to load the Queue in the arraylist. readingborrowed(); this
	 * extended from Database method is to load the Queue in the arraylist.
	 */

	public CLI() {

		System.out.println("--------- LOADED IN THE SYSTEM ---------");
		System.out.println("");

		db.readingReaders();
		db.readingbooks();
		db.readingqueue();
		db.readingborrowed();

		System.out.println("");
		System.out.println("-----------------------------------------");
		System.out.println("");

		menuoption();

	}

	/**
	 * this method return the user input and validation
	 * 
	 * @return user option
	 */

	public int readinguser() {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String optionString = null;
		int option = 0;

		try {
			optionString = br.readLine();
			option = Integer.parseInt(optionString);

		} catch (IOException | NumberFormatException e) {
			System.out.println("That's not a valid option, please an integer number");
			System.out.println("_____________");
			menuoption();
		}

		return option;
	}

	/**
	 * this method display the main menu with some options
	 */
	public void menuoption() {

		System.out.println("Welcome to the Library Shop!! What would you like to do?");
		System.out.println("Press 1 - Search");
		System.out.println("Press 2 - Books/Readers");
		System.out.println("Press 3 - Return a Book");
		System.out.println("Press 4 - Exit");

		int option = readinguser();

		if (option == 1) {
			try {
				searchmenu();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		else if (option == 2) {
			booksreaders();
		}

		else if (option == 3) {
			try {
				returnbook();
			} catch (ParserConfigurationException | IOException | TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		else if (option == 4) {

			System.out.close();
		} else {

			System.out.println("Not a valid number");
			menuoption();

		}

	}

	/**
	 * this method is the search menu, will be displayed after user entered the
	 * choice from the method mainmenu();
	 * 
	 * @throws TransformerException
	 * @throws ParserConfigurationException
	 */

	public void searchmenu() throws TransformerException, ParserConfigurationException {

		System.out.println("Search Session");
		System.out.println("________________");
		System.out.println("Press 1 - Search Book by Title/Author");
		System.out.println("Press 2 - Search Reader by Name/ID");
		System.out.println("Press 3 - Search Books Borrowed by the Reader");
		System.out.println("Press 4 - Return To The Main Menu");

		int option = readinguser();

		if (option == 1) {
			System.out.println("Please Enter Book Title or Author");

			Scanner myScanner = new Scanner(System.in);
			String Author = myScanner.nextLine();

			String choice;

			if (db.searchbyAuthor(Author) == null) {
				System.out.println(Author + " does not exist");
				searchmenu();

			} else if (db.searchbyAuthor(Author).isBorrowed() == true) {
				do {
					System.out.println("Sorry, title " + Author + " not Available");
					System.out.println(" ");
					System.out.println("Would you like to be added in the waiting list? ");
					System.out.println("1 - Yes ");
					System.out.println("2 - NO");

					choice = myScanner.nextLine();
				} while (!choice.matches("[1-2]+"));

				if (choice.equals("1")) {

					System.out.println("Please enter the Reader ID or Name");
					String userinput = myScanner.nextLine();

					if (db.searchbyname(userinput) == null) {
						System.out.println(userinput + " does not exist");
						searchmenu();
					}

					db.waitingList(db.searchbyname(userinput), db.searchbyAuthor(Author));
					db.searchbyname(userinput).getFirstname();
					 
					
				}
				System.out.println(" ");
				menuoption();
				

				if (choice.equals("2")) {

					menuoption();
				}

			}

			else {
				System.out.println("------------------------------------------");
				System.out.println("ID: " + db.searchbyAuthor(Author).getID());
				System.out.println("Title: " + db.searchbyAuthor(Author).getTitle());
				System.out.println("Author: " + db.searchbyAuthor(Author).getAuthor());
				System.out.println(" ");
				System.out.println("!!This Book is Available!!");
				System.out.println(" ");
				System.out.println("Would you like to borrow the book: " + db.searchbyAuthor(Author).getTitle() + "?");
				System.out.println("Press 1 - Yes");
				System.out.println("Press 2 - No");

				String borrowOption = myScanner.nextLine();
				if (borrowOption.equals("1")) {

					// TODO create method to insert book to the user in the array list

					System.out.println("Please enter the Registered Reader Name!");

					Calendar today = Calendar.getInstance();
					today.clear(Calendar.HOUR);
					today.clear(Calendar.MINUTE);
					today.clear(Calendar.SECOND);
					Date todayDate = today.getTime();

					String isToday = todayDate.toString();

					String reader = myScanner.nextLine();

					// call method that inserts (Book, Reader) into borrowed array
					db.myBorrowedBooks(db.searchbyAuthor(Author), db.searchbyname(reader), isToday, "");

					try {
						db.setTrue(db.searchbyAuthor(Author));
					} catch (ParserConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					System.out.println("This Book was bowrred to: " + reader);
					System.out.println("_____________");

					try {
						db.setBorrowed();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					menuoption();

				} else {
					menuoption();
				}

				// borrowedreturn();
			}

			myScanner.close();

		}

		else if (option == 2) {
			System.out.println("Please Enter Reader Name or ID");

			try {
				Scanner myScanner = new Scanner(System.in);
				String Name = myScanner.nextLine();

				myScanner.close();

				if (db.searchbyname(Name) == null) {
					System.out.println("Sorry, Reader " + Name + " not found!");
				}

				else {
					System.out.println("------------------------------------------");
					System.out.println("ID: " + db.searchbyname(Name).getID());
					System.out.println("First Name: " + db.searchbyname(Name).getFirstname());
					System.out.println("Last Name: " + db.searchbyname(Name).getLastname());
					System.out.println("Email: " + db.searchbyname(Name).getEmail());
					System.out.println("Phone: " + db.searchbyname(Name).getPhone());
				}

			} catch (Exception e) {
				System.out.println("Invalid input");

			}
		}

		else if (option == 3) {

			System.out.println(" Please enter Book Borrowed Name ");
			
			Scanner myScanner = new Scanner(System.in);
			String Bookname = myScanner.nextLine();

			if (db.searchbyAuthor(Bookname) == null) {
				System.out.println(Bookname + " does not exist");
				searchmenu();

			} else if (db.searchbyAuthor(Bookname).isBorrowed() == true) {
				
				db.searchrborrowedbook(db.searchbyAuthor(Bookname));
				System.out.println(" ");
				menuoption();
			
		} else if (db.searchbyAuthor(Bookname).isBorrowed() == false) {
			
			System.out.println("This book: " + Bookname +  " wasn't borrowed");
			
			menuoption();
	}

			
			
		else if (option == 4) {

			menuoption();
		}

		else {

			System.out.println("Not a valid number");
			searchmenu();

		}
		}
	}

	/**
	 * this method is the book and readers option to display the books and reader in
	 * the registered in the system
	 */

	public void booksreaders() {

		System.out.println("Books/Readers Session");
		System.out.println("________________");
		System.out.println("Press 1 - All Books in Alphabetical Order");
		System.out.println("Press 2 - All Books in ID Order");
		System.out.println("Press 3 - All Readers in Aphabetical Order");
		System.out.println("Press 4 - All Readers in ID Order");
		System.out.println("Press 5 - Return To the Main Menu");

		int option = readinguser();

		if (option == 1) {

			System.out.println("All Books in Alphabetical Order Below");
			db.alphabeticalorderBooks();
			System.out.println("_____________________________________");
			System.out.println(" ");
			menuoption();

		}

		else if (option == 2) {

			System.out.println("All Books ID Order Below");
			db.IDorderBooks();
			System.out.println("_____________________________________");
			System.out.println(" ");
			menuoption();

		}

		else if (option == 3) {

			System.out.println("All Readers in Alphabetical Order Below");
			db.alphabeticalorderReader();
			System.out.println("_____________________________________");
			System.out.println(" ");
			menuoption();
		} else if (option == 4) {

			System.out.println("All Readers  ID Order Below");
			db.IDorderReaders();
			System.out.println("_____________________________________");
			System.out.println(" ");
			menuoption();

		}

		else if (option == 5) {

			menuoption();
		} else {

			System.out.println("Not a valid number");
			booksreaders();

		}

	}

	/**
	 * 
	 * this method is for the returned book
	 * 
	 * @throws FileNotFoundException
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws TransformerException
	 */
	public void returnbook()
			throws FileNotFoundException, ParserConfigurationException, IOException, TransformerException {

		System.out.println("Return Session");
		System.out.println(" ");
		System.out.println("1 - Return a Book");
		System.out.println("2 - Return To The Main Menu");

		int option = readinguser();

		if (option == 1) {

			Scanner myScan = new Scanner(System.in);

			System.out.println("Please enter Book Name  ");

			String myReturnedBook = myScan.nextLine();

			if (db.searchbyAuthor(myReturnedBook) == null) {
				System.out.println(myReturnedBook + " does not exist");
				returnbook();

			} else {

				System.out.println(" ");
				System.out.println("Checking queue.........");
				System.out.println("Checking queue.........");
				System.out.println("Checking queue.........");
				System.out.println(" ");

			}

			Calendar today = Calendar.getInstance();
			today.clear(Calendar.HOUR);
			today.clear(Calendar.MINUTE);
			today.clear(Calendar.SECOND);
			Date todayDate = today.getTime();

			String isToday = todayDate.toString();

			db.returnbook(db.searchbyAuthor(myReturnedBook).getID(), isToday);

			menuoption();

			myScan.close();

		}

		else if (option == 2) {

			menuoption();

		}

		else {

			System.out.println("Not a valid number");
			returnbook();

		}

	}

}
