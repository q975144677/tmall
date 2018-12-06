package yellow.service;

import java.util.List;

import yellow.pojo.User;

public interface UserService {
	User get(int id);
	List<User> list();
	boolean isExist(User user);
	void add(User user);
	User get(String name,String password);
	String getAnnoymousName(User user);
	User get(String name);
}
