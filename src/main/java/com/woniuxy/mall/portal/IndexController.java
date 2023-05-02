package com.woniuxy.mall.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/api/index")
    public String index(){

        return "index";
    }
}
