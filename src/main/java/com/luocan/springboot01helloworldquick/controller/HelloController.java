package com.luocan.springboot01helloworldquick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//这两个注解可以用RestController进行替换
//@ResponseBody用于返回一个纯字符串，如不加这个注解，则会吧字符串解释为HTML文件名
@Controller
public class HelloController {
    @GetMapping("/")
    public String index(){
//        model.addAttribute("name",name);
        return "index";//如果返回的是对象则返回json数据
    }
}
