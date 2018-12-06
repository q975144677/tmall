package yellow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
import yellow.mapper.UserMapper;
import yellow.pojo.User;
import yellow.pojo.UserExample;
import yellow.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub

		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		UserExample example = new UserExample();
		example.setOrderByClause("id desc");
		return userMapper.selectByExample(example);
	}

	@Override
	public boolean isExist(User user) {
		// TODO Auto-generated method stub
		
		UserExample example = new UserExample();
		if (user!=null) {
			
		
		example.createCriteria().andNameEqualTo(user.getName());
		if(userMapper.selectByExample(example)!=null) {
		if (userMapper.selectByExample(example).isEmpty()) {
			return false;
		}
		}
		else {
			return false;
			
		}
		}
		return true;
	}

	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		userMapper.insert(user);
	}

	@Override
	public User get(String name, String password) {
		UserExample example = new UserExample();
		example.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
		// TODO Auto-generated method stub
		List<User> list = userMapper.selectByExample(example);
		if (list.isEmpty())
			return null;
		else
			return list.get(0);
	}

	@Override
	public String getAnnoymousName(User user) {
		// TODO Auto-generated method stub
		if (user.getName().length() > 2) {
			if (user.getName().length() > 3) {
				char[] ch = user.getName().toCharArray();
				for (int i = 1; i < ch.length - 2; i++) {
					ch[i] = '*';
				}
				return new String(ch);
			}
			char ch[] = user.getName().toCharArray();
			ch[2] = '*';
			return new String(ch);
		}
		return user.getName();
	}
	
	@Override
	public User get(String name) {
		// TODO Auto-generated method stub
		UserExample example  = new UserExample();
		example.createCriteria().andNameEqualTo(name);
		List<User> list = userMapper.selectByExample(example);
		if(!list.isEmpty()) {
			return list.get(0);
			
		}
		return null;
		
	}
}
