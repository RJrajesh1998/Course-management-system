package com.scopeProject.scopeProject.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Registration")
public class registerModel {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@OneToMany(mappedBy ="register")
private Set<TableModel> courses;

public Set<TableModel> getCourses() {
	return courses;
}
public void setCourses(Set<TableModel> courses) {
	this.courses = courses;
}
/*
 * public List<TableModel> getTableModels() { return tableModels; } public void
 * setTableModels(List<TableModel> tableModels) { this.tableModels =
 * tableModels; }
 */
@Column
private String fullname;
@Column
private String dob;
@Column
private String gender;
@Column
private String qualification;
@Column
private String course;
@Column
private String mobileNumber;
@Column
private String email;
@Column
private String guardianMobile;
@Column
private String trainingMode;
@Column
private String location;
@Column
private String guardianName;
@Column
private String password;

@Column
private String guardianOccupation;
@Column
private String preferredTimings;
@Column
private String address;
@Column
private String country;
@Column
private String state;
@Column
private String city;
@Column

private String zipCode;
public String getDob() {
	return dob;
}
public void setDob(String dob) {
	this.dob = dob;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getFullname() {
	return fullname;
}
public void setFullname(String fullname) {
	this.fullname = fullname;
}

public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getQualification() {
	return qualification;
}
public void setQualification(String qualification) {
	this.qualification = qualification;
}
public String getCourse() {
	return course;
}
public void setCourse(String course) {
	this.course = course;
}
public String getMobileNumber() {
	return mobileNumber;
}
public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getGuardianMobile() {
	return guardianMobile;
}
public void setGuardianMobile(String guardianMobile) {
	this.guardianMobile = guardianMobile;
}
public String getTrainingMode() {
	return trainingMode;
}
public void setTrainingMode(String trainingMode) {
	this.trainingMode = trainingMode;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public String getGuardianName() {
	return guardianName;
}
public void setGuardianName(String guardianName) {
	this.guardianName = guardianName;
}
public String getGuardianOccupation() {
	return guardianOccupation;
}
public void setGuardianOccupation(String guardianOccupation) {
	this.guardianOccupation = guardianOccupation;
}
public String getPreferredTimings() {
	return preferredTimings;
}
public void setPreferredTimings(String preferredTimings) {
	this.preferredTimings = preferredTimings;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getZipCode() {
	return zipCode;
}
public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
}
public void setOtp(String newOtp) {
	// TODO Auto-generated method stub
	
}


}
