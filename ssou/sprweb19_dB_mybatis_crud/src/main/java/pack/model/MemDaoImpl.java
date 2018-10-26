package pack.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.controller.MemBean;

@Repository // 객체 생성 관련 O
public class MemDaoImpl extends SqlSessionDaoSupport implements MemDaoInter{
	
	@Autowired // 객채 생성 관련 X
	public MemDaoImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	public List<MemDto> getDataAll() {
		return getSqlSession().selectList("selectDataAll");
	}
	
	@Override
	public MemDto SelectPart(String num) {
		return getSqlSession().selectOne("selectPart", num);
	}
	
//	이러한 방식으로 할 수 있음!! 
//	@Override
//	public boolean insertData(MemBean bean) {
//		try {
//			getSqlSession().update("insertData", bean);
//			return true;
//		} catch (Exception e) {
//			System.out.println("insertData Err : " + e );
//			return false;
//		}
//	}
	
	@Override
	public boolean insertData(MemBean bean) {
		boolean b = false;
		if(getSqlSession().update("insertData", bean) > 0) b = true;
		return b;
	}
	
	@Override
	public boolean updateData(MemBean bean) {
		boolean b = false;
		if(getSqlSession().update("updateData", bean) > 0) b = true;
		return b;
	}
	
	@Override
	public boolean deleteData(String num) {
		boolean b = false;
		if(getSqlSession().delete("deleteData", num) > 0) b = true;
		return b;
	}
}
