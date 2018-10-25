package pack.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class Jikwon_Buser_Impl extends SqlSessionDaoSupport implements Jikwon_Buser_Inter {
	
	// SqlSession 정보
	@Autowired
	public Jikwon_Buser_Impl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	// 전체 직원 목록
	@Override
	public List<JikwonDto> list() throws DataAccessException {
		return getSqlSession().selectList("selectDataAll");
	}
	
	// 부서에 따른 직원 목록
	@Override
	public List<JikwonDto> selectList(String buser) throws DataAccessException {
		return getSqlSession().selectList("selectBuserJikwon", buser);
	}

	// 부서정보
	@Override
	public BuserDto selectBuser(String buser) throws DataAccessException {
		return getSqlSession().selectOne("selectBuser", buser);
	}
	
	// Select List 위한 부서 전체 정보
	@Override
	public List<BuserDto> blist() throws DataAccessException {
		return getSqlSession().selectList("buserDataAll");
	}
}
