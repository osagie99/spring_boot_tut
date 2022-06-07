package com.surgeindustries.springboot.tutorial.service;

import com.surgeindustries.springboot.tutorial.entity.Department;
import com.surgeindustries.springboot.tutorial.error.DepartmentNotFoundException;

import java.util.List;
import java.util.Optional;

/*
    Before specifying the Service we have to implement the interface
 */
public interface DepartmentService {
    Department saveDepartment(Department department);
    List<Department> fetchDepartments();
    Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    void deleteDepartmentById(Long departmentId);

    Department updateDepartment(Long departmentId, Department department);

    Department fetchDepartmentByName(String department) throws DepartmentNotFoundException;
}

