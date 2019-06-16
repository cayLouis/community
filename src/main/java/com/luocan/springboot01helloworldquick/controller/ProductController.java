package com.luocan.springboot01helloworldquick.controller;

import com.luocan.springboot01helloworldquick.mapper.ProductMapper;
import com.luocan.springboot01helloworldquick.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    private Product[] products;

    @Autowired
    private ProductMapper productMapper;
    @GetMapping("/submit")
    public String submitProduct(@RequestParam(name="name") String name,@RequestParam(name="image_uri") String uri,
                              @RequestParam(name="price") Integer price,@RequestParam(name="profile") String profile){
        Product product = new Product();
        product.setName(name);
        product.setUri(uri);
        product.setPrice(price);
        product.setProfile(profile);
        productMapper.add_product(product);
        return "add_product";
    }

    @GetMapping("/add_product")
    public String product(){
        return "add_product";
    }

    @GetMapping("/manage_product")
    public String manage_product(Model model){
        products = productMapper.get_product();
        List<Product> productList = new ArrayList<>();
        for(Product product:products){
            productList.add(product);
        }
        model.addAttribute("product",productList);
        return "manage_product";
    }

    @GetMapping("delete_product")
    public String deleteProduct(@RequestParam(name="pro_id") Integer id){
        productMapper.deleteByID(id);
        return "redirect:/manage_product";
    }

}
