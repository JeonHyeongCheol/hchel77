package pack.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JikwonImpl extends SqlSessionDaoSupport implements JikwonInter{
	
	@Autowired
	private JikwonImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	@Override
	public List<JikwonDto> jikwonList() {
		return getSqlSession().selectList("selectJikwonAll");
	}
	
	@Override
	public JikwonDto getLoginInfo(String no) {
		return getSqlSession().selectOne("selectLogin", no);
	}
}
