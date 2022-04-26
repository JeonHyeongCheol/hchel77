package kr.mvc.model;

import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapConfig {
  public static SqlSessionFactory sqlSession;
 
  static{
     String resource = "kr/mvc/resource/Configuration.xml"; 
     try {
         Reader reader = Resources.getResourceAsReader(resource); 
         sqlSession = new SqlSessionFactoryBuilder().build(reader);
         reader.close();
     } catch (Exception e) {
     System.out.println("SqlMapConfig 오류 : " + e);
  }
}
 
public static SqlSessionFactory getSqlSession(){
     return sqlSession; // 싱글톤으로 클래스 자체를 리턴.
  }
}