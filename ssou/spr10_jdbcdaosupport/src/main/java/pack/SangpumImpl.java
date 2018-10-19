package pack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class SangpumImpl extends JdbcDaoSupport implements SangpumInter{
	@Override
	public List<SangpumDto> selectList() {
		RowMapper rowMapper = new SangRowMapper(); // List 타입으로 계속 들어오는 값을 쌓음.
		return getJdbcTemplate().query("select * from sangdata", rowMapper); // 값이 다들어오면 리턴함.
	}
	
	class SangRowMapper implements RowMapper { // 내부 클래스 사용
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// select 결과를 한 레코드 씩 전달받아 처리하는 메소드.
			System.out.println("rowNum : " + rowNum);
			
			SangpumDto dto = new SangpumDto();
			dto.setCode(rs.getString("code"));
			dto.setSang(rs.getString("sang"));
			dto.setSu(rs.getString("su"));
			dto.setDan(rs.getString("dan"));
			return dto;
		}
	}
}

