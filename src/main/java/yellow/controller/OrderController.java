package yellow.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import yellow.pojo.Order;
import yellow.service.OrderItemService;
import yellow.service.OrderService;

@Controller
public class OrderController {
@Autowired
	OrderService orderService;
	@Autowired
	OrderItemService orderItemService;
	
	@RequestMapping("order")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("order");


		List<Order> list = orderService.list();
		mav.addObject("orders",list);
		return mav;
	}
	@RequestMapping("order_deliver")
	public ModelAndView order_deliver(Order order) {
		ModelAndView mav = new ModelAndView("redirect:/order");
		Date d = new Date();
		order.setDeliveryDate(d);
		  order.setStatus(OrderService.waitConfirm);
		orderService.update(order);
		return mav;
	}
}
