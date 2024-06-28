package com.hendisantika.springbootcrudemployee;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Testcontainers
@SpringBootTest(
        properties = {
                "management.endpoint.health.show-details=always",
                "spring.datasource.url=jdbc:tc:mysql:8.4.0:///employeeDB"
        },
        webEnvironment = RANDOM_PORT
)
public class SpringBootCrudEmployeeApplicationTests {

    @Test
    public void contextLoads() {
    }

}
