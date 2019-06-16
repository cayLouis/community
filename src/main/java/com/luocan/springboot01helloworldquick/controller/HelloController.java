package com.luocan.springboot01helloworldquick.controller;

import com.luocan.springboot01helloworldquick.mapper.ProductMapper;
import com.luocan.springboot01helloworldquick.mapper.UserMapper;
import com.luocan.springboot01helloworldquick.model.Product;
import com.luocan.springboot01helloworldquick.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
//        Cookie[] cookies= request.getCookies();
//        for(Cookie cookie :cookies){
//            if(cookie.getName().equals("token")){
//                String token = cookie.getValue();
//                User user=userMapper.findByToken(token);
//                if(user != null){
//                    request.getSession().setAttribute("user",user);
//                }
//                break;
//            }
//        }

        return "index";//如果返回的是对象则返回json数据
    }
    //注册验证
    @PostMapping("/register")
    public String register(@RequestBody String form){
        String username_s=form.split("&")[0];
        String password_s=form.split("&")[1];
        String username=username_s.split("=")[1];
        String password=password_s.split("=")[1];
        if(userMapper.findByName(username)==null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(username);
//            user.setAccount(String.valueOf(gitHubUser.getId()));
            user.setPassword(password);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            return("redirect:register");
        }
        else
            return("warning0");
    }
    @GetMapping("/register")
    public String first_register(){
        return("register");
    }
    //登录验证
    @PostMapping("/log_in")
    public String log_in(@RequestBody String form,HttpServletRequest request){
        String username_s=form.split("&")[0];
        String password_s=form.split("&")[1];
        String username=username_s.split("=")[1];
        String password=password_s.split("=")[1];
        User user = userMapper.findByName(username);
        if(user==null) return "warning1";
        if(user!=null){
            if(user.getPassword()!=null&&user.getPassword().equals(password)){
                request.getSession().setAttribute("user",user);
                return "redirect:/";}
            else{
                return "warning3";
            }
        }
        return null;

    }
}
