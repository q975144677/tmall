package yellow.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yellow.mapper.ProductImageMapper;
import yellow.mapper.PropertyValueMapper;
import yellow.pojo.Product;
import yellow.pojo.ProductExample;
import yellow.pojo.ProductImage;
import yellow.pojo.ProductImageExample;
import yellow.service.ProductImageService;
@Service
public class ProductImageServiceImpl implements ProductImageService {
@Autowired
ProductImageMapper productImageMapper;

	@Override
	public void add(ProductImage productImage) {
		// TODO Auto-generated method stub
	productImageMapper.insert(productImage);	
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		productImageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<ProductImage> list(int pid) {
		// TODO Auto-generated method stub
		ProductImageExample  example = new ProductImageExample();
		example.createCriteria().andPidEqualTo(pid);
		example.setOrderByClause("id desc");
		List<ProductImage> list = productImageMapper.selectByExample(example);
		return list;
		
	}
	
@Override
		public List<ProductImage> listDetail(int pid) {
			// TODO Auto-generated method stub
			List<ProductImage> list = list(pid);
			ArrayList<ProductImage> productImages = new ArrayList<>();
			for(ProductImage productImage : list) {
				if(productImage.getType().equals("Detail")) {
					productImages.add(productImage);
				}
			}
			return productImages;
		}

@Override
			public List<ProductImage> listSingle(int pid) {
				// TODO Auto-generated method stub
				List<ProductImage> list = list(pid);
				ArrayList<ProductImage> productImages = new ArrayList<>();
				for(ProductImage productImage : list) {
					if(productImage.getType().equals("Single")) {
						productImages.add(productImage);
					}
					
				}
				return productImages;
			}
}
