package com.luocan.springboot01helloworldquick.controller;

import com.luocan.springboot01helloworldquick.mapper.ProductMapper;
import com.luocan.springboot01helloworldquick.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

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
        return "manage_product";
    }

    @GetMapping("/product")
    public String product(){
        return "manage_product";
    }

}
