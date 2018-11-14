package co.kr.auction.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import co.kr.auction.controller.ProductBean;

@Repository
@Qualifier("ipchalimpl")
public class IpchalImpl extends SqlSessionDaoSupport implements IpchalInter{

	@Autowired
	public IpchalImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	@Override
	public TenderDto fuckin(String p_bun) {
		return getSqlSession().selectOne("tenderCount", p_bun);
	}

	@Override
	public boolean fucking(TenderDto dto) {
		boolean b = false;
		
		
		int a = getSqlSession().insert("tenderInsert", dto);
		
		if(a > 0 ) b = true;

		return b;
	}

	@Override
	public void newfuckings(ProductBean frm) {
		
		String t_pbun = Integer.toString(frm.getP_bun());
		
		TenderDto dto = new TenderDto();
		dto.setT_price(frm.getP_sprice());
		dto.setT_memid(frm.getP_memid());
		dto.setT_pbun(t_pbun);
		
		getSqlSession().insert("newTender", dto);
		
		
	}

	@Override
	public List<TenderDto> alltender(TenderDto dto) {
		return getSqlSession().selectList("tenderAll", dto);
	}
	
	@Override
	public List<TenderDto> tenderPart(String m_id) {
		return getSqlSession().selectList("tenderPart", m_id);
	}
	
	@Override
	public TenderDto tenderPartLike(String t_pbun) {
		return getSqlSession().selectOne("tenderPartLike", t_pbun);
	}
	
	
}
