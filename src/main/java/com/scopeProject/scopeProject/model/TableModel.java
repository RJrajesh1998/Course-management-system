package com.scopeProject.scopeProject.model;

import java.util.List;

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
@Table(name="course_deatils")
public class TableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;



	@Column(name = "course_name")
    private String course_name;

    private String duration;
    private double fees;
    
    @ManyToOne
    @JoinColumn(name = "register_table_id" , nullable=false)
    private registerModel register;
    
    public registerModel getRegister() {
		return register;
	}

	public void setRegister(registerModel register) {
		this.register = register;
	}
	/*
	 * @ManyToOne(fetch = FetchType.LAZY, optional = false) // Assuming this is the
	 * foreign key column
	 * 
	 * @JoinColumn(name = "c_id", nullable = false) private registerModel register;
	 * 
	 * public void setRegister(registerModel register) { this.register = register; }
	 */



	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  

    public String getCourse_name() {
		return course_name;
	}



	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}



	public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }
}
