package yellow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yellow.mapper.PropertyMapper;
import yellow.pojo.Property;
import yellow.pojo.PropertyExample;
import yellow.service.PropertyService;
@Service
public class PropertyServiceImpl implements PropertyService {
	@Autowired
PropertyMapper propertyMapper;

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		propertyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(Property property) {
		// TODO Auto-generated method stub
		propertyMapper.insert(property);
	}

	@Override
	public void update(Property property) {
		// TODO Auto-generated method stubzzzz
		propertyMapper.updateByPrimaryKeySelective(property);
	}

	@Override
	public List<Property> list(int cid) {
		// TODO Auto-generated method stub
		PropertyExample pe = new PropertyExample();
		pe.createCriteria().andCidEqualTo(cid);
		pe.setOrderByClause("id desc");
		return propertyMapper.selectByExample(pe);
	}

}
