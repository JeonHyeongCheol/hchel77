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
			SqlMapperInter inter = sqlSession.getMapper(SqlMapperInter.class); // 인터페이스 객체 생성하여 읽어야 함.
			list = inter.selectDataAll();
		} catch (Exception e) {
			System.out.println("selectDataAll Err : " + e);
		} finally {
			if(sqlSession != null) sqlSession.close(); // sql 세션이 열려 있다면 닫기!!
		}
		
		return list;
	}
	
	public DataDto selectDataPart(String id) {
		SqlSession sqlSession = factory.openSession();
		DataDto dto = null;
		try {
			SqlMapperInter inter = sqlSession.getMapper(SqlMapperInter.class);
			dto = inter.selectDatapart(id);
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
			SqlMapperInter inter = sqlSession.getMapper(SqlMapperInter.class);
			if(inter.insertData(bean) > 0) b = true;
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
			SqlMapperInter inter = sqlSession.getMapper(SqlMapperInter.class);
			// 비밀번호 비교 후 수정 처리
			DataDto dto = inter.selectDatapart((bean.getId()));
			if(dto.getPasswd().equals(bean.getPasswd())) { // 비밀번호 비교
				if(inter.updateData(bean) > 0) b = true;
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
			SqlMapperInter inter = sqlSession.getMapper(SqlMapperInter.class);
			if(inter.deleteData(id) > 0) b = true;
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
