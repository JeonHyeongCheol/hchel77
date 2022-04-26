package pack.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import pack.controller.MemberBean;

@Repository
public class MemberDao extends JdbcDaoSupport{
	
	@Autowired
	public MemberDao(DriverManagerDataSource dataSource) {
		setDataSource(dataSource);
	}
	
	public List<MemberDto> getMemberList(int startRow, int endRow) { // 페이지 나누기
		String sql = "select * from memtab limit ?,?"; // limit 쓰면 어디서부터 어디까지인지 알 수 있음. Oracle사용 불가능(RowNum 사용 가능). 
		List<MemberDto> list = getJdbcTemplate().query(sql, 
				new Object[] {startRow, endRow}, new RowMapper() {// 물음표를 배열로 채울 수 있음. 타입은 Object로!!. member 전체 값을 List에 넘겨줌.
					@Override
					public Object mapRow(ResultSet rs, int rowNum) throws SQLException {  // 내부(무명)클래스 사용.
						MemberDto dto = new MemberDto();
						dto.setId(rs.getString("id"));
						dto.setName(rs.getString("name"));
						dto.setPasswd(rs.getString("passwd"));
						dto.setReg_date(rs.getString("reg_date"));
						return dto;
					}
				}); 
		return list;
	}
	
	public int getMemberCount() { // 페이지 
		String sql = "select count(*) from memtab";
		return getJdbcTemplate().queryForObject(sql, Integer.class); // queryForObject : 값을 하나 받아온다는 것, 타입은 Integer로!!
	}
	
	public MemberDto getMember(String id) { // 멤버하나의 값을 Dto에 넘겨줌.
		String sql = "select * from memtab where id=?";
		MemberDto dto = (MemberDto)getJdbcTemplate().queryForObject(sql, new Object[] {id}, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberDto dto = new MemberDto();
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setReg_date(rs.getString("reg_date"));
				return dto;
			}
		});
		return dto;
	}
	
	// 추가
	public void insData(MemberBean bean) {
		String sql = "insert into memtab values(?,?,?,now())";
		Object[] params = {bean.getId(), bean.getPasswd(), bean.getName() }; // 배열로 ?(물음표) 값 설정
		getJdbcTemplate().update(sql, params); 
	}
	
	// 수정
	public void upData(MemberBean bean) {
		String sql = "update memtab set passwd=?, name=? where id = ?";
		Object[] params = {bean.getPasswd(), bean.getName(), bean.getId()}; // 배열로 ?(물음표) 값 설정
		getJdbcTemplate().update(sql, params); 
	}
	
	// 삭제
		public void delData(String id) {
			String sql = "delete from memtab where id = ?";
			getJdbcTemplate().update(sql, new Object[]{id}); 
		}
}
	
