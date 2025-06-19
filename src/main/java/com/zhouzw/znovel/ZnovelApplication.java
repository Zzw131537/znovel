package com.zhouzw.znovel;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
@MapperScan("com.zhouzw.znovel.dao.mapper")
@Slf4j
public class ZnovelApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZnovelApplication.class, args);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 替换弃用的 csrf().disable()
                .securityMatcher(EndpointRequest.toAnyEndpoint())
                .authorizeHttpRequests(requests -> requests.anyRequest().hasRole("ENDPOINT_ADMIN"))
                .httpBasic(Customizer.withDefaults()); // 之前已替换的 httpBasic()
        return http.build();
    }
}
