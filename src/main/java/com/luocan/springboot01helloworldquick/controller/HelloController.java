package com.luocan.springboot01helloworldquick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//这两个注解可以用RestController进行替换
//@ResponseBody用于返回一个纯字符串，如不加这个注解，则会吧字符串解释为HTML文件名
@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(@RequestParam(name="name",required = false,defaultValue = "world") String name , Model model){
        model.addAttribute("name",name);
        return "greeting";//如果返回的是对象则返回json数据
    }
}
