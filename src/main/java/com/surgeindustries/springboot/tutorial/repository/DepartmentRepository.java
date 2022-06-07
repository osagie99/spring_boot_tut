package com.surgeindustries.springboot.tutorial.repository;

import com.surgeindustries.springboot.tutorial.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// The Repository annotation tells us this is a repo( can interact with our DB )
// It extends JpaRepo and takes the

@Repository

//The JpaRepo takes the Entity and the primary key type
public interface DepartmentRepository extends JpaRepository<Department, Long> {
//    Optional<Department> findByDepartmentAddress(String address);
    public Department findByDepartmentName(String departmentName);
    Optional<Department> findByDepartmentNameIgnoreCase(String departmentName);
}
