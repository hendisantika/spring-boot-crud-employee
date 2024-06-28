package com.hendisantika.springbootcrudemployee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-crud-employee
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 28/08/18
 * Time: 05.59
 * To change this template use File | Settings | File Templates.
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public static final String SQL_LOGIN
            = "select username, password, active as enabled \n" +
            "from s_users where username = ?";

    public static final String SQL_PERMISSION
            = "select u.username, r.nama as authority\n" +
            "from s_users u join s_user_role ur on u.id = ur.id_user\n" +
            "join s_roles r on ur.id_role = r.id\n" +
            "where u.username = ?";

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(SQL_LOGIN)
                .authoritiesByUsernameQuery(SQL_PERMISSION)
                .passwordEncoder(passwordEncoder); // bcrypt
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http
//
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login").permitAll()
//                .defaultSuccessUrl("/employee/list", true)
//                .and()
//                .logout();

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/js/**", "/css/**", "/fonts/**", "/login", "/login/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/employee/list", true)
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/login");
//    }
}
