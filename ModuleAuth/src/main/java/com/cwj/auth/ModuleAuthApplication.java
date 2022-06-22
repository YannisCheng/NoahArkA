package com.cwj.auth;

import com.cwj.datasource.DataSourceApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cwj.datasource", "com.cwj.auth"})
@Import(value = {DataSourceApplication.class})
public class ModuleAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleAuthApplication.class, args);
    }

}
