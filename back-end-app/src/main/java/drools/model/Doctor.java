package drools.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import drools.model.enums.DoctorType;

@Entity
public class Doctor implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false, name="doctor_first_name")
	private String firstName;
	
	@Column(nullable = false, name="doctor_last_name")
	private String lastName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="doctor_licence_id")
	private Integer licenceId;
	
	@Column(nullable = false, unique = true, name="doctor_username")
	private String username;
	
	@Column(nullable = false, name="doctor_password")
	private String password;
	
	@Column(nullable = false, name="doctor_type")
	@Enumerated(EnumType.STRING)
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
	
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		
		if(!(o instanceof Doctor)) {
			return false;
		}
		
		Doctor d = (Doctor) o;
		
		if(licenceId.equals(d.getLicenceId()) && firstName.equals(d.getFirstName())
				&& lastName.equals(d.getLastName()) && username.equals(d.getUsername())
				&& password.equals(d.getPassword()) && type == d.getType()) {
			return true;
		}
		
		return false;
	}
}
