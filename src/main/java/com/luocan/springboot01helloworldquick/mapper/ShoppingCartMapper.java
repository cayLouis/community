package com.luocan.springboot01helloworldquick.mapper;

import com.luocan.springboot01helloworldquick.model.ShoppingCart;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ShoppingCartMapper {
    @Insert("insert into shoppingcart (RECORD,ID,PRO_ID) values (#{record},#{id},#{pro_id})")
    void insert(ShoppingCart shoppingCart);
    @Select("select * from shoppingcart where id = #{id}")
    ShoppingCart[] selectById(@Param("id") Integer id);
    @Select("select * from shoppingcart where pro_id = #{pro_id}")
    ShoppingCart[] selectByProId(@Param("pro_id") Integer pro_id);
    @Delete("DELETE FROM shoppingcart where record =#{record}")
    void dropRecord(@Param("record") Integer record);
}
