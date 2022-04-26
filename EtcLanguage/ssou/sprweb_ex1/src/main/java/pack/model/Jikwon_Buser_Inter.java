package pack.model;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface Jikwon_Buser_Inter { // 나중에 프로젝트 할 때는 Jikwon, Buser 이런식으로 Interface 나눌 것!
	List<JikwonDto> list() throws DataAccessException;
	List<JikwonDto> selectList(String buser) throws DataAccessException;
	
	List<BuserDto> blist() throws DataAccessException;
	BuserDto selectBuser(String buser) throws DataAccessException;
}
