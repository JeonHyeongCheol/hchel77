package pack.model;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Gogek_Jikwon_Impl extends SqlSessionDaoSupport implements Gogek_Jikwon_Inter{
	
	@Autowired
	private Gogek_Jikwon_Impl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	@Override
	public JikwonDto jikwon(String damsano) {
		return getSqlSession().selectOne("gogek_jikwon", damsano);
	}
	
	@Override
	public GogekDto getLoginInfo(String name) {
		System.out.println(name);
		return getSqlSession().selectOne("gogekLogin", name);
	}
}
