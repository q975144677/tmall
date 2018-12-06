package yellow.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yellow.mapper.ProductMapper;
import yellow.mapper.PropertyValueMapper;
import yellow.pojo.Category;
import yellow.pojo.OrderItem;
import yellow.pojo.Product;
import yellow.pojo.ProductExample;
import yellow.pojo.ProductImage;
import yellow.pojo.Property;
import yellow.pojo.PropertyValue;
import yellow.pojo.Review;
import yellow.service.CategoryService;
import yellow.service.OrderItemService;
import yellow.service.ProductImageService;
import yellow.service.ProductService;
import yellow.service.PropertyValueService;
import yellow.service.ReviewService;
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductMapper productMapper;
	@Autowired
	ReviewService reviewService;
	@Autowired
	OrderItemService OrderItemService;
	@Autowired
	ProductImageService productImageService;
	@Autowired
	PropertyValueMapper propertyValueMapper;
	@Autowired
	PropertyValueService propertyValueService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	OrderItemService orderItemService;
	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub
		productMapper.updateByPrimaryKeySelective(product);
	}

	@Override
	public void insert(Product product) {
		// TODO Auto-generated method stub
		product.setCreateDate(new Date());
		productMapper.insert(product);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
	
		Product product = get(id);
		List<PropertyValue> list =  propertyValueService.list(product);
		for(PropertyValue p : list) {
		propertyValueMapper.deleteByPrimaryKey(p.getId());
		}
		List<ProductImage> list2 = productImageService.list(id);
		for(ProductImage pi : list2) {
		productImageService.delete(id);}
		List<OrderItem> list3 = orderItemService.list(get(id));
		for(OrderItem oi : list3) {
			orderItemService.delete(oi.getId());
			
		}
		productMapper.deleteByPrimaryKey(id);//外键约束
	}
	public Product get(int id) {
		Product product = productMapper.selectByPrimaryKey(id);
		setAll(product);
		return product;
	}
	@Override
	public List<Product> list(int cid) {
		// TODO Auto-generated method stub
		ProductExample pe = new ProductExample();
		pe.createCriteria().andCidEqualTo(cid);
		pe.setOrderByClause("id desc");
	List<Product> list =  productMapper.selectByExample(pe);
	setSaleAndReviewNum(list);
	if(!list.isEmpty()) {
	System.out.println("销量和评价为"+list.get(0).getReviewCount()+"||"+list.get(0).getSaleCount());
	}
	setFirstImage(list);
	setImage(list);
	setReview(list);
	return list;
	}
	@Override
	public void fill(Category category) {
		// TODO Auto-generated method stub
		List<Product> products = list(category.getId());
		category.setProducts(products);
	
	}
	
	@Override
	public void fill(List<Category> list) {
		// TODO Auto-generated method stub
		for(Category category : list) {
			fill(category);
			
		}
		
		
	}
	@Override
	public void fillByRows(List<Category> categorys,int row) {
		// TODO Auto-generated method stub
			fill(categorys);
		for(Category category : categorys) {
			List<Product> products = category.getProducts();	
			int i = 0;
			List<List<Product>> list = new ArrayList<>();
			List<Product> p = new ArrayList<>();
			if(!category.getProducts().isEmpty()) {
			for(Product product : products) {
				p.add(product);i++;
				if(i%8==0) {
					list.add(p);
					p = new ArrayList<>();
				}
				
			}
			if(!p.isEmpty()) {
				list.add(p);
			}
			category.setProductsByRows(list);
		}
	}
	}
	
	
	@Override
	public void setSaleAndReviewNum(Product product) {
		// TODO Auto-generated method stub
		product.setSaleCount(OrderItemService.getSaleCount(product.getId()));
		product.setReviewCount(reviewService.getCount(product.getId()));
	}
	
	@Override
	public void setSaleAndReviewNum(List<Product> products) {
		// TODO Auto-generated method stub
		for(Product product : products) {
			setSaleAndReviewNum(product);
		}
	}
	public void setFirstImage(Product product) {
		if(!productImageService.listSingle(product.getId()).isEmpty()) {
		ProductImage productImage = productImageService.listSingle(product.getId()).get(0) ;
		product.setFirstProductImage(productImage);
		}
	}
	public void setFirstImage(List<Product> list) {
		for(Product p : list) {
			setFirstImage(p);
			
		}
		
	}
	
	public void setImage(Product product) {
		List<ProductImage> list = productImageService.listSingle(product.getId());
		if(!list.isEmpty()) {
			product.setProductSingleImages(list);
		}
		List<ProductImage> list2 = productImageService.listDetail(product.getId());
		if(!list.isEmpty()) {
			product.setProductDetailImages(list2);
		}
	}
	public void setImage(List<Product> list) {
		for(Product p : list) {
			setImage(p);
			
		}
		
	}
	public void setReview(Product product) {
		List<Review> list = reviewService.list(product.getId());
		product.setReviews(list);
	}
	public void setReview(List<Product> products ) {
		for(Product product : products) {
			setReview(product);
		}
		
	}
	
	public void setAll(Product product) {
		setImage(product);
		setFirstImage(product);
		setReview(product);
		
	}
	public void setAll(List<Product> products) {
		for(Product product : products) {
			setAll(product);
			
		}
	}
	@Override
	public List<Product> search(String key) {
		// TODO Auto-generated method stub
	ProductExample example = new ProductExample();
	example.createCriteria().andNameLike("%"+key+"%");
	example.setOrderByClause("id desc");
	List<Product> list = productMapper.selectByExample(example);
	setAll(list);
	return list;
		}
}
