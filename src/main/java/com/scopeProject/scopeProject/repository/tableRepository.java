package com.scopeProject.scopeProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scopeProject.scopeProject.model.TableModel;

public interface tableRepository extends JpaRepository<TableModel,Long> {

	TableModel save(long id);

	

}
