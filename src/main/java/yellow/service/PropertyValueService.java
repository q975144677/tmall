package yellow.service;

import java.util.List;

import yellow.pojo.Product;
import yellow.pojo.Property;
import yellow.pojo.PropertyValue;

public interface PropertyValueService {
	List<Property> getProperty(Product product);

	void update(PropertyValue propertyValue);

	List<PropertyValue> list(Product product);

	void initData(Product product);

}
