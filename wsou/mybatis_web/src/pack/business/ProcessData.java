package pack.business;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.ibatis.session.SqlSessionFactory;

import pack.mybatis.SqlMapConfig;

public class ProcessData {
	private static ProcessData dao = new ProcessData(); // 클래스를 객체로 만듬.
	
	public static ProcessData getInstance() { // 클래스를 리턴으로 넘겨줌.
		return dao;
	}
	
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession(); // factory Configuration.xml을 가지고 있음.
	
	public List<DataDto> readDataAll() throws SQLException {
		SqlSession sqlSession = factory.openSession(); // 세션을 열어줌.
		List<DataDto> list = sqlSession.selectList("selectDataAll"); // DataMapper에서 보고 사용하고 싶은 ID 입력.
		sqlSession.close();
		return list;
	}
	
	public DataDto readData(String code) throws SQLException {
		SqlSession sqlSession = factory.openSession(); 
		DataDto dto = sqlSession.selectOne("selectDataById", code); 
		sqlSession.close();
		return dto;
	}
	
	public void insertData(DataForm form) throws SQLException {
		SqlSession sqlSession = factory.openSession(); // 세션을 열어줌. 수동 커밋.
		//SqlSession sqlSession = factory.openSession(true); // 자동 커밋
		sqlSession.insert("insertData", form);
		sqlSession.commit();
		sqlSession.close();
	}
	
	public void updateData(DataForm form) throws SQLException {
		SqlSession sqlSession = factory.openSession(); 
		sqlSession.update("updateData", form); 
		sqlSession.commit();
		sqlSession.close();
	}
	
	public boolean deleteData(String arg) {
		SqlSession sqlSession = factory.openSession();
		boolean b = false;
		try {
			int cou = sqlSession.delete("deleteData", arg);
			if(cou > 0) b = true;
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println("deleteData err : " + e);
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) sqlSession.close(); // sqlSession에 값이 있으면 close. 있는 값들 삭제해야 하기 때문에.
		}
		return b;
	}
}
