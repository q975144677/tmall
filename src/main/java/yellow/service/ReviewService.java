package yellow.service;

import java.util.List;

import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.springframework.web.servlet.config.annotation.RedirectViewControllerRegistration;

import yellow.pojo.Review;

public interface ReviewService {
void add(Review review);
void delete(int id);
List<Review> list(int pid);
Review get(int id);
int getCount(int pid);
void update(Review review);
}
