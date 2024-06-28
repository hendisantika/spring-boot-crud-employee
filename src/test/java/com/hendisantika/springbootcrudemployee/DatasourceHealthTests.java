package com.hendisantika.springbootcrudemployee;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-crud-employee
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/28/24
 * Time: 16:56
 * To change this template use File | Settings | File Templates.
 */
@Testcontainers
public class DatasourceHealthTests {

    // will be started before and stopped after each test method
    @Container
    private final MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.4.0")
            .withDatabaseName("employeeDB")
            .withUsername("yuji")
            .withPassword("S3cret");

    @Bean
    public DataSourceInitializer initializer(DataSource dataSource) {

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        // schema.sql and data.sql should be in resources folder under sql folder
        dataSourceInitializer.setDatabasePopulator(new ResourceDatabasePopulator(
                new ClassPathResource("db.migration/V1__init.sql")
                , new ClassPathResource("db.migration/V2__insert_data.sql")
                , new ClassPathResource("db.migration/V3__Insert_Data_User.sql")));

        return dataSourceInitializer;
    }

    @Test
    void test() {
        assertThat(mySQLContainer.isRunning()).isTrue();
        assertThat(mySQLContainer.isRunning()).isTrue();
    }
}
