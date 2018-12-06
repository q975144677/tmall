package yellow.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListResourceBundle;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.net.httpserver.Authenticator.Success;

import yellow.comparator.ProductAllComparator;
import yellow.comparator.ProductDateComparator;
import yellow.comparator.ProductPriceComparator;
import yellow.comparator.ProductReviewComparator;
import yellow.comparator.ProductSaleCountComparator;
import yellow.pojo.Category;
import yellow.pojo.Order;
import yellow.pojo.OrderItem;
import yellow.pojo.Product;
import yellow.pojo.ProductImage;
import yellow.pojo.PropertyValue;
import yellow.pojo.Review;
import yellow.pojo.User;
import yellow.service.CategoryService;
import yellow.service.OrderItemService;
import yellow.service.OrderService;
import yellow.service.ProductImageService;
import yellow.service.ProductService;
import yellow.service.PropertyValueService;
import yellow.service.ReviewService;
import yellow.service.UserService;
import yellow.util.Page;

@Controller
public class ForeController {

	@Autowired
	CategoryService categoryService;
	@Autowired
	UserService userService;
	@Autowired
	ProductService productService;
	@Autowired
	ProductImageService productImageService;
	@Autowired
	PropertyValueService propertyValueService;
	@Autowired
	OrderItemService orderItemService;
	@Autowired
	OrderService orderService;
	@Autowired
	ReviewService reviewService;
	@RequestMapping("homePage")
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("home");
		List<Category> list = categoryService.list();

