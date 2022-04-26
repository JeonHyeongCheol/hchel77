package pack.mybatis;

import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapConfig {
  public static SqlSessionFactory sqlSession;  //DB의 SQL명령을 실행시킬 때 필요한 메소드를 갖고 있다.
 
  static{
     String resource = "pack/mybatis/Configuration.xml"; // Configuration을 가지도록 주소를 알려줌.
     try {
         Reader reader = Resources.getResourceAsReader(resource); // resource 읽기.
         sqlSession = new SqlSessionFactoryBuilder().build(reader); // 읽어 빌드 하기.
         reader.close();
     } catch (Exception e) {
     System.out.println("SqlMapConfig 오류 : " + e);
  }
}
 
public static SqlSessionFactory getSqlSession(){
     return sqlSession;
  }
}