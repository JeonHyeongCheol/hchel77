package co.kr.auction.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.kr.auction.controller.MemberBean;

@Repository
public class MemberDaoImpl extends SqlSessionDaoSupport implements MemberDaoInter {
	
	@Autowired
	public MemberDaoImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	@Override
	public boolean memberNew(MemberBean bean) {
		boolean b = false;
		if(getSqlSession().insert("memberNewInsert", bean) > 0 ) b = true; 
		return b;
	}
	
	@Override
	public MemberDto myLoginInfo(String id) {
		return getSqlSession().selectOne("memberLogin", id);
	}
	
	@Override
	public List<MemberDto> memberList() {
		return getSqlSession().selectList("selectDataAll");
	}
	
	@Override
	public boolean productmemLike(MemberBean bean) {
		boolean b = false;
		if(getSqlSession().update("productmemLike", bean) > 0) b = true;
		return b;
	}
	
	@Override
	public MemberDto memlike(String m_id) {
		return getSqlSession().selectOne("memlike", m_id);
	}
	
	@Override
	public MemberDto updateMemPart(String m_id) {
		return getSqlSession().selectOne("updateMemPart", m_id);
	}
	
	@Override
	public boolean updateMem(MemberBean bean) {
		boolean b = false;
		if(getSqlSession().update("updateMember", bean) > 0) b = true;
		return b;
	}
	
	@Override
	public void productcancle(String p_bun) {
		getSqlSession().update("productcancle", p_bun);
	}
	
}
