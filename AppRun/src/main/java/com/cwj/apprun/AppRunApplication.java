package com.cwj.apprun;

import com.cwj.auth.ModuleAuthApplication;
import com.cwj.datasource.DataSourceApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Import;


@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
// 修改依赖配置，不在直接依赖 ModuleCommon，通过DataSourceApplication间接依赖
@Import(value = {DataSourceApplication.class, ModuleAuthApplication.class})
public class AppRunApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppRunApplication.class, args);
        System.out.println("Swagger2 API Url(Default): http://localhost:8089/NoahArk_Dev/doc.html");
        System.out.println("Swagger2 API Url: http://localhost:8089/NoahArk_Dev/swagger-ui.html");
    //    System.out.println("\n" +
    //            "██████╗ ██╗   ██╗███╗   ██╗     ██████╗██╗    ██╗     ██╗\n" +
    //            "██╔══██╗██║   ██║████╗  ██║    ██╔════╝██║    ██║     ██║\n" +
    //            "██████╔╝██║   ██║██╔██╗ ██║    ██║     ██║ █╗ ██║     ██║\n" +
    //            "██╔══██╗██║   ██║██║╚██╗██║    ██║     ██║███╗██║██   ██║\n" +
    //            "██║  ██║╚██████╔╝██║ ╚████║    ╚██████╗╚███╔███╔╝╚█████╔╝\n" +
    //            "╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═══╝     ╚═════╝ ╚══╝╚══╝  ╚════╝ \n" +
    //            "                                                         \n");
    }

}
