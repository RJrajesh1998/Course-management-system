package com.scopeProject.scopeProject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


//@Entity

public class ChangePasswordModel {

	
		@Id
	    @GeneratedValue(strategy =GenerationType.IDENTITY)
		@Column
	    private Long id;
		  @Column
		private String existingpassword;
		  @Column
		  private String newpassword;
		  @Column
		  private String email;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getExistingpassword() {
			return existingpassword;
		}
		public void setExistingpassword(String existingpassword) {
			this.existingpassword = existingpassword;
		}
		public String getNewpassword() {
			return newpassword;
		}
		public void setNewpassword(String newpassword) {
			this.newpassword = newpassword;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		

}