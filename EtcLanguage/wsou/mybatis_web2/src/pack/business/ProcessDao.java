package pack.business;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import pack.mybatis.SqlMapConfig;

public class ProcessDao {
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession(); // sql 세션 팩토리를 얻어 DataMapper에 접근.
	
	public List<DataDto> selectDataAll() {
		SqlSession sqlSession = factory.openSession();
		List<DataDto> list = null;
		try {
			list = sqlSession.selectList("selectDataAll");
		} catch (Exception e) {
			System.out.println("selectDataAll Err : " + e);
		} finally {
			if(sqlSession != null) sqlSession.close(); // sql 세션이 열려 있다면 닫기!!
		}
		
		return list;
	}
	
	public DataDto selectDataPart(String id) {
		System.out.println(id);
		SqlSession sqlSession = factory.openSession();
		DataDto dto = null;
		try {
			dto = sqlSession.selectOne("selectDataPart", id);
		} catch (Exception e) {
			System.out.println("selectDataPart Err : " + e);
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		return dto;
	}
	
	public boolean insertData(DataFormBean bean) {
		boolean b = false;
		SqlSession sqlSession = factory.openSession();
		try {
			if(sqlSession.insert("insertData",bean) > 0) b = true;
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			System.out.println("insertData Err : " + e);
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		return b;
	}
	
	public boolean updateData(DataFormBean bean) {
		boolean b = false;
		SqlSession sqlSession = factory.openSession();
		try {
			// 비밀번호 비교 후 수정 처리
			DataDto dto = selectDataPart(bean.getId());
			if(dto.getPasswd().equals(bean.getPasswd())) { // 비밀번호 비교
				if(sqlSession.update("updateData",bean) > 0) b = true;
				sqlSession.commit();
				sqlSession.close();				
			}
		} catch (Exception e) {
			System.out.println("updateData Err : " + e);
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		return b;
	}
	
	public boolean deleteData(String id) {
		boolean b = false;
		SqlSession sqlSession = factory.openSession();
		try {
			if(sqlSession.delete("deleteData", id) > 0) b = true;
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			System.out.println("deleteData Err : " + e);
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		return b;
	}
}
