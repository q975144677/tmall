package yellow.service;

import java.util.List;

import org.apache.ibatis.mapping.VendorDatabaseIdProvider;

import yellow.pojo.Order;
import yellow.pojo.OrderItem;
import yellow.pojo.Product;

public interface OrderItemService {
List<OrderItem> list();
OrderItem get(int id );
void delete(int id);
void update(OrderItem oi);
void add(OrderItem oi);
void fill(Order oi);
void fill(List<Order> orderItems);
int getSaleCount(int pid);
List<OrderItem> list(int uid);
List<OrderItem> list(Product product);
void UidNull(OrderItem orderItem);
void setProduct(OrderItem orderItem);
void deleteByOid(int oid);
}
