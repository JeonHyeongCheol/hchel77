package pack.business;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import pack.mybatis.SqlMapConfig;

public class ProcessDao {
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession(); // sql 세션 팩토리를 얻어 DataMapper에 접근.
	
	public List<JikwonDto> selectDataAll() {
		SqlSession sqlSession = factory.openSession();
		List<JikwonDto> list = null;
		
		try {
			list = sqlSession.selectList("selectDataAll");
		} catch (Exception e) {
			System.out.println("selectDataAll Err : " + e);
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		
		return list;
	}
	
	public List<JikwonDto> selectJikPay() {
		SqlSession sqlSession = factory.openSession();
		List<JikwonDto> list = null;
		try {
			list = sqlSession.selectList("selectJikPay");
		} catch (Exception e) {
			System.out.println("selectJikPay Err : " + e);
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		
		return list;
	}
	
	public List<JikwonDto> selectGenPay() {
		SqlSession sqlSession = factory.openSession();
		List<JikwonDto> list = null;
		try {
			list = sqlSession.selectList("selectGenPay");
		} catch (Exception e) {
			System.out.println("selectGenPay Err : " + e);
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		
		return list;
	}
}
