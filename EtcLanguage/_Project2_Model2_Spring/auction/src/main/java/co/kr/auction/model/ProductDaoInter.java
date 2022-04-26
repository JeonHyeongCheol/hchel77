package co.kr.auction.model;

import java.util.List;

import co.kr.auction.controller.MemberBean;
import co.kr.auction.controller.ProductBean;

public interface ProductDaoInter {
	boolean productNew(ProductBean bean);
	List<ProductDto> productList(String p_memid);
	List<TenderDto> salesProductList(String p_memid);
	ProductDto productDetail(int p_bun);
	List<ProductDto> productAll();
	List<ProductDto> productPart(String p_name);
	List<CategoryDto> categorySelect1();
	List<CategoryDto> categorySelect2(int subbun);
	void productlike(String p_bun);
	void productupdate();
	boolean productMemDelete(String p_bun);
	boolean productMemUpdate(MemberBean bean);
	List<ProductDto> categorySearch(String cate);
	
}
