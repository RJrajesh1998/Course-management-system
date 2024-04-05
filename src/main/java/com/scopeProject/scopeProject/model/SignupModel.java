package com.scopeProject.scopeProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Signup_details")
public class SignupModel {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id; 

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String email;
   
    private boolean verified;

    public SignupModel() {
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	 private String otp;
	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	
    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

	
  
}
