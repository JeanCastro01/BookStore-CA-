

/**
 * 
 * 
 * 
 *  This class is 
 * @author jeancastro
 *
 */



public class Borrowed {

	Books mybook;
	Readers myreader;
	private String daterented;
	private String datetreturn;
	
	
	/**
	 * 
	 * Construsctor with attributes for the Borrowed Book
	 * 
	 * @param mybook
	 *         This Book's Object
	 * @param myreader
	 *         This Reader's Object
	 * @param daterented
	 *         This Borrowed Rented Date
	 * @param datereturn
	 *         This Borrowed Returned Date
	 */

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