package com.scopeProject.scopeProject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//@Entity
//@Table(name="Createpassword")
public class Createpassword {

	
		@Id
	    @GeneratedValue(strategy =GenerationType.IDENTITY)
		@Column
	    private Long id;
		  @Column
		private String password;
		  @Column
		  private String email;
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		private String confirmpassword;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getConfirmpassword() {
			return confirmpassword;
		}
		public void setConfirmpassword(String confirmpassword) {
			this.confirmpassword = confirmpassword;
		}


}
