package co.kr.auction.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import co.kr.auction.model.CategoryDto;
import co.kr.auction.model.FileValidator;
import co.kr.auction.model.IpchalInter;
import co.kr.auction.model.MemberDaoImpl;
import co.kr.auction.model.MemberDaoInter;
import co.kr.auction.model.ProductDaoInter;
import co.kr.auction.model.ProductDto;
import co.kr.auction.model.TenderDto;

@Controller
public class ProductController {
	
	@Autowired
	private FileValidator validator;
	
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDaoInter productDaoInter;
	
	@Autowired
	@Qualifier("ipchalimpl")
	private IpchalInter ipchalinter;
	
	@Autowired
	@Qualifier("memberDaoImpl")
	private MemberDaoInter memberDaoInter;
	
	// 메소드명 --------------------------- Start ---------------------------
	// 메소드명 ---------------------------- End ----------------------------
	
	// 물품 등록 --------------------------- Start ---------------------------
	
	@RequestMapping(value = "productRegister", method = RequestMethod.GET)
	public ModelAndView getList() {
		List<CategoryDto> category1List = (List<CategoryDto>)productDaoInter.categorySelect1();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("category1List", category1List);
		modelAndView.setViewName("product/product_upload");
		return modelAndView;
	}
	
	@RequestMapping(value = "productRegister", method = RequestMethod.POST)
	public String upList(@ModelAttribute("uploading") ProductBean frm, BindingResult result) {

		InputStream input = null;
		OutputStream output = null;
		ArrayList<MultipartFile> filelist = new ArrayList<>();
		
//		validator.validate(frm, result);

		MultipartFile file1 = frm.getFile1();
		filelist.add(file1);
		String fileNames = file1.getOriginalFilename();
		
//		String filename1 = file1.getOriginalFilename();	
		
		if(frm.getFile2() != null) {
			MultipartFile file2 = frm.getFile2();
			filelist.add(file2);
			fileNames += "&" + file2.getOriginalFilename();
			
			if(frm.getFile3() != null) {
				MultipartFile file3 = frm.getFile3();
				filelist.add(file3);
				fileNames += "&" + file3.getOriginalFilename();
				
				if(frm.getFile4() != null) {
					MultipartFile file4 = frm.getFile4();
					filelist.add(file4);
					fileNames += "&" + file4.getOriginalFilename();
				}
			}	
		}
		
		frm.setP_sdate(frm.getYear1() +"-"+ frm.getMonth1() +"-"+frm.getDate1() +" "+frm.getTime1()+":"+frm.getMin1() + ":00") ;
		frm.setP_edate(frm.getYear2() +"-"+ frm.getMonth2() +"-"+frm.getDate2() +" "+frm.getTime2()+":"+frm.getMin2() + ":00");
		frm.setP_photo(fileNames);
		frm.setP_category(frm.getP_category1() + "," + frm.getP_category2());
		
		//frm에 값이 담겨진 상태로 넘어옴.
		//sql insert처리만 하면 됨.
		
		boolean b = productDaoInter.productNew(frm);
		
		// tender upload

		ipchalinter.newfuckings(frm);
		
		try {
			
			for (int i = 0; i < filelist.size(); i++) {
				int read = 0;
				byte[] bytes = new byte[1024];
				
				input = filelist.get(i).getInputStream();

				File fileroot = new File("C:/work/ssou/auction/src/main/webapp/resources/img/" + filelist.get(i).getOriginalFilename());

				output = new FileOutputStream(fileroot);

				
				if(!fileroot.exists()) {
					fileroot.createNewFile();
				}
				
				while((read = input.read(bytes)) != -1) {
					output.write(bytes, 0, read);
				}				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				output.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		
		if(b) {
			return "product/product_upload";
		} else {
			return "error";
		}
	}
	
	// 물품 등록 ---------------------------- End ----------------------------
	
	
	// 사용자 상품 Update Form --------------------------- Start ---------------------------
	@RequestMapping(value = "pupdate", method = RequestMethod.GET)
	public ModelAndView productMemupdateForm(@RequestParam("p_bun") String p_bun, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ProductDto dto = productDaoInter.productDetail(Integer.parseInt(p_bun));
	
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("product/updateForm");
		modelAndView.addObject("updateData", dto);
		
	    return modelAndView;
	}
		
	// 사용자 상품 Update Form ---------------------------- End ----------------------------
	
	// 사용자 상품 삭제 --------------------------- Start ---------------------------
	@RequestMapping(value = "pdelete", method = RequestMethod.GET)
	public ModelAndView productMemdelete(@RequestParam("p_bun") String p_bun, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		boolean b = productDaoInter.productMemDelete(p_bun);
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(b) {
			modelAndView.setViewName("product/product_upload");
		} else {
			modelAndView.setViewName("error");
		}
	    return modelAndView;
	}
	
	// 사용자 상품 삭제 ---------------------------- End ----------------------------
	
	// 사용자 판매 목록 --------------------------- Start ---------------------------
	@RequestMapping(value = "productList", method = RequestMethod.GET ,produces="application/json")
	@ResponseBody
	public Map productList(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		String p_memid = (String)request.getParameter("m_id");
		List<ProductDto> productList = productDaoInter.productList(p_memid);
		
		Map<String, Object> data = new HashMap<String, Object>();
	    data.put("productList", productList);
	    return data;
	}
	
	// 사용자 판매 목록 ---------------------------- End ----------------------------
	

	// 사용 판매완료 목록 --------------------------- Start ---------------------------

	@RequestMapping(value = "salesProductList", method = RequestMethod.GET ,produces="application/json")
	@ResponseBody
	public Map salesProductList(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		String p_memid = (String)request.getParameter("m_id");
		List<TenderDto> salesProductList = productDaoInter.salesProductList(p_memid);
		
		Map<String, Object> data = new HashMap<String, Object>();
	    data.put("salesProductList", salesProductList);
	    return data;
	}
	
	// 사용 판매완료 목록 ---------------------------- End ----------------------------

	// 선택 상품 정보(Detail) --------------------------- Start ---------------------------

	@RequestMapping("list_detail")
	public ModelAndView list_detail(ProductDto dto){
		
		ModelAndView view = new ModelAndView();
		view.setViewName("product/list_detail");
		view.addObject("plist", productDaoInter.productDetail(dto.getP_bun()));
		//view.addObject("ipchal", ipchalinter.fuckin(String.valueOf(dto.getP_bun())));
		return view;
	}
	
	// 선택 상품 정보(Detail) ---------------------------- End ----------------------------
	
	
	// 전체 상품 목록(list.jsp) --------------------------- Start ---------------------------
	
	
	@RequestMapping(value = "list")
	public ModelAndView allList1() {

		ModelAndView view = new ModelAndView();
		ArrayList<ProductDto> list = (ArrayList<ProductDto>) productDaoInter.productAll();

		System.out.println(list.size());
		String[] photolist;
		for (int i = 0; i < list.size(); i++) {
			photolist = list.get(i).getP_photo().split("&");
			list.get(i).setPicture(photolist[0]);

		}

		view.addObject("plist", list);
		view.setViewName("product/list");
		return view;

	}
	
	@RequestMapping(value = "list2")
	public ModelAndView allList2(@RequestParam("p_category") String category) {

		ModelAndView view = new ModelAndView();
		ArrayList<ProductDto> list = (ArrayList<ProductDto>) productDaoInter.categorySearch(category);

		System.out.println(list.size());
		String[] photolist;
		for (int i = 0; i < list.size(); i++) {
			photolist = list.get(i).getP_photo().split("&");
			list.get(i).setPicture(photolist[0]);

		}

		view.addObject("plist", list);
		view.setViewName("product/list");
		return view;

	}
	
	@RequestMapping(value = "ajaxlist")
	public ModelAndView allList(ProductDto dto) {
		
		ModelAndView view = new ModelAndView();
		ArrayList<ProductDto> list = null;
		if (dto.getP_name().equals("anything") || dto.getP_name() == null) {
			list = (ArrayList<ProductDto>) productDaoInter.productAll();

		} else {
			list = (ArrayList<ProductDto>) productDaoInter.productPart(dto.getP_name());
		}

		String[] photolist;
		for (int i = 0; i < list.size(); i++) {
			photolist = list.get(i).getP_photo().split("&");
			list.get(i).setPicture(photolist[0]);

		}

		view.setViewName("product/list");
		view.addObject("plist", list);
		return view;

	}
	// 전체 상품 목록(list.jsp) ---------------------------- End ----------------------------

	// 좋아요 누르기 --------------------------- Start ---------------------------
	@RequestMapping("liking")
	public ModelAndView aoplist_detail1(ProductDto dto, MemberBean bean, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		String likes = (String)session.getAttribute("memlike");
	    
	    
	    String[] likelist = likes.split(",");
		boolean b = true;
	    
	    
		for (int i = 0; i < likelist.length; i++) {
			if(likelist[i].equals(String.valueOf(dto.getP_bun()))) {
				b = false;
			}
		}
		
		if(b) {
			if(likes.equals("0") || likes == null) {
				likes = String.valueOf(dto.getP_bun());				
			} else {
				likes += "," + String.valueOf(dto.getP_bun());								
			}
			
			
			// 좋아요 테이블 업뎃
			bean.setM_id((String)session.getAttribute("m_id"));
			bean.setM_like(likes);
			memberDaoInter.productmemLike(bean);
			
			
			// 좋아요 상품 없뎃
			productDaoInter.productlike(String.valueOf(dto.getP_bun()));			
			
			session.removeAttribute("memlike");
			session.setAttribute("memlike", likes);
		}
		ModelAndView view = new ModelAndView();
		if((String)session.getAttribute("m_id") != null) {
			view.setViewName("product/list_detail");
			view.addObject("plist", productDaoInter.productDetail(dto.getP_bun()));
		} else {
			view.setViewName("error");
		}
		return view;
	}
	
	// 좋아요 누르기 --------------------------- End ---------------------------
	
	
	// 카테고리 --------------------------- Start ---------------------------
	
//	@RequestMapping(value = "category1", method = RequestMethod.GET)
//	@ResponseBody
//	public Map category1() {
//		List<CategoryDto> category1List = (List<CategoryDto>)productDaoInter.categorySelect1();
//		
//		Map<String, Object> data = new HashMap<String, Object>();
//	    data.put("category1List", category1List);
//	    return data;
//	}
	
	@RequestMapping(value = "category2", method = RequestMethod.GET)
	@ResponseBody
	public Map category2(@RequestParam("subbun") String sub_bun) {
		int subbun = Integer.parseInt(sub_bun);
		List<CategoryDto> category2List = (List<CategoryDto>)productDaoInter.categorySelect2(subbun);
		
		Map<String, Object> data = new HashMap<String, Object>();
	    data.put("category2List", category2List);
	    return data;
	}
	// 카테고리 ---------------------------- End ----------------------------
}
