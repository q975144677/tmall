package yellow.controller;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryExplorerTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.RequestToViewNameTranslator;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import yellow.pojo.Category;
import yellow.pojo.Product;
import yellow.service.ProductService;
import yellow.util.ImageUtil;
import yellow.util.Page;
import yellow.util.UploadedImage;
@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	@RequestMapping("product")
	public ModelAndView list(Category category,Page page) {
		ModelAndView mav = new ModelAndView("product");
		PageHelper ph = new PageHelper();
		page.setStart(page.getPage());
		page.setNext(page.getPage()+1);
		page.setPrevious(page.getPage()-1);
		ph.offsetPage(page.getStart(), page.getCount());
	
		List<Product> products = productService.list(category.getId());
		int total = (int)new PageInfo<>(products).getTotal();
		page.setEndPage((int)((new PageInfo<>(products).getTotal()/page.getCount())+((new PageInfo<>(products).getTotal()%page.getCount())!=0?1:0)))	;
		page.setEndPage(total/page.getCount()+(total%page.getCount()==0?0:1));
		mav.addObject("products",products);
		mav.addObject("cid",category.getId());
		mav.addObject("page",page);
		return mav;
	}
	
	@RequestMapping("product_insert")
	public ModelAndView add(Product product,UploadedImage image,HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:/product?id="+product.getCid());
		productService.insert(product);
		String string  = session.getServletContext().getRealPath("img/product");
		System.out.println(string);
		File file = new File(string);
	if(!file.exists()) {
		file.mkdirs();
	}
	if(!image.getImage().isEmpty()) {
	File img = new File(file,"product"+product.getId()+".jpg");
	System.out.println(img.getPath());
	MultipartFile mf = image.getImage();
try {
	mf.transferTo(img);
	Image image2 = ImageIO.read(img);
	Image result = ImageUtil.resizeImage(image2, 56, 56);
	ImageIO.write((RenderedImage)result, "jpg", img);
} catch (IllegalStateException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
		return mav;
		
	}
	
	@RequestMapping("product_delete")
	public ModelAndView delete(Product product,Page page) {
		ModelAndView mav = new ModelAndView("redirect:/product?id="+product.getCid()+"&page="+page.getPage());
		productService.delete(product.getId());
		return mav;
		
	}
	@RequestMapping("product_photo_edit")
	public ModelAndView photoEdit(Product product) {
		ModelAndView mav = new ModelAndView("product_photo_edit") ;
		mav.addObject("product",product);
		return mav;
	}
	@RequestMapping("product_changeImage")
	public ModelAndView changeImage(Product product,UploadedImage image,HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:/product?id="+product.getCid());
		File file = new File(session.getServletContext().getRealPath("img/product"),"product"+product.getId()+".jpg");
		if(!file.exists()) {
			file.mkdirs();
		}
		System.out.println(file.getPath());
		if (!image.getImage().isEmpty()) {
		MultipartFile mf = image.getImage();
		try {
			mf.transferTo(file);
			Image image2 = ImageIO.read(file);
			Image result = ImageUtil.resizeImage(image2, 56, 56);
			ImageIO.write((RenderedImage)result,"jpg",file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return mav;
		
	}
	
	@RequestMapping("product_edit")
	public ModelAndView edit(Product product) {
		ModelAndView mav = new ModelAndView("product_update");
		mav.addObject("product",product);
		return mav;
	}
	
	@RequestMapping("product_update")
	public ModelAndView update(Product product) {
		ModelAndView mav = new ModelAndView("redirect:/product?id="+product.getCid());
		productService.update(product);
		
		return mav;
	}
	
	
}
