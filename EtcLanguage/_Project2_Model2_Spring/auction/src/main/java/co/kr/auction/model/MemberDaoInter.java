package co.kr.auction.model;

import java.util.List;

import co.kr.auction.controller.MemberBean;

public interface MemberDaoInter {
	boolean memberNew(MemberBean bean);
	MemberDto myLoginInfo(String id);
	List<MemberDto> memberList();
	boolean productmemLike(MemberBean bean);
	MemberDto memlike(String m_id);
	MemberDto updateMemPart(String m_id);
	boolean updateMem(MemberBean bean);
	void productcancle(String p_bun);
}
