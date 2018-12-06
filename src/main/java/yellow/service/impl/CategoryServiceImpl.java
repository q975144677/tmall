package yellow.service.impl;

import java.util.List;

import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.CountByExampleElementGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import yellow.mapper.CategoryMapper;
import yellow.pojo.Category;
import yellow.pojo.CategoryExample;
import yellow.service.CategoryService;
import yellow.service.ProductService;
import yellow.util.Page;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryMapper categoryMapper  ;
	
	@Autowired 
	ProductService productService;
	
@Override
public List<Category> list() {
	// TODO Auto-generated method stub
	CategoryExample categoryExample = new CategoryExample();
	categoryExample.setOrderByClause("id desc");
	List<Category> categories = categoryMapper.selectByExample(categoryExample);
	productService.fillByRows(categories, 8);
	productService.fill(categories);
	return categories;
}
@Override
public void delete(int id) {
	// TODO Auto-generated method stub
categoryMapper.deleteByPrimaryKey(id);	
}
@Override
public void insert(Category category) {
	// TODO Auto-generated method stub
categoryMapper.insert(category);	
}


@Override
public void update(Category category) {
	// TODO Auto-generated method stub
	categoryMapper.updateByPrimaryKeySelective(category);
}
@Override
public Category get(int id) {
	// TODO Auto-generated method stub
	Category c = categoryMapper.selectByPrimaryKey(id);
	productService.fill(c);
	productService.setSaleAndReviewNum(c.getProducts());
	return c;
}
}
