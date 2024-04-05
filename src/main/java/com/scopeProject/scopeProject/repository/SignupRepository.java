package com.scopeProject.scopeProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scopeProject.scopeProject.model.SignupModel;

import jakarta.transaction.Transactional;

@Repository
public interface SignupRepository extends JpaRepository<SignupModel, Long> {

    SignupModel findByEmail(String email);


//update otp query method
    @Transactional
    @Modifying
    @Query("UPDATE SignupModel s SET s.otp = :otp WHERE s.email = :email")
    void updateOtp(@Param("email") String email, @Param("otp") String otp);

	



	

	
}
