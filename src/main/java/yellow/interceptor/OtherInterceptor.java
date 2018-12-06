package yellow.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import yellow.pojo.Category;
import yellow.pojo.OrderItem;
import yellow.pojo.User;
import yellow.service.CategoryService;
import yellow.service.OrderItemService;

public class OtherInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	CategoryService categoryService;
	@Autowired
	OrderItemService orderItemService;
	
@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
	// TODO Auto-generated method stub
	return super.preHandle(request, response, handler);
}

@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	//变形精钢 gif 路径
	HttpSession session = request.getSession();
	String string = session.getServletContext().getContextPath()+"/homePage";
	session.setAttribute("contextPath", string);
	//分类 list
	List<Category> categories = categoryService.list();
	request.setAttribute("categories", categories);
	//获得购物车数量cartTotalItemNumber
	User user  = (User)session.getAttribute("user");
	int i = 0 ;
	if(user!=null) {
	List<OrderItem> list = orderItemService.list(user.getId());
	if(list!=null) {
for(OrderItem oi : list) {
	if(oi!=null) {
		if(oi.getNumber()!=null) {
	i+=oi.getNumber();
	}
	}
}
	}
session.setAttribute("cartTotalItemNumber", i);
	}
	session.setAttribute("cartTotalItemNumber", i);
	}


@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
}
