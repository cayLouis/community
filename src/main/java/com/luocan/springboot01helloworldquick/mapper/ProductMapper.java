package com.luocan.springboot01helloworldquick.mapper;

import com.luocan.springboot01helloworldquick.model.Product;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ProductMapper{
    @Insert("insert into product (name,uri,price,profile) values (#{name},#{uri},#{price},#{profile})")
    void add_product(Product product);
    @Select("select * from product")
    Product[] get_product();
    @Delete("DELETE FROM product where pro_id=#{id}")
    void deleteByID(@Param("id") Integer id);
    @Select("select * from product where pro_id=#{id}")
    Product selectByID(@Param("id") Integer id);
}
