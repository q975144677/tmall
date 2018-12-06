package yellow.service.impl;

import java.util.List;

import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yellow.mapper.PropertyValueMapper;
import yellow.pojo.Product;
import yellow.pojo.Property;
import yellow.pojo.PropertyValue;
import yellow.pojo.PropertyValueExample;
import yellow.service.PropertyService;
import yellow.service.PropertyValueService;


@Service
public class PropertyValueServiceImpl implements PropertyValueService {
@Autowired 
PropertyValueMapper propertyValueMapper;
@Autowired
PropertyService propertyService;
	@Override
		public void initData(Product product) {
			// TODO Auto-generated method stub
			//对应pid的 属性集合
		List<Property> properties = getProperty(product);
		for(Property property : properties) {
			PropertyValueExample pe = new PropertyValueExample();
			pe.createCriteria().andPtidEqualTo(property.getId()).andPidEqualTo(product.getId());
			//获取property中的属性名对应的列 没有就新增 有 判断 多了还是正好
			List<PropertyValue> propertyValues = propertyValueMapper.selectByExample(pe);		
			PropertyValue pv = new PropertyValue();
			
			if(propertyValues.isEmpty()) {
			pv.setPtid(property.getId());
			pv.setPid(product.getId());
			propertyValueMapper.insert(pv);
			}
		
			
			if(!propertyValues.isEmpty()) {
				if(propertyValues.size()>=2) {
					for(int i  = 1 ;i<propertyValues.size() ; i++) {
						propertyValueMapper.deleteByPrimaryKey(propertyValues.get(i).getId());
					}
					
				}
				
			}
	

		}
		boolean flag  = true;
					//判断如果有对应分类不应该存在的 ptid 则删除
		List<Property> list =propertyService.list(product.getCid());
		for(PropertyValue p : list(product)) {
			 flag = containsPtid(list, p.getPtid());
			 System.out.println(flag);
			if(!flag) {
				propertyValueMapper.deleteByPrimaryKey(p.getId());
			}
	}		
		}
	@Override
	public List<Property> getProperty(Product product) {
		// TODO Auto-generated method stub
		return propertyService.list(product.getCid());
	}

	@Override
	public void update(PropertyValue propertyValue) {
		// TODO Auto-generated method stub
		propertyValueMapper.updateByPrimaryKeySelective(propertyValue);
	}

	@Override
	public List<PropertyValue> list(Product product) {
		// TODO Auto-generated method stub
		PropertyValueExample example = new PropertyValueExample();
		example.createCriteria().andPidEqualTo(product.getId());
		example.setOrderByClause("id desc");
		return propertyValueMapper.selectByExample(example);
	}
	public boolean containsPtid(List<Property> list , int p) {
		for(Property property  : list) {
			if(property.getId()==p)
			return true;
		}
			return false;
		
	}

}
