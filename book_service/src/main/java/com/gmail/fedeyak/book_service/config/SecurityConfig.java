package com.gmail.fedeyak.book_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/h2-console/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/books", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .headers(headers -> headers.frameOptions().disable()) // Для h2-console
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")); // Для h2-console

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("admin")
                .password("{noop}admin") // {noop} — без шифрования
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}




//package com.gmail.fedeyak.book_service.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("password")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
//                        .requestMatchers("/", "/login").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .permitAll()
//                )
//                .logout(logout -> logout.permitAll());
//        return http.build();
//    }
//}
//
//
//
////package com.gmail.fedeyak.book_service.config;
////
////
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.web.SecurityFilterChain;
////
////@Configuration
////@EnableWebSecurity
////public class SecurityConfig {
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//////        http
//////                .authorizeHttpRequests()  // Используем authorizeHttpRequests вместо authorizeRequests
//////                .requestMatchers("/h2-console/**").permitAll()  // Разрешаем доступ к консоли H2
//////                .anyRequest().authenticated()  // Все остальные страницы требуют авторизации
//////                .and()
//////                .csrf().disable()  // Отключаем CSRF (необходим для работы с H2 консолью)
//////                .headers().frameOptions().disable();  // Разрешаем отображение фреймов (нужно для H2-консоли)
//////
//////        return http.build();
//////    }
////        http
////                .authorizeRequests()  // Используем authorizeRequests для Spring Security 5.x
////                .antMatchers("/h2-console/**").permitAll()  // Разрешаем доступ к консоли H2
////                .anyRequest().authenticated()  // Все остальные страницы требуют авторизации
////                .and()
////                .csrf().disable()  // Отключаем CSRF (необходим для работы с H2 консолью)
////                .headers().frameOptions().disable();  // Разрешаем отображение фреймов (нужно для H2-консоли)
////
////        return http.build();
////    }
////}
