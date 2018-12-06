package yellow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import yellow.pojo.User;
import yellow.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	
	@RequestMapping("user")
	
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("user");
		
		mav.addObject("users",userService.list());
		return mav;
	}
	
	
}
