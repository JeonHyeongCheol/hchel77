package pack.model;

import java.util.List;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import pack.controller.SangpumBean;

@Repository
public class SangpumImpl extends SqlSessionDaoSupport implements SangpumInter{
	
	//@Autowired // Xml에서 dataSource 설정 안 했을시 이 것을 생성해주면 됨. webspr15 확인!!
	//public DataSource dataSource;
	
	@Autowired // 생성자에 대해서 Setter 설정
	public SangpumImpl (SqlSessionFactory factory) { // application-config.xml에서 SqlFactoryBean에 값을 가져옴.
		setSqlSessionFactory(factory);
	}
	
	@Override
	public List<SangpumDto> list() throws DataAccessException { // 상품자료.
		return getSqlSession().selectList("selectDataAll");
	}
	
	@Override
	public List<SangpumDto> search(SangpumBean bean) throws DataAccessException { // 검색시.
		return getSqlSession().selectList("selectSearch", bean);
	}
}
