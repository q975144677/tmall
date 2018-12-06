package yellow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import yellow.mapper.OrderItemMapper;
import yellow.pojo.Order;
import yellow.pojo.OrderItem;
import yellow.pojo.OrderItemExample;
import yellow.pojo.Product;
import yellow.service.OrderItemService;
import yellow.service.ProductService;
@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	OrderItemMapper orderItemMapper;
	@Autowired
	ProductService productService;
	@Override
	public List<OrderItem> list() {
		// TODO Auto-generated method stub
		OrderItemExample example = new OrderItemExample();
		example.setOrderByClause("id desc");
		return orderItemMapper.selectByExample(example);
	}

	@Override
	public OrderItem get(int id) {
		// TODO Auto-generated method stub
	OrderItem i = orderItemMapper.selectByPrimaryKey(id);
	setProduct(i);
	return i;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		orderItemMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(OrderItem oi) {
		// TODO Auto-generated method stub
		orderItemMapper.updateByPrimaryKeySelective(oi);
	}

	@Override
	public void add(OrderItem oi) {
		// TODO Auto-generated method stub
		orderItemMapper.insert(oi);
	}

	@Override
	public void fill(Order oi) {
		// TODO Auto-generated method stub
		OrderItemExample example = new OrderItemExample();
		example.createCriteria().andOidEqualTo(oi.getId());
		example.setOrderByClause("id desc");
		List<OrderItem> orderItems = orderItemMapper.selectByExample(example);
		float total = 0f;
		int totalNumber = 0;
		for(OrderItem o : orderItems) {
			setProduct(o);
			total+=o.getNumber()*o.getProduct().getPromotePrice();//npe
			totalNumber+=o.getNumber();
		
		}
		oi.setTotal(total);
		oi.setTotalNumber(totalNumber);
		oi.setOrderItems(orderItems);
	
	}

	@Override
	public void fill(List<Order> orders) {
		// TODO Auto-generated method stub
for(Order order : orders) {
	fill(order);
	
}
	}
	public void setProduct(OrderItem orderItem) {
		Product  p = productService.get(
				orderItem.getPid());
		orderItem.setProduct(p);
	}
	
@Override
	public int getSaleCount(int pid) {
		// TODO Auto-generated method stub
	OrderItemExample example = new OrderItemExample();
	example.createCriteria().andPidEqualTo(pid);
	List<OrderItem> list = orderItemMapper.selectByExample(example);
	int total = 0;
	for(OrderItem orderItem : list) {
		if(orderItem.getNumber()!=null) {
	total += orderItem.getNumber();
		}
	}
	return total;
	}	
public void setProduct(List<OrderItem> orderItems) {
	for(OrderItem orderItem : orderItems) {
		setProduct(orderItem);
		
	}
	
}

@Override
public List<OrderItem> list(int uid) {
	// TODO Auto-generated method stub
	OrderItemExample example  = new OrderItemExample();
	example.createCriteria().andUidEqualTo(uid);
	List<OrderItem> list = orderItemMapper.selectByExample(example);
setProduct(list);
	return list;
}@Override
public List<OrderItem> list(Product product) {
	// TODO Auto-generated method stub
	OrderItemExample example = new OrderItemExample();
	example.createCriteria().andPidEqualTo(product.getId());
	return orderItemMapper.selectByExample(example);
}
@Override
public void UidNull(OrderItem orderItem) {
	// TODO Auto-generated method stub
	orderItem.setUid(null);
	orderItemMapper.updateByPrimaryKey(orderItem);
}

@Override
public void deleteByOid(int oid) {
	// TODO Auto-generated method stub
OrderItemExample example = new OrderItemExample();
example.createCriteria().andOidEqualTo(oid);
List<OrderItem> list = orderItemMapper.selectByExample(example);
for(OrderItem o : list) {
	orderItemMapper.deleteByPrimaryKey(o.getId());
}
}
}
