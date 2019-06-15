package com.luocan.springboot01helloworldquick.mapper;

import com.luocan.springboot01helloworldquick.model.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductMapper{
    @Insert("insert into product (name,uri,price,profile) values (#{name},#{uri},#{price},#{profile})")
    void add_product(Product product);
    @Select("select * from product")
    Product[] get_product();
}
