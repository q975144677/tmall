package yellow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import yellow.pojo.Category;
import yellow.pojo.Property;
import yellow.service.PropertyService;
import yellow.util.Page;

@Controller
public class PropertyController {
	@Autowired
PropertyService propertyService ;
	
	
	@RequestMapping("property")
	public ModelAndView list(Category category,Page page) {
		ModelAndView mav = new ModelAndView("property");
		int cid = category.getId();
		PageHelper ph = new PageHelper();
		page.setPrevious(page.getPage()-1);
		page.setNext(page.getPage()+1);
		page.setStart(page.getPage());
		ph.offsetPage(page.getStart(), page.getCount());
		List<Property> propertys = propertyService.list(cid);
		System.out.println(new PageInfo<>(propertys).getTotal());
		page.setEndPage((int)((new PageInfo<>(propertys).getTotal()/page.getCount())+((new PageInfo<>(propertys).getTotal()%page.getCount())!=0?1:0)))	;
		mav.addObject("propertys",propertys);
		mav.addObject("cid",category.getId());
		mav.addObject("page",page);
		return mav;
	}
	
	@RequestMapping("property_add")
	public ModelAndView add(Property property) {
		ModelAndView mav = new ModelAndView("redirect:/property?id="+property.getCid()) ; 
		propertyService.insert(property);
		return mav;
		
	}
	@RequestMapping("property_delete")
	public ModelAndView delete(Property property) {
		ModelAndView mav = new ModelAndView("redirect:/property?id="+property.getCid());
		propertyService.delete(property.getId());

		return mav;
	}
	@RequestMapping("property_update")
	public ModelAndView update(Property property) {
		ModelAndView mav  = new ModelAndView("redirect:/property?id="+property.getCid());
		propertyService.update(property);
		
		return mav;
	}
	
	@RequestMapping("property_edit")
	public ModelAndView edit(Property property) {
		ModelAndView mav = new ModelAndView("property_edit");
		mav.addObject("property",property);
		return mav;
		
	}
}
