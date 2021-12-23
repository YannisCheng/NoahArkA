package com.cwj.common.configuration;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
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
public class SwaggerConfigureCenter {

    /**
     * SpringBoot+MySQL数据库 接口API
     *
     * @param environment 环境
     * @return Docket
     */
    @Bean
    public Docket createMySQLRestApi(Environment environment) {
        // 设置要显示Swagger的环境
        //Profiles profiles = Profiles.of("data-dev");
        //if (environment.acceptsProfiles(profiles)) {
        //    System.out.println("Swagger configuration in dev env");
        //}

        return new Docket(DocumentationType.SWAGGER_2)
                // 设置Swagger2基本信息
                .apiInfo(mysqlApiInfo())
                // 设置分组
                .groupName("MySQL API")
                // 自动配置Swagger2
                .enable(true)
                .select()
                // 扫描接口位置：配置Swagger2接口方式：设置要扫描的包路径
                .apis(RequestHandlerSelectors.basePackage("com.cwj.datasource.mysql"))
                // 路径扫描：全部扫描
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo mysqlApiInfo() {
        Contact contact = new Contact("YannisCheng", "", "cwj1714@163.com");
        return new ApiInfoBuilder()
                .title("NoahArk-MySQL REST API")
                .description("SpringBoot与MySQL数据源 API集合")
                .version("1.0")
                .contact(contact)
                .termsOfServiceUrl("")
                .build();
    }

    /**
     * SpringBoot+ElasticSearch 接口API
     * @return Docket
     */
    @Bean
    public Docket createElasticSearchRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("ElasticSearch API")
                .apiInfo(elasticSearchApiInfo())
                .select()
                // 以下写法中 ".elastic" 与 ".elasticsearch" 是等效的。
                // .apis(RequestHandlerSelectors.basePackage("com.cwj.datasource.elastic"))
                .apis(RequestHandlerSelectors.basePackage("com.cwj.datasource.elasticsearch"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo elasticSearchApiInfo() {
        return new ApiInfoBuilder()
                .contact(new Contact("YannisCheng", "", "cwj1714@163.com"))
                .title("NoahArk-ElasticSearch REST API")
                .description("SpringBoot与ElasticSearch API集合")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

    /**
     * SpringBoot+Redis 接口API
     *
     * @return Docket
     */
    @Bean
    public Docket createRedisRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //.host(host)
                .groupName("Redis API")
                .apiInfo(redisApiInfo())
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cwj.datasource.redis"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo redisApiInfo() {
        return new ApiInfoBuilder()
                .contact(new Contact("YannisCheng", "", "cwj1714@163.com"))
                .title("NoahArk-Redis REST API")
                .description("SpringBoot与Redis API集合")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

    /**
     * SpringBoot+Hadoop 接口API
     *
     * @return Docket
     */
    @Bean
    public Docket createHadoopRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Hadoop API")
                .apiInfo(hadoopApiInfo())
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cwj.hadoopproj"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo hadoopApiInfo() {
        return new ApiInfoBuilder()
                .contact(new Contact("YannisCheng", "", "cwj1714@163.com"))
                .title("NoahArk-Hadoop REST API")
                .description("SpringBoot与Hadoop生态 API集合")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

    /**
     * Actuator组件：项目健康报告API集合
     *
     * @return Docket
     */
    @Bean
    public Docket createNoahArkActuatorApi() {

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("NoahArk-Actuator REST API")
                .description("SpringBoot中Actuator组件：项目健康报告API集合")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Actuator API")
                .apiInfo(apiInfo)
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.springframework.boot.actuate"))
                .paths(PathSelectors.any())
                .build();
    }
}
