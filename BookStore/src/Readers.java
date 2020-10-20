
public class Readers {

	public Readers() {}
		
		
		private String ID;
		private String firstname;
		private String lastname;
		private String email;
		private String phone;
		
		
		
		public Readers(String ID, String firstname, String lastname, String email, String phone) {
	
			this.ID = ID;
			this.firstname = firstname;
			this.lastname = lastname;
			this.email = email;
			this.phone = phone;
		}



		public String getID() {
			return ID;
		}



		public void setID(String ID) {
			this.ID = ID;
		}



		public String getFirstname() {
			return firstname;
		}



		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}



		public String getLastname() {
			return lastname;
		}



		public void setLastname(String lastname) {
			this.lastname = lastname;
		}



		public String getEmail() {
			return email;
		}



		public void setEmail(String email) {
			this.email = email;
		}



		public String getPhone() {
			return phone;
		}



		public void setPhone(String phone) {
			this.phone = phone;
		}
		
		
		
	
		
	
}


