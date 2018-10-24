package pack.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

@Repository 
public class DataDao extends JdbcDaoSupport{
	
	@Autowired
	public DataDao(DriverManagerDataSource dataSource) { // DataSource에 있는 DriverManagerDataSource와 맵핑
		setDataSource(dataSource);
	}
	
	public List<SangpumDto> getDataAll() {
		String sql = "select * from sangData";
		return getJdbcTemplate().query(sql, new ItemRowMapper());
	}
	
	// 내부 클래스
	
	class ItemRowMapper implements RowMapper { // RowMapper import 할 때 org.springframework.jdbc.core.RowMapper;
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			SangpumDto dto = new SangpumDto();
			dto.setCode(rs.getString(1));
			dto.setSang(rs.getString(2));
			dto.setSu(rs.getString(3));
			dto.setDan(rs.getString(4));
			return dto;
		}
	}
}
