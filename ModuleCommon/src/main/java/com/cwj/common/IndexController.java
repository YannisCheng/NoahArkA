package com.cwj.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * com.cwj.common
 *
 * 测试类，不是主要功能
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-08-06 10:01
 */
@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
