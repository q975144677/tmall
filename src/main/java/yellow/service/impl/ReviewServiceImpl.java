package yellow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.swing.internal.plaf.metal.resources.metal_zh_TW;

import yellow.mapper.ReviewMapper;
import yellow.pojo.Review;
import yellow.pojo.ReviewExample;
import yellow.pojo.User;
import yellow.service.ReviewService;
import yellow.service.UserService;
@Service
public class ReviewServiceImpl implements ReviewService {
@Autowired
ReviewMapper reviewMapper;
@Autowired
UserService userService;

	
	@Override
	public void add(Review review) {
		// TODO Auto-generated method stub
		reviewMapper.insert(review);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		reviewMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Review> list(int pid) {
		// TODO Auto-generated method stub
		ReviewExample example = new ReviewExample();
		example.createCriteria().andPidEqualTo(pid);
		example.setOrderByClause("id desc");
		List<Review> list = reviewMapper.selectByExample(example);
		setUser(list);
		return list;
	}

	@Override
	public Review get(int id) {
		// TODO Auto-generated method stub
		Review review = reviewMapper.selectByPrimaryKey(id);
		setUser(review);
		return review;
	}

	@Override
	public int getCount(int pid) {
		// TODO Auto-generated method stub
		return list(pid).size();
		
	}

	@Override
	public void update(Review review) {
		// TODO Auto-generated method stub
		reviewMapper.updateByPrimaryKey(review);
	}

	public User getUser(int uid) {
		User user = userService.get(uid);		
		return user;
	}
	public void setUser(Review review) {
		review.setUser(getUser(review.getUid()));
		
	}
	public void setUser(List<Review> list) {
		for(Review review : list) {
			setUser(review);
		}
		
	}
}
