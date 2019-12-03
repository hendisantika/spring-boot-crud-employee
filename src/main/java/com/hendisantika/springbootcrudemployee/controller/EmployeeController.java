package com.hendisantika.springbootcrudemployee.controller;

import com.hendisantika.springbootcrudemployee.entity.Employee;
import com.hendisantika.springbootcrudemployee.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-crud-employee
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 28/08/18
 * Time: 06.01
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping("/list")
    public ModelMap daftarEmployee(@PageableDefault(size = 5) Pageable pageable, @RequestParam(name = "value", required = false) String value, Model model) {
        if (value != null) {
            model.addAttribute("key", value);
            return new ModelMap().addAttribute("daftarEmployee", employeeRepo.findByNamaContainingIgnoreCase(value, pageable));
        } else {
            return new ModelMap().addAttribute("daftarEmployee", employeeRepo.findAll(pageable));
        }
    }

    @RequestMapping("/hapus")
    public String hapus(@RequestParam("id") String id) {
        employeeRepo.deleteById(id);
        return "redirect:list";
    }

    @GetMapping("/form")
    public ModelMap tapilkanForm(@RequestParam(value = "id", required = false) Employee employee) {
        if (employee == null) {
            employee = new Employee();
        }
        return new ModelMap("employee", employee);
    }


    @PostMapping("/form")
    public String simpan(@ModelAttribute @Valid Employee employee, BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
            return "employee/form";
        }
        employeeRepo.save(employee);
        status.setComplete();
        return "redirect:list";
    }

}