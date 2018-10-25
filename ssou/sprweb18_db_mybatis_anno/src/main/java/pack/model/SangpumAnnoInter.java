package pack.model;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import pack.controller.SangpumBean;

public interface SangpumAnnoInter {
	
	@Select("select * from Sangdata")
	public List<SangpumDto> selectAllData();
	
	@Select("select code, sang, su, dan from sangdata where sang like concat('%',#{searchValue},'%')")
	public List<SangpumDto> selectSearch(SangpumBean bean);

}
