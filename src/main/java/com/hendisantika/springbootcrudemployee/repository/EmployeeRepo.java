package com.hendisantika.springbootcrudemployee.repository;

import com.hendisantika.springbootcrudemployee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-crud-employee
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 28/08/18
 * Time: 05.56
 * To change this template use File | Settings | File Templates.
 */
public interface EmployeeRepo extends JpaRepository<Employee, String> {
    Page<Employee> findByNamaContainingIgnoreCase(String nama, Pageable pageable);
}
