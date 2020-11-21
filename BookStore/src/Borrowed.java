
public class Borrowed {

	Books mybook;
	Readers myreader;
	private String daterented;
	private String datetreturn;

	public Borrowed(Books mybook, Readers myreader, String daterented, String datereturn) {

		this.mybook = mybook;
		this.myreader = myreader;
		this.daterented = daterented;
		this.datetreturn = datereturn;
	}

	public Books getMybook() {
		return mybook;
	}

	public void setMybook(Books mybook) {
		this.mybook = mybook;
	}

	public Readers getMyreader() {
		return myreader;
	}

	public void setMyreader(Readers myreader) {
		this.myreader = myreader;
	}

	public String getDaterented() {
		return daterented;
	}

	public void setDaterented(String daterented) {
		this.daterented = daterented;
	}

	public String getDatetreturn() {
		return datetreturn;
	}

	public void setDatetreturn(String datetreturn) {
		this.datetreturn = datetreturn;
	}

}