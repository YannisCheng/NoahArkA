package com.cwj.common.configuration;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * com.cwj.datasource.configuration.SwaggerConfigure
 * Swagger2 API文档配置中心
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-08-02 11:48
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfigure {
    public Docket createRestApi(Environment environment){
        // 设置要显示Swagger的环境
        //Profiles profiles = Profiles.of("data-dev");
        //if (environment.acceptsProfiles(profiles)) {
        //    System.out.println("Swagger configuration in dev env");
        //}

        return new Docket(DocumentationType.SWAGGER_2)
                // 设置Swagger2基本信息
                .apiInfo(apiInfo())
                // 设置分组
                .groupName("Spring Data API")
                // 自动配置Swagger2
                .enable(true)
                .select()
                // 扫描接口位置：配置Swagger2接口方式：设置要扫描的包路径
                .apis(RequestHandlerSelectors.basePackage("com.cwj.datasource.mysql"))
                // 路径扫描：全部扫描
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("YannisCheng", "", "cwj1714@163.com");
        return new ApiInfoBuilder()
                .title("NoahArk API")
                .description("SpringBoot数据源集合")
                .version("1.0")
                .contact(contact)
                .termsOfServiceUrl("")
                .build();
    }
}
