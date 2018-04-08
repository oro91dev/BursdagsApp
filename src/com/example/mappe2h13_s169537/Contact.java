package com.example.mappe2h13_s169537;

public class Contact {
	private int Id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String birthday;
	private String message;

	public Contact(int id, String firstname, String lastname,
			String phonenumber, String birthDay, String Message) {
		this.Id = id;
		this.firstName = firstname;
		this.lastName = lastname;
		this.phoneNumber = phonenumber;
		this.birthday = birthDay;
		this.message = Message;
	}

	public int getId() {
		return Id;
	}

	public String getFirstname() {
		return firstName;
	}

	public void setFirstname(String firstname) {
		firstName = firstname;
	}

	public String getLastname() {
		return lastName;
	}

	public void setLastname(String lastname) {
		lastName = lastname;
	}

	public String getPhonenumber() {
		return phoneNumber;
	}

	public void setPhonenumber(String phonenumber) {
		phoneNumber = phonenumber;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthDay) {
		birthday = birthDay;
	}

	public void setMessage(String Message) {
		message = Message;
	}

	public String getMessage() {
		return message;
	}

	public String getName() {
		return firstName + lastName;
	}

	@Override
	public String toString() {
		return lastName + " " + firstName + ", " + birthday;
	}
}
