package yellow.service;

import java.util.List;

import org.apache.ibatis.mapping.VendorDatabaseIdProvider;

import yellow.pojo.Category;
import yellow.pojo.Product;

public interface ProductService {
void update(Product product);
void insert(Product product);
void delete(int id);
List<Product> list(int cid);
Product get(int id);
void fill(Category category);
void fill(List<Category> list);
void fillByRows(List<Category> categorys,int row);
void setSaleAndReviewNum(Product product);
void setSaleAndReviewNum(List<Product> products);
List<Product> search(String key);
}
