package com.luocan.springboot01helloworldquick.controller;

import com.luocan.springboot01helloworldquick.mapper.ProductMapper;
import com.luocan.springboot01helloworldquick.mapper.ShoppingCartMapper;
import com.luocan.springboot01helloworldquick.model.Product;
import com.luocan.springboot01helloworldquick.model.ShoppingCart;
import com.luocan.springboot01helloworldquick.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @GetMapping("/buying")
    public String buyProduct(@RequestParam("pro_id") Integer pro_id, Model model){
        Product product = productMapper.selectByID(pro_id);
        model.addAttribute("product",product);
        return "/pay";

    }

    @GetMapping("/addincart")
    public String addInCart( @RequestParam("pro_id") Integer pro_id,HttpServletRequest request){
        ShoppingCart shoppingCart = new ShoppingCart();
        User user=(User)request.getSession().getAttribute("user");
        System.out.println(user);
        if(user!=null){
            shoppingCart.setId(user.getId());
            shoppingCart.setPro_id(pro_id);
            shoppingCartMapper.insert(shoppingCart);
            return "redirect:";}
        return "redirect:";
    }

    @GetMapping("/cart")
    public String cart(@RequestParam(value = "user_id") Integer id ,Model model){
        Integer total_price=0;
        List<Product> products = new ArrayList<>();
        ShoppingCart[] shoppingCarts=shoppingCartMapper.selectById(id);
        for(ShoppingCart shoppingCart:shoppingCarts){
            Product product = productMapper.selectByID(shoppingCart.getPro_id());
            products.add(product);
            total_price+=(int)product.getPrice();
        }
        model.addAttribute("products",products);
        model.addAttribute("total_price",total_price);
        return "cart";
    }

    @GetMapping("/moveoutcart")
    public String move(@RequestParam("pro_id") Integer pro_id,HttpServletRequest request){
        int i=0;
        ShoppingCart[] shoppingCarts=shoppingCartMapper.selectByProId(pro_id);
        for(ShoppingCart shoppingCart:shoppingCarts){
            i++;
            System.out.println(shoppingCart.getRecord());
            shoppingCartMapper.dropRecord(shoppingCart.getRecord());
            if(i==1) break;
        }
        User user=(User)request.getSession().getAttribute("user");
        String id = user.getId().toString();
        return "redirect:cart?user_id="+id;
    }
}
