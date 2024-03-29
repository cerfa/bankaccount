package fenn.bank.account.dto;

public class UserData {
	private String name;
	private String surname;	
	
	public UserData() {
		//Just for the sake of making sonar happy.
	}
	public UserData(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "User account fullname " + this.name + " "  + this.surname;
	}
		
}
