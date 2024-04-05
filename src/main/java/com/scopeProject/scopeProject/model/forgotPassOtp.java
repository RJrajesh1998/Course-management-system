package com.scopeProject.scopeProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


//@Entity
//@Table(name = "Signup_details")
public class forgotPassOtp {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    private String otp;
    private boolean verified;

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