package com.hendisantika.springbootcrudemployee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.GenericGenerator;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-crud-employee
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 28/08/18
 * Time: 05.55
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 150)
    private String nama;

    @Column(nullable = false, unique = true)
    @Email
    @NotEmpty
    private String email;

}
