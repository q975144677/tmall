package test;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import junit.framework.Test;
import yellow.pojo.Category;
import yellow.pojo.Product;
import yellow.service.CategoryService;
import yellow.service.ProductService;
@Component
public class test {
@Autowired
	ProductService productService;
	@Autowired
	 CategoryService categoryService;
	

public static void main(String args[]) {	
	test test = (test)(new ClassPathXmlApplicationContext("applicationContext.xml").getBean("test"));
	List<Category> list = test.categoryService.list();
	for(Category c : list) {
	List<Product> list2 = test.productService.list(c.getId());
	for(Product p : list2) {
		p.setCreateDate(new Date());
		test.productService.update(p);
	}
	}
}
}
