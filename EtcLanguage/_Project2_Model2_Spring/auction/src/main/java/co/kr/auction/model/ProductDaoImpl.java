package co.kr.auction.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.kr.auction.controller.MemberBean;
import co.kr.auction.controller.ProductBean;

@Repository
public class ProductDaoImpl extends SqlSessionDaoSupport implements ProductDaoInter{
	
	@Autowired
	public ProductDaoImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	@Override
	public boolean productNew(ProductBean bean) {
		boolean b = false;
		if(getSqlSession().insert("productNewInsert", bean) > 0) b = true;
		return b;
	}
	
	@Override
	public List<ProductDto> productList(String p_memid) {
		return getSqlSession().selectList("productList", p_memid);
	}
	
	@Override
	public List<TenderDto> salesProductList(String p_memid) {
		return getSqlSession().selectList("salesProductList", p_memid);
	}
	
	//상품 상세정보
	@Override
	public ProductDto productDetail(int p_bun) {
		return getSqlSession().selectOne("productDetail", p_bun);
	}
	
	@Override
	public List<ProductDto> productAll() {
		return getSqlSession().selectList("productAll");
	}

	@Override
	public List<ProductDto> productPart(String p_name) {
		return getSqlSession().selectList("productPart", p_name);
	}
	
	@Override
	public List<CategoryDto> categorySelect1() {
		return getSqlSession().selectList("categoryAll");
	}
	
	@Override
	public List<CategoryDto> categorySelect2(int subbun) {
		return getSqlSession().selectList("categoryPart", subbun);
	}
	
	@Override
	public void productlike(String p_bun) {
		getSqlSession().update("productlike", p_bun);
	}

	@Override
	public void productupdate() {
		getSqlSession().update("productupdate");
	}
	
	@Override
	public boolean productMemDelete(String p_bun) {
		boolean b = false;
		if(getSqlSession().delete("productMemDelete", p_bun) > 0) b = true;
		return b;
	}
	
	@Override
	public boolean productMemUpdate(MemberBean bean) {
		boolean b = false;
		if(getSqlSession().update("updateMember", bean) > 0) b = true;
		return b;
	}
	
	@Override
	public List<ProductDto> categorySearch(String cate) {
		return getSqlSession().selectList("categorysearch", cate);
	}
}
