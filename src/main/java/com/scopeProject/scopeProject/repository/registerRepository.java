package com.scopeProject.scopeProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.scopeProject.scopeProject.model.TableModel;
import com.scopeProject.scopeProject.model.registerModel;

import jakarta.transaction.Transactional;





public interface registerRepository extends JpaRepository<registerModel,Integer> {
	
	@Query(value = "SELECT EXISTS(SELECT * fron"
			+ " registration WHERE email = :email)", nativeQuery = true)
	    String checkuser(String email);

	registerModel findById(int id);

	registerModel findByEmail(String email);

//	registerModel existsById(int id);

	
	@Transactional
	@Modifying
	@Query("UPDATE registerModel s SET s.fullname = :fullname, s.dob = :dob, s.email = :email, " +
	       "s.mobileNumber = :mobileNumber, s.address = :address, s.country = :country, " +
	       "s.qualification = :qualification, s.zipCode = :zipCode, s.city = :city, " +
	       "s.state = :state, s.guardianName = :guardianName, s.guardianMobile = :guardianMobile " +
	       "WHERE s.email = :email")
	void updateAllDetails(@Param("fullname") String fullname, @Param("dob") String dob, 
	                      @Param("email") String email, @Param("mobileNumber") String mobileNumber,
	                      @Param("address") String address, @Param("country") String country,
	                      @Param("qualification") String qualification, @Param("zipCode") String zipCode,
	                      @Param("city") String city, @Param("state") String state,
	                      @Param("guardianName") String guardianName, 
	                      @Param("guardianMobile") String guardianMobile);
	
    @Query(value = "SELECT * FROM Registration r JOIN course_deatils t ON r.id = t.registerModel_id WHERE t.id = :tableModelId", nativeQuery = true)
    List<registerModel> findByTableModelId(Long tableModelId);

	
}

	





	


	

//	public void save(SignupModel existingStudent);
//
//	registerModel findById(int id);
//
//	public void save(Createpassword newPassword);
//
//	void save(String user);

	
//    boolean existsByEmail(@Param("email") String email);

	


