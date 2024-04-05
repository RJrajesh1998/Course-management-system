/*
 * package com.scopeProject.scopeProject.service;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.scopeProject.scopeProject.model.TableModel; import
 * com.scopeProject.scopeProject.repository.registerRepository; import
 * com.scopeProject.scopeProject.repository.tableRepository;
 * 
 * @Service public class courseService {
 * 
 * @Autowired private static tableRepository tablerepository;
 * 
 * 
 * 
 * 
 * @Autowired private registerRepository registerrepository;
 * 
 * public void addCourseDetails(CourseDetails courseDetails, Long
 * registrationId) { // Check if the provided registrationId exists in the
 * registration table if (registerrepository.existsById(registrationId)) { //
 * Set the registration id in the course details entity
 * courseDetails.setRegisterTableId(registrationId); // Save the course details
 * entity registerrepository.save(courseDetails); } else { // Handle the case
 * where the provided registrationId does not exist throw new
 * IllegalArgumentException("Invalid registration id provided."); } } }
 */