		mav.addObject("categories", list);
		return mav;
	}

	@RequestMapping("registPage")
	public ModelAndView regist() {
		ModelAndView mav = new ModelAndView("register");

		return mav;
	}

	@RequestMapping("isExist")
	@ResponseBody
	public String isExist(String name) {
		User user =  new User();
		user.setName(name);
		System.out.println("ss");
		System.out.println(!userService.isExist(user));
		if (!userService.isExist(user) && user.getName().length() >= 3) {
			return "true";
		}
		
		
		return "false";
	}

	@RequestMapping("registSuccess")
	public ModelAndView registSuccess(User user) {
		ModelAndView mav = new ModelAndView("redirect:/homePage");
		userService.add(user);
		return mav;
	}

	@RequestMapping("loginPage")
	public ModelAndView login() {
		return new ModelAndView("login");

	}

	@RequestMapping("login_check")
	public ModelAndView loginCheck(User user, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User u = userService.get(user.getName(), user.getPassword());
		if (u == null) {
			mav.setViewName("login");
			mav.addObject("msg","账号密码错误");
			return mav;
		} else {
			session.setAttribute("user", u);
			mav.setViewName("redirect:/homePage");
			return mav;
		}
	}
	@RequestMapping("forelogout")
	public ModelAndView logOut(HttpSession session) {
		session.removeAttribute("user");
		
		return new ModelAndView("redirect:/homePage");
	}
	
	@RequestMapping("product_page")
	public ModelAndView product() {
		ModelAndView mav = new ModelAndView("productPage");
		
		
		return mav;
	}
	@RequestMapping("foreproduct")
	public ModelAndView productPage(Product product) {
		ModelAndView mav = new ModelAndView("productPage");
		List<ProductImage> list1 = productImageService.listSingle(product.getId());
		List<ProductImage> list2 = productImageService.listDetail(product.getId());
		Product p = productService.get(product.getId());
		p.setProductSingleImages(list1);
		p.setProductDetailImages(list2);
		if(!list1.isEmpty()) {
		p.setFirstProductImage(list1.get(0));
		}
		List<PropertyValue> list  = propertyValueService.list(product);
	
		mav.addObject("pvs" , list);
		mav.addObject("p",p);
		return mav;
		
	}
	
	@RequestMapping("forecheckLogin")
    @ResponseBody
    public String checkLogin( HttpSession session) {
        User user =(User)  session.getAttribute("user");
        if(null!=user)
            return "success";
        return "fail";
    }
	@RequestMapping("foreloginAjax")
    @ResponseBody
    public String loginAjax(String name,  String password,HttpSession session) {
        name = HtmlUtils.htmlEscape(name);
        User user = userService.get(name,password);
 
        if(null==user){
            return "fail";
        }
        session.setAttribute("user", user);
        return "success";
    }
	@RequestMapping("forecategory")
	public ModelAndView category(int id,String sort) {
		Category c =  categoryService.get(id);
		if(null!=sort){
            switch(sort){
                case "review":
                    Collections.sort(c.getProducts(),new ProductReviewComparator());
                    break;
                case "date" :
                    Collections.sort(c.getProducts(),new ProductDateComparator());
                    break;
 
                case "saleCount" :
                    Collections.sort(c.getProducts(),new ProductSaleCountComparator());
                    break;
 
                case "price":
                    Collections.sort(c.getProducts(),new ProductPriceComparator());
                    break;
 
                case "all":
                    Collections.sort(c.getProducts(),new ProductAllComparator());
                    break;
            }
        }
		ModelAndView mav = new ModelAndView("fore/category");
		mav.addObject("c",c);
		return mav;
	}
	@RequestMapping("foresearch")
	public ModelAndView search(String keyword,Page page) {
		PageHelper  pageHelper = new PageHelper();
		pageHelper.offsetPage(page.getPage(), 20);
		List<Product> list = productService.search(keyword);
		ModelAndView mav = new ModelAndView("searchResult");
		mav.addObject("ps",list);
		return mav;
	}
	
	@RequestMapping("forebuyone")
	public ModelAndView buyOne(OrderItem orderItem,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Product p = productService.get(orderItem.getPid());
		User user = (User) session.getAttribute("user");
		orderItem.setUid(user.getId());
		List<OrderItem> list = orderItemService.list(user.getId());
		Boolean flag = false;
		int oiid =0;
		if(list!=null) {
		for(OrderItem orderItem2 : list) {
			if(orderItem2.getPid()==p.getId()) {
				flag = true;
				if(orderItem2.getNumber()==null) {
					orderItem2.setNumber(0);
				}
				orderItem2.setNumber(orderItem2.getNumber()+1);
			
				
				orderItemService.update(orderItem2);
				oiid = orderItem2.getId();
				break;
			}
		}
		}
		if(!flag) {
		
		orderItemService.add(orderItem);
		oiid=orderItem.getId();
		}
		
		mav.setViewName("redirect:/forbuy?oiid="+oiid);
		return mav;
	}
	@RequestMapping("forbuy")
	public ModelAndView buy(String[] oiid) {
		ModelAndView mav = new ModelAndView("buy");
		List<OrderItem> list = new ArrayList<>();
		int total=0;
		for(String id: oiid) {
			OrderItem orderItem = orderItemService.get(Integer.parseInt(id));
			list.add(orderItem);
			total+=orderItem.getTotalPrice();
		}
		mav.addObject("ois",list);
		mav.addObject("total",total);
		return mav;
	}
	@RequestMapping("review_check")
	@ResponseBody
	public String rev(String value) {
		return String.valueOf(200-value.length());
		
	}
	@RequestMapping("forecart")
	public ModelAndView cart(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("forecart");
		User user = (User)session.getAttribute("user");
		List<OrderItem> list = orderItemService.list(user.getId());
		modelAndView.addObject("ois",list);
		return modelAndView;
	}
	@RequestMapping("delete_orderItem")
	public ModelAndView delete(OrderItem orderItem) {
		ModelAndView modelAndView = new ModelAndView("redirect:/forecart");
		int id = orderItem.getId();
		if(id!=0) {
		orderItemService.delete(id);
		}
		if(id==0) {
			List<OrderItem> list = orderItemService.list(orderItem.getUid());
			for(OrderItem oi : list) {
				orderItemService.delete(oi.getId());
			}
		}
		return modelAndView;
	}
	@RequestMapping("foreAddCart")
	@ResponseBody
	public String addCart(OrderItem orderItem,HttpSession session) {
		int total = (int) new PageInfo<>(orderItemService.list()).getTotal();
		User user = (User)session.getAttribute("user");
		orderItem.setUid(user.getId());
		List<OrderItem> list = orderItemService.list(user.getId());
		boolean flag =false ;
		int id = 0;
		for(OrderItem oi : list) {
			if(oi.getPid()==orderItem.getPid()) {
			flag=true;
			id=oi.getId();
					}
		}
		if(!flag)
		{orderItemService.add(orderItem);
		}
		else {
			OrderItem oi = orderItemService.get(id);
			oi.setNumber(oi.getNumber()+orderItem.getNumber());
			orderItemService.update(oi);
			
		}
		
		int total2 = (int)new PageInfo<>(orderItemService.list()).getTotal();
		if(total == total2) {
			return "false";
		}
		else {
			return "success";
		}
		
	}
	@RequestMapping("change_orderItem")
	@ResponseBody
	public String change_orderItem(OrderItem orderItem) {
		int number =orderItem.getNumber();
		OrderItem oi = (orderItemService.get(orderItem.getId()));
		Product product = productService.get(oi.getPid());
		int stock = product.getStock();
System.out.println(number);
System.out.println(stock);
		if(number>stock||number<1) {
			return "false";
		}
		else {
			oi.setNumber(number);
			orderItemService.update(oi);
		return ""+number;
		}
		
	}
	@RequestMapping("createOrder")
	public ModelAndView createOrder(Order order,HttpSession session,int[] oiid) {
		ModelAndView mav = new ModelAndView();
		order.setUid(((User)session.getAttribute("user")).getId());
		List<OrderItem> list = new ArrayList<>();
		order.setStatus("waitPay");
	
		orderService.add(order);
		
		for(int i : oiid) {
			OrderItem oi = orderItemService.get(i);
			oi.setOid(order.getId());
		orderItemService.UidNull(oi);
			orderItemService.update(oi);
			list.add(oi);
		}	
		order.setOrderItems(list);
		int price = orderService.getTotal(order);
		mav.setViewName("redirect:/forepay?oid="+order.getId()+"&price="+price);
		return mav;
	}
	@RequestMapping("forepay")
	public ModelAndView pay(int oid,float price) {
		ModelAndView mav = new ModelAndView("pay");
		mav.addObject("oid",oid);
		mav.addObject("price",price);
		return mav;
	}
	@RequestMapping("end_pay")
	public ModelAndView pay2(int oid) {
		ModelAndView mav = new ModelAndView("redirect:/homePage");
		Order o = orderService.get(oid);
		o.setStatus(OrderService.waitDelivery);
		orderService.update(o);
		return mav;
		
	}
	
	@RequestMapping("forebought")
	public ModelAndView order(HttpSession session) {
		ModelAndView mav = new ModelAndView("myOrder");
		User user = (User)session.getAttribute("user");
		List<Order> list = orderService.list(user.getId());
	
		for(Order order : list) {
			List<OrderItem> oi = order.getOrderItems();
			for(OrderItem o : oi) {
				orderItemService.setProduct(o);
			}
		}
		List<Order> list2 = new ArrayList<>();
		for(Order order : list) {
			if(order.getStatus().equals("waitPay")) {
				list2.add(order);
			}
			
		}
		List<Order> list3 = new ArrayList<>();
		for(Order order : list) {
			if(order.getStatus().equals("waitDelivery")) {
				list3.add(order);
			}
			
		}	
		List<Order> list4 = new ArrayList<>();
		for(Order order : list) {
			if(order.getStatus().equals("waitConfirm")) {
				list4.add(order);
			}
			
		}
		List<Order> list5 = new ArrayList<>();
		for(Order order : list) {
			if(order.getStatus().equals("waitReview")) {
				list5.add(order);
			}
			
		}	orderService.setTotal(list);
		mav.addObject("waitReviewOrders",list5);
		mav.addObject("waitConfirmOrders",list4);
		mav.addObject("waitDeliveryOrders",list3);
		mav.addObject("waitpayOrders",list2);
		mav.addObject("orders",list);
		return mav;
	}
	
	@RequestMapping("order_delete")
	public ModelAndView order_delete(int id) {
		ModelAndView mav = new ModelAndView("redirect:/forebought");
		orderItemService.deleteByOid(id);
		orderService.delete(id);
		return mav;
	}
	@RequestMapping("recive")
	@ResponseBody
	public String recive(int oid) {
	Order order =	orderService.get(oid);
		order.setStatus("waitReview");
		orderService.update(order);
		return "success";
	}
	@RequestMapping("review")
	public ModelAndView b(int oid) {
		ModelAndView mav = new ModelAndView("review");
		Order order = orderService.get(oid);
		mav.addObject("o",order);
		return mav;
		
	}
	
	@RequestMapping("createReview")
	public ModelAndView createReview(String[] content,int[] id,int oid,HttpSession session) {
	 ModelAndView mav = new ModelAndView("redirect:/homePage");
	System.out.println( Arrays.toString(id)+"Duiying"+Arrays.toString(content));
	User user = (User)session.getAttribute("user");
	Order order = orderService.get(oid);

	System.out.println(content[0]);
	if(user!=null) {
		order.setStatus("finish");
		orderService.update(order);
		List<Review> list = new ArrayList<>();
		for(int i =0 ;i<id.length;i++ ) {
			Review r = new Review();
			r.setUid(user.getId());
			r.setPid(id[i]);
			r.setCreateDate(new Date());
			r.setContent(content[i]);	
			reviewService.add(r);
		}
	
		
		
	}
	 return mav;
		
	}
	
}	

