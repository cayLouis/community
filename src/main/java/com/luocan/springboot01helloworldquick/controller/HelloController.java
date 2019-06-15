package com.luocan.springboot01helloworldquick.controller;

import com.luocan.springboot01helloworldquick.mapper.ProductMapper;
import com.luocan.springboot01helloworldquick.mapper.UserMapper;
import com.luocan.springboot01helloworldquick.model.Product;
import com.luocan.springboot01helloworldquick.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

//这两个注解可以用RestController进行替换
//@ResponseBody用于返回一个纯字符串，如不加这个注解，则会吧字符串解释为HTML文件名
@Controller
public class HelloController {
    private Product[] products;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/")
    public String index(HttpServletRequest request,Model model){
//        model.addAttribute("name",name);
        products = productMapper.get_product();
        List<Product> productList = new ArrayList<>();
        for(Product product:products){
            productList.add(product);
        }
        model.addAttribute("product",productList);
        Cookie[] cookies= request.getCookies();
        for(Cookie cookie :cookies){
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user=userMapper.findByToken(token);
                if(user != null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }

        return "index";//如果返回的是对象则返回json数据
    }
}
