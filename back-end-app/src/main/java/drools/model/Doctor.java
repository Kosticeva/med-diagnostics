package drools.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import drools.model.enums.DoctorType;

@Entity
public class Doctor{
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Id
	private Integer licenceId;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private DoctorType type;
	
	public Doctor() {}

	public Doctor(String firstName, String lastName, Integer licenceId, String username, String password,
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

	public Integer getLicenceId() {
		return licenceId;
	}

	public void setLicenceId(Integer licenceId) {
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
	
	@Override
	public String toString() {
		return "Dr. " + firstName + " " + lastName + ", " + licenceId ;
	}
}
