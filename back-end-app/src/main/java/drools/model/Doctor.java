package drools.model;

import drools.model.enums.DoctorType;

public class Doctor {
	
	private String firstName;
	private String lastName;
	
	private String licenceId;
	private String username;
	private String password;
	
	private DoctorType type;
	
	public Doctor() {}

	public Doctor(String firstName, String lastName, String licenceId, String username, String password,
			DoctorType type) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.licenceId = licenceId;
		this.username = username;
		this.password = password;
		this.type = type;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLicenceId() {
		return licenceId;
	}

	public void setLicenceId(String licenceId) {
		this.licenceId = licenceId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DoctorType getType() {
		return type;
	}

	public void setType(DoctorType type) {
		this.type = type;
	}
}
