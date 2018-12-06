package yellow.service;

import java.util.List;

import yellow.pojo.Product;
import yellow.pojo.ProductImage;

public interface ProductImageService {
	void add(ProductImage productImage);
	void delete(int id);
	List<ProductImage> list(int pid);
List<ProductImage> listSingle(int pid);
List<ProductImage> listDetail(int pid);
	
}
