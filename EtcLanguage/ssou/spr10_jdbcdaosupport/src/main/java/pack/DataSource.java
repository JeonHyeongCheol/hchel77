package pack;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSource extends DriverManagerDataSource{
	public DataSource() { // 생성자를 사용하여 연결.
		setDriverClassName("org.mariadb.jdbc.Driver");
		setUrl("jdbc:mysql://localhost:3306/test");
		setUsername("root");
		setPassword("123");
	}
}
