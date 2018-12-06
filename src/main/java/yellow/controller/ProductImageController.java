package yellow.controller;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.sf.jsqlparser.statement.delete.Delete;
import yellow.pojo.Product;
import yellow.pojo.ProductImage;
import yellow.service.ProductImageService;
import yellow.util.ImageUtil;
import yellow.util.UploadedImage;

@Controller
public class ProductImageController {

	@Autowired
	ProductImageService productImageService;
	
	@RequestMapping("productImage")
	public ModelAndView productImage(Product product) {
		ModelAndView mav = new ModelAndView("productImage");
		List<ProductImage> list = productImageService.list(product.getId());
		List<ProductImage> list2 = productImageService.listSingle(product.getId());
		List<ProductImage> list3  = productImageService.listDetail(product.getId());
		mav.addObject("productImages",list);
		mav.addObject("singleImages",list2);
		mav.addObject("detailImages",list3);
		mav.addObject("product",product);
		mav.addObject("cid",product.getCid());
		return mav;
	}
	@RequestMapping("productImage_delete")
	public ModelAndView delete(int pid,int cid,int id,HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:/productImage?id="+pid+"&cid="+cid);
		productImageService.delete(id);

		new File(session.getServletContext().getRealPath("img/productImage"),id+".jpg").delete();
		return mav;
	}
	@RequestMapping("productImage_insert")
	public ModelAndView insert(ProductImage productImage,int pid,int cid,HttpSession session,UploadedImage image ) {
		productImageService.add(productImage);
		ModelAndView mav= new ModelAndView("redirect:/productImage?id="+pid+"&cid="+cid);
		MultipartFile mf = image.getImage();
		if(!mf.isEmpty()) {
		File file  = new File( session.getServletContext().getRealPath("img/productImage"))	;
		if(!file.exists()) {
			file.mkdirs();
		}
			try {
				file = new File(file,String.valueOf(productImage.getId())+".jpg");
				mf.transferTo(file);
				Image i = ImageIO.read(file);
				Image i2 = i;
				if(productImage.getType().equals("Single")) {
			 i2 = ImageUtil.resizeImage(i, 400, 400);
				}
		
				ImageIO.write((RenderedImage)i2, "jpg", file);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		System.out.println(productImage.getPid()+"||"+pid+"||"+file.getPath());
	
		}
		return mav;
	}
	
}
