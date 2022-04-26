package pack.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.controller.MemBean;

@Repository // 객체 생성 관련 O
public class MemDaoImpl implements MemDaoInter{
	
	@Autowired // 객채 생성 관련 X
	MemAnnoInter memAnnoInter;
	
	public List<MemDto> getDataAll() {
		return memAnnoInter.getDataAll();
	}
	
	@Override
	public MemDto SelectPart(String num) {
		return memAnnoInter.selectPart(num);
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
		// 이러한 방식으로 할 수 있음.
//		int re = memAnnoInter.deleteData(num);
//		if(re > 0) {
//			return true;
//		} else {
//			return false;
//		}
		
		boolean b = false;
		if(memAnnoInter.insertData(bean) > 0) b = true;
		return b;
	}
	
	@Override
	public boolean updateData(MemBean bean) {
		boolean b = false;
		if(memAnnoInter.updateData(bean) > 0) b = true;
		return b;
	}
	
	@Override
	public boolean deleteData(String num) {
		boolean b = false;
		if(memAnnoInter.deleteData(num) > 0) b = true;
		return b;
	}
}
