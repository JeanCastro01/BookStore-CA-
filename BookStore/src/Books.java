import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * This class is for the Book Object
 * 
 * 
 * @author jeancastro
 *
 */

public class Books {

	MyLinkedList queue ;

	public Books() {
	}

	private String ID;
	private String Author;
	private String Title;
	private String Genre;
	private boolean Borrowed;
	
	
	/**
	 * This method return the Book's QUEUE
	 * @return this book's queue
	 */

	public MyLinkedList getQueue() {
		return queue;
	}
	
	/**
	 * This method se the Book's queue
	 * @param this book's queue
	 */

	public void setQueue(MyLinkedList queue) {
		this.queue = queue;
	}

	/**
	 * Construsctor with attributes for the Book
	 * 
	 * @param iD       Book's id
	 * 
	 * @param author   Books's Author
	 * 
	 * @param title    Book's Title
	 * 
	 * @param genre    Book's Genre
	 * 
	 * @param borrowed Book's borrowed boolean
	 */

	public Books(String iD, String author, String title, String genre, boolean borrowed) {

		ID = iD;
		Author = author;
		Title = title;
		Genre = genre;
		Borrowed = borrowed;
		queue = new MyLinkedList();
	}

	/**
	 * This method return Borrwed boolean
	 * 
	 * @return This Book's Borrowed boolean
	 */

	public boolean isBorrowed() {
		return Borrowed;
	}

	/**
	 * This method set Borrowed boolean
	 * 
	 * @param this book's borrowed boolean
	 */
	public void setBorrowed(boolean borrowed) {
		Borrowed = borrowed;
	}

	/**
	 * This method return the ID
	 * 
	 * @return this Book's ID
	 */

	public String getID() {
		return ID;
	}

	/**
	 * This method return the Book Author
	 * 
	 * @return This Book's Author
	 */

	public String getAuthor() {
		return Author;
	}

	/**
	 * this method return the book title
	 * 
	 * @param This Book's Title
	 */

	public String getTitle() {
		return Title;
	}
	
	/**
	 * this method return the Genre
	 * 
	 * @return this Book's Genre
	 */

	public String getGenre() {
		return Genre;
	}

	/**
	 * This method compareTo Books
	 * @param Books books
	 * @return this book's 0
	 */
	public int compareTo(Books books) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {// overriding the toString() method
		return this.Title;
	}

	{

	}

}
