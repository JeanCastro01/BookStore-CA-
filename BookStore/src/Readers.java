

/**
 * 
 * This class is for the Readers Object
 * 
 * 
 * @author jeancastro
 *
 */
public class Readers {

	public Readers() {
	}

	private String ID;
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	
	
	/**
	 * Construsctor with attributes for the Readers
	 * 
	 * @param ID
	 * This Reader's ID
	 * @param firstname
	 * This Reader's FirstName
	 * @param lastname
	 * This Readers LastName
	 * @param email
	 * This Readers Email
	 * @param phone
	 * This Readers phone number
	 */

	public Readers(String ID, String firstname, String lastname, String email, String phone) {

		this.ID = ID;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
	}
	/**
	 * this method return the reader ID
	 * @return this reader's ID
	 */

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}
/**
 * this method retunr the Reader's First Name
 * @return this Reader's firstname
 */
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * this methos returns the Reader's Last Name
	 * @return this reader's last name
	 */
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
/**
 * this method return the Reader's email
 * @return this reader's email
 */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
/**
 * this method return the reader's phone
 * @return this reader's phone
 */
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
