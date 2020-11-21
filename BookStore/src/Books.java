
public class Books {

	public Books() {
	}

	private String ID;
	private String Author;
	private String Title;
	private String Genre;
	private boolean Borrowed;

	public Books(String iD, String author, String title, String genre, boolean borrowed) {

		ID = iD;
		Author = author;
		Title = title;
		Genre = genre;
		Borrowed = borrowed;
	}

	public boolean isBorrowed() {
		return Borrowed;
	}

	public void setBorrowed(boolean borrowed) {
		Borrowed = borrowed;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String genre) {
		Genre = genre;
	}

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
