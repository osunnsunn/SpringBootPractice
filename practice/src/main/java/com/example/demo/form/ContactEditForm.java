package com.example.demo.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ContactEditForm {
	
	@NotBlank
	@Size(max = 255)
	private String lastName;
	
	@NotBlank
    @Size(max = 60)
    private String firstName;
	
	@NotBlank
	@Email
	@Size(max = 255)
	private String email;
	
	@Size(max = 11)
	private String phone;
	
	@Size(max = 8)
    private String zipCode;
	
	@Size(max = 255)
    private String address;
	
	@Size(max = 255)
    private String buildingName;
	
	@Size(max = 50)
    private String contactType;
	
    private String body;
	
	public String getLastName() {
        return lastName;
    }
	
	public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
