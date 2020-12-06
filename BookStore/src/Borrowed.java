

/**
 * 
 * 
 * 
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

	/**
	 * this method return the Books
	 * @return this books
	 */
	public Books getMybook() {
		return mybook;
	}

	public void setMybook(Books mybook) {
		this.mybook = mybook;
	}
/**
 * this method return the readers
 * @return this readers
 */
	public Readers getMyreader() {
		return myreader;
	}

	public void setMyreader(Readers myreader) {
		this.myreader = myreader;
	}

	/**
	 * this method return the rented date
	 * @return this daterented
	 */
	public String getDaterented() {
		return daterented;
	}
	/**
	 * this method set the DateRended 
	 * @param daterented
	 */

	public void setDaterented(String daterented) {
		this.daterented = daterented;
	}

	/**
	 * this method return the Returned Date
	 * @return this datereturned
	 */
	public String getDatetreturn() {
		return datetreturn;
	}
/**
 * this method set the returned date
 * @param this datetreturn
 */
	public void setDatetreturn(String datetreturn) {
		this.datetreturn = datetreturn;
	}

}