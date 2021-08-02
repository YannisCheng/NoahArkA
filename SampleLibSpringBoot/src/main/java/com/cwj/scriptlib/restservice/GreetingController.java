package com.cwj.scriptlib.restservice;

import com.cwj.scriptlib.autowired_mutil_test.UseServiceDemo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 官方示例：https://spring.io/guides/gs/rest-service/
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-07-30 14:46
 *
 * Swagger：指定该controller类在Swagger界面中对应显示的名称，如果不使用Api注解进行指定说明，则默认使用类名进行展示
 * 在Swagger界面中，一个Controller注解就表示一个分组，该Controller下的各个方法就是分组下的列表项。
 */
@Api(tags = "GET请求API测试")
@RestController
public class GreetingController {
    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Resource
    private UseServiceDemo useServiceDemo;

    @ApiOperation(value = "欢迎测试", notes = "欢迎测试的notes信息")
    //@ApiImplicitParams({
    //        @ApiImplicitParam(
    //                name = "参数名",
    //                value = "world",
    //                paramType = "query"
    //        )
    //})
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name",defaultValue = "World") String name){
        // 测试 @Autowired注解的使用
        useServiceDemo.serviceDemo.sayHello();
        return new Greeting(counter.incrementAndGet(),String.format(TEMPLATE,name));
    }

    @ApiOperation(value = "例子测试", notes = "例子测试notes")
    @GetMapping("/getExample")
    public String getExample(@RequestParam(value = "param1", defaultValue = "param") String param){
        return param;
    }
}
