package pack.business;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface SqlJikwonInter {
	@Select("select jikwon_no, jikwon_name, jikwon_jik, jikwon_gen, jikwon_pay from jikwon") // 밑에까지 한 묶음.
	public List<JikwonDto> selectDataAll();
	
	@Select("select jikwon_jik, sum(jikwon_pay) as jiksum, avg(jikwon_pay) as jikavg from jikwon group by jikwon_jik order by jikwon_jik")
	public List<JikwonDto> selectJikPay();
	
	@Select("select jikwon_gen, count(*) as jikcou, avg(jikwon_pay) as jikavg from jikwon group by jikwon_gen order by jikwon_gen")
	public List<JikwonDto> selectGenPay();
}
