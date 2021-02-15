package com.shoppingmall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.shoppingmall.model.Products;

@Mapper
public interface ProductsMapper {

	@Select("select * from products order by product_id")
	List<Products> getAll();
	
	@Select("select * from products where product_id=#{product_id}")
	Products getProducts(@Param("product_id")int product_id);
	
	@Insert("INSERT INTO products(category_id,product_name,product_description,product_price, product_picture, stock, info_img, quality_img)"
			+ " VALUES(#{products.category_id}, #{products.product_name},#{products.product_description}, "
			+ "#{products.product_price}, #{products.product_picture}, #{products.stock}, #{products.info_img}, #{products.quality_img})")
	@Options(useGeneratedKeys = true, keyProperty = "product_id")
	int insertProducts(@Param("products") Products products);
	
	@Update("UPDATE products SET category_id=#{category_id},product_name=#{product_name},product_description=#{product_description},"
			+ "product_price=#{product_price},product_picture=#{product_picture}, stock=#{stock}, info_img=#{info_img}, quality_img=#{quality_img}"
			+ "WHERE product_id=#{product_id}")
	int updateProducts(@Param("product_id") int product_id,@Param("category_id") int category_id,
			@Param("product_name") String product_name, @Param("product_description") String product_description,
			@Param("product_price") int product_price,@Param("product_picture") byte[] product_picture, @Param("stock") int stock, 
			@Param("info_img") byte[] info_img, @Param("quality_img") byte[] quality_img);
	
	@Delete("DELETE FROM products WHERE product_id=#{product_id}")
	int deleteProducts(@Param("product_id")int product_id);
	
	/*
	 * @Select("select product_name from products where product_id=#{product_id}")
	 * 
	 * @Options(useGeneratedKeys = true, keyProperty = "product_id") String
	 * getProductName(int product_id);
	 */
	
	@Select("select product_picture from products where product_id=#{product_id}")
	@Options(useGeneratedKeys = true, keyProperty = "product_id")
	byte[] selectImage(int product_id);

}

