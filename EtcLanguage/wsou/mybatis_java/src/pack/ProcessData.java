package pack;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

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
		SqlSession sqlSession = factory.openSession(); // 세션을 열어줌.
		DataDto dto = sqlSession.selectOne("selectDataById", code); // DataMapper에서 보고 사용하고 싶은 ID 입력.
		sqlSession.close();
		return dto;
	}
}
