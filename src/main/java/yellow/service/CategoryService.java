package yellow.service;

import java.util.List;

import yellow.pojo.Category;
import yellow.util.Page;

public interface CategoryService {

	List<Category> list();
	void delete(int id);
	Category get(int id);
	void insert(Category category);
	void update(Category category);
}
