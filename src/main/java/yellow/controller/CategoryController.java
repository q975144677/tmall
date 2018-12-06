package yellow.controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.Buffer;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import yellow.pojo.Category;
import yellow.service.CategoryService;
import yellow.util.ImageUtil;
import yellow.util.Page;
import yellow.util.UploadedImage;

@Controller
@RequestMapping("")
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@RequestMapping("admin")
	public ModelAndView list(Page page) {
	 ModelAndView modelAndView = new ModelAndView("list");
	 page.setStart(page.getPage());
	 page.setPrevious(page.getPage()-1);
	 page.setNext(page.getPage()+1);
	 List<Category> cc = categoryService.list();

PageInfo pageInfo =new PageInfo<>(cc);
long total = pageInfo.getTotal();
System.out.println(total);
int end =(int)(total/page.getCount()+(total%page.getCount()==0?0:1));
page.setEndPage(end);
System.out.println(page);
	 if(page.getNext()>end) {
		page.setPage(end);
		page.setStart(page.getPage());
		page.setNext(page.getPage());
		 
	 }
	 System.out.println(page);
	 if(page.getPrevious()<1){
		 page.setPage(1);
		 page.setStart(page.getPage());
		 page.setPrevious(page.getPage());
		 
	 }
	 System.out.println(page);
	 PageHelper pageHelper = new PageHelper();
	 pageHelper.offsetPage(page.getStart(),page.getCount() );
	 List<Category> cs = categoryService.list();
//System.out.println(	 cs.get(0).getProductsByRows().get(0).get(0).getSubTitle());//测试pbr可行性 ✔
modelAndView.addObject("cs",cs);
	 modelAndView.addObject("page",page);
	 return modelAndView;
 }

	@RequestMapping("delete")
	public ModelAndView delete(int id,Page page,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/admin?page="+page.getPage());
		categoryService.delete(id);
	//	System.out.println(session.getServletContext().getRealPath("img/category/category")+id);
		File file = new File(session.getServletContext().getRealPath("img/category/category")+id+".jpg");
		file.delete();
		return mav;

	}
	@RequestMapping("insert")
	public ModelAndView insert(Category category,UploadedImage image,HttpSession session) throws IllegalStateException, IOException {
		ModelAndView mav = new ModelAndView("redirect:/admin");
		categoryService.insert(category);
		System.out.println(image.getImage());
		//try {
		if(!image.getImage().isEmpty()) {
		File file = new File(session.getServletContext().getRealPath("img/category"));
		System.out.println(file);
		if(!file.exists()) {
			file.mkdirs();
		}
		File img = new File(file.getPath()+"/category"+category.getId()+".jpg");
	
		System.out.println(img);
		image.getImage().transferTo(img);
		//BufferedInputStream bufferedInputStream  = new BufferedInputStream(new FileInputStream(img));
		Image bf = ImageIO.read(img);
	bf = ImageUtil.resizeImage(bf, 56, 56);
	ImageIO.write((RenderedImage)bf, "jpg", img);
//		ImageUtil.resizeImage(img, 56, 56, img);
		}
		//}
		//catch(Exception exception) {
	//		exception.printStackTrace();
		//}
		//bufferedInputStream.close();
		return mav;
	}
	@RequestMapping("edit")
	public ModelAndView edit(Category category) {
		ModelAndView mav = new ModelAndView("edit");
		mav.addObject("category",category);
		return mav;
	}
	
	@RequestMapping("change")
	public ModelAndView change(Category category,UploadedImage image,HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:/admin");
		if(!image.getImage().isEmpty()) {
			File file = new File(session.getServletContext().getRealPath("img/category"));
			System.out.println(file.getPath());
			if(!file.exists()) {
				file.mkdirs();
				
			}
			file = new File(file,"category"+category.getId()+".jpg");
			MultipartFile img = image.getImage();
			try {
				img.transferTo(file);
				Image image2  = ImageIO.read(file);
				Image image3 = ImageUtil.resizeImage(image2, 200,60 );
				ImageIO.write((RenderedImage)image3, "jpg", file);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		categoryService.update(category);
		return mav;
	}
}
