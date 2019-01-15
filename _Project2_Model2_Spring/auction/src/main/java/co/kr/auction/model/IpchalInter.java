package co.kr.auction.model;

import java.util.List;

import co.kr.auction.controller.ProductBean;

public interface IpchalInter {
	
	TenderDto fuckin(String p_bun);
	boolean fucking(TenderDto dto);
	void newfuckings(ProductBean frm);
	List<TenderDto> alltender(TenderDto dto);
	List<TenderDto> tenderPart(String m_id);
	TenderDto tenderPartLike(String t_pbun);
	
	
}
