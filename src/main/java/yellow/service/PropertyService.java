package yellow.service;

import java.util.List;

import yellow.pojo.Product;
import yellow.pojo.Property;

public interface PropertyService {
	void delete(int id );
	void insert(Property property);
	void update(Property property);
	List<Property> list(int cid);
}
