package com.cwj.scriptlib.configuration;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * com.cwj.datasource.configuration
 *
 * Spring Data Swagger2配置
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-07-31 11:00
 */
// 声明此类为配置类
@Configuration
//开启Swagger2
@EnableSwagger2
// 使用第三方UI，不添加此注解使用的是默认的UI界面
@EnableSwaggerBootstrapUI
public class Swagger2Config {

    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                // 用于生成API基本信息
                .apiInfo(apiInfo())
                // 返回一个ApiSelectorBuilder实例，用户来控制接口被Swagger做成文档
                .select()
                // 用于指定扫描哪个包下的接口
                .apis(RequestHandlerSelectors.basePackage("com.cwj.scriptlib.restservice"))
                // 选择所有API
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 标题
                .title("Spring Boot Module API")
                // 描述
                .description("描述")
                // 定义服务的域名
                .termsOfServiceUrl("")
                // 版本号
                .version("1.0")
                .build();
    }
}
