package com.surgeindustries.springboot.tutorial.service;

import com.surgeindustries.springboot.tutorial.entity.Department;
import com.surgeindustries.springboot.tutorial.error.DepartmentNotFoundException;
import com.surgeindustries.springboot.tutorial.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
//A test case for our service layer
@SpringBootTest
class DepartmentServiceTest {
//Dependency injection to get the Department Service
    @Autowired
    private DepartmentService departmentService;
//    We use Mockito MockBean to mock the repo
    @MockBean
    private DepartmentRepository departmentRepository;
// Before each test case is run we setup the database mock
    @BeforeEach
    void setUp() {
//        Create a Database Department using the Builder annotation in our entity

        Department department = Department.builder().departmentId(1L)
                .departmentAddress("Lagos")
                .departmentCode("STSL-01")
                .departmentName("STSL")
                .build();

//        Mockito when findByDepartmentNameIgnoreCase is called looking for value of STSL
//        Return the department we created in the set up

        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("STSL")).thenReturn(Optional.ofNullable(department));
        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("Hello")).thenReturn(Optional.ofNullable(department));
    }

    @Test
    @DisplayName("Get data based on valid department name")
    public void whenValidDepartmentName_thenDepartmentShouldFound () throws DepartmentNotFoundException {
//        Set the department name we expect to get back from out test
        String departmentName = "STSL";
//        We call the department service expecting it to return the STSL set above
        Department found = departmentService.fetchDepartmentByName(departmentName);
//        Check to assert if STSL === department searched for
        assertEquals(departmentName, found.getDepartmentName());
    }

    @Test
    @DisplayName("Searching for unavailable Department")
    public void whenInvalidDepartmentName_thenDepartmentShouldNotBeFound() throws DepartmentNotFoundException {
//        Set Department name we know is not in the database
        String departmentName = "Hello";
        Department notFound = departmentService.fetchDepartmentByName(departmentName);
        assertNotEquals(departmentName, notFound.getDepartmentName());

    }
}