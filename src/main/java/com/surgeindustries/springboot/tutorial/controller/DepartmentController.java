package com.surgeindustries.springboot.tutorial.controller;

import com.surgeindustries.springboot.tutorial.entity.Department;
import com.surgeindustries.springboot.tutorial.error.DepartmentNotFoundException;
import com.surgeindustries.springboot.tutorial.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Entry point of our API
 * This is the Controller Layer
 * It Handles Request, Delegate work and return response.
 */


/**
 * The Rest Controller Annotation is used to make this map this class as a request handler
 */
@Slf4j
@RestController
public class DepartmentController {

    /**
     *
     * @AutoWired annotation is used for dependency injection in order not to create a new instance of the object
     *
     */
    @Autowired
    private DepartmentService departmentService;

    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
    // PostMapping handles POST requests
    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody Department department) {
        LOGGER.info("Inside the SaveDepartment Block");
        return departmentService.saveDepartment(department);
    }
    // GetMapping handles GET requests
    @GetMapping("/departments")
    public List<Department> fetchDepartments() {
        LOGGER.info("Inside the Fetch Department Block");
        log.info("");
        return departmentService.fetchDepartments();
    }

    @GetMapping("/departments/{id}")
    public Department fetchDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        return departmentService.fetchDepartmentById(departmentId);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById( @PathVariable("id") Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);
        return "Department deleted successfully";
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId, @RequestBody Department department) {
        return departmentService.updateDepartment(departmentId, department);
    }

    @GetMapping("/departments/name/{name}")
    public Department fetchDepartmentByName(@PathVariable("name") String department) throws DepartmentNotFoundException {
        return departmentService.fetchDepartmentByName(department);
    }

}


