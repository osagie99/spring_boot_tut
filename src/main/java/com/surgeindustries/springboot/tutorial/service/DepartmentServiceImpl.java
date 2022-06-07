package com.surgeindustries.springboot.tutorial.service;

import com.surgeindustries.springboot.tutorial.entity.Department;
import com.surgeindustries.springboot.tutorial.error.DepartmentNotFoundException;
import com.surgeindustries.springboot.tutorial.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if(!department.isPresent()) {
            throw new DepartmentNotFoundException("Department does not exist");
        }
        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department debID = departmentRepository.findById(departmentId).get();

        if(Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())) {
            debID.setDepartmentName(department.getDepartmentName());
        }

        if(Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress())){
            debID.setDepartmentAddress(department.getDepartmentAddress());
        }

        if(Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode())) {
            debID.setDepartmentCode(department.getDepartmentCode());
        }

        return departmentRepository.save(debID);
    }

    @Override
    public Department fetchDepartmentByName(String department) throws DepartmentNotFoundException {
        Optional<Department> departmentName = departmentRepository.findByDepartmentNameIgnoreCase(department);

        if(!departmentName.isPresent()) {
                throw new DepartmentNotFoundException("Department with " + department + " Does not exists.");
        }
        return departmentName.get();
    }
}
