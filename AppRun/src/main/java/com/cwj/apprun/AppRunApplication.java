package com.cwj.apprun;

import com.cwj.common.CommonApplication;
import com.cwj.datasource.DataSourceApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Import;


@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@Import(value = {CommonApplication.class, DataSourceApplication.class})
public class AppRunApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppRunApplication.class, args);
        System.out.println("Swagger2 API Url(Default): http://localhost:8089/NoahArk_Dev/doc.html");
        System.out.println("Swagger2 API Url: http://localhost:8089/NoahArk_Dev/swagger-ui.html");
        System.out.println("\n" +
                "██████╗ ██╗   ██╗███╗   ██╗     ██████╗██╗    ██╗     ██╗\n" +
                "██╔══██╗██║   ██║████╗  ██║    ██╔════╝██║    ██║     ██║\n" +
                "██████╔╝██║   ██║██╔██╗ ██║    ██║     ██║ █╗ ██║     ██║\n" +
                "██╔══██╗██║   ██║██║╚██╗██║    ██║     ██║███╗██║██   ██║\n" +
                "██║  ██║╚██████╔╝██║ ╚████║    ╚██████╗╚███╔███╔╝╚█████╔╝\n" +
                "╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═══╝     ╚═════╝ ╚══╝╚══╝  ╚════╝ \n" +
                "                                                         \n");
    }

}
