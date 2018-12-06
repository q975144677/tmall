package yellow.service.impl;

import java.awt.image.RenderedImage;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yellow.mapper.OrderItemMapper;
import yellow.mapper.OrderMapper;
import yellow.mapper.UserMapper;
import yellow.pojo.Order;
import yellow.pojo.OrderExample;
import yellow.pojo.OrderItem;
import yellow.pojo.OrderItemExample;
import yellow.service.OrderItemService;
import yellow.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderMapper orderMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	OrderItemMapper orderItemMapper;
	@Autowired
	OrderItemService orderItemService ;
	@Override
	public void update(Order order) {
		// TODO Auto-generated method stub
		orderMapper.updateByPrimaryKeySelective(order);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		orderMapper.deleteByPrimaryKey(id);
	}
	@Override
	public List<Order> list() {
		// TODO Auto-generated method stub
		OrderExample example = new OrderExample();
		example.setOrderByClause("id desc");
	
		List<Order> list =  orderMapper.selectByExample(example);
		setUser(list);
		setOrderItems(list);
		orderItemService.fill(list);
		return list;
	}

	@Override
	public void add(Order order) {
		// TODO Auto-generated method stub
		order.setCreateDate(new Date());

		order.setOrderCode(order.getOrderCode());
		orderMapper.insert(order);
	}

	@Override
	public Order get(int id) {
		// TODO Auto-generated method stub
		Order order = orderMapper.selectByPrimaryKey(id);
		setOrderItems(order);
		return order;
		
	}
	
	public void setUser(Order order) {
		order.setUser(userMapper.selectByPrimaryKey(order.getUid()));	
	}
	public void setUser(List<Order> orders) {
		for(Order order : orders) {
			setUser(order);
			
		}
		
	}
	public void setOrderItems(Order order) {
		OrderItemExample example = new OrderItemExample();
		example.createCriteria().andOidEqualTo(order.getId());
		List<OrderItem> list = orderItemMapper.selectByExample(example);
		for(OrderItem oi : list) {
		orderItemService.setProduct(oi);
		}
		order.setOrderItems(list);
		
	}
	
	public void setOrderItems(List<Order> orders) {
		for(Order order : orders) {
			setOrderItems(order);
			
		}
		
	}
	@Override
	public int getTotal(Order order) {
		// TODO Auto-generated method stub
		List<OrderItem> list = order.getOrderItems();
		int price=0;
		if(list!=null) {
			for(OrderItem oi : list) {
				if(oi.getProduct()!=null&&oi.getProduct().getPromotePrice()!=null) {
				price+=oi.getNumber()*oi.getProduct().getPromotePrice();
				}
			}
			
		}
		
		return price;
	}
	
	@Override
	public List<Order> list(int uid) {
		// TODO Auto-generated method stub
		OrderExample example  = new OrderExample();
		example.createCriteria().andUidEqualTo(uid);
		example.setOrderByClause("id desc");
		List<Order> list = orderMapper.selectByExample(example);
		setOrderItems(list);
		return list;
	}
	
	@Override
	public void setTotal(List<Order> list) {
		// TODO Auto-generated method stub
		for(Order order : list) {
			setTotal(order);
			
		}
	}
	
	public void setTotal(Order order) {
		order.setTotal(getTotal(order));
		
	}

}
