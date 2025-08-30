package com.example.hospitalmsystem.service;

import com.example.hospitalmsystem.model.Department;
import com.example.hospitalmsystem.repository.DepartmentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServices {

    private final DepartmentRepo departmentRepo;

    public DepartmentServices(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    public class NotFoundException extends RuntimeException {
        public NotFoundException(String message) { super(message); }
    }

    public Department createDepartment(Department department){
        return departmentRepo.save(department);
    }

    public List<Department> findAllDepartment(){
        return departmentRepo.findAll();
    }
    public Department findById(Long departmentId){
        return departmentRepo.findById(departmentId)
                .orElseThrow(()-> new NotFoundException("Id Not Found"));
    }

    public Department updateDepartment(Long departmentId, Department newDepartment){
        Department d = findById(departmentId);
        d.setName(newDepartment.getName());
        return departmentRepo.save(d);
    }

    public void deleteDepartment(Long id){
        departmentRepo.deleteById(id);
    }
}
