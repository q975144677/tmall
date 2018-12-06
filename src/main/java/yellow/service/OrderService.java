package yellow.service;

import java.util.List;

import yellow.pojo.Order;

public interface OrderService {
	String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";
    
    int getTotal(Order order);
    void update(Order order);
    void delete(int id);
    List<Order> list();
    void add(Order order);
    Order get(int id);
    List<Order> list(int uid);
    void setTotal(List<Order> list);
}	
