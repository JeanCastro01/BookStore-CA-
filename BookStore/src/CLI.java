
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CLI {
	 
	
	        Database db = new Database();
    
	


   public CLI()  {
	   
            db.readingReaders();
            db.redingbooks();
           
	        menuoption();
	        
	         
	   
   }
   
  
	   
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
	       
   
	  public void menuoption()  {
		  

		  System.out.println("Welcome to the Library Shop!! What would you like to do?");
		  System.out.println("Press 1 - Search");
		  System.out.println("Press 2 - Books/Readers");
		  System.out.println("Press 3 - Borrowed Books / Returned Books");
		  System.out.println("Press 4 - Exit");
		  
		  int option = readinguser();
	
		  
		  if (option ==1) {  
			  searchmenu();  
			  
		  }
		  
		  else if (option ==2) {
			  searchbooksreaders();
		  }
		  
		  else if (option == 3) {
			  borrowedreturn();
			  
		  }
		  
		  else if (option ==4) {
			  
			  System.out.close();
		  }
          else  {
        	  
        	  System.out.println("Not a valid number");
			  menuoption();
			  
		  }
		    
		  
	  }

	  public void searchmenu()  {
		  

		  System.out.println("Search Session");
		  System.out.println("________________");
		  System.out.println("Press 1 - Search Book by Title/Author");
		  System.out.println("Press 2 - Search Reader by Name/ID");
		  System.out.println("Press 3 - Search Books Borrowed by the Reader");
		  System.out.println("Press 4 - Return To The Main Menu");
		  
		  
		  int option = readinguser();
		  
		  
		  if (option ==1) {  
			  System.out.println("Please Enter Book Title or Author");
			  try {
				  Scanner myScanner = new Scanner(System.in);
				  String Author = myScanner.nextLine();
				  
				  myScanner.close();
				
				  if(db.searchbyAuthor(Author) == null) {
					  System.out.println("Sorry, title " + Author + " not found!");
				  }
				  
				  else {
					  System.out.println("------------------------------------------");
					  System.out.println("ID: " + db.searchbyAuthor(Author).getID());
					  System.out.println("Title: " + db.searchbyAuthor(Author).getTitle());
					  System.out.println("Author: " + db.searchbyAuthor(Author).getAuthor());
					 
				  }
				
					 
			  
			  } catch (Exception e ) {
				  System.out.println("Invalid input");
				  
				  
			  }
			  }
		  
		  else if (option ==2) {
			  System.out.println("Please Enter Reader Name or ID");
			  
			  try {
				  Scanner myScanner = new Scanner(System.in);
				  String Name = myScanner.nextLine();
				  
				  myScanner.close();
				
				  if(db.searchbyname(Name) == null) {
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
				
					 
			  
			  } catch (Exception e ) {
				  System.out.println("Invalid input");
				  
				  
			  }
			  }
			
	
		  else if (option == 3) {
				 
			  System.out.println(" Please enter Book Borrowed Name ");
		  }
		  
		  else if (option ==4) {
			  
			  menuoption();
		  }
		  
	        else  {
	        	  
	        	  System.out.println("Not a valid number");
				  searchmenu();
				  
			  }
  
		  
	  }
      public void searchbooksreaders()  {
		  

		  System.out.println("Books/Readers Session");
		  System.out.println("________________");
		  System.out.println("Press 1 - All Books in Alphabetical Order");
		  System.out.println("Press 2 - All Books in ID Order");
		  System.out.println("Press 3 - All Readers in Aphabetical Order");
		  System.out.println("Press 4 - All Readers in ID Order");
		  System.out.println("Press 5 - Return To the Main Menu");

		  int option = readinguser();
		  
		  if (option ==1) {  
			  System.out.println("All Books in Alphabetical Order Below");
			 db.alphabeticalorder();  
			
			  
		  }
		  
		  else if (option ==2) {
			  System.out.println("All Books ID Order Below");
			 
		  }
		  
		  else if (option == 3) {
			  System.out.println("All Readers in Alphabetical Order Below");
			  
		  }
	       else if (option == 4) {
			  
	    	   System.out.println("All Readers  ID Order Below");
		  }
      
           else if (option == 5) {
		  
		  menuoption();
	  }
	        else  {
	        	  
	        	  System.out.println("Not a valid number");
				  searchbooksreaders();
				  
			  }
  
		  
	  }
         
      public void borrowedreturn()  {
 

         System.out.println("Borrowed/Returned Session");
         System.out.println("________________");
         System.out.println("Press 1 - To Register a Borrowed Book To The Reader");
         System.out.println("Press 2 - To Register a Returned Book By The Reader");
         System.out.println("Press 3 - Return To The Main Menu");

 
         int option = readinguser();
        
	  
	  if (option ==1) {  
		  
		  System.out.println("Please enter Book Name ");
		
		   
		  
	  }
	  
	  else if (option ==2) {
		  
		  System.out.println("Please enter Book Name ");
		   // loop to ask again the reader name
		  
	  }
	  
	  else if (option == 3) {
	
		  menuoption();
	  }
	  
	
      else  {
    	  
    	  System.out.println("Not a valid number");
		  borrowedreturn();
		  
	  }
 
}
	  

 
}

