package co.kr.auction.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.kr.auction.controller.Address2Bean;

@Repository
public class AddressDaoImpl extends SqlSessionDaoSupport implements AddressDaoInter{
	
	@Autowired
	public AddressDaoImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	@Override
	public List<Address2Dto> city() {
		return getSqlSession().selectList("sido");
	}
	
	@Override
	public List<Address2Dto> gu(String city) {
		return getSqlSession().selectList("gungu", city);
	}
	
	@Override
	public List<Address2Dto> search(Address2Bean bean) {
		return getSqlSession().selectList("search", bean);
	}
}
