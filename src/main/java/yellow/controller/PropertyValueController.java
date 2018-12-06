package yellow.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.experimental.max.MaxCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import yellow.pojo.Product;
import yellow.pojo.Property;
import yellow.pojo.PropertyValue;
import yellow.service.PropertyService;
import yellow.service.PropertyValueService;

@Controller
public class PropertyValueController {
	@Autowired
	PropertyValueService propertyValueService;
	@Autowired
	PropertyService propertyService;
	@RequestMapping("property_value")
	public ModelAndView list(Product product) {
		propertyValueService.initData(product);
		ModelAndView mav = new ModelAndView("property_value");
		List<PropertyValue> propertyValues = propertyValueService.list(product);//0
		
	

		List<Property> properties = propertyService.list(product.getCid());
		Map<Integer,String> map = new HashMap<>();
		for(PropertyValue pv  : propertyValues) {
			for(Property p : properties) {
				if(pv.getPtid()==p.getId()) {
					map.put(pv.getPtid(), p.getName());
					System.out.println(pv.getPtid()+"|"+p.getName());
				}
			}
		}
		int cid = product.getCid();
		mav.addObject("map",map);
		mav.addObject("propertyValues",propertyValues);
		mav.addObject("pid",product.getId());
		mav.addObject("cid",cid);
		return mav;
	}
	@RequestMapping("property_value_edit")
	public ModelAndView edit(Product product) {
		ModelAndView mav = new ModelAndView("property_value_edit");
		List<PropertyValue> list = propertyValueService.list(product);
		List<Property> list2 = propertyService.list(product.getCid());
		Map<Integer, String> map = new HashMap<>();
		for(Property property  : list2) {
			map.put(property.getId(), property.getName());
		}
		mav.addObject("map",map);
		mav.addObject("propertyValues",list);
		return mav;
	}
	
	@RequestMapping("property_value_update")
	@ResponseBody
	public String update(PropertyValue propertyValue) {
		System.out.println("pvpvpv"+propertyValue.getValue()+"llko"+propertyValue.getId());
		propertyValueService.update(propertyValue);
		
		return "success";
		
	}
	
}
