package co.kr.auction.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


@Repository
@Qualifier("ajaxImpl")
public class AjaxImpl extends SqlSessionDaoSupport implements AjaxInter{

	
	@Autowired
	public AjaxImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	@Override
	public List<String> getAjax(String p_name) {
		
		return getSqlSession().selectList("productAjax", p_name);
	}

}
