package co.kr.auction.model;

import java.util.List;

import co.kr.auction.controller.Address2Bean;

public interface AddressDaoInter {
	
	List<Address2Dto> city();
	List<Address2Dto> gu(String city);
	List<Address2Dto> search(Address2Bean bean);
}
